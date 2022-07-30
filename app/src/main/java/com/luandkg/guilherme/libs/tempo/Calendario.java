package com.luandkg.guilherme.libs.tempo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Calendario {

    public static int RETITAR = 0;
    public static int ADICIONAR = 0;
    public static int MIN_ADICIONAR = 0;

    public static String SEGUNDA = "SEGUNDA";
    public static String TERCA = "TERCA";
    public static String QUARTA = "QUARTA";
    public static String QUINTA = "QUINTA";
    public static String SEXTA = "SEXTA";
    public static String SABADO = "SABADO";
    public static String DOMINGO = "DOMINGO";

    public static String getDiaAtual() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String ret = "";

        int dia = cal.get(Calendar.DAY_OF_WEEK);

        if (dia == 1) {
            ret = DOMINGO;
        } else if (dia == 2) {
            ret = SEGUNDA;
        } else if (dia == 3) {
            ret = TERCA;
        } else if (dia == 4) {
            ret = QUARTA;
        } else if (dia == 5) {
            ret = QUINTA;
        } else if (dia == 6) {
            ret = SEXTA;
        } else if (dia == 7) {
            ret = SABADO;
        }

        //ret = SEXTA;

        return ret;
    }

    public static int getTempoDoDia() {

        Calendar c = Calendar.getInstance();

        int seg = c.getTime().getSeconds();
        int min = c.getTime().getMinutes();
        int hora = c.getTime().getHours();

        hora -= RETITAR;
        hora += ADICIONAR;
        min += MIN_ADICIONAR;

        // hora = 16;
        // min = min + 0;
        // System.out.println("Agora :: " + hora + ":" + min);


        return (hora * 60 * 60) + (min * 60) + seg;
    }

    public static int getHoraDoDia() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int hora = cal.get(Calendar.HOUR_OF_DAY);

        hora -= RETITAR;
        hora += ADICIONAR;

        return hora;
    }

    public static int getMinutoDoDia() {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int min = cal.get(Calendar.MINUTE);

        return min;
    }

    public static boolean isIgual(String a, String b) {
        return a.contentEquals(b);
    }

    public static boolean isDiferente(String a, String b) {
        return !a.contentEquals(b);
    }


    public static boolean isDiaDaSemana(String eDia) {
        boolean ret = false;

        if (eDia.contentEquals(SEGUNDA)) {
            ret = true;
        } else if (eDia.contentEquals(TERCA)) {
            ret = true;
        } else if (eDia.contentEquals(QUARTA)) {
            ret = true;
        } else if (eDia.contentEquals(QUINTA)) {
            ret = true;
        } else if (eDia.contentEquals(SEXTA)) {
            ret = true;
        }

        return ret;
    }

    public static boolean isFimDeSemana(String eDia) {
        boolean ret = false;

        if (eDia.contentEquals(DOMINGO)) {
            ret = true;
        } else if (eDia.contentEquals(SABADO)) {
            ret = true;
        }

        return ret;
    }


    public static String getMesPrefixo(String mes) {

        String sMes = "";

        if (mes.contentEquals("01")) {
            sMes = "JAN";
        } else if (mes.contentEquals("02")) {
            sMes = "FEV";
        } else if (mes.contentEquals("03")) {
            sMes = "MAR";
        } else if (mes.contentEquals("04")) {
            sMes = "ABR";
        } else if (mes.contentEquals("05")) {
            sMes = "MAI";
        } else if (mes.contentEquals("06")) {
            sMes = "JUN";
        } else if (mes.contentEquals("07")) {
            sMes = "JUL";
        } else if (mes.contentEquals("08")) {
            sMes = "OUT";
        } else if (mes.contentEquals("09")) {
            sMes = "SET";
        } else if (mes.contentEquals("10")) {
            sMes = "OUT";
        } else if (mes.contentEquals("11")) {
            sMes = "NOV";
        } else if (mes.contentEquals("12")) {
            sMes = "DEZ";
        }
        return sMes;
    }


    public static int getIndice(ArrayList<Data> datas, String qualdata) {
        int v = 0;
        boolean enc = false;
        for (Data data : datas) {

            if (data.getTempoLegivel().contentEquals(qualdata)) {
                enc = true;
                break;
            }

            v += 1;
        }
        if (!enc) {
            v = -1;
        }
        return v;
    }

    public static ArrayList<Data> filtrar_ate(ArrayList<Data> datas, int pos) {
        int v = 0;
        ArrayList<Data> ret = new ArrayList<Data>();

        for (Data data : datas) {

            if (v < pos) {
                ret.add(data);
            }


            v += 1;
        }

        return ret;
    }


    public static String toFaixaTemporal(String tempo_AMD) {
        String faixa_tempo = "";

        if (tempo_AMD.length() == 10) {

            String sDia = String.valueOf(tempo_AMD.charAt(8)) + String.valueOf(tempo_AMD.charAt(9));
            String sMes = String.valueOf(tempo_AMD.charAt(5)) + String.valueOf(tempo_AMD.charAt(6));

            faixa_tempo = sDia + " de " + Calendario.getMesPrefixo(sMes);

        }

        return faixa_tempo;
    }


    public  static String inverter_mes_dia(String entrada) {

        String saida = "";

        if (entrada.length() == 5) {
            saida = String.valueOf(entrada.charAt(3)) + String.valueOf(entrada.charAt(4));
            saida += "/" + String.valueOf(entrada.charAt(0)) + String.valueOf(entrada.charAt(1));
        }

        return saida;
    }

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

    public static String formate_dia_mes(String faixa) {

        String dia = String.valueOf(faixa.charAt(8)) + String.valueOf(faixa.charAt(9));
        String mes = String.valueOf(faixa.charAt(5)) + String.valueOf(faixa.charAt(6));

        String sMes = Calendario.getMesPrefixo(mes);

        return dia + " de " + sMes;

    }

}
