package com.example.aulatec;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.barra_navegacao.BarraDeNavegacao;
import com.example.recomendacoes.EscolherRecomendacoes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetalhesProf extends AppCompatActivity {

    String diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_prof);

        String nomeProf = getIntent().getStringExtra("nomeProf");
        String emailProf = getIntent().getStringExtra("emailProf");

        int idModulo = getIntent().getIntExtra("id_modulo", 3);
        String turma =  getIntent().getStringExtra("turma");

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_emails);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);

        TextView txtNome = findViewById(R.id.txtNome);
        LinearLayout containerProfessor = findViewById(R.id.conteinerProf);

        DatabaseHelper bdHelper = new DatabaseHelper(this);
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        txtNome.setText(nomeProf + "\n"+ emailProf + "\nAulas nesse módulo:");
        txtNome.setTextSize(20);
        txtNome.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        txtNome.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

        Cursor cursor = db.rawQuery(
                "SELECT a.nomeAula, a.diaSemana, a.lab from aulas a " +
                        "JOIN professores p ON a.id_professor = p.id_professor " +
                        "JOIN modulos m ON a.id_modulo = m.id_modulo " +
                        "WHERE p.nomeProf = ? AND a.id_modulo = ? AND m.turma = ?",
                new String[]{nomeProf, String.valueOf(idModulo), turma}
        );

        if(cursor.moveToFirst()){
            do{
                int indiceSemana = cursor.getInt(1);
                switch (indiceSemana){
                    case 2:
                        diaSemana = "Segunda-feira";
                        break;
                    case 3:
                        diaSemana = "Terça-feira";
                        break;
                    case 4:
                        diaSemana = "Quarta-feira";
                        break;
                    case 5:
                        diaSemana = "Quinta-feira";
                        break;
                    case 6:
                        diaSemana = "Sexta-feira";
                        break;
                }
                String nomeAulas = cursor.getString(0);
                int lab = cursor.getInt(2);

                TextView txtAulas = new TextView(this);
                txtAulas.setText("• " + diaSemana + " - " + nomeAulas + " - lab - " + lab);
                txtAulas.setTextSize(20);
                txtAulas.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                containerProfessor.addView(txtAulas);
            }while (cursor.moveToNext());
        }else{
            TextView txtAulas = new TextView(this);
            txtAulas.setText("Não tem aulas com esse professor nesse módulo");
            txtAulas.setTextSize(20);
            containerProfessor.addView(txtAulas);
        }
        cursor.close();
        db.close();





    }
}