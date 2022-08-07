package com.luandkg.guilherme;

import com.luandkg.guilherme.escola.anotacoes.Anotacao;

public class AvisoData {

    private int mDia;
    private int mMes;
    private Anotacao mAviso;

    public AvisoData(int eDia,int eMes,Anotacao eAviso){
        mDia=eDia;
        mMes=eMes;
        mAviso=eAviso;
    }

    public int getDia(){return mDia;}
    public int getMes(){return mMes;}
    public Anotacao getAviso(){return mAviso;}

}
