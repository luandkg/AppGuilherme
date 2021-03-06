package com.luandkg.guilherme.escola.render;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeRealizada;
import com.luandkg.guilherme.escola.tempo.ESTANCIA3_2BIMESTRE;
import com.luandkg.guilherme.escola.tempo.SemanaContinua;
import com.luandkg.guilherme.utils.tempo.Data;

import java.util.ArrayList;

public class FluxoDeEntrega {


    public static Bitmap criarFluxoDeEntregaDoAluno(ArrayList<Data> quais_datas, ArrayList<AtividadeRealizada> mAtividades) {

        int w = 400;
        int h = 300;

        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);

        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#689F38"));


        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor("#FFC107"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#E53935"));

        Paint pintor_preto = new Paint();
        pintor_preto.setColor(Color.parseColor("#78909C"));

        Paint pintor_branco = new Paint();
        pintor_branco.setColor(Color.parseColor("#fafafa"));

        Paint paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(200);


        int eixo_x = 0;

        int coluna_largura = w / quais_datas.size();
        int coluna_largura_pintar = coluna_largura - 1;

        for (Data data : quais_datas) {

            for (AtividadeRealizada eSemanaContinuaValores : mAtividades) {

              //  System.out.println(eSemanaContinuaValores.getData() + " :: " + data.getTempo());

                if (data.getTempo().contentEquals(eSemanaContinuaValores.getData())) {

                    if (eSemanaContinuaValores.getStatus()) {
                        int coluna_altura = 200;
                        canvas.drawRect(eixo_x, h - coluna_altura, eixo_x + coluna_largura_pintar, h, pintor_verde);
                    }

                }
            }


            eixo_x += coluna_largura;

        }

        canvas.drawRect(0, h - 20, quais_datas.size() * coluna_largura, h, pintor_preto);

        // int ps = -1;
        // int tam = 0;

        //int met = coluna_largura / 2;
        // int eixo_x2 = met;

        int larg = quais_datas.size() * coluna_largura;

        int coluna_largura2 = larg / ESTANCIA3_2BIMESTRE.getSemanas().size();
        int coluna_largura_pintar2 = coluna_largura2 - 5;

        int come = 5;

        for (SemanaContinua eSemana : ESTANCIA3_2BIMESTRE.getSemanas()) {


            int UM = 0;
            int DOIS = 0;
            int TRES = 0;

            for (Data data : eSemana.getDatas()) {

                for (AtividadeRealizada eSemanaContinuaValores : mAtividades) {

                    if (data.getTempo().contentEquals(eSemanaContinuaValores.getData())) {
                        if (eSemanaContinuaValores.getStatus()) {
                            UM += 1;
                        }
                    }
                }

                //   eixo_x2 += coluna_largura;
            }


            int maior = 0;
            int quantidade = 0;

            if (UM > 0) {
                if (UM >= quantidade) {
                    quantidade = UM;
                    maior = 1;
                }
            }


            //  System.out.println("Semana :: " + eSemana.getNumero() + " ->> " + UM + " _ " + DOIS + " _ " + TRES + " :: " + maior);


            if (maior == 0) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            } else if (maior == 1) {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_verde);
            } else {
                canvas.drawRect(come, h - 15, come + coluna_largura_pintar2, h - 5, pintor_branco);
            }

            come += coluna_largura2;

        }


        return bmp;

    }


}
