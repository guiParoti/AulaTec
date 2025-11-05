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

public class ListaProf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_prof);
        int idModulo = getIntent().getIntExtra("id_modulo", 3);
        String turma = getIntent().getStringExtra("turma");

        exibirLista(idModulo, turma);

        BottomNavigationView barraNavegacao = findViewById(R.id.bottomNavigationView);
        barraNavegacao.setSelectedItemId(R.id.nav_emails);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);
    }
    public void exibirLista(int idModulo, String turma){
        try{
            DatabaseHelper bancoHelper = new DatabaseHelper(this);
            SQLiteDatabase bancoDados = bancoHelper.getReadableDatabase();

            Cursor cursor = bancoDados.rawQuery("SELECT DISTINCT p.nomeProf, p.emailProf " +
                    "FROM professores p " +
                    "JOIN modulos m ON a.id_modulo = m.id_modulo " +
                    "JOIN aulas a ON p.id_professor = a.id_professor " +
                    "WHERE a.id_modulo = ?",
                     new String[]{String.valueOf(idModulo)});

            ArrayList<String> lista = new ArrayList<>();
            ArrayList<String> nomes = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();

            if (cursor.moveToFirst()){
                do{
                    String nomeProf = cursor.getString(0);
                    String emailProf = cursor.getString(1);
                    lista.add(nomeProf);
                    nomes.add(nomeProf);
                    emails.add((emailProf));

                }while (cursor.moveToNext());
            }

            cursor.close();
            bancoDados.close();

            ListView listaProf = findViewById(R.id.listaProf);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this, android.R.layout.simple_list_item_1, lista

            ); listaProf.setAdapter(adapter);


            listaProf.setOnItemClickListener((parent, view, position, id) -> {
                String nomeSelecionado = nomes.get(position);
                String emailSelecionado = emails.get(position);


                Intent intent = new Intent(ListaProf.this, DetalhesProf.class);
                intent.putExtra("nomeProf", nomeSelecionado);
                intent.putExtra("emailProf", emailSelecionado);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
            });
        }catch (Exception a){
            a.printStackTrace();
        }

    }
}