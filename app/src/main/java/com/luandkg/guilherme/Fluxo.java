package com.luandkg.guilherme;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.luandkg.guilherme.escola.Aluno;
import com.luandkg.guilherme.escola.Avaliador;
import com.luandkg.guilherme.escola.ContandoData;
import com.luandkg.guilherme.utils.tempo.Data;

import java.util.ArrayList;

public class Fluxo {

    public static Bitmap onBimestre(int v, int acabar) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        int mStrokeWidth = 20;              // Width of outline
        boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends
        float mStartAngle = -90;      // Always start from top (default is: "3 o'clock on a watch.")

        Paint paint = new Paint();
        paint.setStrokeWidth(mStrokeWidth);
        paint.setAntiAlias(true);
        paint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);


        // ARCO 1
        if (v > 0) {
            float arco1 = (360.0F / 100.0F) * v;
            paint.setColor(Color.parseColor("#c62828"));
            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), mStartAngle, arco1, false, paint);
        }

        Paint escritor = new Paint();
        escritor.setTextSize(150);
        escritor.setColor(Color.parseColor("#c62828"));


        Rect bounds = new Rect();
        escritor.getTextBounds(String.valueOf(acabar), 0, String.valueOf(acabar).length(), bounds);
        int tamanho = bounds.width();

        canvas.drawText(String.valueOf(acabar), (w / 2) - (tamanho / 2) - 20, (h / 2) + 30, escritor);


        return bmp;

    }


    public static Bitmap criarFluxoDeEntrega(ArrayList<Aluno> alunos, ArrayList<Data> quais_datas ) {

        ArrayList<ContandoData> contadores = Avaliador.getFluxoDeEntrega(alunos,quais_datas);


        int w = 350;
        int h = 300;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint pintor_atividades = new Paint();
        pintor_atividades.setColor(Color.parseColor("#689F38"));


        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        int eixo_x = 0;

        int coluna_largura = w / quais_datas.size();


        for (ContandoData contador : contadores) {


            if (contador.getValor() > 0) {

                int coluna_altura = (contador.getValor() * 3);

                canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura, h, pintor_atividades);
            }

            eixo_x += coluna_largura;
        }


        return bmp;

    }

}
