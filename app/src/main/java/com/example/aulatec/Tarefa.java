package com.example.aulatec;

public class Tarefa {
    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private String prazo;

    public Tarefa(int id, String titulo, String descricao, String status, String prazo){
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.prazo = prazo;

    }

    public int getId(){
        return id;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getDescricao(){
        return descricao;
    }

    public String getStatus(){
        return status;
    }

    public String getPrazo() {return  prazo;}
}
