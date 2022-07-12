package com.luandkg.guilherme.horario;

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

     //   ret = QUARTA;

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

       // hora=9;
      //// min=10;

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


    public static boolean isDiferente(String a, String b) {
        return !a.contentEquals(b);
    }

    public static boolean isIgual(String a, String b) {
        return a.contentEquals(b);
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


}
