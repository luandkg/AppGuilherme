package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.alunos.AlunoPerfil;
import com.luandkg.guilherme.escola.tempo.ESTANCIA3_2BIMESTRE;
import com.luandkg.guilherme.escola.render.FluxoDeEntrega;
import com.luandkg.guilherme.escola.metodo_avaliativo.Perfilizar;

public class AlunoRelatorioActivity extends AppCompatActivity {

    private TextView TV_Aluno;
    private ImageView IV_Aluno;
    private ListView LV_Semanas;
    private TextView TV_AlunoNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno_relatorio);


        TV_Aluno = (TextView) findViewById(R.id.relatorio_nome);
        TV_AlunoNota = (TextView) findViewById(R.id.relatorio_nota);
        IV_Aluno = (ImageView) findViewById(R.id.relatorio_fluxo);
        LV_Semanas = (ListView) findViewById(R.id.relatorio_semanas);

        Intent it = getIntent();
        String aluno_id = it.getStringExtra("AlunoID");

        AlunoPerfil perfil = Perfilizar.getPerfil(aluno_id, ESTANCIA3_2BIMESTRE.getSemanas());


        TV_Aluno.setText(perfil.getNome());
        TV_AlunoNota.setText(perfil.getNotaFinal());

         IV_Aluno.setImageBitmap(FluxoDeEntrega.criarFluxoDeEntregaDoAluno(ESTANCIA3_2BIMESTRE.getBimestre(), perfil.getAtividadesRealizadas()));

        //   LV_Semanas.setAdapter(new Lista_SemanaAvaliacao(getBaseContext(), perfil.getSemanas()));

    }


}