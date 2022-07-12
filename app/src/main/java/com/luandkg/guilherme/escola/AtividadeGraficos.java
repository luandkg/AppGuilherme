package com.luandkg.guilherme.escola;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.luandkg.guilherme.utils.ImagemCriador;
import com.luandkg.guilherme.utils.Matematica;

public class AtividadeGraficos {

    public static Bitmap criarAvaliacao(int fizeram, int total) {


        int largura = 500;
        int altura = 500;

        Bitmap imagem = Bitmap.createBitmap(largura, altura, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(imagem);


        Paint escritor = new Paint();
        escritor.setTextSize(100);
        escritor.setColor(Color.parseColor("#388e3c"));


        Paint desenhador_arco = new Paint();
        desenhador_arco.setStrokeWidth(40);
        desenhador_arco.setAntiAlias(true);
        desenhador_arco.setStrokeCap(Paint.Cap.BUTT);
        desenhador_arco.setStyle(Paint.Style.STROKE);

        float porcentagem = Matematica.getPorcentagem(fizeram, total);


        String E = "#FF3D00";
        String D = "#FF9100";
        String C = "#FFC400";
        String B = "#64DD17";
        String A = "#00B0FF";


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


        float angulo = Matematica.getAngulo(porcentagem);

        ImagemCriador.drawArco(canvas, largura / 2, altura / 2, 200, angulo, desenhador_arco);


        return imagem;

    }


}
