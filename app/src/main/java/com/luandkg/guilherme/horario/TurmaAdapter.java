package com.luandkg.guilherme.horario;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.luandkg.guilherme.R;

import java.util.ArrayList;

public class TurmaAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<TurmaItem> mLista;
    private static LayoutInflater inflater = null;
    private Professor mProfessor;

    public TurmaAdapter(Context mainActivity, ArrayList<TurmaItem> eLista, Professor eProfessor) {
        context = mainActivity;
        mLista = eLista;
        mProfessor = eProfessor;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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


        TurmaItemStruct mTurmaItemStruct = new TurmaItemStruct();
        View rowView = convertView;

        TurmaItem eSelecionado = mLista.get(position);

        if (mProfessor.getSigla().contentEquals("GG")) {
            if (rowView == null) {
                rowView = inflater.inflate(R.layout.item_turma_gg, parent, false);
            }

            mTurmaItemStruct.nome = (TextView) rowView.findViewById(R.id.gg_turma_nome);
            mTurmaItemStruct.status = (Button) rowView.findViewById(R.id.gg_turma_status);
            mTurmaItemStruct.duplo = (Button) rowView.findViewById(R.id.gg_turma_duplo);
            mTurmaItemStruct.sala = (Button) rowView.findViewById(R.id.gg_sala);


        } else {

            if (rowView == null) {
                rowView = inflater.inflate(R.layout.item_turma, parent, false);
            }

            mTurmaItemStruct.nome = (TextView) rowView.findViewById(R.id.turma_nome);
            mTurmaItemStruct.status = (Button) rowView.findViewById(R.id.turma_status);
            mTurmaItemStruct.duplo = (Button) rowView.findViewById(R.id.turma_duplo);

        }


        mTurmaItemStruct.duplo.setVisibility(View.INVISIBLE);

        mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#37474f"));

        if (eSelecionado.getNome().contentEquals("IN")) {

        } else {
            mTurmaItemStruct.status.setText(eSelecionado.getNome());
        }

        mTurmaItemStruct.nome.setText(eSelecionado.getTempo());

        if (eSelecionado.isDupla()) {
            mTurmaItemStruct.duplo.setVisibility(View.VISIBLE);
            mTurmaItemStruct.duplo.setBackgroundColor(Color.parseColor("#039be5"));
        }

        if (eSelecionado.isTripla()) {
            mTurmaItemStruct.duplo.setVisibility(View.VISIBLE);
            mTurmaItemStruct.duplo.setBackgroundColor(PaletaDeCores.VERMELHO);
        }


        if (mProfessor.getSigla().contentEquals("LUAN")) {

            if (eSelecionado.getNome().contains("9")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else if (eSelecionado.getNome().contains("8")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
            } else if (eSelecionado.getNome().contains("7")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#FDD835"));
            }

        } else if (mProfessor.getSigla().contentEquals("GG")) {

            if (eSelecionado.getTipo().contains("CN")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else if (eSelecionado.getTipo().contains("PD")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
            }

            mTurmaItemStruct.sala.setText(eSelecionado.getSala());
            mTurmaItemStruct.sala.setBackgroundColor(Color.parseColor("#ff8a65"));

            if (eSelecionado.getNome().contentEquals("I")) {
                mTurmaItemStruct.sala.setVisibility(View.INVISIBLE);
            }

        } else if (mProfessor.getSigla().contentEquals("FREITAS")) {

            if (eSelecionado.getTipo().contains("CN")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else if (eSelecionado.getTipo().contains("PD")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
            }

        } else if (mProfessor.getSigla().contentEquals("DANTAS")) {

            if (eSelecionado.getTipo().contains("CN")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#4CAF50"));
            } else if (eSelecionado.getTipo().contains("PD")) {
                mTurmaItemStruct.status.setBackgroundColor(Color.parseColor("#F44336"));
            }

        }


        return rowView;
    }


}