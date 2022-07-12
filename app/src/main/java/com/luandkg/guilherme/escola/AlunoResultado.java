package com.luandkg.guilherme.escola;

import com.luandkg.guilherme.utils.Matematica;

import java.util.ArrayList;

public class AlunoResultado {

    private String mID;
    private String mTurma;
    private String mNome;

    private int mAtividades;
    private int mRealizadas;
    private String mNotaFinal;
    private double mNotaFinalDouble;

    private ArrayList<AtividadeRealizada> mAtividadesLista;

    public AlunoResultado(String eID, String eTurma, String eNome) {
        mID = eID;

        mTurma = eTurma;
        mNome = eNome;
        mAtividades = 0;
        mRealizadas = 0;

        mNotaFinal = "";
        mNotaFinalDouble = 0.0;

        mAtividadesLista = new ArrayList<AtividadeRealizada>();
    }

    public String getID() {
        return mID;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }

    public void avaliar(String eData, String eArquivo, String eDataEntregue, boolean realizada) {
        mAtividades += 1;
        if (realizada) {
            mRealizadas += 1;
        }

        mAtividadesLista.add(new AtividadeRealizada(eData, eArquivo, eDataEntregue, realizada));
    }

    public ArrayList<AtividadeRealizada> getAtividadesRealizadas() {
        return mAtividadesLista;
    }

    public boolean temAtividadeSim(String eData) {
        boolean ret = false;

        for (AtividadeRealizada atv : mAtividadesLista) {
            if (atv.getData().contentEquals(eData)) {
                if (atv.getStatus()) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }


    public int getAtividades() {
        return mAtividades;
    }

    public int getRealizadas() {
        return mRealizadas;
    }

    public void calcular(double maximo) {

        double tx = (double) maximo / (double) mAtividades;
        double vl = (double) mRealizadas * tx;

        boolean temAtrasadas = false;
        int atrasadas_quantidade = 0;
        String tem = "NAO";

        for (AtividadeRealizada aa : mAtividadesLista) {
            if (aa.getStatus()) {
                if (aa.isAtrasada()) {
                    temAtrasadas = true;
                    atrasadas_quantidade += 1;
                    tem = "SIM";
                }
            }
        }

        double va = (double)atrasadas_quantidade * (tx/4.0);

        String s1 = Matematica.getNumeroReal2C(String.valueOf((vl)).replace(".", ","));
        String s2 = Matematica.getNumeroReal2C(String.valueOf((vl - va)).replace(".", ","));


        System.out.println("Aluno : " + mNome);
        System.out.println("\t - Nota : " + s1);
        System.out.println("\t - Tem atrasadas : " + tem);
        if (temAtrasadas) {
            System.out.println("\t - Atrasadas : " + atrasadas_quantidade);
            System.out.println("\t - Descontar : " + String.valueOf(va).replace(".", ","));
            System.out.println("\t - Nova Nota : " + s2);
        }

        mNotaFinal = Matematica.getNumeroReal2C(String.valueOf((vl)).replace(".", ","));
        mNotaFinalDouble = vl;
    }

    public String getNotaFinal() {
        return mNotaFinal;
    }

    public double getNotaFinalDouble() {
        return mNotaFinalDouble;
    }


    public String getFrase() {
        String v = String.valueOf(getRealizadas() + " de " + getAtividades());
        return v;
    }

}
