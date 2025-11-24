package com.example.aulatec;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class TelaMod extends AppCompatActivity {

    private CheckBox checkLembrar;
    private SharedPreferences preferences;
    private String nomeAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_mod);

        Button priDs = findViewById(R.id.btn1modA);
        Button priDsB = findViewById(R.id.btn1modB);
        Button segDs = findViewById(R.id.btn2modA);
        Button segDsB = findViewById(R.id.btn2modB);
        Button terDs = findViewById(R.id.btn3mod);
        checkLembrar =  findViewById(R.id.checkBoxLembrar);
        checkLembrar.setChecked(true);

        preferences = getSharedPreferences("PreferenciaModulo", MODE_PRIVATE);

        boolean pularVerificacao = getIntent().getBooleanExtra("pularVerificacao", false);
        if(!pularVerificacao){
            verificarModuloSalvo();
        }

        priDs.setOnClickListener(v -> abrirHome(1, "A"));
        priDsB.setOnClickListener(v -> abrirHome(2, "B"));
        segDs.setOnClickListener(v -> abrirHome(3, "A"));
        segDsB.setOnClickListener(v -> abrirHome(4, "B"));
        terDs.setOnClickListener(v -> abrirHome(5, "A"));
    }
    // Nessa função eu vou passar os dois parametros necessarios
    private void abrirHome(int idModulo, String turma){
        if(checkLembrar.isChecked()){                               // Se a checkbox estiver marcada
            SharedPreferences.Editor editor = preferences.edit();   // ele vai guardar as informações do módulo selecionado(idModulo(1,2,etc...), turma(A ou B) e que a checkBox está marcada(true)
            editor.putInt("id_modulo", idModulo);
            editor.putString("turma", turma);
            editor.putBoolean("lembrar", true);
            editor.apply();
        }else{
            preferences.edit().clear().apply();                     // se não estiver, remove as preferências
        }
        Intent intent = new Intent(TelaMod.this, Home.class);
        intent.putExtra("id_modulo", idModulo);
        intent.putExtra("turma", turma);
        mensagemDeOla();
        startActivity(intent);
        finish();
    }

    private void verificarModuloSalvo(){
        boolean lembrar = preferences.getBoolean("lembrar", false);

        if(lembrar){
            int idModulo = preferences.getInt("id_modulo", -1); // se for verdadeiro, pega os valores guardado nas preferências
            String turma = preferences.getString("turma", "");

            if(idModulo != -1 && !turma.isEmpty()){                  // aplica a lógica, se o Idmodulo for diferente de -1 e a turma não estiver vazia
                Intent intent = new Intent(TelaMod.this, Home.class);   // Vai pra tela Home direto
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                mensagemDeOla();
                startActivity(intent);
                finish();
            }
        }
    }

    private void mensagemDeOla(){
        DatabaseHelper bancoDados = new DatabaseHelper(this);
        SQLiteDatabase bd = bancoDados.getReadableDatabase();

        Cursor cursor = bd.rawQuery("SELECT nomeAluno FROM alunos WHERE id_aluno = 1", null);
        if(cursor.moveToFirst()) {
            nomeAluno = cursor.getString(0);

            if(nomeAluno != null){
                Toast.makeText(this, "Olá, " + nomeAluno, Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Olá, Aluno(a)", Toast.LENGTH_SHORT).show();
            }
        }
        cursor.close();
        bd.close();
    }
}