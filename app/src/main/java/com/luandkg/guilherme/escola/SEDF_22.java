package com.luandkg.guilherme.escola;

import com.luandkg.guilherme.utils.tempo.Data;
import com.luandkg.guilherme.utils.tempo.DiaSemanal;
import com.luandkg.guilherme.utils.tempo.Tempo;


import java.util.ArrayList;

public class SEDF_22 {

    private ArrayList<Data> mDatas;

    public SEDF_22() {
        mDatas = Tempo.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    }

    public void mostrar() {
        Tempo.mostrarDatas(mDatas);
    }


    public ArrayList<Data> getDatas(){return mDatas;}


    public ArrayList<Data> getPrimeiro() {
        return Tempo.filtrar(mDatas, Tempo.parse("14/02/2022"), Tempo.parse("29/04/2022"));
    }

    public ArrayList<Data> getSegundo() {
        return Tempo.filtrar(mDatas, Tempo.parse("02/05/2022"), Tempo.parse("11/07/2022"));
    }

    public ArrayList<Data> getTerceiro() {
        return Tempo.filtrar(mDatas, Tempo.parse("29/07/2022"), Tempo.parse("07/10/2022"));
    }

    public ArrayList<Data> getQuarto() {
        return Tempo.filtrar(mDatas, Tempo.parse("10/10/2022"), Tempo.parse("22/12/2022"));
    }
}
