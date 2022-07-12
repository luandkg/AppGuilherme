package com.luandkg.guilherme.utils.tempo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Tempo {

    public static String getADM() {
        String date = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getADMComBarras() {
        String date = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getData() {
        String date = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());
        return date;
    }

    public static String getHora() {
        String date = new SimpleDateFormat("hh:mm", Locale.getDefault()).format(new Date());
        return date;
    }

    public static Data parse(String sData) {

        int i = 0;
        int o = sData.length();

        String sDia = "";
        String sMes = "";
        String sAno = "";

        int f = 0;

        while (i < o) {
            String l = String.valueOf(sData.charAt(i));

            if (f == 0) {
                if (l.contentEquals("/")) {
                    f += 1;
                } else {
                    sDia += l;
                }
            } else if (f == 1) {
                if (l.contentEquals("/")) {
                    f += 1;
                } else {
                    sMes += l;
                }
            } else if (f == 2) {
                if (l.contentEquals("/")) {
                    f += 1;
                } else {
                    sAno += l;
                }
            }


            i += 1;
        }

        return new Data(Integer.parseInt(sAno), Integer.parseInt(sMes), Integer.parseInt(sDia), DiaSemanal.Domingo);
    }


    public static DiaSemanal proximoDia(DiaSemanal eDiaSemanal) {

        DiaSemanal eRetorno = eDiaSemanal;

        if (eDiaSemanal == DiaSemanal.Domingo) {
            eRetorno = DiaSemanal.Segunda;
        } else if (eDiaSemanal == DiaSemanal.Segunda) {
            eRetorno = DiaSemanal.Terca;
        } else if (eDiaSemanal == DiaSemanal.Terca) {
            eRetorno = DiaSemanal.Quarta;
        } else if (eDiaSemanal == DiaSemanal.Quarta) {
            eRetorno = DiaSemanal.Quinta;
        } else if (eDiaSemanal == DiaSemanal.Quinta) {
            eRetorno = DiaSemanal.Sexta;
        } else if (eDiaSemanal == DiaSemanal.Sexta) {
            eRetorno = DiaSemanal.Sabado;
        } else if (eDiaSemanal == DiaSemanal.Sabado) {
            eRetorno = DiaSemanal.Domingo;
        }

        return eRetorno;
    }

    public static ArrayList<Data> construirAno(int eAno, DiaSemanal eDiaSemanal, int eJan, int eFev, int eMar, int eAbr, int eMai, int eJun, int eJul, int eAgo, int eSet, int eOut, int eNov, int eDez) {

        ArrayList<Data> eDatas = new ArrayList<Data>();

        for (int eDia = 1; eDia <= eJan; eDia++) {
            eDatas.add(new Data(eAno, 1, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eFev; eDia++) {
            eDatas.add(new Data(eAno, 2, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eMar; eDia++) {
            eDatas.add(new Data(eAno, 3, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eAbr; eDia++) {
            eDatas.add(new Data(eAno, 4, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eMai; eDia++) {
            eDatas.add(new Data(eAno, 5, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eJun; eDia++) {
            eDatas.add(new Data(eAno, 6, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eJul; eDia++) {
            eDatas.add(new Data(eAno, 7, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eAgo; eDia++) {
            eDatas.add(new Data(eAno, 8, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eSet; eDia++) {
            eDatas.add(new Data(eAno, 9, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eOut; eDia++) {
            eDatas.add(new Data(eAno, 10, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eNov; eDia++) {
            eDatas.add(new Data(eAno, 11, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        for (int eDia = 1; eDia <= eDez; eDia++) {
            eDatas.add(new Data(eAno, 12, eDia, eDiaSemanal));
            eDiaSemanal = proximoDia(eDiaSemanal);
        }

        return eDatas;
    }


    public static void mostrarDatas(ArrayList<Data> datas) {

        for (Data eData : datas) {
            System.out.println("-->> " + eData.getTitulo());
        }

    }

    public static ArrayList<Data> filtrar(ArrayList<Data> mDatas, Data comecar, Data terminar) {

        ArrayList<Data> datas = new ArrayList<Data>();

        boolean dentro = false;

        for (Data eData : mDatas) {

            if (comecar.isIgual(eData)) {
                dentro = true;
            }

            if (dentro) {
                if (terminar.isIgual(eData)) {
                    datas.add(eData);
                    dentro = false;
                } else {
                    datas.add(eData);
                }
            }

        }

        return datas;
    }


    public static Data filtrar_primeira(ArrayList<Data> mDatas) {
        return mDatas.get(0);
    }

    public static Data filtrar_ultima(ArrayList<Data> mDatas) {
        return mDatas.get(mDatas.size() - 1);
    }
}
