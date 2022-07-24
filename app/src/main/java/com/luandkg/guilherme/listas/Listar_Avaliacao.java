package com.luandkg.guilherme.listas;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.metodo_avaliativo.CoresDeAvaliacao;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.alunos.AlunoComNota;

import java.util.ArrayList;

public class Listar_Avaliacao extends BaseAdapter {

    private Context context;
    private ArrayList<AlunoComNota> mLista;
    private static LayoutInflater inflater = null;

    public Listar_Avaliacao(Context mainActivity, ArrayList<AlunoComNota> eLista) {
        context = mainActivity;
        mLista = eLista;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public ArrayList<AlunoComNota> getLista() {
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

        AlunoComNota aluno = mLista.get(position);

        if (rowView == null) {
            rowView = inflater.inflate(R.layout.item_avaliacao, parent, false);
        }

        TextView nome = (TextView) rowView.findViewById(R.id.item_avaliacao_nome);

        Button BTN_RUIM = (Button) rowView.findViewById(R.id.item_avaliacao_zero);
        Button BTN_EXCELENTE = (Button) rowView.findViewById(R.id.item_avaliacao_excelente);

        nome.setText(aluno.getNome());

        BTN_RUIM.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.NAO_AVALIADO));
        BTN_EXCELENTE.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.NAO_AVALIADO));


        mudar(BTN_RUIM, Avaliador.ATIVIDADE_NAO, aluno, BTN_RUIM, BTN_EXCELENTE);
        mudar(BTN_EXCELENTE, Avaliador.ATIVIDADE_SIM, aluno, BTN_RUIM, BTN_EXCELENTE);

        System.out.println("Nota de :: " + aluno.getNome() + " :: ( " + aluno.getNota() + " )");


        marcar(BTN_RUIM, BTN_EXCELENTE, aluno);


        return rowView;
    }

    public void marcar(Button b1, Button b2, AlunoComNota aluno) {

        if (aluno.getNota().contentEquals(Avaliador.ATIVIDADE_NAO)) {
            b1.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.RUIM));
        } else if (aluno.getNota().contentEquals(Avaliador.ATIVIDADE_SIM)) {
            b2.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));
        }

        if (aluno.getNota().contentEquals("SIM")) {
            if (aluno.isDeAtestado()) {
                b2.setText("AM");
                b2.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.BOM));
            } else {
                b2.setText("SIM");
                b2.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));
            }
        }

    }

    public void mudar(Button eBotao, String eValor, AlunoComNota aluno, Button BTN_RUIM, Button BTN_EXCELENTE) {

        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BTN_RUIM.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.NAO_AVALIADO));
                BTN_EXCELENTE.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.NAO_AVALIADO));


                aluno.mudarNota(eValor);

                marcar(BTN_RUIM, BTN_EXCELENTE, aluno);

                if (aluno.getNota().contentEquals("SIM")) {
                    if (aluno.isDeAtestado()) {
                        BTN_EXCELENTE.setText("AM");
                        BTN_EXCELENTE.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.BOM));
                    } else {
                        BTN_EXCELENTE.setText("SIM");
                        BTN_EXCELENTE.setBackgroundColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));
                    }
                }


            }
        });

    }

}