package com.luandkg.guilherme.listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.luandkg.guilherme.Avaliacao;
import com.luandkg.guilherme.escola.metodo_avaliativo.CicloDeAvaliacao;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.metodo_avaliativo.Atividade;
import com.luandkg.guilherme.utils.tempo.Calendario;

import java.util.ArrayList;

public class Listar_AtividadesDaTurma extends BaseAdapter {

    private Context context;
    private ArrayList<Atividade> mLista;
    private static LayoutInflater inflater = null;

    public Listar_AtividadesDaTurma(Context mainActivity, ArrayList<Atividade> eLista) {
        context = mainActivity;
        mLista = eLista;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<Atividade> getLista() {
        return mLista;
    }


    @Override
    public int getCount() {
        return mLista.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        View rowView = convertView;

        Atividade atividade = mLista.get(position);

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_atividade, parent, false);
        }

        ImageView imagem = (ImageView) rowView.findViewById(R.id.item_atividade_imagem);

        TextView nome = (TextView) rowView.findViewById(R.id.item_atividade_nome);
        TextView status = (TextView) rowView.findViewById(R.id.item_atividade_status);
        TextView tempo = (TextView) rowView.findViewById(R.id.item_atividade_tempo);

        imagem.setImageBitmap(CicloDeAvaliacao.visualizar(atividade.getFizeram(),atividade.getTotal()));
        nome.setText(atividade.getNome());
        status.setText(atividade.getStatus());


        String faixa_tempo = Calendario.toFaixaTemporal(atividade.getTempo());

        tempo.setText(faixa_tempo);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Avaliacao.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("TURMA", atividade.getTurma());
                intent.putExtra("ATIVIDADE", atividade.getArquivo());

                v.getContext().startActivity(intent);

            }
        });

        return rowView;
    }


}