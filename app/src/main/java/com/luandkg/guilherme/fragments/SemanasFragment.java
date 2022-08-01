package com.luandkg.guilherme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.guilherme.utils.AvaliadorAnimattor;
import com.luandkg.guilherme.activities.MomentoDeAvaliacao;
import com.luandkg.guilherme.escola.render.AvaliadorImagem;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.databinding.FragmentSemanasBinding;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeGraficos;
import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadesAnalisador;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.calendarios.ESTANCIA3_3BIMESTRE;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.tempo.SemanaContinua;
import com.luandkg.guilherme.escola.tempo.SemanaContinuaCarregada;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.professores.Guilherme;
import com.luandkg.guilherme.utils.Widget;
import com.luandkg.guilherme.utils.Strings;
import com.luandkg.guilherme.libs.tempo.Data;

import java.util.ArrayList;

public class SemanasFragment extends Fragment {

    private ListView LISTA_AVALIACAO;
    private ImageView IMG_VISUALIZADOR;
    private TextView TV_ATIVIDADES;

    private ArrayList<AlunoResultado> mAlunos;
    private ArrayList<Data> SEGUNDO_BIMESTRE;
    private ArrayList<SemanaContinua> LISTA_DE_SEMANAS;
    private ArrayList<SemanaContinuaCarregada> mSemanas;

    private FragmentSemanasBinding mInterface;
    private AvaliadorAnimattor mAvaliadorAnimattor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mInterface = FragmentSemanasBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        LISTA_AVALIACAO = (ListView) mInterface.semanasLista;
        IMG_VISUALIZADOR = (ImageView) mInterface.semanasFluxo;
        TV_ATIVIDADES = (TextView) mInterface.semanasAtividades;

        mAlunos = Escola.getAlunosComResultadoDaEscola();


        SEGUNDO_BIMESTRE = ESTANCIA3_3BIMESTRE.getBimestre();
       LISTA_DE_SEMANAS = ESTANCIA3_3BIMESTRE.getSemanas();

        int atividades = 0;


        for (String turma : Guilherme.getGuilherme().listar_turmas()) {

            String AVALIADOR_VALOR_STRING = Strings.seVazioEntao(Avaliador.getAvaliacao(turma), "1,0");
            double AVALIADOR_VALOR = Double.parseDouble(AVALIADOR_VALOR_STRING.replace(",", "."));

            Avaliador.avaliar_resultado(turma, AVALIADOR_VALOR, mAlunos);

        }

        ArrayList<SemanaContinuaCarregada> ret = new ArrayList<SemanaContinuaCarregada>();

        int si = 0;

        for (SemanaContinua semana : LISTA_DE_SEMANAS) {

            int todos = mAlunos.size();
            int fizeram = AtividadesAnalisador.contar(semana.getDatas(), mAlunos);

            System.out.println("SEMANA : " + si + " -->> " + fizeram);

            SemanaContinuaCarregada sc = new SemanaContinuaCarregada(si, "SEMANA DE AVALIÇÃO " + semana.getNome(), semana.getStatus(), todos, fizeram);
            ret.add(sc);

            atividades += fizeram;
            si += 1;
        }


        mSemanas = ret;


        TV_ATIVIDADES.setText(String.valueOf(atividades));

        LISTA_AVALIACAO.setAdapter(new ListaGenerica(getContext(), mSemanas.size(), onItem(mSemanas)));

        IMG_VISUALIZADOR.setImageBitmap(AvaliadorImagem.onFluxo(0, 0, mSemanas, mAlunos));


        mAvaliadorAnimattor = new AvaliadorAnimattor(getContext(), IMG_VISUALIZADOR);
        mAvaliadorAnimattor.run(0, 0, mSemanas, mAlunos);

        return root;
    }


    public Itenizador onItem(ArrayList<SemanaContinuaCarregada> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                SemanaContinuaCarregada eSemanaContinua = eLista.get(position);
                Widget mWidget = new Widget(R.layout.item_atividade, inflater, parent);

                TextView nome = mWidget.getTextView(R.id.item_atividade_nome);
                TextView status = mWidget.getTextView(R.id.item_atividade_status);
                TextView modo = mWidget.getTextView(R.id.item_atividade_tempo);

                ImageView grafico = mWidget.getImageView(R.id.item_atividade_imagem);


                int fizeram = eSemanaContinua.getFizeram();
                int total = eSemanaContinua.getTodos();


                nome.setText(eSemanaContinua.getNome());


                status.setText(eSemanaContinua.getStatus());
                modo.setText("");

                grafico.setImageBitmap(AtividadeGraficos.criarAvaliacao(fizeram, total));

                modo.setText(String.valueOf(fizeram));

                mWidget.getView().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Intent intent = new Intent(v.getContext(), MomentoDeAvaliacao.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("Semana", String.valueOf(eSemanaContinua.getNumero()));

                        v.getContext().startActivity(intent);


                    }
                });

                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


}