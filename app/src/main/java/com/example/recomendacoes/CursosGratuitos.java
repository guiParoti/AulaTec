package com.example.recomendacoes;

import android.content.Intent;
import android.os.Bundle;

import com.example.aulatec.Home;
import com.example.aulatec.ListaAulas;
import com.example.aulatec.ListaProf;
import com.example.aulatec.R;
import com.example.aulatec.TelaMod;
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

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_recomendacoes);


        barraNavegacao.setOnItemSelectedListener( item -> {
            int id = item.getItemId();
            if (id == R.id.nav_modulo) {
                Intent intent = new Intent(CursosGratuitos.this, TelaMod.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_home){
                Intent intent = new Intent(CursosGratuitos.this, Home.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_emails){
                Intent intent = new Intent(CursosGratuitos.this, ListaProf.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_aulas){
                Intent intent = new Intent(CursosGratuitos.this, ListaAulas.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }else if(id == R.id.nav_recomendacoes){
                Intent intent = new Intent(CursosGratuitos.this, EscolherRecomendacoes.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                overridePendingTransition(0, 0);
                return true;
            }

            return false;
        });


    }

}