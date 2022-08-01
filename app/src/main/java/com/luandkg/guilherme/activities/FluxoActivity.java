package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.alunos.Aluno;
import com.luandkg.guilherme.escola.render.BimestreImagem;
import com.luandkg.guilherme.escola.render.FluxoDeEntrega;
import com.luandkg.guilherme.escola.tempo.BimestreTemporal;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.calendarios.SEDF_22;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;

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

        ArrayList<Data> datas = eSEDF_22.getTerceiro();
        String hoje = Calendario.getADMComBarras();


        if (eSEDF_22.getTerceiro().size() > 0) {
            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(datas).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(datas).getTempoLegivel());
        }


        FLUXO_IV_ATIVIDADES.setImageBitmap(FluxoDeEntrega.criarFluxoDeEntrega(alunos, datas));


        int acabar = BimestreTemporal.getDiasParaAcabar(hoje, datas);
        int progresso = BimestreTemporal.getPorcentagem(hoje, datas);

        FLUXO_IV_TEMPO.setImageBitmap(BimestreImagem.onBimestre(progresso, acabar));


    }




}