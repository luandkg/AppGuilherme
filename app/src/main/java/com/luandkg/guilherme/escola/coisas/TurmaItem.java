package com.luandkg.guilherme.escola.coisas;

import com.luandkg.guilherme.utils.tempo.TempoEstampa;

import java.util.ArrayList;

public class TurmaItem {

    private String mDiaSemana;
    private String mNome;
    private String mStatus;
    private int mInicio;
    private int mFim;
    private String mHorarioInicio;
    private String mHorarioFim;
    private String mTipo;

    private boolean isSimples;
    private boolean isDuplo;
    private boolean isTriplo;

    private String mSala;
    private boolean mAvisado;


    public TurmaItem(String eDiaSemana, String eNome, String eTipo, TempoEstampa entrada, TempoEstampa saida, int aulas) {
        mDiaSemana = eDiaSemana;
        mStatus = "NAO";
        mTipo = eTipo;
        mNome = eNome;
        isSimples = false;
        isDuplo = false;
        isTriplo = false;
        mSala = "";

        mInicio = entrada.getValor();
        mFim = saida.getValor();

        String eh = String.valueOf(entrada.getHora());
        if (eh.length() == 1) {
            eh = "0" + eh;
        }

        String em = String.valueOf(entrada.getMinuto());
        if (em.length() == 1) {
            em = "0" + em;
        }

        String sh = String.valueOf(saida.getHora());
        if (sh.length() == 1) {
            sh = "0" + sh;
        }

        String sm = String.valueOf(saida.getMinuto());
        if (sm.length() == 1) {
            sm = "0" + sm;
        }

        if (aulas == 1) {
            isSimples = true;
        } else if (aulas == 2) {
            isDuplo = true;
        } else if (aulas == 3) {
            isTriplo = true;
        }

        mHorarioInicio = eh + ":" + em;
        mHorarioFim = sh + ":" + sm;
        mAvisado = false;

    }

    public TurmaItem(String eDiaSemana, String eNome, String eTipo, TempoEstampa entrada, TempoEstampa saida, int aulas, String eSala) {
        mDiaSemana = eDiaSemana;
        mStatus = "NAO";
        mTipo = eTipo;
        mNome = eNome;
        isSimples = false;
        isDuplo = false;
        isTriplo = false;
        mSala = eSala;

        mInicio = entrada.getValor();
        mFim = saida.getValor();

        String eh = String.valueOf(entrada.getHora());
        if (eh.length() == 1) {
            eh = "0" + eh;
        }

        String em = String.valueOf(entrada.getMinuto());
        if (em.length() == 1) {
            em = "0" + em;
        }

        String sh = String.valueOf(saida.getHora());
        if (sh.length() == 1) {
            sh = "0" + sh;
        }

        String sm = String.valueOf(saida.getMinuto());
        if (sm.length() == 1) {
            sm = "0" + sm;
        }

        if (aulas == 1) {
            isSimples = true;
        } else if (aulas == 2) {
            isDuplo = true;
        } else if (aulas == 3) {
            isTriplo = true;
        }

        mHorarioInicio = eh + ":" + em;
        mHorarioFim = sh + ":" + sm;
        mAvisado = false;


    }


    public void avisar() {
        mAvisado = true;
    }

    public boolean foiAvisado() {
        return mAvisado;
    }

    public void desavisar() {
        mAvisado = false;
    }

    public boolean isDupla() {
        return isDuplo;
    }

    public boolean isTripla(){return isTriplo;}

    public String getStatus() {
        return mStatus;
    }

    public String getTipo() {
        return mTipo;
    }

    public String getNome() {
        return mNome;
    }

    public String getDiaDaSemana() {
        return mDiaSemana;
    }

    public String getFaixa() {
        return mNome + " - " + mHorarioInicio + " :: " + mHorarioFim;
    }

    public int getInicio() {
        return mInicio;
    }

    public int getFim() {
        return mFim;
    }

    public String getSala() {
        return mSala;
    }

    public String getTempo() {
        if (isDuplo) {
            return mHorarioInicio + " :: " + mHorarioFim;
        } else {
            return mHorarioInicio + " :: " + mHorarioFim;
        }
    }

    public static ArrayList<TurmaItem> filtrarTurmas(String eDiaDaSemana, ArrayList<TurmaItem> turmas) {
        ArrayList<TurmaItem> filtro = new ArrayList<TurmaItem>();
        for (TurmaItem ti : turmas) {
            if (ti.getDiaDaSemana().contentEquals(eDiaDaSemana)) {
                filtro.add(ti);
            }
        }
        return filtro;
    }

    public boolean isDentro(int eTempo) {

        boolean ret = false;

        if (eTempo >= getInicio() && eTempo < getFim()) {
            ret = true;
        }

        return ret;
    }

    public boolean isQuantosMinutosAntes(int eTempo, int eAntes) {
        boolean ret = false;


        int tocar_antes_inicio = getInicio() - (eAntes * 60);

        if (eTempo >= tocar_antes_inicio && eTempo < getFim()) {
            ret = true;
        }


        return ret;

    }
}
