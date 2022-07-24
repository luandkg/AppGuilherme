package com.luandkg.guilherme.escola.alunos;

import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeRealizada;
import com.luandkg.guilherme.utils.Matematica;

import java.util.ArrayList;

public class AlunoPerfil {

    private String mID;

    private String mNome;
    private String mTurma;



    private int mAtividades;
    private int mRealizadas;
    private String mNotaFinal;
    private double mNotaFinalDouble;

    private ArrayList<AtividadeRealizada> mAtividadesLista;

    public AlunoPerfil() {
        mID = "";
        mNome = "";
        mTurma = "";


        mAtividades = 0;
        mRealizadas = 0;

        mNotaFinal = "";
        mNotaFinalDouble=0.0;

        mAtividadesLista = new ArrayList<AtividadeRealizada>();
    }

    public void set(String id, String eNome,String eTurma) {
        mID = id;
        mNome = eNome;
        mTurma=eTurma;
    }

    public String getNome() {
        return mNome;
    }





    public String getID() {
        return mID;
    }

    public String getTurma() {
        return mTurma;
    }


    public void avaliar(String eData, String eArquivo,String eDataEntregue,boolean realizada,boolean teve_atestado) {
        mAtividades += 1;
        if (realizada) {
            mRealizadas += 1;
        }

        mAtividadesLista.add(new AtividadeRealizada(eData,eArquivo, eDataEntregue,realizada,teve_atestado));
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

        mNotaFinal = Matematica.getNumeroReal2C(String.valueOf((vl)).replace(".", ","));
        mNotaFinalDouble=vl;
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
