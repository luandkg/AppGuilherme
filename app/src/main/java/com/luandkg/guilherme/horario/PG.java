package com.luandkg.guilherme.horario;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;


import java.util.ArrayList;

public class PG extends View {

    private int mViewWidth;
    private int mViewHeight;

    private final float mStartAngle = -90;      // Always start from top (default is: "3 o'clock on a watch.")
    private float mMaxSweepAngle = 360;         // Max degrees to sweep = full circle
    private int mMaxProgress = 100;             // Max progress to use
    private boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends

    private int mProgressoGrande = 0;              // How long to sweep from mStartAngle
    private int mProgressoPequeno = 0;              // How long to sweep from mStartAngle

    private float mAnguloGrande = 0;              // How long to sweep from mStartAngle
    private float mAnguloPequeno = 0;              // How long to sweep from mStartAngle

    private Professor mProfessor;
    private String mTempo;
    private int HOJE_TEMPO;

    public boolean isPodeDesenhar = false;

    public void podeDesenhar() {
        isPodeDesenhar = true;
    }

    private boolean isFerias = false;

    public void set(String eTempo, int eHOJE_TEMPO, boolean eFerias,Professor eProfessor) {
        mTempo = eTempo;
        mProfessor = eProfessor;
        HOJE_TEMPO = eHOJE_TEMPO;
        isFerias=eFerias;
    }

    public PG(Context context) {
        this(context, null);
    }

    public PG(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PG(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initMeasurments();
        drawOutlineArc(canvas);
    }

    private void initMeasurments() {
        mViewWidth = getWidth();
        mViewHeight = getHeight();
    }

    private int mFerias_Tamanho = 0;
    private int mFerias_Passou = 0;

    public void setFerias(int ePassou,int eTamanho){
        mFerias_Passou=ePassou;
        mFerias_Tamanho=eTamanho;
    }

    private void drawOutlineArc(Canvas canvas) {

        int height = mViewHeight / 2;
        int width = mViewWidth / 2;

        mAnguloGrande = calcSweepAngleFromProgress(mProgressoGrande);
        mAnguloPequeno = calcSweepAngleFromProgress(mProgressoPequeno);

        int tempo_total = 24 * 60 * 60;

        if(isFerias){

            Paint mPaint;                 // Allocate paint outside onDraw to avoid unnecessary object creation
            mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mPaint.setStrokeWidth(30);
            mPaint.setAntiAlias(true);
            mPaint.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            mPaint.setStyle(Paint.Style.STROKE);


            mPaint.setColor(PaletaDeCores.VERMELHO);



            if (mFerias_Tamanho>0){

                double px = ((double)mFerias_Passou/(double)mFerias_Tamanho)*100.0f;

                float ang_i = calcSweepAngleFromProgress((int) px);

                mPaint.setColor(PaletaDeCores.VERMELHO);

                canvas.drawArc(new RectF(width - 260, height - 260, width + 260, height + 260),   mStartAngle, ang_i, false, mPaint);

                int tempo_agora =Calendario.getTempoDoDia();
                double tc = (double) tempo_agora / (double) tempo_total;
                double itc = (tc * 100.0f);
                float ang_f = calcSweepAngleFromProgress((int) itc);


                mPaint.setColor(Color.parseColor("#1565C0"));
                mPaint.setStrokeWidth(10);

                canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200),   mStartAngle, ang_f, false, mPaint);

            }


        }else{

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
                    canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200),   mStartAngle, ang_f, false, mPaint);

                }else{

                    float ang_f = calcSweepAngleFromProgress((int) mProgressoGrande);

                    int tempo_agora =Calendario.getTempoDoDia();
                    double tc = (double) tempo_agora / (double) tempo_total;
                    double itc = (tc * 100.0f);
                    float ang_i = calcSweepAngleFromProgress((int) itc);


                    mPaint.setColor(Color.parseColor("#1565C0"));
                    canvas.drawArc(new RectF(width - 260, height - 260, width + 260, height + 260),   mStartAngle, ang_i, false, mPaint);


                }


                if (mProfessor.existeAtividade(mTempo)) {

                    ArrayList<AtividadeEspecial> atividades = mProfessor.getAtividades(mTempo);


                    for (AtividadeEspecial a : atividades) {

                        int tempo_comecar = a.getInicio();
                        int tempo_terminar = a.getFim();

                        double tc = (double) tempo_comecar / (double) tempo_total;
                        float tt = (float) (tempo_terminar - tempo_comecar) / (float) tempo_total;

                        double itc = (tc * 100.0f);
                        double itt = (tt * 100.0f);

                        float ang_i = calcSweepAngleFromProgress((int) itc);
                        float ang_f = calcSweepAngleFromProgress((int) itt);


                        if (a.isDentro(HOJE_TEMPO)) {

                            mPaint.setColor(PaletaDeCores.VERMELHO);
                            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                            int resta = HOJE_TEMPO - a.getInicio();
                            tt = (float) (resta) / (float) tempo_total;
                            itt = (tt * 100.0f);
                            ang_f = calcSweepAngleFromProgress((int) itt);


                            mPaint.setColor(PaletaDeCores.AMARELO);
                            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                            interno=PaletaDeCores.AMARELO;

                        } else {

                            if (HOJE_TEMPO > a.getFim()) {
                                mPaint.setColor(PaletaDeCores.AMARELO);
                                canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                                interno=PaletaDeCores.AMARELO;

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

                    float tc = (float) tempo_comecar / (float) tempo_total;
                    float tt = (float) (tempo_terminar - tempo_comecar) / (float) tempo_total;

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
                        tt = (float) (resta) / (float) tempo_total;
                        itt = (tt * 100.0f);
                        ang_f = calcSweepAngleFromProgress((int) itt);


                        mPaint.setColor(PaletaDeCores.VERDE);
                        canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                        interno=PaletaDeCores.VERDE;

                    } else {

                        if (HOJE_TEMPO > tempo_terminar) {
                            mPaint.setColor(PaletaDeCores.VERDE);
                            canvas.drawArc(new RectF(width - 200, height - 200, width + 200, height + 200), ang_i + mStartAngle, ang_f, false, mPaint);

                            interno=PaletaDeCores.VERDE;

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


        }


    }


    private float calcSweepAngleFromProgress(int progress) {
        return (mMaxSweepAngle / mMaxProgress) * progress;
    }


    public void setProgressGrande(int progress) {
        mProgressoGrande = progress;
        invalidate();
    }

    public void setProgressPequeno(int progress) {
        mProgressoPequeno = progress;
        invalidate();
    }


}