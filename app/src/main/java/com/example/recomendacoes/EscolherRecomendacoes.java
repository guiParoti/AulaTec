package com.example.recomendacoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aulatec.Home;
import com.example.aulatec.ListaAulas;
import com.example.aulatec.ListaProf;
import com.example.aulatec.R;
import com.example.aulatec.TelaMod;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EscolherRecomendacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_recomendacoes);
        int idModulo = getIntent().getIntExtra("id_modulo", 3);


        Button btnCursos = findViewById(R.id.btnRCursos);
        Button btnJogos = findViewById(R.id.btnRJogos);
        Button btnFilmes = findViewById(R.id.btnRFilmes);
        Button btnApps = findViewById(R.id.btnRApps);

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_recomendacoes);

        barraNavegacao.setOnItemSelectedListener(item -> {
            int id = item.getItemId(); // pega o id do item clicado
            if (id == R.id.nav_recomendacoes) {
                // já está nas recomendações dos professores, não faz nada
                return true;

            } else if (id == R.id.nav_home) {
                Intent intent = new Intent(EscolherRecomendacoes.this, Home.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            }else if(id == R.id.nav_aulas){
                Intent intent = new Intent(EscolherRecomendacoes.this, ListaAulas.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            }else if(id == R.id.nav_emails){
                Intent intent = new Intent(EscolherRecomendacoes.this, ListaProf.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;

            }else if(id == R.id.nav_modulo){
                Intent intent = new Intent(EscolherRecomendacoes.this, TelaMod.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }
            return false;
        });

        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolherRecomendacoes.this, TipoCurso.class);
                startActivity(intent);
            }
        });


    }
}