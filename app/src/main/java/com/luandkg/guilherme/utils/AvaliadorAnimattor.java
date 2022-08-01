package com.luandkg.guilherme.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.widget.ImageView;


import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.render.AvaliadorImagem;
import com.luandkg.guilherme.escola.tempo.SemanaContinuaCarregada;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AvaliadorAnimattor {

    private ImageView mImaginador;


    final Handler myHandler = new Handler();
    private Timer temporizador;
    private Context mContexto;

    private ArrayList<Bitmap> mImagens;
    private int index;
    private int quantidade;

    public AvaliadorAnimattor(Context eContext, ImageView eImaginador) {
        mContexto = eContext;
        mImaginador = eImaginador;

        mImagens = new ArrayList<Bitmap>();
        index = 0;
        quantidade = 0;
    }


    public void run(int presentes_porcentagem, int recuperacao_porcentagem, ArrayList<SemanaContinuaCarregada> mSemanas, ArrayList<AlunoResultado> mAlunos) {


        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 10));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 20));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 40));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 60));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 80));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 100));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 120));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 140));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 160));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 180));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 200));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 220));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 240));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 260));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 280));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 300));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 320));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 340));
        mImagens.add(AvaliadorImagem.onFluxoCom(presentes_porcentagem, recuperacao_porcentagem, mSemanas, mAlunos, 360));


        index = 0;
        quantidade = mImagens.size() - 1;

        temporizador = new Timer();
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 0, 200);

    }

    public Runnable myRunnable = new Runnable() {
        public void run() {

            index += 1;
            if (index >= quantidade) {
                index = 0;
            }

            mImaginador.setImageBitmap(mImagens.get(index));

        }
    };


}