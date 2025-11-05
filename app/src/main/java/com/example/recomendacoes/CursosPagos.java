package com.example.recomendacoes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aulatec.AreaAluno;
import com.example.aulatec.Home;
import com.example.aulatec.ListaAulas;
import com.example.aulatec.ListaProf;
import com.example.aulatec.R;
import com.example.aulatec.TelaMod;
import com.example.barra_navegacao.BarraDeNavegacao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CursosPagos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cursos_pagos);
        int idModulo = getIntent().getIntExtra("id_modulo", 3);
        String turma = getIntent().getStringExtra("turma");

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_recomendacoes);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);

    }
}