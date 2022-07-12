package com.luandkg.guilherme.escola.tempo;

public class ContandoData {

    private String mData;
    private int mValor;

    public ContandoData(String eData) {
        mData=eData;
        mValor=0;
    }

    public void aumentar(){
        mValor+=1;
    }

    public String getData(){return mData;}
    public int getValor(){return mValor;}

}
