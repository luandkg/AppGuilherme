package com.luandkg.guilherme.escola.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.luandkg.guilherme.escola.utils.PaletaDeCores;
import com.luandkg.guilherme.utils.Matematica;
import com.luandkg.guilherme.libs.tempo.Calendario;

public class BimestreImagem {

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


    public static Bitmap onFerias(int mFerias_Passou, int mFerias_Tamanho, String eTexto) {

        int w = 700;
        int h = 700;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        int height = h / 2;
        int width = w / 2;


        criarFerias(canvas, w, h, width, height, mFerias_Passou, mFerias_Tamanho, eTexto);


        return bmp;
    }

    private static void criarFerias(Canvas canvas, int w, int h, int width, int height, int mFerias_Passou, int mFerias_Tamanho, String mTexto) {

        final float mStartAngle = -90;
        int TEMPO_TOTAL = 24 * 60 * 60;

        Paint mPaint;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(30);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);


        mPaint.setColor(PaletaDeCores.VERMELHO);


        if (mFerias_Tamanho > 0) {

            double px = ((double) mFerias_Passou / (double) mFerias_Tamanho) * 100.0f;

            float ang_i = Matematica.getAngulo((int) px);

            mPaint.setColor(PaletaDeCores.VERMELHO);

            canvas.drawArc(new RectF(width - 260, height - 260, width + 260, height + 260), mStartAngle, ang_i, false, mPaint);

            int tempo_agora = Calendario.getTempoDoDia();
            double tc = (double) tempo_agora / (double) TEMPO_TOTAL;
            double itc = (tc * 100.0f);
            float ang_f = Matematica.getAngulo((int) itc);


            mPaint.setColor(Color.parseColor("#1565C0"));
            mPaint.setStrokeWidth(10);

            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), mStartAngle, ang_f, false, mPaint);

            if (mTexto.length() > 0) {

                Paint paint2 = new Paint();
                paint2.setColor(Color.BLACK);
                paint2.setTextSize(80);


                Rect bounds = new Rect();
                paint2.getTextBounds(String.valueOf(mTexto), 0, String.valueOf(mTexto).length(), bounds);
                int tamanho = bounds.width();

                canvas.drawText(mTexto, (w / 2) - (tamanho / 2), (h / 2) + 60, paint2);

            }

        }


    }


}
