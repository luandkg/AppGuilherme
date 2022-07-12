package com.luandkg.guilherme.escola.alunos;

import com.luandkg.guilherme.utils.tempo.Calendario;
import com.luandkg.guilherme.utils.tempo.Tempo;

public class AlunoComNota {

    private String mID;
    private String mTurma;
    private String mNome;
    private String mNota;
    private String mData;

    public AlunoComNota(String eID, String eTurma, String eNome, String eNota, String eData) {
        mID = eID;

        mTurma = eTurma;
        mNome = eNome;
        mNota = eNota;
        mData = eData;

    }

    public String getID() {
        return mID;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }


    public String getNota() {
        return mNota;
    }

    public void setNota(String e) {
        mNota = e;
    }

    public String getData() {
        return mData;
    }

    public void setData(String e) {
        mData = e;
    }

    public void mudarNota(String eNota) {

        if (!mNota.contentEquals(eNota)) {
            mNota = eNota;
            mData = Calendario.getADM();
        }

    }

}
