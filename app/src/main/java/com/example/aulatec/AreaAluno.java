package com.example.aulatec;

import static android.view.View.TEXT_ALIGNMENT_CENTER;
import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.BitmapCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barra_navegacao.BarraDeNavegacao;
import com.example.recomendacoes.EscolherRecomendacoes;
import com.example.recomendacoes.RFilmes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AreaAluno extends AppCompatActivity {

    private DatabaseHelper bancoDados;

    private static final int PEGAR_PEDIDO_DE_IMAGEM = 1;
    private ImageView capaAluno;
    private TextView txtNomeAluno;
    private TextView txtEmailInstitucional;
    private String titulo;
    private String descricao;
    private ListView listaDeTarefas;
    private ArrayList<String> listaTarefasValores;
    private ArrayList<Integer> listaTarefasIds;



    @Override
    protected void onActivityResult(int pedidoDeCodigo, int resultadoDeCodigo, @Nullable Intent data){
        super.onActivityResult(pedidoDeCodigo, resultadoDeCodigo, data);

        if(pedidoDeCodigo == PEGAR_PEDIDO_DE_IMAGEM && resultadoDeCodigo == RESULT_OK && data != null){
            Uri imagemUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imagemUri);
                capaAluno.setImageBitmap(bitmap);
                salvarImagemAluno(bitmap);
            }catch (IOException e){
                e.printStackTrace();
                Toast.makeText(this, "Erro ao carregar imagem!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_area_aluno);

        int idModulo = getIntent().getIntExtra("id_modulo", 5);
        String turma = getIntent().getStringExtra("turma");

        Button btnEditar = findViewById(R.id.btnEditarPerfil);
        Button btnNovaTarefa = findViewById(R.id.btnNovaTarefa);
        capaAluno = findViewById(R.id.capaAluno);
        txtNomeAluno = findViewById(R.id.txtNomeAluno);
        txtEmailInstitucional = findViewById(R.id.txtEmailInstitucional);
        listaDeTarefas = findViewById(R.id.listaTarefas);
        Button btnVoltarTelaMod = findViewById(R.id.btnTrocarModulo);


        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_aluno);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);

        bancoDados = new DatabaseHelper(this);
        carregarDadosAluno();
        carregarTarefas();

        aplicarCorTurma(turma, btnEditar, btnNovaTarefa, btnVoltarTelaMod);

        btnEditar.setOnClickListener(v ->{;
            String[] opcoesDialogBuilder = {"Editar imagem", "Editar nome", "Editar email institucional"};
            EditText nome = new EditText(this);
            nome.setHint("Digite seu nome");
            nome.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            EditText emailInstitucional = new EditText(this);
            emailInstitucional.setHint("Digite seu email institucional");
            emailInstitucional.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            
            new AlertDialog.Builder(this).setTitle("Escolha uma opção")
                    .setItems(opcoesDialogBuilder, (dialog, which) -> {

                if(which == 0){
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, PEGAR_PEDIDO_DE_IMAGEM);
                }

                else if (which == 1){
                            try {
                                new AlertDialog.Builder(this).setTitle("Editar nome").setView(nome)
                                                .setPositiveButton("Salvar", (d, w)-> {
                                                    String novoNomeAluno = nome.getText().toString().trim();
                                                    salvarNomeAluno(novoNomeAluno);
                                                    txtNomeAluno.setText(novoNomeAluno);
                                                    Toast.makeText(this, "Nome salvo!", Toast.LENGTH_SHORT).show();
                                                })
                                        .setNegativeButton("Cancelar", null)
                                        .show();
                            }catch(Exception a){
                                Toast.makeText(this, "Erro ao salvar nome!", Toast.LENGTH_SHORT).show();
                            }
                        }
                else if(which == 2){
                    new AlertDialog.Builder(this).setTitle("Editar email institucional").setView(emailInstitucional).setPositiveButton("Salvar", (d, w) ->{
                                String novoEmailInstitucional = emailInstitucional.getText().toString().trim();
                                if(novoEmailInstitucional.contains("@etec.sp.gov")){
                                    salvarEmailInstitucional(novoEmailInstitucional);
                                    txtEmailInstitucional.setText(novoEmailInstitucional);
                                    txtEmailInstitucional.setVisibility(VISIBLE);
                                    Toast.makeText(this, "Email institucional salvo", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(this, "Por-favor insira um email válido", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("Cancelar", null)
                            .show();
                }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });

        btnNovaTarefa.setOnClickListener(v ->{
            EditText tituloTarefa = new EditText(this);
            tituloTarefa.setHint("Título da tarefa");
            tituloTarefa.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            EditText descricaoTarefa = new EditText(this);
            descricaoTarefa.setHint("Descrição da tarefa");
            descricaoTarefa.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            new AlertDialog.Builder(this).setTitle("Adicionar nova tarefa")
                    .setView(tituloTarefa)
                    .setPositiveButton("Proximo", (dialog, which) ->{
                        titulo = tituloTarefa.getText().toString().trim();
                        if(!titulo.isEmpty()){
                            new AlertDialog.Builder(this).setTitle("Descrição da Tarefa")
                                    .setView(descricaoTarefa)
                                    .setPositiveButton("Salvar tarefa", (dialogD, whichD) ->{
                                        descricao = descricaoTarefa.getText().toString().trim();
                                        salvarTarefa(titulo, descricao);
                                    })
                                    .setNegativeButton("Cancelar", null)
                                    .show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });


        listaDeTarefas.setOnItemClickListener((parent, view, position, id) ->{
            int idTarefaSelecionada = listaTarefasIds.get(position);
            String tituloTarefa = listaTarefasValores.get(position);

            String[] opcoesDialogBuilder = {"Editar tarefa","Excluir tarefa", "Marcar como concluída"};


            new AlertDialog.Builder(this).setTitle("Escolha uma opção")
                    .setItems(opcoesDialogBuilder, (dialog, which) -> {
                        if(which == 0){
                           editarTarefa(idTarefaSelecionada);
                        }else if(which == 1){
                            new AlertDialog.Builder(this).setTitle("Excluir tarefa")
                            .setMessage("Deseja realmente excluir essa tarefa \"" + tituloTarefa + "\"?")
                                    .setPositiveButton("Sim", (d, w) -> {
                                        deletarTarefa(idTarefaSelecionada);
                                        carregarTarefas();
                                    })
                                    .setNegativeButton("Não", null)
                                    .show();;

                        }else if(which == 2){
                            new AlertDialog.Builder(this).setTitle("Confirmação")
                                    .setMessage("Tem certeza que deseja marcar como concluido?")
                                    .setPositiveButton("Sim", (dialogo, qual) -> {
                                        marcarComoConcluido(idTarefaSelecionada);
                                    })
                                    .setNegativeButton("Cancelar", null)
                                    .show();
                        }
                    }).setNegativeButton("Cancelar", null)
                    .show();

        });

        btnVoltarTelaMod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AreaAluno.this, TelaMod.class);
                intent.putExtra("pularVerificacao", true);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                finish();
            }
        });

    };
    private void salvarImagemAluno(Bitmap bitmap){
        try{
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
            byte[] imagemBytes = stream.toByteArray();

            SQLiteDatabase bd = bancoDados.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("fotoAluno", imagemBytes);

            // Já atualiza a foto no banco pra ficar sempre apenas uma foto salva
            Cursor cursor = bd.rawQuery("SELECT * FROM alunos WHERE id_aluno = 1", null);
            if(cursor.moveToFirst()){
                bd.update("alunos", valores, "id_aluno = ?", new String[]{"1"}); // Já existir um registro no banco, atualiza com o novo
            }else{
                valores.put("id_aluno", 1);
                bd.insert("alunos", null, valores);        // Senão insere no banco na tabela do aluno de id 1
            }
            cursor.close();
            bd.close();

            Toast.makeText(this, "Imagem salva!", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Erro ao salvar imagem", Toast.LENGTH_SHORT).show();
        }
    }

    private void salvarNomeAluno(String nome){
        try {
            SQLiteDatabase bd = bancoDados.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("nomeAluno", nome);

            Cursor cursor = bd.rawQuery("SELECT * FROM alunos WHERE id_aluno = 1", null);
            if (cursor.moveToFirst()) {
                bd.update("alunos", valores, "id_aluno = ?", new String[]{"1"});
            } else {
                valores.put("id_aluno", 1);
                bd.insert("alunos", null, valores);
            }
            cursor.close();
            bd.close();
        }catch (Exception a){
            System.out.println("Erro ao salvar aluno no banco");
            a.printStackTrace();
        }

    }

    private void salvarEmailInstitucional(String email){
        try{
            SQLiteDatabase bd = bancoDados.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("email_institucional", email);

            Cursor cursor = bd.rawQuery("SELECT * FROM alunos WHERE id_aluno = 1", null);
            if(cursor.moveToFirst()){
                bd.update("alunos", valores, "id_aluno = ?", new String[]{"1"});
            }else{
                valores.put("id_aluno", 1);
                bd.insert("alunos", null, valores);
            }
            cursor.close();
            bd.close();

        }catch (Exception a){
            System.out.println("Sei nem aonde eu consigo acessar o log");
            a.printStackTrace();
        }
    }

    private void salvarTarefa(String titulo, String descricao){
        try {
            SQLiteDatabase bd = bancoDados.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("tituloTarefa", titulo);
            valores.put("descricaoTarefa", descricao);
            valores.put("id_aluno", 1);

            long resultado = bd.insert("tarefas", null, valores);
            bd.close();

            if (resultado != -1) {
                Toast.makeText(this, "Tarefa salva!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Erro ao salvar tarefa!", Toast.LENGTH_SHORT).show();
            }
            carregarTarefas();
        }catch (Exception a){
            System.out.println("Erro ao salvar a tarefa no banco, erro abaixo!");
            a.printStackTrace();
        }
    }

    private void deletarTarefa(int idTarefa){
        SQLiteDatabase bd =  bancoDados.getWritableDatabase();
        int linhasAfetadas = bd.delete("tarefas", "id_tarefa = ?", new String[]{String.valueOf(idTarefa)});

        if(linhasAfetadas > 0){
            Toast.makeText(this, "Tarefa deletada!", Toast.LENGTH_SHORT).show();
        }else{
            System.out.println("Não é pra dar else não!");
        }
        bd.close();
    }

    private void editarTarefa(int idTarefa){
        SQLiteDatabase bd = bancoDados.getWritableDatabase();
        Cursor cursor = bd.rawQuery("SELECT tituloTarefa, descricaoTarefa FROM tarefas WHERE id_tarefa = ?", new String[]{String.valueOf(idTarefa)});

        try {
            if (cursor.moveToFirst()) {
                String tituloTarefaAtual = cursor.getString(0);
                String descricaoTarefaAtual = cursor.getString(1);

                EditText Caixatitulo = new EditText(this);
                Caixatitulo.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                Caixatitulo.setText(tituloTarefaAtual);
                Caixatitulo.setHint("Titulo da tarefa");

                EditText CaixaDescricao = new EditText(this);
                CaixaDescricao.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                CaixaDescricao.setText(descricaoTarefaAtual);
                CaixaDescricao.setHint("Descrição da tarefa");

                ContentValues valores = new ContentValues();

                new AlertDialog.Builder(this).setTitle("Titulo da tarefa")
                        .setView(Caixatitulo)
                        .setPositiveButton("Proximo", (dialog, which) -> {
                            String novoTitulo = Caixatitulo.getText().toString().trim();
                            if (!novoTitulo.isEmpty()) {
                                valores.put("tituloTarefa", novoTitulo);

                                new AlertDialog.Builder(this).setTitle("Descrição da tarefa")
                                        .setView(CaixaDescricao).setPositiveButton("Atualizar tarefa", (di, wi) -> {
                                            String novaDescricao = CaixaDescricao.getText().toString().trim();
                                            if (!novaDescricao.isEmpty()) {
                                                valores.put("descricaoTarefa", novaDescricao);

                                                SQLiteDatabase bdAtualizar = bancoDados.getWritableDatabase();

                                                bdAtualizar.update("tarefas", valores, "id_tarefa = ?", new String[]{String.valueOf(idTarefa)});
                                                bdAtualizar.close();
                                                carregarTarefas();
                                                Toast.makeText(this, "Tarefa atualizada", Toast.LENGTH_SHORT).show();
                                            } else {
                                                Toast.makeText(this, "Por-favor, insira uma descrição válida", Toast.LENGTH_SHORT).show();
                                            }
                                        }).setNegativeButton("Cancelar", null)
                                        .show();

                            } else {
                                Toast.makeText(this, "Por-favor, insira um titulo válido", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("Cancelar", null)
                        .show();

                cursor.close();
                bd.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void marcarComoConcluido(int idTarefa){
        SQLiteDatabase bd = bancoDados.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("status", "CONCLUIDO");

        try{
            bd.update("tarefas", values, "id_tarefa = ?", new String[]{String.valueOf(idTarefa)});
            bd.close();
            carregarTarefas();
            Toast.makeText(this, "Tarefa concluida!", Toast.LENGTH_SHORT).show();
        }catch (Exception a){
            a.printStackTrace();
        }
    }
    private void carregarDadosAluno(){
        SQLiteDatabase bd = bancoDados.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT nomeAluno, fotoAluno, email_institucional FROM ALUNOS WHERE id_aluno = 1", null);

        if(cursor.moveToFirst()){
            String nomeSalvo = cursor.getString(0);
            txtNomeAluno.setText(nomeSalvo);

            byte[] imagemBytes = cursor.getBlob(1);
            if(imagemBytes != null && imagemBytes.length > 0){
                    Bitmap bitmap = BitmapFactory.decodeByteArray(imagemBytes, 0, imagemBytes.length);
                    capaAluno.setImageBitmap(bitmap);
            }
            String emailSalvo = cursor.getString(2);
            if(emailSalvo != null && !emailSalvo.isEmpty()) {
                txtEmailInstitucional.setText(emailSalvo);
                txtEmailInstitucional.setVisibility(VISIBLE);
            }
        }
        cursor.close();
        bd.close();
    }

    private void carregarTarefas(){
        SQLiteDatabase bd = bancoDados.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT id_tarefa, tituloTarefa, descricaoTarefa, status FROM tarefas WHERE id_aluno = 1", null);

        listaTarefasIds = new ArrayList<>();
        listaTarefasValores = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                int idTarefa = cursor.getInt(0);
                String tituloTarefa = cursor.getString(1);
                String descricaoTarefa = cursor.getString(2);
                String statusTarefa = cursor.getString(3);

                listaTarefasIds.add(idTarefa);
                listaTarefasValores.add(tituloTarefa + "\n" + descricaoTarefa + "\nStatus: " + statusTarefa);
            }while(cursor.moveToNext());
        }
        cursor.close();
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTarefasValores);
        listaDeTarefas.setAdapter(adapter);
    }

    private void aplicarCorTurma(String turma, Button btnEditar, Button btnNovaTarefa, Button btnVoltarMod){

        if(turma == null){
            return;
        }

        int cor;
        if(turma.equals("A")){
            cor = ContextCompat.getColor(this, R.color.turmaA);
        }else if(turma.equals("B")){
            cor = ContextCompat.getColor(this, R.color.turmaB);
        }else{
            return;
        }

        Button[] botoes = {btnEditar, btnNovaTarefa, btnVoltarMod};
        for(Button botao : botoes){
            botao.setBackgroundTintList(ColorStateList.valueOf(cor));
        }
    }
}
