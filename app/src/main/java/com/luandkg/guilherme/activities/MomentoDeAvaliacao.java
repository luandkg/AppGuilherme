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
import com.luandkg.guilherme.escola.alunos.Aluno;
import com.luandkg.guilherme.escola.alunos.AlunoAtividade;
import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeGraficos;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.tempo.ESTANCIA3_2BIMESTRE;
import com.luandkg.guilherme.escola.tempo.ESTANCIA3_3BIMESTRE;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.tempo.SemanaContinua;
import com.luandkg.guilherme.escola.utils.PaletaDeCores;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.professores.Guilherme;
import com.luandkg.guilherme.utils.Widget;
import com.luandkg.guilherme.utils.Strings;
import com.luandkg.guilherme.libs.tempo.Data;

import java.util.ArrayList;

public class MomentoDeAvaliacao extends AppCompatActivity {

    private String mSemana;

    private TextView mTitulo;
    private ListView LISTA;
    private ImageView IMG_VISUALIZADOR;

    private TextView TX_ALFA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_momento_de_avaliacao);

        mTitulo = (TextView) findViewById(R.id.momento_avaliacao_titulo);
        LISTA = (ListView) findViewById(R.id.momento_avaliacao_lista);
        IMG_VISUALIZADOR = (ImageView) findViewById(R.id.momento_avaliacao_grafico);

        TX_ALFA = (TextView) findViewById(R.id.momento_avaliacao_alfa);

        Intent it = getIntent();
        mSemana = it.getStringExtra("Semana");

        mTitulo.setText(mSemana);

        ArrayList<SemanaContinua> LISTA_DE_SEMANAS = ESTANCIA3_2BIMESTRE.getSemanas();
        ArrayList<Data> TERCEIRO_BIMESTRE = ESTANCIA3_2BIMESTRE.getBimestre();

        ArrayList<Aluno> mAlunos = Escola.getAlunosVisiveis();

        int index = 0;

        if (mSemana.length() > 0) {
            index = Integer.parseInt(mSemana);
        }


        if (LISTA_DE_SEMANAS.size() >= index) {


            if (index < LISTA_DE_SEMANAS.size()) {
                mTitulo.setText(LISTA_DE_SEMANAS.get(index).getStatus());

                SemanaContinua semana = LISTA_DE_SEMANAS.get(index);


                ArrayList<AlunoAtividade> avaliados = new ArrayList<AlunoAtividade>();

                for (String turma : Guilherme.getGuilherme().listar_turmas()) {

                    String AVALIADOR_VALOR_STRING = Strings.seVazioEntao(Avaliador.getAvaliacao(turma), "1,0");
                    double AVALIADOR_VALOR = Double.parseDouble(AVALIADOR_VALOR_STRING.replace(",", "."));

                    Avaliador.avaliar_atividades(turma,semana.getDatas(), mAlunos, avaliados);

                }



                IMG_VISUALIZADOR.setImageBitmap(AtividadeGraficos.criarAvaliacao(getUnicos(avaliados), mAlunos.size()));

                int alfa = 0;

                for (AlunoAtividade eAluno : avaliados) {
                    if (eAluno.getNota().contentEquals("SIM")) {
                        alfa += 1;
                    }
                }

                TX_ALFA.setText(String.valueOf(alfa));


                LISTA.setAdapter(new ListaGenerica(getBaseContext(), avaliados.size(), onItem(avaliados)));
            } else {
                mTitulo.setText(mSemana);
            }

        } else {
            mTitulo.setText(mSemana);
        }


    }

    public int getUnicos(ArrayList<AlunoAtividade> alunos) {

        ArrayList<String> ids = new ArrayList<String>();

        for (AlunoAtividade aluno : alunos) {
            String sid = aluno.getID();

            if (!ids.contains(sid)) {
                ids.add(sid);
            }
        }

        return ids.size();

    }

    public Itenizador onItem(ArrayList<AlunoAtividade> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                AlunoAtividade eAlunoChamada = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_momento_avaliativo, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_momento_avaliativo_aluno);
                TextView data = mWidget.getTextView(R.id.item_momento_avaliativo_data);


                nome.setText(eAlunoChamada.getNome());

                data.setText((String.valueOf(Data.organizarData(eAlunoChamada.getData())).replace("/2022", "")));

                //   data.setText(Calendario.inverter_mes_dia(String.valueOf(eAlunoChamada.getData())));
                //  nota.setText(String.valueOf(eAlunoChamada.getNotaFinal()));

                data.setBackgroundColor(PaletaDeCores.VERDE);


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}