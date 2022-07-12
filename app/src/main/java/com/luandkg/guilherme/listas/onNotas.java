package com.luandkg.guilherme.listas;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.luandkg.guilherme.Avaliacao;
import com.luandkg.guilherme.activities.AlunoRelatorioActivity;
import com.luandkg.guilherme.activities.MomentoDeAvaliacao;
import com.luandkg.guilherme.escola.AlunoResultado;
import com.luandkg.guilherme.escola.CicloDeAvaliacao;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.Atividade;
import com.luandkg.guilherme.utils.tempo.Calendario;

import java.util.ArrayList;

public class onNotas extends BaseAdapter {

    private Context context;
    private ArrayList<AlunoResultado> mLista;
    private static LayoutInflater inflater = null;

    public onNotas(Context mainActivity, ArrayList<AlunoResultado> eLista) {
        context = mainActivity;
        mLista = eLista;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<AlunoResultado> getLista() {
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

        AlunoResultado aluno = mLista.get(position);

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_aluno_nota_final, parent, false);
        }


        TextView nome = (TextView) rowView.findViewById(R.id.item_aluno_nota_final_nome);
        Button nota = (Button) rowView.findViewById(R.id.item_aluno_nota_final_nota);

        nome.setText(aluno.getNome());
        nota.setText(aluno.getNotaFinal());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), AlunoRelatorioActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("AlunoID", aluno.getID());

                v.getContext().startActivity(intent);


            }
        });

        return rowView;
    }


}