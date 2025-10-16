package com.example.funcoes_curiosidades;

import java.util.Random;

public class FuncaoCuriosidade {

    private String curiosidadeEscolhida;
    Random escolherIndice = new Random();


    public void gerarCuriosidadeJava(){
        String[] curioJava = new String[3];

        curioJava[0] = "Exemplo de um for em Java\n" +
                "\npublic class ForEmJava {\n" +
                "\npublic static void main(String[] args){\n" +
                "   for(int contador = 0; contador <= 10; contador++){\n" +
                "       System.out.println('Contando: ' + contador);" +
                "   }\n" +
                "}\n" +
                "}";

        curioJava[1] = "Em Java temos 8 tipos primitivos no total.\n" +
                "byte, short, int, long, float, double, boolean e char";

        curioJava[2] = "String não é um tipo primitivo, mas sim um objeto(Uma instância de uma classe)";

        int indiceCurioJava = escolherIndice.nextInt(curioJava.length);
        this.setCuriosidadeEscolhida(curioJava[indiceCurioJava]);
    }

    public String getCuriosidadeEscolhida(){
        return curiosidadeEscolhida;
    }

    public void setCuriosidadeEscolhida(String curiosidade){
        this.curiosidadeEscolhida = curiosidade;
    }




}
