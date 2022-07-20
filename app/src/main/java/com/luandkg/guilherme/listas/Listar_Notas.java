package com.luandkg.guilherme.listas;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.luandkg.guilherme.activities.AlunoRelatorioActivity;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.R;

import java.util.ArrayList;

public class Listar_Notas extends BaseAdapter {

    private Context context;
    private ArrayList<AlunoResultado> mLista;
    private static LayoutInflater inflater = null;

    public Listar_Notas(Context mainActivity, ArrayList<AlunoResultado> eLista) {
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

        Button nota_total = (Button) rowView.findViewById(R.id.item_aluno_nota_final_atividades);
        Button nota_atrasadas = (Button) rowView.findViewById(R.id.item_aluno_nota_final_atrasadas);
        Button nota_final = (Button) rowView.findViewById(R.id.item_aluno_nota_final_nota);



        nome.setText(aluno.getNome());

        nota_total.setText(aluno.getNotaCompleta());
        nota_atrasadas.setText(aluno.getNotaAtrasadas());
        nota_final.setText(aluno.getNotaFinal());

        if (aluno.getNotaFinalDouble()>=5){
            nota_final.setBackgroundColor(Color.parseColor("#43a047"));
        }else{
            nota_final.setBackgroundColor(Color.parseColor("#f4511e"));
        }


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