package com.luandkg.guilherme.utils.tempo;

public class TempoEstampa {

    private int mHora;
    private int mMinuto;

    public TempoEstampa(int eHora, int eMinuto) {
        mHora = eHora;
        mMinuto = eMinuto;
    }

    public int getHora() {
        return mHora;
    }

    public int getMinuto() {
        return mMinuto;
    }

    public int getValor() {
        return (mHora * 60 * 60) + (mMinuto * 60);
    }

    public int getAntes(int aMin) {
        return getValor() - (aMin * 60);
    }
    public int getDepois(int aMin) {
        return getValor() + (aMin * 60);
    }

    public void set(int eHora, int eMinuto) {
        mHora = eHora;
        mMinuto = eMinuto;
    }

}
