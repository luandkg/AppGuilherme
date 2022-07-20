package com.luandkg.guilherme.escola.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.luandkg.guilherme.escola.Professor;
import com.luandkg.guilherme.escola.coisas.AtividadeEspecial;
import com.luandkg.guilherme.escola.utils.PaletaDeCores;
import com.luandkg.guilherme.libs.tempo.Calendario;

import java.util.ArrayList;


public class Temporizador {


    private final float mStartAngle = -90;
    private float mMaxSweepAngle = 360;
    private int mMaxProgress = 100;
    private boolean mRoundedCorners = false;

    private int mProgressoGrande = 0;
    private int mProgressoPequeno = 0;

    private float mAnguloPequeno = 0;

    private Professor mProfessor;
    private String mTempo;
    private int HOJE_TEMPO;

    public boolean isPodeDesenhar = false;

    public void podeDesenhar() {
        isPodeDesenhar = true;
    }

    private boolean isFerias = false;
    private int mFerias_Tamanho = 0;
    private int mFerias_Passou = 0;

    private String mTexto = "";
    private int TEMPO_TOTAL = 24 * 60 * 60;

    public void setText(String texto) {
        mTexto = texto;
    }

    public Bitmap criar() {

        int w = 700;
        int h = 700;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        int height = h / 2;
        int width = w / 2;

        mAnguloPequeno = calcSweepAngleFromProgress(mProgressoPequeno);


        if (isFerias) {

            criarFerias(canvas,w,h,width,height);

        } else {

            criarTrabalho(canvas,w,h,width,height);

        }



        return bmp;
    }


    public void set(String eTempo, int eHOJE_TEMPO, boolean eFerias, Professor eProfessor) {
        mTempo = eTempo;
        mProfessor = eProfessor;
        HOJE_TEMPO = eHOJE_TEMPO;
        isFerias = eFerias;
    }

    public void setFerias(int ePassou, int eTamanho) {
        mFerias_Passou = ePassou;
        mFerias_Tamanho = eTamanho;
    }


    private float calcSweepAngleFromProgress(int progress) {
        return (mMaxSweepAngle / mMaxProgress) * progress;
    }


    public void setProgressGrande(int progress) {
        mProgressoGrande = progress;
    }

    public void setProgressPequeno(int progress) {
        mProgressoPequeno = progress;
    }


    private void criarFerias(Canvas canvas,int w,int h, int width, int height) {

        Paint mPaint;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(30);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        mPaint.setStyle(Paint.Style.STROKE);


        mPaint.setColor(PaletaDeCores.VERMELHO);


        if (mFerias_Tamanho > 0) {

            double px = ((double) mFerias_Passou / (double) mFerias_Tamanho) * 100.0f;

            float ang_i = calcSweepAngleFromProgress((int) px);

            mPaint.setColor(PaletaDeCores.VERMELHO);

            canvas.drawArc(new RectF(width - 260, height - 260, width + 260, height + 260), mStartAngle, ang_i, false, mPaint);

            int tempo_agora = Calendario.getTempoDoDia();
            double tc = (double) tempo_agora / (double) TEMPO_TOTAL;
            double itc = (tc * 100.0f);
            float ang_f = calcSweepAngleFromProgress((int) itc);


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


    private void criarTrabalho(Canvas canvas,int w,int h, int width, int height){

        int interno = PaletaDeCores.AZUL;

        if (isPodeDesenhar) {
            Paint mPaint;                 // Allocate paint outside onDraw to avoid unnecessary object creation
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStrokeWidth(30);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            mPaint.setStyle(Paint.Style.STROKE);


            mPaint.setColor(PaletaDeCores.VERMELHO);
            //canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), mStartAngle, mAnguloGrande, false, mPaint);

            if (mTempo.contentEquals(Calendario.SABADO) || mTempo.contentEquals(Calendario.DOMINGO)) {

                float ang_f = calcSweepAngleFromProgress((int) mProgressoGrande);

                mPaint.setColor(PaletaDeCores.VERMELHO);
                canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), mStartAngle, ang_f, false, mPaint);

            } else {

                float ang_f = calcSweepAngleFromProgress((int) mProgressoGrande);

                int tempo_agora = Calendario.getTempoDoDia();
                double tc = (double) tempo_agora / (double) TEMPO_TOTAL;
                double itc = (tc * 100.0f);
                float ang_i = calcSweepAngleFromProgress((int) itc);


                mPaint.setColor(Color.parseColor("#1565C0"));
                canvas.drawArc(new RectF(width - 260, height - 260, width + 260, height + 260), mStartAngle, ang_i, false, mPaint);


            }


            if (mProfessor.existeAtividade(mTempo)) {

                ArrayList<AtividadeEspecial> atividades = mProfessor.getAtividades(mTempo);


                for (AtividadeEspecial a : atividades) {

                    int tempo_comecar = a.getInicio();
                    int tempo_terminar = a.getFim();

                    double tc = (double) tempo_comecar / (double) TEMPO_TOTAL;
                    float tt = (float) (tempo_terminar - tempo_comecar) / (float) TEMPO_TOTAL;

                    double itc = (tc * 100.0f);
                    double itt = (tt * 100.0f);

                    float ang_i = calcSweepAngleFromProgress((int) itc);
                    float ang_f = calcSweepAngleFromProgress((int) itt);


                    if (a.isDentro(HOJE_TEMPO)) {

                        mPaint.setColor(PaletaDeCores.VERMELHO);
                        canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                        int resta = HOJE_TEMPO - a.getInicio();
                        tt = (float) (resta) / (float) TEMPO_TOTAL;
                        itt = (tt * 100.0f);
                        ang_f = calcSweepAngleFromProgress((int) itt);


                        mPaint.setColor(PaletaDeCores.AMARELO);
                        canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                        interno = PaletaDeCores.AMARELO;

                    } else {

                        if (HOJE_TEMPO > a.getFim()) {
                            mPaint.setColor(PaletaDeCores.AMARELO);
                            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                            interno = PaletaDeCores.AMARELO;

                        } else {
                            mPaint.setColor(PaletaDeCores.VERMELHO);
                            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);
                        }
                    }


                }

            }

            if (mProfessor.existeCargaDeTrabalho(mTempo)) {

                int tempo_comecar = mProfessor.regencia_iniciar(mTempo);
                int tempo_terminar = mProfessor.regencia_terminar(mTempo);

                float tc = (float) tempo_comecar / (float) TEMPO_TOTAL;
                float tt = (float) (tempo_terminar - tempo_comecar) / (float) TEMPO_TOTAL;

                float itc = (tc * 100.0f);
                float itt = (tt * 100.0f);

                float ang_i = calcSweepAngleFromProgress((int) itc);
                float ang_f = calcSweepAngleFromProgress((int) itt);

                // System.out.println("Regencia");
                // System.out.println(ang_i);
                //  System.out.println(ang_f);

                if (HOJE_TEMPO >= tempo_comecar && HOJE_TEMPO < tempo_terminar) {


                    mPaint.setColor(PaletaDeCores.VERMELHO);
                    canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                    int resta = HOJE_TEMPO - tempo_comecar;
                    tt = (float) (resta) / (float) TEMPO_TOTAL;
                    itt = (tt * 100.0f);
                    ang_f = calcSweepAngleFromProgress((int) itt);


                    mPaint.setColor(PaletaDeCores.VERDE);
                    canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                    interno = PaletaDeCores.VERDE;

                } else {

                    if (HOJE_TEMPO > tempo_terminar) {
                        mPaint.setColor(PaletaDeCores.VERDE);
                        canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                        interno = PaletaDeCores.VERDE;

                    } else {
                        mPaint.setColor(PaletaDeCores.VERMELHO);
                        canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);
                    }
                }


            }


        }

        Paint mPaint2;
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setStrokeWidth(20);
        mPaint2.setAntiAlias(true);
        mPaint2.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        mPaint2.setStyle(Paint.Style.STROKE);


        mPaint2.setColor(interno);
        canvas.drawArc(new RectF(width - 150, height - 150, width + 150, height + 150), mStartAngle, mAnguloPequeno, false, mPaint2);

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
