package com.example.aulatec;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Objects;

public class TarefaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Tarefa> lista;

    public TarefaAdapter(Context context, ArrayList<Tarefa> lista) {
        this.context = context;
        this.lista = lista;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position){
        return lista.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        View view = LayoutInflater.from(context).inflate(R.layout.item_tarefa, parent, false);

        TextView titulo = view.findViewById(R.id.txtTituloTarefa);
        TextView descricao = view.findViewById(R.id.txtDescricaoTarefa);
        TextView status = view.findViewById(R.id.txtStatusTarefa);
        TextView prazo = view.findViewById(R.id.txtdiaPrazoTarefa);

        Tarefa tarefa = lista.get(position);

        titulo.setText(tarefa.getTitulo());
        descricao.setText(tarefa.getDescricao());
        prazo.setText(tarefa.getPrazo());

        if(tarefa.getStatus().equalsIgnoreCase("CONCLUIDA")) {
            status.setText("CONCLUÍDA");
            status.setBackgroundColor(Color.parseColor("#2E7D32"));
        }else{
            status.setText("PENDENTE");
            status.setBackgroundColor(Color.parseColor("#F9A825"));
        }

        return view;

    }

}
