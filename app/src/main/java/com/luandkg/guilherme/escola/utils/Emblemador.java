package com.luandkg.guilherme.escola.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

public class Emblemador {


    public static Bitmap criarLogo(String eModo, String l) {

        int w = 500;
        int h = 500;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        if (eModo.contentEquals("CHAMADA")) {


            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#4caf50"));

            if (l.contains("7") || l.contains("8")) {
                paint.setColor(Color.parseColor("#039be5"));
            }

            canvas.drawCircle(w / 2, h / 2, 230, paint);

        }

        if (eModo.contentEquals("AVALIACAO")) {


            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffb300"));

            if (l.contains("7") || l.contains("8")) {
                paint.setColor(Color.parseColor("#f44336"));
            }


            canvas.drawCircle(w / 2, h / 2, 230, paint);

        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(l), 0, String.valueOf(l).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(l), (w / 2) - (tamanho / 2), (h / 2) + 60, paint2);


        return bmp;

    }

    public static Bitmap criarNota(String valor) {

        int w = 500;
        int h = 500;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);


        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#ffb300"));


        if (valor.contains("1")) {
            paint.setColor(Color.parseColor("#E53935"));
        } else if (valor.contains("2")) {
            paint.setColor(Color.parseColor("#FFC107"));
        } else if (valor.contains("3")) {
            paint.setColor(Color.parseColor("#4CAF50"));
        }

        canvas.drawCircle(w / 2, h / 2, 230, paint);


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(valor), 0, String.valueOf(valor).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(valor), (w / 2) - (tamanho / 2), (h / 2) + 60, paint2);


        return bmp;

    }

    public static Bitmap criarAulaSimbolo(String eModo, String l) {

        int w = 500;
        int h = 500;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        if (eModo.contentEquals("AULA")) {


            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#4CAF50"));


            canvas.drawCircle(w / 2, h / 2, 230, paint);

        }

        if (eModo.contentEquals("INTERVALO")) {


            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffb300"));


            canvas.drawCircle(w / 2, h / 2, 230, paint);

        }

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(l), 0, String.valueOf(l).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(l), (w / 2) - (tamanho / 2)-10, (h / 2) + 60, paint2);


        return bmp;

    }

    public static Bitmap criarAluno(String eCor, String l) {

        int w = 500;
        int h = 500;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        Paint paint = new Paint();
        paint.setColor(Color.parseColor(eCor));

        canvas.drawCircle(w / 2, h / 2, 230, paint);


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        Rect bounds = new Rect();
        paint2.getTextBounds(String.valueOf(l), 0, String.valueOf(l).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(l), (w / 2) - (tamanho / 2), (h / 2) + 60, paint2);


        return bmp;

    }


}
