package com.example.aulatec;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String nomeBanco = "etec.db"; // Nome do banco de dados
    private static final int versaoBanco = 22; // Versão do Banco (usado pra atualizar a tabela)



    // Construtor da Classe, recebe contexto da Activity
    public DatabaseHelper(Context context){
        super(context, nomeBanco, null, versaoBanco);
    }


    // Metodo chamado apenas na primeira vez que o banco é criado
    @Override
    public void onCreate(SQLiteDatabase bd){

        // Cria a tabela de modulos
        bd.execSQL("CREATE TABLE modulos(" +
                "id_modulo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nomeModulo TEXT)");
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
                "id_modulo INTEGER," +
                "FOREIGN KEY (id_professor) REFERENCES professores(id_professor)," +
                "FOREIGN KEY (id_modulo) REFERENCES modulos(id_modulo))");

        // Insere os dados fixos do banco para cada dia da semana
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (2,'BANCO DADOS 3', '19:00', '20:40', 10, 4, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (2, 'LTT', '21:08', '22:50', 15, 6, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (3, 'PAM2', '19:00', '20:40', 3, 5, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (3, 'PWEB3', '21:08', '22:50', 10, 4, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (4, 'SEGURANÇA DE SISTEMAS', '19:00', '20:40', 10, 1, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (4, 'INTERNET E PROTOCOLOS', '21:08', '22:50', 10, 4, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (5, 'DS2', '19:00', '20:40', 10, 1, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (5, 'TCC', '21:08', '22:50', 5, 3, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (6, 'PWEB3', '19:00', '20:40', 10, 4, 3)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (6, 'PAM2', '21:08', '22:50', 10, 5, 3)");


        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (2,'PWEB2', '19:00', '20:40', null, 2, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (2, 'SISTEMAS EMBARCADOS', '21:08', '22:50', null, 1, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (3, 'PAM1', '19:00', '20:40', null, 1, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (3, 'PAM1', '21:08', '22:50', null, 1, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (4, 'ANALISE e PROJETO de SISTEMAS', '19:00', '20:40', null, 3, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (4, 'BANCO DADOS 2', '21:08', '22:50', null, 7, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (5, 'DS1', '19:00', '20:40', null, 5, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (5, 'DS1', '21:08', '22:50', null, 5, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (6, 'PWEB2', '19:00', '20:40', null, 2, 2)");
        bd.execSQL("INSERT INTO aulas (diaSemana, nomeAula, horaInicio, horaFim, lab, id_professor, id_modulo)" +
                "VALUES (6, 'TCC', '21:08', '22:50', null, 1, 2)");


        // Insere o nome dos professores e seus emails(se tiver) fixos no banco
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Edson', 'edson.baptista@etec.sp.gov.br')"); // id_professor(1)
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Davi', 'davi.quierelli@etec.sp.gov.br')"); // id_professor(2)
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Miriam Lopes', 'miriam.siqueira@etec.sp.gov.br')"); // id_professor(3)
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Vagner', 'vagner.santos41@etec.sp.gov.br')"); // id_professor(4)
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Valdecir', '')"); // id_professor(5)
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Raquel', '')"); // id_professor(6)
        bd.execSQL("INSERT INTO professores (nomeProf, emailProf)" +
                "VALUES ('Andre', 'andre.candido@etec.sp.gov.br')"); // id_professor(7)

        bd.execSQL("INSERT INTO modulos(id_modulo, nomeModulo) " +
                "VALUES(1, '1°DS')");
        bd.execSQL("INSERT INTO modulos(id_modulo, nomeModulo) " +
                "VALUES(2, '2°DS')");
        bd.execSQL("INSERT INTO modulos(id_modulo, nomeModulo) " +
                "VALUES(3, '3°DS')");

    }



    // Metodo chamado quando a versão do banco é alterada
    @Override
    public void onUpgrade(SQLiteDatabase bd, int vA, int nV){
        bd.execSQL("DROP TABLE IF EXISTS aulas");
        bd.execSQL("DROP TABLE IF EXISTS professores");
        bd.execSQL("DROP TABLE IF EXISTS modulos");
        onCreate(bd);
    }


}
