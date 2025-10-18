package com.example.funcoes_curiosidades;

import java.util.Random;

public class FuncaoCuriosidade {

    private String curiosidadeEscolhida;
    Random escolherIndice = new Random();

    public void escolherFuncaoCuriosidade(){
        int indice = escolherIndice.nextInt(3);

        if(indice == 0){
            gerarCuriosidadeJava();
        }else if (indice == 1){
            gerarCuriosidadePython();
        }else{
            gerarCuriosidadeJava();
        }
    }

    public void gerarCuriosidadeJava(){
        String[] curioJava = new String[4];

        curioJava[0] = "                        Exemplo de um for em Java\n" +
                "\npublic class ForEmJava {\n" +
                "\npublic static void main(String[] args){\n" +
                "   for(int contador = 0; contador <= 10; contador++){\n" +
                "       System.out.println(\"Contando: \" + contador);" +
                "       \n      }" +
                "   \n   }" +
                "   \n}";

        curioJava[1] = "Em Java temos 8 tipos primitivos no total\n" +
                "--Numéricos--\nbyte, short, int, long, float, double\n--Lógico--\nboolean\n--Caractere--\nchar";

        curioJava[2] = "String não é um tipo primitivo, mas sim um objeto(Uma instância de uma classe)";

        curioJava[3] = "       Exemplo de como dar um Hello World em Java\n" +
                "\npublic Class HelloWorld{" +
                "\npublic static void main(String[] args){\n" +
                "       System.out.println(\"Hello World\");" +
                "\n     }" +
                "\n}";

        int indiceCurioJava = escolherIndice.nextInt(curioJava.length);
        this.setCuriosidadeEscolhida(curioJava[indiceCurioJava]);
    }

    public void gerarCuriosidadePython(){
        String[] curioPython = new String[2];

        curioPython[0] = "       Exemplo de um Hello World em Python\n" +
                "\nprint(\"Hello World\")";

        curioPython[1] = "  Exemplo de um for em Python\n" +
                "\nfor contador in range(10):" +
                "   \n print(contador)";

        int indiceCurioPython = escolherIndice.nextInt(curioPython.length);
        this.setCuriosidadeEscolhida(curioPython[indiceCurioPython]);
    }

    public String getCuriosidadeEscolhida(){
        return curiosidadeEscolhida;
    }

    public void setCuriosidadeEscolhida(String curiosidade){
        this.curiosidadeEscolhida = curiosidade;
    }




}
