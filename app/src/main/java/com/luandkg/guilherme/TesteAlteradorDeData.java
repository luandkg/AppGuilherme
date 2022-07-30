package com.luandkg.guilherme;

public class TesteAlteradorDeData {

    public static String alterar(String data) {

        if (data.contains("_03_")) {
            data = data.replace("_03_", "_09_");
        } else if (data.contains("_02_")) {
            data = data.replace("_02_", "_08_");


        } else if (data.contains("_04_")) {
            data = data.replace("_04_", "_08_");
        } else if (data.contains("_05_")) {
            data = data.replace("_05_", "_09_");
        } else if (data.contains("_06_")) {
            data = data.replace("_06_", "_10_");

        }

        return data;
    }
}
