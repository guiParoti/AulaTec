package com.example.aulatec;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.barra_navegacao.BarraDeNavegacao;
import com.example.recomendacoes.EscolherRecomendacoes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ListaAulas extends AppCompatActivity {
    String diaSemana;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_aulas);

        int idModulo = getIntent().getIntExtra("id_modulo", 3);
        String turma =  getIntent().getStringExtra("turma");

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_aulas);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);

        exibirAulas(idModulo, turma);

    }
    public void exibirAulas(int idModulo, String turma){
        try{
            DatabaseHelper bancoHelper = new DatabaseHelper(this);
            SQLiteDatabase bancoDados = bancoHelper.getReadableDatabase();

            ArrayList<String> lista = new ArrayList<>();

            Cursor cursor = bancoDados.rawQuery("SELECT a.diaSemana, a.nomeAula, a.horaInicio, a.horaFim, a.lab," +
                    " p.nomeProf " +
                    "FROM aulas a " +
                    "INNER JOIN professores p ON a.id_professor = p.id_professor " +
                    "JOIN modulos m ON a.id_modulo = m.id_modulo " +
                    "WHERE a.id_modulo = ? AND m.turma  = ? " +
                    "ORDER BY a.diaSemana",
                        new String[]{String.valueOf(idModulo), turma});

            if(cursor.moveToFirst()){
                do{
                    int indiceSemana = cursor.getInt(0);
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

                    String nomeAula = cursor.getString(1);
                    String horaInicio = cursor.getString(2);
                    String horaFim = cursor.getString(3);
                    int lab = cursor.getInt(4);
                    String nomeProfessor = cursor.getString(5);

                    if(lab == 0) {
                        String maker = "Maker";
                        if(horaInicio.equals("19:00") && horaFim.equals("20:40")) {
                            lista.add("\n" + diaSemana + "\n1°Aula\n" + nomeAula + "\n" + horaInicio + " - " + horaFim + "\n"
                                    + nomeProfessor + "\nLab: " + maker + "\n");
                        }else {
                            lista.add("\n" + diaSemana + "\n2°Aula\n" + nomeAula + "\n" + horaInicio + " - " + horaFim + "\n"
                                    + nomeProfessor + "\nLab: " + maker + "\n");
                        }
                    }else{
                        if(horaInicio.equals("19:00") && horaFim.equals("20:40")) {
                            lista.add("\n" + diaSemana + "\n1°Aula\n" + nomeAula + "\n" + horaInicio + " - " + horaFim + "\n"
                                    + nomeProfessor + "\nLab: " + lab + "\n");
                        }else {
                            lista.add("\n" + diaSemana + "\n2°Aula\n" + nomeAula + "\n" + horaInicio + " - " + horaFim + "\n"
                                    + nomeProfessor + "\nLab: " + lab + "\n");
                        }
                    }
                }while (cursor.moveToNext());
            }

            cursor.close();
            bancoDados.close();

            ListView listaAulas = findViewById(R.id.listaAulas);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
            listaAulas.setAdapter(adapter);

        }catch (Exception a){
            a.printStackTrace();
        }

    }
}