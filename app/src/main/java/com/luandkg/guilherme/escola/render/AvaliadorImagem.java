package com.luandkg.guilherme.escola.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.tempo.SemanaContinuaCarregada;
import com.luandkg.guilherme.utils.Matematica;

import java.util.ArrayList;

public class AvaliadorImagem {

    private static float calcSweepAngleFromProgress(int progress) {
        return (360.0F / 100.0F) * progress;
    }

    public static Bitmap onFluxo(int presente, int recuperacao, ArrayList<SemanaContinuaCarregada> semanas, ArrayList<AlunoResultado> alunos) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends
        // float mStartAngle = -90;      // Always start from top (default is: "3 o'clock on a watch.")


        int arco_0 = Color.parseColor("#ff6f00");

        int arco_1 = Color.parseColor("#c62828");
        int arco_2 = Color.parseColor("#6a1b9a");
        int arco_3 = Color.parseColor("#1565c0");
        int arco_4 = Color.parseColor("#2e7d32");
        int arco_5 = Color.parseColor("#f9a825");


        // ARCO 0
        if (presente > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(arco_0);

            float arco1 = calcSweepAngleFromProgress(presente);
            canvas.drawArc(new RectF(width - 240, height - 240, width + 240, height + 240), 50, arco1, false, desenhar_fino);
        }


        // ARCO 0
        if (recuperacao > 0) {

            Paint desenhar_fino = new Paint();

            desenhar_fino.setStrokeWidth(10);
            desenhar_fino.setAntiAlias(true);
            desenhar_fino.setStrokeCap(mRoundedCorners ? Paint.Cap.ROUND : Paint.Cap.BUTT);
            desenhar_fino.setStyle(Paint.Style.STROKE);
            desenhar_fino.setColor(arco_4);

            float tamanho_arco = calcSweepAngleFromProgress(recuperacao);
            canvas.drawArc(new RectF(width - 220, height - 220, width + 220, height + 220), 120, tamanho_arco, false, desenhar_fino);
        }


        // ArrayList<String> cores = new ArrayList<String>();
        // cores.add("#c62828");
        // cores.add("#6a1b9a");
        // cores.add("#1565c0");
        // cores.add("#c62828");
        // cores.add("#2e7d32");
        // cores.add("#f9a825");
        // cores.add("#c62828");
        // cores.add("#c62828");
        //  cores.add("#c62828");
        //   cores.add("#c62828");
        //   cores.add("#c62828");
        //  cores.add("#c62828");

        String E = "#FF3D00";
        String D = "#FF9100";
        String C = "#FFC400";
        String B = "#64DD17";
        String A = "#00B0FF";


        int i = 0;
        int distancia = 200;

        for (SemanaContinuaCarregada semana : semanas) {

            int v = semana.getFizeram();


            double taxa = (double) v / (double) alunos.size();

            taxa = taxa * 100.0;

            float porcentagem = Matematica.getPorcentagem(v, alunos.size());

            // desenhador_arco.setColor(Color.parseColor(cores.get(i)));

            Paint desenhador_arco = new Paint();

            desenhador_arco.setStrokeWidth(5);
            desenhador_arco.setAntiAlias(true);
            desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
            desenhador_arco.setStyle(Paint.Style.STROKE);

            if (porcentagem >= 90) {
                desenhador_arco.setColor(Color.parseColor(A));
            } else if (porcentagem >= 75 && porcentagem < 90) {
                desenhador_arco.setColor(Color.parseColor(B));
            } else if (porcentagem >= 50 && porcentagem < 75) {
                desenhador_arco.setColor(Color.parseColor(C));
            } else if (porcentagem >= 25 && porcentagem < 50) {
                desenhador_arco.setColor(Color.parseColor(D));
            } else {
                desenhador_arco.setColor(Color.parseColor(E));
            }


            System.out.println("SEMANA " + semana.getNome() + " >>> " + v + " de " + alunos.size() + " ::> " + taxa);

            float tamanho_arco = calcSweepAngleFromProgress((int) taxa);

            canvas.drawArc(new RectF(width - distancia, height - distancia, width + distancia, height + distancia), 270, tamanho_arco, false, desenhador_arco);

            distancia -= 10;
            i += 1;
        }


        return bmp;

    }

    public static Bitmap onFluxoCom(int presente, int recuperacao, ArrayList<SemanaContinuaCarregada> semanas, ArrayList<AlunoResultado> alunos,int mais) {

        int w = 500;
        int h = 500;

        int height = h / 2;
        int width = w / 2;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);

        boolean mRoundedCorners = false;     // Set to true if rounded corners should be applied to outline ends
        // float mStartAngle = -90;      // Always start from top (default is: "3 o'clock on a watch.")


        int arco_0 = Color.parseColor("#ff6f00");

        int arco_1 = Color.parseColor("#c62828");
        int arco_2 = Color.parseColor("#6a1b9a");
        int arco_3 = Color.parseColor("#1565c0");
        int arco_4 = Color.parseColor("#2e7d32");
        int arco_5 = Color.parseColor("#f9a825");



        String E = "#FF3D00";
        String D = "#FF9100";
        String C = "#FFC400";
        String B = "#64DD17";
        String A = "#00B0FF";

        ArrayList<Integer> numeros = new ArrayList<Integer>();
        numeros.add(270);
        numeros.add(40);
        numeros.add(120);
        numeros.add(300);
        numeros.add(150);
        numeros.add(60);
        numeros.add(95);
        numeros.add(45);
        numeros.add(120);
        numeros.add(100);
        numeros.add(40);
        numeros.add(60);
        numeros.add(200);
        numeros.add(160);
        numeros.add(50);
        numeros.add(260);
        numeros.add(95);
        numeros.add(45);
        numeros.add(120);
        numeros.add(100);
        numeros.add(40);
        numeros.add(60);
        numeros.add(200);

        int i = 0;
        int distancia = 200;

        int index= 0;

        for (SemanaContinuaCarregada semana : semanas) {

            int v = semana.getFizeram();

            //v=30;

            double taxa = (double) v / (double) alunos.size();

            taxa = taxa * 100.0;

            float porcentagem = Matematica.getPorcentagem(v, alunos.size());

            // desenhador_arco.setColor(Color.parseColor(cores.get(i)));

            Paint desenhador_arco = new Paint();

            desenhador_arco.setStrokeWidth(5);
            desenhador_arco.setAntiAlias(true);
            desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
            desenhador_arco.setStyle(Paint.Style.STROKE);

            if (porcentagem >= 90) {
                desenhador_arco.setColor(Color.parseColor(A));
            } else if (porcentagem >= 75 && porcentagem < 90) {
                desenhador_arco.setColor(Color.parseColor(B));
            } else if (porcentagem >= 50 && porcentagem < 75) {
                desenhador_arco.setColor(Color.parseColor(C));
            } else if (porcentagem >= 25 && porcentagem < 50) {
                desenhador_arco.setColor(Color.parseColor(D));
            } else {
                desenhador_arco.setColor(Color.parseColor(E));
            }


            System.out.println("SEMANA " + semana.getNome() + " >>> " + v + " de " + alunos.size() + " ::> " + taxa);

            float tamanho_arco = calcSweepAngleFromProgress((int) taxa);

            int comecar = numeros.get(index) + mais;

            if (i % 2 ==0){
                comecar=numeros.get(index)-mais;
            }

            canvas.drawArc(new RectF(width - distancia, height - distancia, width + distancia, height + distancia), comecar, tamanho_arco, false, desenhador_arco);

            distancia -= 10;
            i += 1;
            index+=1;

        }


        return bmp;

    }

}
