package com.example.recomendacoes;

import android.content.Intent;
import android.os.Bundle;

import com.example.aulatec.AreaAluno;
import com.example.aulatec.Home;
import com.example.aulatec.ListaAulas;
import com.example.aulatec.ListaProf;
import com.example.aulatec.R;
import com.example.aulatec.TelaMod;
import com.example.barra_navegacao.BarraDeNavegacao;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.aulatec.databinding.ActivityCursosGratuitosBinding;

public class CursosGratuitos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos_gratuitos);
        int idModulo = getIntent().getIntExtra("id_modulo", 3);
        String turma =  getIntent().getStringExtra("turma");

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_recomendacoes);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);

    }

}