package com.luandkg.guilherme.escola;


import com.luandkg.guilherme.utils.tempo.Data;
import com.luandkg.guilherme.utils.tempo.DiaSemanal;
import com.luandkg.guilherme.utils.tempo.Tempo;

import java.util.ArrayList;

public class ESTANCIA3_2BIMESTRE {

    public static ArrayList<Data> getBimestre() {
        ArrayList<Data> TODAS_DATAS = Tempo.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> SEGUNDO_BIMESTRE = Tempo.filtrar(TODAS_DATAS, Tempo.parse("02/05/2022"), Tempo.parse("11/07/2022"));
        return SEGUNDO_BIMESTRE;
    }

    public static ArrayList<SemanaContinua> getSemanas() {

        ArrayList<Data> SEGUNDO_BIMESTRE = getBimestre();

        ArrayList<SemanaContinua> mSemanas = new ArrayList<SemanaContinua>();

        mSemanas.add(new SemanaContinua("1", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("02/05/2022"), Tempo.parse("07/05/2022"))));
        mSemanas.add(new SemanaContinua("2", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("08/05/2022"), Tempo.parse("14/05/2022"))));
        mSemanas.add(new SemanaContinua("3", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("15/05/2022"), Tempo.parse("21/05/2022"))));
        mSemanas.add(new SemanaContinua("4", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("22/05/2022"), Tempo.parse("28/05/2022"))));
        mSemanas.add(new SemanaContinua("5", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("29/05/2022"), Tempo.parse("04/06/2022"))));
        mSemanas.add(new SemanaContinua("6", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("05/06/2022"), Tempo.parse("11/06/2022"))));
        mSemanas.add(new SemanaContinua("7", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("12/06/2022"), Tempo.parse("18/06/2022"))));
        mSemanas.add(new SemanaContinua("8", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("19/06/2022"), Tempo.parse("25/06/2022"))));
        mSemanas.add(new SemanaContinua("9", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("26/06/2022"), Tempo.parse("02/07/2022"))));
        mSemanas.add(new SemanaContinua("10", Tempo.filtrar(SEGUNDO_BIMESTRE, Tempo.parse("03/07/2022"), Tempo.parse("11/07/2022"))));

        return mSemanas;
    }

}
