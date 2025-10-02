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

public class ListaProf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_prof);
        exibirLista();

    }

    public void exibirLista(){
        try{
            DatabaseHelper bancoHelper = new DatabaseHelper(this);
            SQLiteDatabase bancoDados = bancoHelper.getReadableDatabase();

            Cursor cursor = bancoDados.rawQuery("SELECT nomeProf, emailProf " +
                    "FROM professores", null);

            ArrayList<String> lista = new ArrayList<>();

            if (cursor.moveToFirst()){
                do{
                    String nomeProf = cursor.getString(0);
                    String emailProf = cursor.getString(1);
                    lista.add("\nProfessor(a): " + nomeProf + "\nEmail: " + emailProf + "\n");
                }while (cursor.moveToNext());
            }

            cursor.close();
            bancoDados.close();

            ListView listaProf = findViewById(R.id.listaProf);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, lista

            ); listaProf.setAdapter(adapter);
        }catch (Exception a){
            a.printStackTrace();
        }

    }
}