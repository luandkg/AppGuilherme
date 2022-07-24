package com.luandkg.guilherme.escola.alunos;

import com.luandkg.guilherme.libs.tempo.Calendario;

public class AlunoComNota {

    private String mID;
    private String mTurma;
    private String mNome;
    private String mNota;
    private String mData;
    private String mAtestado;

    public AlunoComNota(String eID, String eTurma, String eNome, String eNota, String eAtestado, String eData) {
        mID = eID;

        mTurma = eTurma;
        mNome = eNome;
        mNota = eNota;
        mData = eData;
        mAtestado = eAtestado;

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

    public void setAtestado(String e) {
        mAtestado = e;
    }

    public String getAtestado() {
        return mAtestado;
    }


    public String getData() {
        return mData;
    }

    public void setData(String e) {
        mData = e;
    }

    public void mudarNota(String eNota) {

        if (mNota.contentEquals("SIM") && eNota.contentEquals("SIM")) {
            if (mAtestado.contentEquals("SIM")) {
                mAtestado = "NAO";
            } else {
                mAtestado = "SIM";
            }
        }

        if (!mNota.contentEquals(eNota)) {
            mNota = eNota;
            mData = Calendario.getADM();
        }


    }

    public boolean isDeAtestado() {
        return mAtestado.contentEquals("SIM");
    }

}
