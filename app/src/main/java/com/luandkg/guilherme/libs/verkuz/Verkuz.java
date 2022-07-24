package com.luandkg.guilherme.libs.verkuz;

import java.util.ArrayList;

public class Verkuz {

    private ArrayList<Commit> mBuilds;

    private String mAutor;
    private int mModo;
    private int mEstagio;


    public static final int SIMPLES = 1;
    public static final int COMPLEXO = 2;


    public static final int RELEASE = 1;
    public static final int TESTE = -1;

    public Verkuz() {
        mAutor = "";
        mModo = Verkuz.SIMPLES;
        mEstagio = Verkuz.TESTE;
        mBuilds = new ArrayList<Commit>();
    }

    public void setModo(int eModo) {
        mModo = eModo;
    }

    public void setAutor(String eAutor) {
        mAutor = eAutor;
    }

    public String getAutor() {
        return mAutor;
    }

    public int getEstagio() {
        return mEstagio;
    }

    public void setEstagio(int eEstagio) {
        mEstagio = eEstagio;
    }

    public void DEV(String eData, String eCommit) {
        mBuilds.add(new Commit(eData, eCommit));
    }


    public String getVersao() {

        String versao = "";

        if (mModo == COMPLEXO) {

            int maior = 0;
            int menor = 0;

            int quantidade = getBuilds();

            while (quantidade > 10) {
                quantidade -= 10;
                menor += 1;
            }

            while (menor > 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor + " #" + quantidade;

        } else if (mModo == SIMPLES) {

            int maior = 0;

            int menor = getBuilds();

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor;

        }

        return versao;
    }

    public String getVersaoCompleta() {

        String versao = "";

        if (mModo == COMPLEXO) {
            int maior = 0;
            int menor = 0;

            int quantidade = getBuilds();

            while (quantidade > 10) {
                quantidade -= 10;
                menor += 1;
            }

            while (menor > 10) {
                menor -= 10;
                maior += 1;
            }

            if (quantidade == 0) {
                versao = maior + "." + menor;
            } else {
                versao = maior + "." + menor + " #PATCH " + quantidade;
            }
        } else if (mModo == SIMPLES) {

            int maior = 0;

            int menor = getBuilds();

            while (menor >= 10) {
                menor -= 10;
                maior += 1;
            }

            versao = maior + "." + menor;


        }

        return versao;
    }

    public int getBuilds() {
        return mBuilds.size();
    }


    public String getUltima() {
        String ret = "";

        if (mBuilds.size() > 0) {
            ret = mBuilds.get(0).getData();
        }

        return ret;
    }

    public String getIniciado() {
        String ret = "";

        if (mBuilds.size() > 0) {
            ret = mBuilds.get(mBuilds.size() - 1).getData();
        }

        return ret;
    }


    public String getVersaoCompletaComAutor(){
        return getVersaoCompleta() + " - " + getAutor();
    }

    public String getData() {
        return mBuilds.get(0).getData();
    }

}