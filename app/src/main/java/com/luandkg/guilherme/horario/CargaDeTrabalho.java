package com.luandkg.guilherme.horario;

import com.luandkg.guilherme.utils.tempo.Calendario;

public class CargaDeTrabalho {

    private String mDia;
    private TempoEstampa mInicio;
    private TempoEstampa mFim;

    public CargaDeTrabalho(String eDia, TempoEstampa eInicio, TempoEstampa eFim) {
        mDia = eDia;
        mInicio = eInicio;
        mFim = eFim;
    }

    public String getDia() {
        return mDia;
    }

    public TempoEstampa getInicio() {
        return mInicio;
    }

    public TempoEstampa getFim() {
        return mFim;
    }

    public boolean isDentro(String eDia, int tempo) {

        boolean ret = false;

        if (Calendario.isIgual(mDia, eDia)) {
            if (tempo >= mInicio.getValor() && tempo < mFim.getValor()) {
                ret = true;
            }
        }

        return ret;
    }

}
