package com.luandkg.guilherme.escola.organizacao;

import com.luandkg.guilherme.libs.tempo.TempoEstampa;

import java.util.ArrayList;

public class AtividadeEspecial {

    private String mDiaSemana;

    private String mSigla;
    private String mNome;
    private String mStatus;
    private int mInicio;
    private int mFim;
    private String mHorarioInicio;
    private String mHorarioFim;
    private String mTipo;
    private String mEstouIndo;

    private boolean mAvisado;
    private boolean mMostrarIndo;

    public AtividadeEspecial(String eDiaSemana, String eSigla, String eNome, String eTipo, TempoEstampa entrada, TempoEstampa saida) {
        mDiaSemana = eDiaSemana;
        mStatus = "NAO";
        mTipo = eTipo;
        mSigla = eSigla;
        mNome = eNome;

        mInicio = entrada.getValor();
        mFim = saida.getValor();

        mEstouIndo = "";

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


        mHorarioInicio = eh + ":" + em;
        mHorarioFim = sh + ":" + sm;
        mAvisado = false;
        mMostrarIndo = false;

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


    public String getStatus() {
        return mStatus;
    }

    public String getTipo() {
        return mTipo;
    }

    public String getNome() {
        return mNome;
    }

    public String getEstouIndo() {
        return mEstouIndo;
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

    public String getSigla() {
        return mSigla;
    }

    public String getTempo() {
        return mHorarioInicio + " :: " + mHorarioFim;
    }

    public static ArrayList<AtividadeEspecial> filtrar(String eDiaDaSemana, ArrayList<AtividadeEspecial> turmas) {
        ArrayList<AtividadeEspecial> filtro = new ArrayList<AtividadeEspecial>();
        for (AtividadeEspecial ti : turmas) {
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

    public boolean isDentroIndo(int eTempo) {

        boolean ret = false;

        if (eTempo >= getIndo_Inicio() && eTempo < getIndo_Fim()) {
            ret = true;
        }

        return ret;
    }

    public int getIndo_Inicio() {
        int v = getInicio() - (30 * 60);
        return v;
    }

    public int getIndo_Fim() {
        int v = getInicio() - (0 * 60);
        return v;
    }

    public void mostrarIndo(String eEstouIndo) {
        mMostrarIndo = true;
        mEstouIndo=eEstouIndo;
    }

    public boolean isMostrarIndo() {
        return mMostrarIndo;
    }
}
