package com.luandkg.guilherme.escola.calendarios;

import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.tempo.DiaSemanal;


import java.util.ArrayList;

public class SEDF_22 {

    private ArrayList<Data> mDatas;

    public SEDF_22() {
        mDatas = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
    }

    public void mostrar() {
        Calendario.mostrarDatas(mDatas);
    }


    public ArrayList<Data> getDatas(){return mDatas;}


    public ArrayList<Data> getPrimeiro() {
        return Calendario.filtrar(mDatas, Calendario.parse("14/02/2022"), Calendario.parse("29/04/2022"));
    }

    public ArrayList<Data> getSegundo() {
        return Calendario.filtrar(mDatas, Calendario.parse("02/05/2022"), Calendario.parse("11/07/2022"));
    }

    public ArrayList<Data> getTerceiro() {
        return Calendario.filtrar(mDatas, Calendario.parse("29/07/2022"), Calendario.parse("07/10/2022"));
    }

    public ArrayList<Data> getQuarto() {
        return Calendario.filtrar(mDatas, Calendario.parse("10/10/2022"), Calendario.parse("22/12/2022"));
    }
}
