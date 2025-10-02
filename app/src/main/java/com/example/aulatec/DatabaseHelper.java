package com.example.aulatec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String nomeBanco = "etec.db"; // Nome do banco de dados
    private static final int versaoBanco = 17; // Versão do Banco (usado pra atualizar a tabela)



    // Construtor da Classe, recebe contexto da Activity
    public DatabaseHelper(Context context){
        super(context, nomeBanco, null, versaoBanco);
    }


    // Metodo chamado apenas na primeira vez que o banco é criado
    @Override
    public void onCreate(SQLiteDatabase bd){
        // Cria a tabela de professores
        bd.execSQL("CREATE TABLE professores(" +
                "id_professor INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeProf TEXT," +
                "emailProf TEXT)");
        // Cria a tabela de aulas e as suas respectivas colunas
        bd.execSQL("CREATE TABLE aulas(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "diaSemana INTEGER," +
                "nomeAula TEXT," +
                "horaInicio TEXT," +
                "horaFim TEXT," +
                "lab INTEGER, " +
                "id_professor INTEGER," +
                "FOREIGN KEY (id_professor) REFERENCES professores(id_professor))");


        // Insere os dados fixos do banco para cada dia da semana
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (2,'BANCO DADOS 3', '19:00', '20:40', 10, 4)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (2, 'LTT', '21:08', '22:50', 15, 6)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (3, 'PAM2', '19:00', '20:40', 3, 5)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (3, 'PWEB3', '21:08', '22:50', 10, 4)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (4, 'SEGURANÇA DE SISTEMAS', '19:00', '20:40', 10, 1)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (4, 'INTERNET E PROTOCOLOS', '21:08', '22:50', 10, 4)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (5, 'DS2', '19:00', '20:40', 10, 1)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (5, 'TCC', '21:08', '22:50', 5, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (6, 'PWEB3', '19:00', '20:40', 10, 4)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor)" +
                "VALUES (6, 'PAM2', '21:08', '22:50', 10, 5)");

        // Insere o nome dos professores e seus emails(se tiver) fixos no banco
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Edson', 'edson.baptista@etec.sp.gov.br')");
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Davi', 'davi.quierelli@etec.sp.gov.br')");
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Miriam Lopes', 'miriam.siqueira@etec.sp.gov.br')");
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Vagner', 'vagner.santos41@etec.sp.gov.br')");
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Valdecir', '')");
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Raquel', '')");
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Andre', 'andre.candido@etec.sp.gov.br')");


    }

    // Metodo chamado quando a versão do banco é alterada
    @Override
    public void onUpgrade(SQLiteDatabase bd, int vA, int nV){
        bd.execSQL("DROP TABLE IF EXISTS aulas");
        bd.execSQL("DROP TABLE IF EXISTS professores");
        onCreate(bd);
    }


}
