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

public class TipoCurso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tipo_curso);
        int idModulo = getIntent().getIntExtra("id_modulo", 3);

        Button btnCGratis = findViewById(R.id.btnCGratis);
        Button btnCPago = findViewById(R.id.btnCPagos);

        btnCGratis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipoCurso.this, CursosGratuitos.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
            }
        });

        btnCPago.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(TipoCurso.this, CursosPagos.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
            }
        });

        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);


        barraNavegacao.setOnItemSelectedListener( item -> {
            int id = item.getItemId();
            if (id == R.id.nav_modulo) {
                Intent intent = new Intent(TipoCurso.this, TelaMod.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                return true;
            }else if(id == R.id.nav_home){
                Intent intent = new Intent(TipoCurso.this, Home.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                return true;
            }else if(id == R.id.nav_emails){
                Intent intent = new Intent(TipoCurso.this, ListaProf.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                return true;
            }else if(id == R.id.nav_aulas){
                Intent intent = new Intent(TipoCurso.this, ListaAulas.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                return true;
            }else if(id == R.id.nav_recomendacoes){
                Intent intent = new Intent(TipoCurso.this, EscolherRecomendacoes.class);
                intent.putExtra("id_modulo", idModulo);
                startActivity(intent);
                return true;
            }

            return false;
        });

    }
}