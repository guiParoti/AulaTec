package com.example.aulatec;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AreaAluno extends AppCompatActivity {

    private DatabaseHelper bancoDados;
    private TextView txtNomeAluno;
    private String titulo;
    private String descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_area_aluno);

        Button btnEditar = findViewById(R.id.btnEditarPerfil);
        Button btnNovaTarefa = findViewById(R.id.btnNovaTarefa);
        txtNomeAluno = findViewById(R.id.txtNomeAluno);
        Button btnVoltarTelaMod = findViewById(R.id.btnTrocarModulo);

        bancoDados = new DatabaseHelper(this);
        carregarDadosAluno();

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
                        titulo = tituloTarefa.toString().trim();
                        if(!titulo.isEmpty()){
                            new AlertDialog.Builder(this).setTitle("Descrição da Tarefa")
                                    .setView(descricaoTarefa)
                                    .setPositiveButton("Salvar tarefa", (dialogD, whichD) ->{
                                        descricao = descricaoTarefa.toString().trim();
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
                startActivity(intent);
                finish();
            }
        });
    };

    private void salvarNomeAluno(String nome){
        SQLiteDatabase bd = bancoDados.getReadableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nomeAluno", nome);

        Cursor cursor = bd.rawQuery("SELECT * FROM alunos WHERE id_aluno = 1", null);
        if(cursor.moveToFirst()){
            bd.update("alunos", valores, "id_aluno = ?", new String[]{"1"});
        }else{
            valores.put("id_aluno", 1);
            bd.insert("alunos", null, valores);
        }
        cursor.close();
        bd.close();

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
}