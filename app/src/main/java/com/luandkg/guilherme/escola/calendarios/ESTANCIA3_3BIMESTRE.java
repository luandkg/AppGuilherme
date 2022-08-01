package com.luandkg.guilherme.escola.calendarios;


import com.luandkg.guilherme.escola.tempo.SemanaContinua;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.tempo.DiaSemanal;

import java.util.ArrayList;

public class ESTANCIA3_3BIMESTRE {

    public static ArrayList<Data> getBimestre() {
        ArrayList<Data> TODAS_DATAS = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> SEGUNDO_BIMESTRE = Calendario.filtrar(TODAS_DATAS, Calendario.parse("29/07/2022"), Calendario.parse("07/10/2022"));
        return SEGUNDO_BIMESTRE;
    }

    public static ArrayList<SemanaContinua> getSemanas() {

        ArrayList<Data> TODAS_DATAS = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
        ArrayList<Data> TERCEIRO_BIMESTRE_DATAS = Calendario.filtrar(TODAS_DATAS, Calendario.parse("29/07/2022"), Calendario.parse("07/10/2022"));


        ArrayList<SemanaContinua> TERCEIRO_BIMESTRE_SEMANAS = new ArrayList<SemanaContinua>();

        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(1, "1", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("29/07/2022"), Calendario.parse("06/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(2, "2", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("07/08/2022"), Calendario.parse("13/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(3, "3", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("14/08/2022"), Calendario.parse("20/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(4, "4", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("21/08/2022"), Calendario.parse("27/08/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(5, "5", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("28/08/2022"), Calendario.parse("03/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(6, "6", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("04/08/2022"), Calendario.parse("10/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(7, "7", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("11/09/2022"), Calendario.parse("17/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(8, "8", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("18/09/2022"), Calendario.parse("24/09/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(9, "9", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("25/09/2022"), Calendario.parse("01/10/2022"))));
        TERCEIRO_BIMESTRE_SEMANAS.add(new SemanaContinua(10, "10", Calendario.filtrar(TERCEIRO_BIMESTRE_DATAS, Calendario.parse("02/10/2022"), Calendario.parse("07/10/2022"))));

        return TERCEIRO_BIMESTRE_SEMANAS;
    }

}
