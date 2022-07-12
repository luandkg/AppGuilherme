package com.luandkg.guilherme;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.guilherme.dkg.DKG;
import com.luandkg.guilherme.dkg.DKGObjeto;
import com.luandkg.guilherme.escola.Aluno;
import com.luandkg.guilherme.escola.AlunoResultado;
import com.luandkg.guilherme.escola.Avaliador;
import com.luandkg.guilherme.escola.BimestreTemporal;
import com.luandkg.guilherme.escola.ContandoData;
import com.luandkg.guilherme.escola.CoresDeAvaliacao;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.SEDF_22;
import com.luandkg.guilherme.utils.FS;
import com.luandkg.guilherme.utils.tempo.Data;
import com.luandkg.guilherme.utils.tempo.Tempo;

import java.io.File;
import java.util.ArrayList;

public class FluxoActivity extends AppCompatActivity {

    private ImageView FLUXO_IV_ATIVIDADES;
    private TextView TV_BIMESTRE_INICIO;
    private TextView TV_BIMESTRE_FIM;
    private ImageView FLUXO_IV_TEMPO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fluxo);


        FLUXO_IV_ATIVIDADES = (ImageView) findViewById(R.id.FLUXO_IV_ATIVIDADES);
        FLUXO_IV_TEMPO = (ImageView) findViewById(R.id.FLUXO_IV_TEMPO);
        TV_BIMESTRE_INICIO = (TextView) findViewById(R.id.FLUXO_TV_BIMESTRE_INICIO);
        TV_BIMESTRE_FIM = (TextView) findViewById(R.id.FLUXO_TV_BIMESTRE_FIM);

        ArrayList<Aluno> alunos = Escola.getAlunosVisiveis();


        SEDF_22 eSEDF_22 = new SEDF_22();

        ArrayList<Data> datas = eSEDF_22.getSegundo();
        String hoje = Tempo.getADMComBarras();


        if (eSEDF_22.getSegundo().size() > 0) {
            TV_BIMESTRE_INICIO.setText(Tempo.filtrar_primeira(datas).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Tempo.filtrar_ultima(datas).getTempoLegivel());
        }


        FLUXO_IV_ATIVIDADES.setImageBitmap(Fluxo.criarFluxoDeEntrega(alunos, datas));


        int acabar = BimestreTemporal.getDiasParaAcabar(hoje, datas);
        int progresso = BimestreTemporal.getPorcentagem(hoje, datas);

        FLUXO_IV_TEMPO.setImageBitmap(Fluxo.onBimestre(progresso, acabar));


    }




}