package com.luandkg.guilherme.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

public class Threader {

    public static void atualizar_imagem(ImageView imaginador, Bitmap imagem){

        imaginador.post(new Runnable() {
            @Override
            public void run() {
                imaginador.setImageBitmap(imagem);
            }
        });

    }

    public static void atualizar_texto(TextView textador, String texto){

        textador.post(new Runnable() {
            @Override
            public void run() {
                textador.setText(texto);
            }
        });

    }

}
