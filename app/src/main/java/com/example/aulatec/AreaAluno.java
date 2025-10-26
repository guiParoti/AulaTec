package com.example.aulatec;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recomendacoes.EscolherRecomendacoes;
import com.example.recomendacoes.RFilmes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class AreaAluno extends AppCompatActivity {

    private DatabaseHelper bancoDados;
    private TextView txtNomeAluno;
    private String titulo;
    private String descricao;

    private ListView listaDeTarefas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_area_aluno);

        int idModulo = getIntent().getIntExtra("id_modulo", 5);
        String turma = getIntent().getStringExtra("turma");

        Button btnEditar = findViewById(R.id.btnEditarPerfil);
        Button btnNovaTarefa = findViewById(R.id.btnNovaTarefa);
        txtNomeAluno = findViewById(R.id.txtNomeAluno);
        listaDeTarefas = findViewById(R.id.listaTarefas);
        Button btnVoltarTelaMod = findViewById(R.id.btnTrocarModulo);
        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);

        barraNavegacao.setSelectedItemId(R.id.nav_aluno);

        bancoDados = new DatabaseHelper(this);
        carregarDadosAluno();
        carregarTarefas();

        aplicarCorTurma(turma, btnEditar, btnNovaTarefa, btnVoltarTelaMod);


        btnEditar.setOnClickListener(v ->{;
            EditText nome = new EditText(this);
            nome.setHint("Digite seu nome");
            nome.setTextAlignment(TEXT_ALIGNMENT_CENTER);

            new AlertDialog.Builder(this).setTitle("Editar nome").setView(nome)
                    .setPositiveButton("Salvar", (dialog, which) -> {
                        String novoNomeAluno = nome.getText().toString().trim();
                        if(!novoNomeAluno.isEmpty()){
                            try {
                                salvarNomeAluno(novoNomeAluno);
                                txtNomeAluno.setText(novoNomeAluno);
                                Toast.makeText(this, "Nome salvo!", Toast.LENGTH_SHORT).show();

                            }catch(Exception a){
                                Toast.makeText(this, "Erro ao salvar nome!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(this, "Olá, " + novoNomeAluno, Toast.LENGTH_SHORT).show();
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

        barraNavegacao.setOnItemSelectedListener( item -> {
            int id = item.getItemId();
            if (id == R.id.nav_aluno) {
                return true;
            }else if(id == R.id.nav_home){
                Intent intent = new Intent(AreaAluno.this, Home.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_emails){
                Intent intent = new Intent(AreaAluno.this, ListaProf.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_aulas){
                Intent intent = new Intent(AreaAluno.this, ListaAulas.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_recomendacoes){
                Intent intent = new Intent(AreaAluno.this, EscolherRecomendacoes.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });
    };

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
    private void carregarDadosAluno(){
        SQLiteDatabase bd = bancoDados.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT nomeAluno FROM ALUNOS WHERE id_aluno = 1", null);

        if(cursor.moveToFirst()){
            String nomeSalvo = cursor.getString(0);
            txtNomeAluno.setText(nomeSalvo);
        }
        cursor.close();
    }

    private void carregarTarefas(){
        SQLiteDatabase bd = bancoDados.getReadableDatabase();
        Cursor cursor = bd.rawQuery("SELECT tituloTarefa, descricaoTarefa FROM tarefas WHERE id_aluno = 1", null);

        ArrayList<String> listaTarefas = new ArrayList<>();

        if(cursor.moveToFirst()){
            do {
                String tituloTarefa = cursor.getString(0);
                String descricaoTarefa = cursor.getString(1);
                listaTarefas.add(tituloTarefa + "\n" + descricaoTarefa);
            }while(cursor.moveToNext());
        }
        cursor.close();
        bd.close();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaTarefas);
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
