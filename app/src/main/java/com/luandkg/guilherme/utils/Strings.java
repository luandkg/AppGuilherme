package com.luandkg.guilherme.utils;

public class Strings {

    public static String seVazioEntao(String campo, String valor) {
        if (campo.length() == 0) {
            return valor;
        } else {
            return campo;
        }
    }

    public static int getTamanho(String campo) {
        return campo.length();
    }

    public static boolean comecaCom(String campo, String eComecaCom) {
        return campo.startsWith(eComecaCom);
    }

    public static boolean terminaCom(String campo, String eTerminaCom) {
        return campo.endsWith(eTerminaCom);
    }


}
