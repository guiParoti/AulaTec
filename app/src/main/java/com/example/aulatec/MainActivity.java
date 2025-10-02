package com.example.aulatec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private int timerS = 2000; //Tempo da tela splash de 1,5 segundo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Handler handler = new Handler();

        TextView linkGui = findViewById(R.id.linkGuilherme);
        ImageView linkImageIsta = findViewById(R.id.iconInsta);


        Runnable abrirHome = () -> {
            Intent intent = new Intent(MainActivity.this, TelaMod.class);
            startActivity(intent);
            finish();
        };

        handler.postDelayed(abrirHome, timerS);


        View.OnClickListener abrirPerfil = v -> {
            handler.removeCallbacks(abrirHome);
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/guilhermeparoti?igsh=MTIzbmlqbHpvazc2eQ=="));
            startActivity(intent);
        };

        linkGui.setOnClickListener(abrirPerfil);
        linkImageIsta.setOnClickListener(abrirPerfil);




    }
}