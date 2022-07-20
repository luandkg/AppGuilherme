package com.luandkg.guilherme.libs.tempo;

import java.util.ArrayList;

public class HorarioTurma {

    private String mTurma;
    private ArrayList<DiaSemanal> mDias;
    private DiaSemanal mPD;

    public HorarioTurma(String eTurma) {
        mTurma = eTurma;
        mDias = new ArrayList<DiaSemanal>();
    }

    public HorarioTurma(String eTurma, DiaSemanal d1) {
        mTurma = eTurma;
        mDias = new ArrayList<DiaSemanal>();
        mDias.add(d1);
    }

    public HorarioTurma(String eTurma, DiaSemanal d1, DiaSemanal d2) {
        mTurma = eTurma;
        mDias = new ArrayList<DiaSemanal>();
        mDias.add(d1);
        mDias.add(d2);
    }

    public HorarioTurma(String eTurma, DiaSemanal d1, DiaSemanal d2, DiaSemanal d3) {
        mTurma = eTurma;
        mDias = new ArrayList<DiaSemanal>();
        mDias.add(d1);
        mDias.add(d2);
        mDias.add(d3);
    }

    public void adicionarDia(DiaSemanal eDiaSemanal) {
        mDias.add(eDiaSemanal);
    }

    public ArrayList<DiaSemanal> getDias() {
        return mDias;
    }

    public String getTurma() {
        return mTurma;
    }

    public void setPD(DiaSemanal ePD) {
        mPD = ePD;
    }

    public DiaSemanal getPD() {
        return mPD;
    }

    public boolean temAula(DiaSemanal eDiaSemanal) {
        boolean ret = false;

        for (DiaSemanal e : mDias) {
            if (e == eDiaSemanal) {
                ret = true;
                break;
            }
        }

        return ret;
    }
}
