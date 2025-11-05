package com.example.recomendacoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aulatec.AreaAluno;
import com.example.aulatec.Home;
import com.example.aulatec.ListaAulas;
import com.example.aulatec.ListaProf;
import com.example.aulatec.R;
import com.example.aulatec.TelaMod;
import com.example.barra_navegacao.BarraDeNavegacao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EscolherRecomendacoes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_escolher_recomendacoes);
        int idModulo = getIntent().getIntExtra("id_modulo", 3);
        String turma =  getIntent().getStringExtra("turma");


        Button btnCursos = findViewById(R.id.btnCGratis);
        Button btnJogos = findViewById(R.id.btnCPagos);
        Button btnFilmes = findViewById(R.id.btnRFilmes);
        Button btnApps = findViewById(R.id.btnRApps);

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_recomendacoes);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);



        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolherRecomendacoes.this, TipoCurso.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
            }
        });

        btnJogos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolherRecomendacoes.this, RJogos.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
            }
        });

        btnFilmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolherRecomendacoes.this, RFilmes.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
            }
        });

        btnApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EscolherRecomendacoes.this, RApps.class);
                intent.putExtra("id_modulo", idModulo);
                intent.putExtra("turma", turma);
                startActivity(intent);
            }
        });


    }
}