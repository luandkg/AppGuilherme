package com.luandkg.guilherme.escola.tempo;


import com.luandkg.guilherme.libs.tempo.TempoEstampa;
import com.luandkg.guilherme.libs.tempo.TempoEstampaDuracao;

public class Horario {

    public static TempoEstampa entrar(TempoEstampaDuracao horario) {
        return horario.getEntrada();
    }

    public static TempoEstampa sair(TempoEstampaDuracao horario) {
        return horario.getSaida();
    }

    public static TempoEstampa auto_mais(TempoEstampa agora, int maisMinutos) {

        int hora = agora.getHora();
        int min = agora.getMinuto() + maisMinutos;

        if (min >= 60) {
            hora += 1;
            min = min - 60;
        }

        if (hora > 23) {
            hora = 0;
        }

        agora.set(hora, min);
        return agora;
    }

}
