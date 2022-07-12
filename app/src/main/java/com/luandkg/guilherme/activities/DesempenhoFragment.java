package com.luandkg.guilherme.activities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.luandkg.guilherme.databinding.FragmentDesempenhoBinding;
import com.luandkg.guilherme.databinding.FragmentSlideshowBinding;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.metodo_avaliativo.CoresDeAvaliacao;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.utils.Strings;

import java.util.ArrayList;


public class DesempenhoFragment extends Fragment {

    private FragmentDesempenhoBinding binding;
    private ArrayList<AlunoResultado> mAlunos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDesempenhoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        ImageView IV_DESEMPENHO = binding.desempenhoGrafico;


        ArrayList<String> turmas = new ArrayList<String>();

        turmas.add("7H");
        turmas.add("7I");
        turmas.add("7J");
        turmas.add("7K");
        turmas.add("7L");
        turmas.add("7M");
        turmas.add("7N");

        turmas.add("PD7H");
        turmas.add("PD7I");

        mAlunos = Escola.getAlunosComResultadoDaEscola();

        for (String turma : turmas) {

            String AVALIADOR_VALOR_STRING = Strings.seVazioEntao(Avaliador.getAvaliacao(turma), "1,0");
            double AVALIADOR_VALOR = Double.parseDouble(AVALIADOR_VALOR_STRING.replace(",", "."));

            Avaliador.avaliar_resultado(turma, AVALIADOR_VALOR, mAlunos);

        }

        IV_DESEMPENHO.setImageBitmap(onResultadoFinal_Pre(mAlunos));


        return root;

    }

    public static Bitmap onResultadoFinal_Pre(ArrayList<AlunoResultado> alunos_continuos) {

        int w = 600;
        int h = 200;

        Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
        Bitmap bmp = Bitmap.createBitmap(w, h, conf); // this creates a MUTABLE bitmap
        Canvas canvas = new Canvas(bmp);


        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setAntiAlias(true);
        paint.setStrokeCap(false ? Paint.Cap.ROUND : Paint.Cap.BUTT);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#455a64"));

        Paint pintor_vermelho = new Paint();
        pintor_vermelho.setColor(Color.parseColor("#f4511e"));
        pintor_vermelho.setStyle(Paint.Style.FILL);

        Paint pintor_amarelo = new Paint();
        pintor_amarelo.setColor(Color.parseColor(CoresDeAvaliacao.BOM));
        pintor_amarelo.setStyle(Paint.Style.FILL);


        Paint pintor_verde = new Paint();
        pintor_verde.setColor(Color.parseColor("#7cb342"));
        pintor_verde.setStyle(Paint.Style.FILL);


        int ex = 50;

        int px = 0;

        int altura = h;

        for (int a = 0; a <= 10; a++) {

            paint.setColor(Color.parseColor("#455a64"));

            int total = pre_quantosDesse(alunos_continuos, a);

           // total = 5;

            double prop = (double) total / (double) alunos_continuos.size();

            int real = (int) (prop * 100.0F)*2;

            //real = 40;

            if (real > 0) {
                canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, paint);

                Paint pp = null;

                if (a >= 0 && a < 5) {
                    pp = pintor_vermelho;
                } else if (a >= 5 && a < 7) {
                    pp = pintor_amarelo;
                } else {
                    pp = pintor_verde;
                }

                canvas.drawRect(ex + px, altura - real - 20, ex + px + 20, altura - 20, pp);


                paint.setColor(Color.parseColor("#fafafa"));

                if (String.valueOf(total).length() == 3) {
                    canvas.drawText(String.valueOf(total), ex + px, altura - real - 30, paint);
                } else {
                    canvas.drawText(String.valueOf(total), ex + px + 5, altura - real - 30, paint);
                }


            }


            paint.setColor(Color.parseColor("#000000"));
            canvas.drawText(String.valueOf(a), ex + px + 5, altura - 5, paint);


            px += 50;
        }

        return bmp;

    }


    public static int pre_quantosDesse(ArrayList<AlunoResultado> alunos_continuos, int valor) {

        int f = 0;

        for (AlunoResultado v : alunos_continuos) {

            double nota =v.getNotaFinalDouble();

            if (nota >= valor && (nota < (valor + 1.0))) {
                f += 1;
            }
        }

        return f;

    }
}