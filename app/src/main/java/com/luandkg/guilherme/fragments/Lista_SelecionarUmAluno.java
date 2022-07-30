package com.luandkg.guilherme.fragments;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.luandkg.guilherme.R;
import com.luandkg.guilherme.activities.AlunoRelatorioActivity;
import com.luandkg.guilherme.escola.alunos.AlunoPerfil;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.tempo.Hoje;
import com.luandkg.guilherme.escola.utils.Emblemador;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;

public class Lista_SelecionarUmAluno extends BaseAdapter {

    private Context mContext;
    private int mQuantidade;
    private ArrayList<AlunoPerfil> eLista;
    private ArrayList<Widget> mWidgets;

    private static LayoutInflater inflater = null;

    private boolean mTemSelecionado = false;
    private AlunoPerfil mSelecionado = null;

    public Lista_SelecionarUmAluno(Context eContext, ArrayList<AlunoPerfil> lista) {
        mContext = eContext;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        eLista = lista;
        mQuantidade = lista.size();

        mWidgets = new ArrayList<Widget>();
    }

    public boolean temSelecionado() {
        return mTemSelecionado;
    }

    public AlunoPerfil getSelecionado() {
        return mSelecionado;
    }

    @Override
    public int getCount() {
        return mQuantidade;
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


        AlunoPerfil alunoContinuo = eLista.get(position);

        Widget mWidget = new Widget(R.layout.item_atualizacao, inflater, parent);

        ImageView imagem = mWidget.getImageView(R.id.atualizacao_imagem);
        TextView nome = mWidget.getTextView(R.id.atualizacao_nome);
        TextView novidades = mWidget.getTextView(R.id.atualizacao_contador);
        TextView evento = mWidget.getTextView(R.id.atualizacao_status);
        TextView tempo = mWidget.getTextView(R.id.atualizacao_tempo);

        String s_nome = alunoContinuo.getNome();

        // s_nome += "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";

        if (s_nome.length() > 25) {
            s_nome = s_nome.substring(0, 25) + "...";
        }

        nome.setText(s_nome);

        String entregues = "Nenhuma atividade realizada !";
        if (alunoContinuo.getAtividadesRealizadas().size() == 1) {
            entregues = "1 atividade entregue !";
        } else if (alunoContinuo.getAtividadesRealizadas().size() > 1) {
            entregues = alunoContinuo.getAtividadesRealizadas().size() + " atividade entregue !";
        }

        evento.setText(entregues);


        String nao_existe = "#bdbdbd";
        String existe = "#f44336";
        String esta_hoje = "#ffca28";

        String HOJE_DIA = Calendario.getDiaAtual();
        //  String HOJE = Calendario.getADMComTracoInferior();


        String qual_cor = nao_existe;

        ArrayList<String> turmas_hoje = Hoje.getTurmas(HOJE_DIA);
        //turmas_hoje.add("7I");

        if (turmas_hoje.contains(String.valueOf(alunoContinuo.getTurma()))) {
            qual_cor = esta_hoje;
        }

        //if (Hoje.estava_presente(HOJE, String.valueOf(alunoContinuo.getID()))) {
        //  qual_cor = esta_hoje;
        // }

        imagem.setImageBitmap(Emblemador.criarAluno(qual_cor, alunoContinuo.getTurma()));


        // String mensao = Mensionador.getMensao(alunoContinuo.getAcumuladoContinuidadeComRecuperacao());

        // novidades.setBackgroundColor(Color.parseColor(Mensionador.getCorDaMensao(mensao)));

        novidades.setText(String.valueOf(alunoContinuo.getNotaFinal()));

        String ultima = "";

        if (alunoContinuo.getAtividadesRealizadas().size() > 0) {
            ultima = alunoContinuo.getAtividadesRealizadas().get(alunoContinuo.getAtividadesRealizadas().size() - 1).getDataEntregue();
        }

        if (ultima.length() == 10) {
            tempo.setText(Calendario.formate_dia_mes(Data.toData(ultima).getTempo()));
        } else {
            tempo.setText("--");
        }

        mWidget.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (Widget w : mWidgets) {
                    w.getView().setBackgroundColor(Color.parseColor("#fafafa"));
                }

                for (AlunoPerfil a : eLista) {
                    a.desmarcar();
                }

                mWidget.getView().setBackgroundColor(Color.parseColor("#4caf50"));

                alunoContinuo.marcar();

                mTemSelecionado = true;
                mSelecionado = alunoContinuo;

            }
        });


        mWidget.setAuto();

        if (!mWidgets.contains(mWidget)) {
            mWidgets.add(mWidget);
        }

        return mWidget.getView();
    }

}
