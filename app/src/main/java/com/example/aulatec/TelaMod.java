package com.example.aulatec;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaMod extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_mod);

        Button priDs = findViewById(R.id.btn1mod);
        Button segDs = findViewById(R.id.btn2mod);
        Button terDs = findViewById(R.id.btn3mod);
        ImageView linkImageNsa = findViewById(R.id.iconNsa2);
        //TextView linkNsa = findViewById(R.id.linkNsa2);


        priDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaMod.this, Home.class);
                intent.putExtra("id_modulo", 3  );
                startActivity(intent);
            }
        });

        segDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaMod.this, Home.class);
                intent.putExtra("id_modulo", 2);
                startActivity(intent);

            }
        });

        terDs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TelaMod.this, Home.class);
                intent.putExtra("id_modulo", 3);
                startActivity(intent);
            }
        });

        View.OnClickListener abrirNsa = v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsa.cps.sp.gov.br"));
            startActivity(intent);
        };
        //linkNsa.setOnClickListener(abrirNsa);
        linkImageNsa.setOnClickListener(abrirNsa);

    }
}