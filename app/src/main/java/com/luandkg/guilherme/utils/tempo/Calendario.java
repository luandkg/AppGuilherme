package com.luandkg.guilherme.utils.tempo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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


}
