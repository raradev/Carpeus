package com.app.anthony.pi4.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.anthony.pi4.R;
import com.app.anthony.pi4.br.edu.cairu.modelo.Avalicao;

import java.util.ArrayList;
import java.util.List;

public class AvalicaoAdapter extends ArrayAdapter<Avalicao> {

    private List<Avalicao> listAvalicao;
    private Context context;
    public AvalicaoAdapter(Context c, ArrayList<Avalicao> objcts) {
        super(c, 0, objcts);
        this.context = c;
        this.listAvalicao = objcts;
    }

    @Override
    public View getView(int position, @Nullable View convertView, ViewGroup parent) {
        View view = null;
        if (listAvalicao != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_avalicao,parent,false);
            TextView txtViewNome = (TextView) view.findViewById(R.id.txtViewNome);
            TextView txtViewQualificacao = (TextView) view.findViewById(R.id.txtViewQualificacao);
            TextView txtViewDescricao = (TextView) view.findViewById(R.id.txtViewDescricao);

            Avalicao avalicao = listAvalicao.get(position);

            txtViewNome.setText(avalicao.getNome());
            txtViewQualificacao.setText(avalicao.getQualificacao());
            txtViewDescricao.setText(avalicao.getDescricao());


        }
        return view;
    }
}
