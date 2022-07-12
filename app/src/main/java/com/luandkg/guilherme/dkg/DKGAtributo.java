package com.luandkg.guilherme.dkg;


public class DKGAtributo {

    private String mNome;
    private String mValor;

    public DKGAtributo(String eNome) {
        mNome = eNome;
        mValor = "";
    }

    public DKGAtributo(String eNome, String eValor) {
        mNome = eNome;
        mValor = eValor;
    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public String getNome() {
        return mNome;
    }

    public void setValor(String eValor) {
        mValor = eValor;
    }

    public void setInteiro(int eValor) {
        mValor = String.valueOf(eValor);
    }

    public void setFloat(float eValor) {
        mValor = String.valueOf(eValor);
    }

    public void setDouble(double eValor) {
        mValor = String.valueOf(eValor);
    }

    public void setBool(boolean eValor) {
        mValor = String.valueOf(eValor);
    }

    public String getValor() {
        return mValor;
    }

    public String getValor(String valorPadrao) {
        if (getValor().length() == 0) {
            setValor(valorPadrao);
        } else {
            valorPadrao = getValor();
        }

        return valorPadrao;
    }

    public int getInteiro() {
        return Integer.parseInt(getValor());
    }

    public int getInteiro(int valorPadrao) {
        if (getValor().length() == 0) {
            setInteiro(valorPadrao);
        } else {
            valorPadrao = Integer.parseInt(getValor());
        }

        return valorPadrao;
    }

    public float getFloat() {
        return Float.parseFloat(getValor());
    }

    public float getFloat(float valorPadrao) {
        if (getValor().length() == 0) {
            setFloat(valorPadrao);
        } else {
            valorPadrao = Float.parseFloat(getValor());
        }

        return valorPadrao;
    }

    public double getDouble() {
        return Double.parseDouble(getValor());
    }

    public double getDouble(double valorPadrao) {
        if (getValor().length() == 0) {
            setDouble(valorPadrao);
        } else {
            valorPadrao = Double.parseDouble(getValor());
        }

        return valorPadrao;
    }


    public boolean getBool() {
        return Boolean.parseBoolean(getValor());
    }

    public boolean getBool(boolean valorPadrao) {
        if (getValor().length() == 0) {
            setBool(valorPadrao);
        } else {
            valorPadrao = Boolean.parseBoolean(getValor());
        }

        return valorPadrao;
    }

}
