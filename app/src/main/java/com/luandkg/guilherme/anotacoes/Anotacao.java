package com.luandkg.guilherme.anotacoes;

public class Anotacao {

    private String mID;
    private String mMensagem;

    public Anotacao(String eID, String eMensagem) {
        mID = eID;
        mMensagem = eMensagem;
    }

    public String getID() {
        return mID;
    }

    public int getIDNumerico() {
        int v = 0;
        if (mID.length() > 0) {
            v = Integer.parseInt(mID);
        }
        return v;
    }

    public String getMensagem() {
        return mMensagem;
    }
}
