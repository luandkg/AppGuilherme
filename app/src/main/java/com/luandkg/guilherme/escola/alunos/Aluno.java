package com.luandkg.guilherme.escola.alunos;

public class Aluno {

    private String mID;
    private String mTurma;
    private String mNome;
    private String mVisibilidade;

    public Aluno(String eID,String eTurma , String eNome , String eVisibilidade) {
        mID = eID;

        mTurma = eTurma;
        mNome = eNome;
        mVisibilidade = eVisibilidade;
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



    public String getVisibilidade() {
        return mVisibilidade;
    }



    public void mudarVisibilidade() {

        if (mVisibilidade.contentEquals("SIM")) {
            mVisibilidade = "NAO";
        } else if (mVisibilidade.contentEquals("NAO")) {
            mVisibilidade = "SIM";
        }

    }
}
