package com.luandkg.guilherme.horario;

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
