package com.luandkg.guilherme.utils;

public class Matematica {

    public static float getAngulo(float progresso) {
        return (360.0F / 100.0F) * progresso;
    }

    public static float getPorcentagem(int valor, int maximo) {

        float porcentagem = (float) (valor) / ((float) maximo) * 100.0F;

        return porcentagem;
    }


    public static boolean isNumeroReal(String s) {

        int i = 0;
        int o = s.length();

        boolean numero = true;
        boolean temSegundaPsrte = false;
        boolean teveSegundaPsrte = false;

        while (i < o) {
            String l = String.valueOf(s.charAt(i));

            if (l.contentEquals("0") || l.contentEquals("1") || l.contentEquals("2") || l.contentEquals("3") || l.contentEquals("4") || l.contentEquals("5")) {
            } else if (l.contentEquals("6") || l.contentEquals("7") || l.contentEquals("8") || l.contentEquals("9")) {
            } else if (l.contentEquals(",")) {
                temSegundaPsrte = true;
                i += 1;
                break;
            } else {
                numero = false;
                break;
            }

            i += 1;
        }

        if (temSegundaPsrte) {

            while (i < o) {
                String l = String.valueOf(s.charAt(i));

                if (l.contentEquals("0") || l.contentEquals("1") || l.contentEquals("2") || l.contentEquals("3") || l.contentEquals("4") || l.contentEquals("5")) {
                    teveSegundaPsrte = true;
                } else if (l.contentEquals("6") || l.contentEquals("7") || l.contentEquals("8") || l.contentEquals("9")) {
                    teveSegundaPsrte = true;
                } else {
                    numero = false;
                    break;
                }

                i += 1;
            }

            if (!teveSegundaPsrte) {
                numero=false;
            }
        }


        return numero;
    }

    public static String getNumeroRealPTBR(double eNotaCompleta){
        return  getNumeroReal2C(String.valueOf(eNotaCompleta).replace(".", ","));
    }

    public static String getNumeroReal2C(String s) {

        int i = 0;
        int o = s.length();

        boolean numero = true;
        boolean temSegundaPsrte = false;

        String p1 = "";
        String p2 = "";

        while (i < o) {
            String l = String.valueOf(s.charAt(i));

            if (l.contentEquals("0") || l.contentEquals("1") || l.contentEquals("2") || l.contentEquals("3") || l.contentEquals("4") || l.contentEquals("5")) {
                p1 += l;
            } else if (l.contentEquals("6") || l.contentEquals("7") || l.contentEquals("8") || l.contentEquals("9")) {
                p1 += l;
            } else if (l.contentEquals(",")) {
                p1 += l;
                temSegundaPsrte = true;
                i += 1;
                break;
            } else {
                numero = false;
                break;
            }

            i += 1;
        }

        if (temSegundaPsrte) {

            while (i < o) {
                String l = String.valueOf(s.charAt(i));

                if (l.contentEquals("0") || l.contentEquals("1") || l.contentEquals("2") || l.contentEquals("3") || l.contentEquals("4") || l.contentEquals("5")) {
                    if (p2.length() < 2) {
                        p2 += l;
                    }

                } else if (l.contentEquals("6") || l.contentEquals("7") || l.contentEquals("8") || l.contentEquals("9")) {
                    if (p2.length() < 2) {
                        p2 += l;
                    }

                } else {
                    numero = false;
                    break;
                }

                i += 1;
            }
        }

        if (!numero) {
            p1 = "0.";
            p2 = "0.";
        }

        if (p2.length() == 0) {
            p2 = "0.";
        }

        return p1 + p2;
    }

}
