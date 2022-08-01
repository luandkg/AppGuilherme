package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.alunos.AlunoPerfil;
import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeRealizada;
import com.luandkg.guilherme.escola.calendarios.ESTANCIA3_3BIMESTRE;
import com.luandkg.guilherme.escola.render.FluxoDeEntrega;
import com.luandkg.guilherme.escola.metodo_avaliativo.Perfilizar;
import com.luandkg.guilherme.escola.utils.Emblemador;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;

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

        AlunoPerfil perfil = Perfilizar.getPerfil(aluno_id, ESTANCIA3_3BIMESTRE.getSemanas());


        TV_Aluno.setText(perfil.getNome());
        TV_AlunoNota.setText(perfil.getNotaFinal());

        IV_Aluno.setImageBitmap(FluxoDeEntrega.criarFluxoDeEntregaDoAluno(ESTANCIA3_3BIMESTRE.getBimestre(), perfil.getAtividadesRealizadas()));

        //  LV_Semanas.setAdapter(new Lista_SemanaAvaliacao(getBaseContext(), perfil.getSemanas()));
        LV_Semanas.setAdapter(new ListaGenerica(getBaseContext(), perfil.getAtividadesRealizadas().size(), onItem(perfil.getAtividadesRealizadas())));

    }

    public Itenizador onItem(ArrayList<AtividadeRealizada> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                AtividadeRealizada eAtividade = eLista.get(position);
                Widget mWidget = new Widget(R.layout.item_atividade, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_atividade_nome);
                TextView status = mWidget.getTextView(R.id.item_atividade_status);
                TextView modo = mWidget.getTextView(R.id.item_atividade_tempo);

                ImageView grafico = mWidget.getImageView(R.id.item_atividade_imagem);


                String e_data = eAtividade.getData();

                boolean e_status = eAtividade.getStatus();
                String s_status = "NÃO REALIZADA";

                if (e_status) {
                    s_status = "REALIZADA !";
                }

                nome.setText(Data.toData(e_data).getFluxo());


                status.setText(s_status);
                modo.setText("");

                if (eAtividade.getStatus()) {
                    grafico.setImageBitmap(Emblemador.criarAluno("#66bb6a", ""));
                } else {
                    grafico.setImageBitmap(Emblemador.criarAluno("#f4511e", ""));
                }

                if (eAtividade.isAtrasada()) {
                    modo.setText(String.valueOf(eAtividade.temAtestado()));
                } else {
                    modo.setText("");
                }

                mWidget.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                    }
                });

                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}