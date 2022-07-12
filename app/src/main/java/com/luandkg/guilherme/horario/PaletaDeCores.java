package com.luandkg.guilherme.horario;

import android.graphics.Color;

public class PaletaDeCores {

    public static int getCorHex(String colorStr) {
        return Color.parseColor(colorStr);
    }

    public static int VERDE = getCorHex("#4CAF50");
    public static  int VERMELHO = getCorHex("#F44336");
    public static int AMARELO = getCorHex("#FDD835");
    public static  int AZUL = getCorHex("#42a5f5");

}
