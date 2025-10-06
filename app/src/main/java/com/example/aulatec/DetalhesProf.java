package com.example.aulatec;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalhesProf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalhes_prof);

        String nomeProf = getIntent().getStringExtra("nomeProf");
        String emailProf = getIntent().getStringExtra("emailProf");
        int idModulo = getIntent().getIntExtra("id_modulo", 3);

        TextView txtNome = findViewById(R.id.txtNome);
        LinearLayout containerProfessor = findViewById(R.id.conteinerProf);

        DatabaseHelper bdHelper = new DatabaseHelper(this);
        SQLiteDatabase db = bdHelper.getReadableDatabase();

        txtNome.setText("Professor(a): " + nomeProf + "\nEmail: " + emailProf + "\nAulas:");
        txtNome.setTextSize(20);
        txtNome.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

        Cursor cursor = db.rawQuery(
                "SELECT a.nomeAula from aulas a " +
                        "JOIN professores p ON a.id_professor = p.id_professor " +
                        "WHERE p.nomeProf = ? AND a.id_modulo = ?",
                new String[]{nomeProf, String.valueOf(idModulo)}
        );

        if(cursor.moveToFirst()){
            do{
                String nomeAulas = cursor.getString(0);

                TextView txtAulas = new TextView(this);
                txtAulas.setText("• " + nomeAulas);
                txtAulas.setTextSize(20);
                containerProfessor.addView(txtAulas);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();





    }
}