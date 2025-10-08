package com.example.recomendacoes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.aulatec.R;

public class TipoCurso extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tipo_curso);

        Button btnCGratis = findViewById(R.id.btnCGratis);
        Button btnCPago = findViewById(R.id.btnCPagos);

        btnCGratis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TipoCurso.this, CursosGratuitos.class);
                startActivity(intent);
            }
        });

        btnCPago.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(TipoCurso.this, CursosPagos.class);
                startActivity(intent);
            }
        });

    }
}