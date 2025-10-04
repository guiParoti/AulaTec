package com.example.aulatec;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.recomendacoes.EscolherRecomendacoes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Home extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        int idModulo = getIntent().getIntExtra("id_modulo", 3);

        // Pega o container onde vamos adicionar as aulas
        LinearLayout containerAulas = findViewById(R.id.containerAulas);
        //TextView que vai receber o dia da semana
        TextView txtDia = findViewById(R.id.txtdiaSem);
        TextView linkNsa = findViewById(R.id.txtLinkNsa);
//      Button btnProf = findViewById(R.id.btnListProf);
//      Button btnAulas = findViewById(R.id.btnVerAulas);
//      Button btnRecomendacoes = findViewById(R.id.btnRecomendacoes);
        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_home);


            barraNavegacao.setOnItemSelectedListener(item -> {
                int id = item.getItemId(); // pega o id do item clicado
                if (id == R.id.nav_home) {
                    // já está no Home, não faz nada
                    return true;
                } else if (id == R.id.nav_aulas) {
                    Intent intent = new Intent(Home.this, ListaAulas.class);
                    intent.putExtra("id_modulo", idModulo);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }else if(id == R.id.nav_emails){
                    Intent intent = new Intent(Home.this, ListaProf.class);
                    intent.putExtra("id_modulo", idModulo);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }else if(id == R.id.nav_recomendacoes){
                    Intent intent = new Intent(Home.this, EscolherRecomendacoes.class);
                    intent.putExtra("id_modulo", idModulo);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }else if(id == R.id.nav_modulo){
                    Intent intent = new Intent(Home.this, TelaMod.class);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    return true;
                }
                return false;
            });

        // Instancia a classe DatabaseHelper em um objeto pra poder acessar o banco de dados
        DatabaseHelper bdHelper = new DatabaseHelper(this);
        // Abre o banco no modo leitura
        SQLiteDatabase bd = bdHelper.getReadableDatabase();

        // Instancia um objeto Calendar pra pegar a data atual
        Calendar calendario = Calendar.getInstance();
        int diaSemana = calendario.get(Calendar.DAY_OF_WEEK); // Dia da semana, 1= Domingo, 2= Segunda e etc

        View.OnClickListener abrirNsa = v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsa.cps.sp.gov.br"));
            startActivity(intent);
        };

        linkNsa.setOnClickListener(abrirNsa);

//        btnProf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, ListaProf.class);
//                startActivity(intent);
//            }
//        });

//        btnAulas.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, ListaAulas.class);
//                intent.putExtra("id_modulo", idModulo);
//                startActivity(intent);
//            }
//        });

//        btnRecomendacoes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Home.this, EscolherRecomendacoes.class);
//                startActivity(intent);
//            }
//        });

        // Percorre o banco selecionando o nome e os horarios das aulas com base no dia da semana
        // Atualização agora faz o SELECT especificando de qual tabela é a. da tabela de aulas e p. da tabela de professores
        Cursor cursor = bd.rawQuery(
                "SELECT a.nomeAula, a.horaInicio, a.horaFim, a.lab, p.nomeProf" +
                        " FROM aulas a " +
                        "JOIN professores p ON a.id_professor = p.id_professor" +
                        " WHERE a.diaSemana = ? AND a.id_modulo = ?",
                new String[]{String.valueOf(diaSemana), String.valueOf(idModulo)}
        );

// Limpa o container antes de adicionar
        containerAulas.removeAllViews();

        // Verefica se o cursor achou alguma coisa
        if(cursor.moveToFirst()){
            do{
                // Pega os dados da aula do cursor
                String aula = cursor.getString(0); // Nome da aula
                String inicio = cursor.getString(1); // Horario que começa
                String fim = cursor.getString(2); // Horario que termina
                int lab = cursor.getInt(3); // Pega o numero do laboratorio
                String professor = cursor.getString(4); // Nome do professor


                // Cria TextView para cada aula
                TextView tvAula = new TextView(this);
                tvAula.setText(idModulo + "°DS:\n" + aula + " - " + inicio + " até " + fim + "\nProfessor(a): " + professor + "\nLab: " + lab); // Define o texto
                tvAula.setTextSize(16); // Tamanho da fonte do texto
                tvAula.setPadding(10,10,10,10); // Espaçamento interno
                tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples

                // Adiciona o TextView no container
                containerAulas.addView(tvAula);

            }while(cursor.moveToNext()); // Continua enquanto tiver aulas
        } else {
            // Se não tiver aula no dia
            TextView tvAula = new TextView(this);
            tvAula.setText("Hoje não tem aula\nAproveite seu final de semana!");
            tvAula.setTextSize(18);
            tvAula.setPadding(16,16,16,16);
            tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples
            containerAulas.addView(tvAula);
        }

        // Fecha o cursor e o banco pra liberar memoria
        cursor.close();
        bd.close();

// Define o dia da semana no TextView txtDia
        switch(diaSemana){
            case 2:
                txtDia.setText("Segunda-feira");
                break;
            case 3:
                txtDia.setText("Terça-feira");
                break;
            case 4:
                txtDia.setText("Quarta-feira");
                break;
            case 5:
                txtDia.setText("Quinta-feira");
                break;
            case 6:
                txtDia.setText("Sexta-feira");
                break;
            case 7:
                txtDia.setText("Sábado");
                break;
            case 1:
                txtDia.setText("Domingo");
                break;
        }




    }
}