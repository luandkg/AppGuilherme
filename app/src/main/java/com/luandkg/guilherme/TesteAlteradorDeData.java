package com.luandkg.guilherme;

public class TesteAlteradorDeData {

    public static String alterar(String data){

        if (data.contentEquals("2022_04_14")) {
            data = "2022_05_14";
        } else if (data.contentEquals("2022_04_12")) {
            data = "2022_05_12";
        } else if (data.contentEquals("2022_04_19")) {
            data = "2022_05_19";
        } else if (data.contentEquals("2022_07_15")) {
            data = "2022_06_15";
        }

        return data;
    }
}
