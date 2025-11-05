package com.example.barra_navegacao;


import android.app.Activity;
import android.content.Intent;

import com.example.aulatec.AreaAluno;
import com.example.aulatec.Home;
import com.example.aulatec.ListaAulas;
import com.example.aulatec.ListaProf;
import com.example.aulatec.R;
import com.example.recomendacoes.EscolherRecomendacoes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class BarraDeNavegacao {

    public static void configurarNavegacao(Activity tela, BottomNavigationView barra, int idModulo, String turma){

        barra.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            //Esse (not)instanceof serve pra caso você já esteja na activity do item que você clicar não acontecer nada

            if(id == R.id.nav_home && !(tela instanceof Home)){
                irPara(tela, Home.class, idModulo, turma);
                return true;
            }else if(id == R.id.nav_aulas && !(tela instanceof ListaAulas)){
                irPara(tela, ListaAulas.class, idModulo, turma);
                return true;
            }else if(id == R.id.nav_emails && !(tela instanceof  ListaProf)){
                irPara(tela, ListaProf.class, idModulo, turma);
                return true;
            }else if(id == R.id.nav_recomendacoes && !(tela instanceof EscolherRecomendacoes)){
                irPara(tela, EscolherRecomendacoes.class, idModulo, turma);
                return true;
            }else if(id == R.id.nav_aluno && !(tela instanceof AreaAluno)){
                irPara(tela, AreaAluno.class, idModulo, turma);
            }

            return false;
        });
    }

    private static void irPara(Activity origem, Class<?> destino, int idModulo, String turma){
        Intent intent = new Intent(origem, destino);
        intent.putExtra("id_modulo", idModulo);
        intent.putExtra("turma", turma);
        origem.startActivity(intent);
        origem.overridePendingTransition(0, 0);
        origem.finish();
    }

}
