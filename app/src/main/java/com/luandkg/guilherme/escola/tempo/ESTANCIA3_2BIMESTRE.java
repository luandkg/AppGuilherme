package com.luandkg.guilherme.escola.tempo;


import com.luandkg.guilherme.escola.tempo.SemanaContinua;
import com.luandkg.guilherme.utils.tempo.Calendario;
import com.luandkg.guilherme.utils.tempo.Data;
import com.luandkg.guilherme.utils.tempo.DiaSemanal;

import java.util.ArrayList;

public class ESTANCIA3_2BIMESTRE {

    public static ArrayList<Data> getBimestre() {
        ArrayList<Data> TODAS_DATAS = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> SEGUNDO_BIMESTRE = Calendario.filtrar(TODAS_DATAS, Calendario.parse("02/05/2022"), Calendario.parse("11/07/2022"));
        return SEGUNDO_BIMESTRE;
    }

    public static ArrayList<SemanaContinua> getSemanas() {

        ArrayList<Data> SEGUNDO_BIMESTRE = getBimestre();

        ArrayList<SemanaContinua> mSemanas = new ArrayList<SemanaContinua>();

        mSemanas.add(new SemanaContinua("1", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("02/05/2022"), Calendario.parse("07/05/2022"))));
        mSemanas.add(new SemanaContinua("2", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("08/05/2022"), Calendario.parse("14/05/2022"))));
        mSemanas.add(new SemanaContinua("3", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("15/05/2022"), Calendario.parse("21/05/2022"))));
        mSemanas.add(new SemanaContinua("4", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("22/05/2022"), Calendario.parse("28/05/2022"))));
        mSemanas.add(new SemanaContinua("5", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("29/05/2022"), Calendario.parse("04/06/2022"))));
        mSemanas.add(new SemanaContinua("6", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("05/06/2022"), Calendario.parse("11/06/2022"))));
        mSemanas.add(new SemanaContinua("7", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("12/06/2022"), Calendario.parse("18/06/2022"))));
        mSemanas.add(new SemanaContinua("8", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("19/06/2022"), Calendario.parse("25/06/2022"))));
        mSemanas.add(new SemanaContinua("9", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("26/06/2022"), Calendario.parse("02/07/2022"))));
        mSemanas.add(new SemanaContinua("10", Calendario.filtrar(SEGUNDO_BIMESTRE, Calendario.parse("03/07/2022"), Calendario.parse("11/07/2022"))));

        return mSemanas;
    }

}
