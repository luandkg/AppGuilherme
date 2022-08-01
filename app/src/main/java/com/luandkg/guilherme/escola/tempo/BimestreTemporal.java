package com.luandkg.guilherme.escola.tempo;

import com.luandkg.guilherme.escola.calendarios.SEDF_22;
import com.luandkg.guilherme.libs.tempo.Data;

import java.util.ArrayList;

public class BimestreTemporal {


    public static boolean temBimestre(String hoje, SEDF_22 calendario) {
        boolean ret = false;

        if (BimestreTemporal.estouNesse(hoje, calendario.getPrimeiro())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getSegundo())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getTerceiro())) {
            ret = true;
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getQuarto())) {
            ret = true;
        }

        return ret;
    }

    public static String getBimestreNome(String hoje, SEDF_22 calendario) {
        String ret = "0";

        if (BimestreTemporal.estouNesse(hoje, calendario.getPrimeiro())) {
            ret = "1";
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getSegundo())) {
            ret = "2";
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getTerceiro())) {
            ret = "3";
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getQuarto())) {
            ret = "4";
        }

        return ret;
    }

    public static ArrayList<Data> getBimestre(String hoje, SEDF_22 calendario) {
        ArrayList<Data> ret = new ArrayList<Data>();

        if (BimestreTemporal.estouNesse(hoje, calendario.getPrimeiro())) {
            ret = calendario.getPrimeiro();
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getSegundo())) {
            ret = calendario.getSegundo();
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getTerceiro())) {
            ret = calendario.getTerceiro();
        } else if (BimestreTemporal.estouNesse(hoje, calendario.getQuarto())) {
            ret = calendario.getQuarto();
        }

        return ret;
    }

    public static boolean estouNesse(String hoje, ArrayList<Data> datas) {
        boolean r = false;
        for (Data data : datas) {
            if (data.getTempo().replace("_", "/").contentEquals(hoje)) {
                r = true;
                break;
            }
        }
        return r;
    }

    public static int getID(String hoje, ArrayList<Data> datas) {

        int v = 0;

        for (Data data : datas) {
            if (data.getTempo().replace("_", "/").contentEquals(hoje)) {
                break;
            }
            v += 1;
        }

        if (v > datas.size()) {
            v = datas.size();
        }

        return v;
    }

    public static int getDiasParaAcabar(String data_corrente, ArrayList<Data> datas) {

        int v = getID(data_corrente, datas);

        int acabar = datas.size() - v;

        return acabar;
    }

    public static int getPorcentagem(String data_corrente, ArrayList<Data> datas) {

        int v = getID(data_corrente, datas);

        double prop = (double) v / (double) datas.size();
        int real = (int) (prop * 100.0F);

        return real;
    }

}
