package com.example.funcoes_curiosidades;

import java.util.Random;

public class FuncaoCuriosidade {

    private String curiosidadeEscolhida;
    Random escolherIndice = new Random();

    public void escolherFuncaoCuriosidade(){
        int indice = escolherIndice.nextInt(4);

        switch(indice) {
            case 0:
                gerarCuriosidadeJava();
                break;
            case 1:
                gerarCuriosidadePython();
                break;
            case 2:
                gerarCuriosidadeC();
                break;
            case 3:
                gerarCuriosidadeJavaScript();
                break;
        }
    }

    public void gerarCuriosidadeJava(){
        String[] curioJava = new String[9];

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

        curioJava[4] = "       Exemplo de uma estrutura condicional em Java\n\nint idade = 18;\nif(idade >= 18){\n   " +
                "System.out.println(\"Maior de idade\");\n}else{\n  " +
                " System.out.println(\"Menor de idade\");\n}";

        curioJava[5] = "O Garbage Collector (Coletor de Lixo) do Java é responsável por liberar memória automaticamente, sem que o programador precise gerenciar isso manualmente.";

        curioJava[6] = "O nome 'Java' vem de um tipo de café!\n" +
                "\nQuando os criadores da linguagem estavam desenvolvendo o projeto (inicialmente chamado Oak), eles queriam um nome curto, fácil de lembrar e que transmitisse energia. " +
                "Durante uma pausa tomando café, decidiram chamar a linguagem de 'Java', em referência ao café da ilha de Java, na Indonésia.";

        curioJava[7] = "Em Java, uma classe é como um molde (ou modelo) que define as características e comportamentos de um objeto.\n" +
                "As classes contêm atributos (variáveis) e métodos (funções) que representam o que um objeto 'tem' e o que ele 'faz'.\n\n" +
                "Por exemplo:\n" +
                "public class Pessoa {\n" +
                "   String nome;\n" +
                "   int idade;\n\n" +
                "   void apresentar(){\n" +
                "       System.out.println(\"Olá, meu nome é \" + nome);\n" +
                "   }\n" +
                "}";

        curioJava[8] = "Em Java, os métodos podem ter ou não ter retorno.\n" +
                "Quando usamos 'void', significa que o método **não retorna nenhum valor**.\n" +
                "Já quando definimos um tipo (como int, String, etc.), o método **precisa retornar** algo desse tipo.\n\n" +
                "Exemplo:\n\n" +
                "public class ExemploMetodo {\n\n" +
                "   // Método sem retorno (void)\n" +
                "   void dizerOla(){\n" +
                "       System.out.println(\"Olá, mundo!\");\n" +
                "   }\n\n" +
                "   // Método com retorno (int)\n" +
                "   int somar(int a, int b){\n" +
                "       return a + b;\n" +
                "   }\n" +
                "}";

        int indiceCurioJava = escolherIndice.nextInt(curioJava.length);
        this.setCuriosidadeEscolhida(curioJava[indiceCurioJava]);
    }
    public void gerarCuriosidadePython(){
        String[] curioPython = new String[4];

        curioPython[0] = "       Exemplo de um Hello World em Python\n" +
                "\nprint(\"Hello World\")";

        curioPython[1] = "  Exemplo de um for em Python\n" +
                "\nfor contador in range(10):" +
                "   \n print(contador)";

        curioPython[2] = "Python foi criado por Guido van Rossum no final dos anos 80 e recebeu o nome inspirado no grupo de comédia britânico 'Monty Python'.";

        curioPython[3] = "          Exemplo de função em Python\n" +
                "\ndef saudacao(nome):\n    print(f\"Olá, {nome}!\")\n\nsaudacao(\"Mundo\")";

        int indiceCurioPython = escolherIndice.nextInt(curioPython.length);
        this.setCuriosidadeEscolhida(curioPython[indiceCurioPython]);
    }
    public void gerarCuriosidadeC(){
        String[] curioC = new String[3];

        curioC[0] = "A linguagem C foi criada por Dennis Ritchie em 1972 e influenciou quase todas as linguagens modernas, como Java, C++ e C#.";

        curioC[1] = "       Exemplo de um Hello World em C\n\n#include <stdio.h>\nint main(){\n   printf(\"Hello, World!\\n\");\n   return 0;\n}";

        curioC[2] = "Em C, não existe coleta automática de memória. O programador precisa liberar a memória manualmente com 'free()'.";

        int indiceCurioC = escolherIndice.nextInt(curioC.length);
        this.setCuriosidadeEscolhida(curioC[indiceCurioC]);
    }
    public void gerarCuriosidadeJavaScript(){
        String[] curioJS = new String[3];

        curioJS[0] = "JavaScript foi criado em apenas 10 dias por Brendan Eich em 1995.";

        curioJS[1] = "       Exemplo de um Hello World em JavaScript\n\nconsole.log('Hello, World!');";

        curioJS[2] = "No JavaScript, funções também são tratadas como objetos de primeira classe, o que permite passá-las como parâmetros.";

        int indiceCurioJS = escolherIndice.nextInt(curioJS.length);
        this.setCuriosidadeEscolhida(curioJS[indiceCurioJS]);
    }
    public String getCuriosidadeEscolhida(){
        return curiosidadeEscolhida;
    }

    public void setCuriosidadeEscolhida(String curiosidade){
        this.curiosidadeEscolhida = curiosidade;
    }




}
