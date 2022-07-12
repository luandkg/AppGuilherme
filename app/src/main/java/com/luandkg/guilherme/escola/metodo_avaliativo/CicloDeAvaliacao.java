package com.luandkg.guilherme.escola.metodo_avaliativo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.luandkg.guilherme.escola.metodo_avaliativo.CoresDeAvaliacao;
import com.luandkg.guilherme.utils.ImagemCriador;
import com.luandkg.guilherme.utils.Matematica;


public class CicloDeAvaliacao {


    public static Bitmap visualizar(int fizeram, int total) {

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

        if (porcentagem >= 70) {
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.EXCELENTE));
        } else if (porcentagem >= 50 && porcentagem < 70) {
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.BOM));
        } else {
            desenhador_arco.setColor(Color.parseColor(CoresDeAvaliacao.RUIM));
        }


        float angulo = Matematica.getAngulo(porcentagem);

        // ImagemCriador.drawTextoCentralizado(canvas, largura, altura, 40, eHazde.getArkoComIttas(), escritor);
        ImagemCriador.drawArco(canvas, largura / 2, altura / 2, 200, angulo, desenhador_arco);


        return imagem;

    }


}
