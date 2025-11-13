package com.example.aulatec;

import static android.view.View.TEXT_ALIGNMENT_CENTER;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.barra_navegacao.BarraDeNavegacao;
import com.example.funcoes_curiosidades.FuncaoCuriosidade;
import com.example.recomendacoes.EscolherRecomendacoes;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;

public class Home extends AppCompatActivity {

    String modulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        int idModulo = getIntent().getIntExtra("id_modulo", 5);
        String turma = getIntent().getStringExtra("turma");
        TextView txtTurma = findViewById(R.id.txtTurma);


        // Pega o container onde vamos adicionar as aulas
        LinearLayout containerAulas = findViewById(R.id.containerAulas);

        //TextView que vai receber o dia da semana
        TextView txtDia = findViewById(R.id.txtdiaSem);
        TextView linkNsa = findViewById(R.id.txtLinkNsa);


        BottomNavigationView barraNavegacao = findViewById(R.id.bottom_navigation);
        barraNavegacao.setSelectedItemId(R.id.nav_home);
        BarraDeNavegacao.configurarNavegacao(this, barraNavegacao, idModulo, turma);

        TextView txtCuriosidade = findViewById(R.id.txtCuriosidade);
        FuncaoCuriosidade curiosidade1 = new FuncaoCuriosidade();
        curiosidade1.escolherFuncaoCuriosidade();

        txtCuriosidade.setText(curiosidade1.getCuriosidadeEscolhida());
        txtCuriosidade.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);
        txtCuriosidade.setPadding(50,50,50,50);



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

        // Percorre o banco selecionando o nome e os horarios das aulas com base no dia da semana e modulo
        // Atualização agora faz o SELECT especificando de qual tabela é, a. da tabela de aulas e p. da tabela de professores
        Cursor cursor = bd.rawQuery(
                "SELECT a.nomeAula, a.horaInicio, a.horaFim, a.lab, p.nomeProf " +
                        "FROM aulas a " +
                        "JOIN professores p ON a.id_professor = p.id_professor " +
                        "JOIN modulos m ON a.id_modulo = m.id_modulo " +
                        "WHERE a.diaSemana = ? AND a.id_modulo = ? AND UPPER(m.turma) = UPPER(?)",
                new String[]{String.valueOf(diaSemana), String.valueOf(idModulo), turma}
        );

// Limpa o container antes de adicionar
        containerAulas.removeAllViews();

        // Verifica se o cursor achou alguma coisa
        if(cursor.moveToFirst()){
            do{
                // Pega os dados das aulas do cursor
                String aula = cursor.getString(0); // Nome da aula
                String inicio = cursor.getString(1); // Horario que começa
                String fim = cursor.getString(2); // Horario que termina
                int lab = cursor.getInt(3); // Pega o numero do laboratorio
                String professor = cursor.getString(4); // Nome do professor

                if(lab == 0) {
                    String maker = "Maker";

                    if(inicio.equals("19:00") && fim.equals("20:40")){
                        // Cria TextView para cada aula
                        TextView tvAula = new TextView(this);
                        tvAula.setText("1°Aula -" + "\n" + aula + " - " + inicio + " até " + fim + "\n" + professor + "\nLab: " + maker); // Define o texto
                        tvAula.setTextSize(16); // Tamanho da fonte do texto;
                        tvAula.setPadding(20, 20, 20, 20); // Espaçamento interno
                        tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples
                        tvAula.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                        // Adiciona o TextView no container
                        containerAulas.addView(tvAula);
                    }else {
                        TextView tvAula = new TextView(this);
                        tvAula.setText("2°Aula -" + "\n" + aula + " - " + inicio + " até " + fim + "\n" + professor + "\nLab: " + maker); // Define o texto
                        tvAula.setTextSize(16); // Tamanho da fonte do texto;
                        tvAula.setPadding(20, 20, 20, 20);// Espaçamento interno
                        tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples
                        tvAula.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                        // Adiciona o TextView no container
                        containerAulas.addView(tvAula);
                    }
                }
                else{
                    if(inicio.equals("19:00") && fim.equals("20:40")){
                        // Cria TextView para cada aula
                        TextView tvAula = new TextView(this);
                        tvAula.setText("1°Aula -" + "\n" + aula + " - " + inicio + " até " + fim + "\n" + professor + "\nLab: " + lab); // Define o texto
                        tvAula.setTextSize(16); // Tamanho da fonte do texto;
                        tvAula.setPadding(20, 20, 20, 20); // Espaçamento interno
                        tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples
                        tvAula.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                        // Adiciona o TextView no container
                        containerAulas.addView(tvAula);
                    }else {
                        TextView tvAula = new TextView(this);
                        tvAula.setText("2°Aula -" + "\n" + aula + " - " + inicio + " até " + fim + "\n" + professor + "\nLab: " + lab); // Define o texto
                        tvAula.setTextSize(16); // Tamanho da fonte do texto;
                        tvAula.setPadding(20, 20, 20, 20); // Espaçamento interno
                        tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples
                        tvAula.setTextAlignment(TEXT_ALIGNMENT_CENTER);
                        // Adiciona o TextView no container
                        containerAulas.addView(tvAula);
                    }
                }

            }while(cursor.moveToNext()); // Continua enquanto tiver aulas
        } else {
            // Se não tiver aula no dia
            TextView tvAula = new TextView(this);
            tvAula.setText("Hoje não tem aula\nAproveite seu final de semana!");
            tvAula.setTextSize(16);
            tvAula.setPadding(20, 20, 20, 20);
            tvAula.setBackgroundResource(android.R.drawable.dialog_holo_light_frame); // Uma borda simples
            tvAula.setTextAlignment(TEXT_ALIGNMENT_CENTER);
            containerAulas.addView(tvAula);
        }

        // Fecha o cursor e o banco pra liberar memoria
        cursor.close();
        bd.close();

        switch (idModulo){
            case 1:
                txtTurma.setText("1°DS " + turma);
                break;
            case 2:
                txtTurma.setText("1°DS " + turma);
                break;
            case 3:
                txtTurma.setText("2°DS " + turma);
                break;
            case  4:
                txtTurma.setText("2°DS " + turma);
                break;
            case 5:
                txtTurma.setText("3°DS " + turma);
                break;
        }


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