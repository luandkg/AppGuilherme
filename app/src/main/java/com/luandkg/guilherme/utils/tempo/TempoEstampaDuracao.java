package com.luandkg.guilherme.utils.tempo;


public class TempoEstampaDuracao {

    private TempoEstampa mEntrada;
    private TempoEstampa mSaida;

    public TempoEstampaDuracao(TempoEstampa eEntrada, TempoEstampa eSaida) {
        mEntrada = eEntrada;
        mSaida = eSaida;
    }

    public TempoEstampa getEntrada() {
        return mEntrada;
    }

    public TempoEstampa getSaida() {
        return mSaida;
    }

}
