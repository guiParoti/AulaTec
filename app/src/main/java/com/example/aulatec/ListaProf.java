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

        barraNavegacao.setOnItemSelectedListener(item -> {
            int id = item.getItemId(); // pega o id do item clicado
            if (id == R.id.nav_emails) {
                // já está nos contatos dos professores, não faz nada
                return true;

            } else if (id == R.id.nav_home) {
                Intent intent = new Intent(ListaProf.this, Home.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            }else if(id == R.id.nav_aulas){
                Intent intent = new Intent(ListaProf.this, ListaAulas.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            }else if(id == R.id.nav_recomendacoes){
                Intent intent = new Intent(ListaProf.this, EscolherRecomendacoes.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            }else if(id == R.id.nav_aluno){
                Intent intent = new Intent(ListaProf.this, AreaAluno.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

    }

    public void exibirLista(int idModulo, String turma){
        try{
            DatabaseHelper bancoHelper = new DatabaseHelper(this);
            SQLiteDatabase bancoDados = bancoHelper.getReadableDatabase();

            Cursor cursor = bancoDados.rawQuery("SELECT nomeProf, emailProf " +
                    "FROM professores", null);

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