package com.example.aulatec;

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

import java.util.ArrayList;

public class ListaAulas extends AppCompatActivity {

    String diaSemana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_aulas);
        exibirAulas();
    }
    public void exibirAulas(){
        try{
            DatabaseHelper bancoHelper = new DatabaseHelper(this);
            SQLiteDatabase bancoDados = bancoHelper.getReadableDatabase();

            ArrayList<String> lista = new ArrayList<>();

            Cursor cursor = bancoDados.rawQuery("SELECT a.diaSemana, a.nomeAula, a.horaInicio, a.horaFim, a.lab," +
                    " p.nomeProf " +
                    "FROM aulas a " +
                    "INNER JOIN professores p ON a.id_professor = p.id_professor " +
                    "ORDER BY a.diaSemana", null);

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
                    lista.add("\n"+ diaSemana + "\n" + nomeAula + "\n" + horaInicio + " - " + horaFim + "\nProfessor(a): "
                            + nomeProfessor + "\nLab: " + lab + "\n");
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