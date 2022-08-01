package com.luandkg.guilherme.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.guilherme.databinding.FragmentBuscarAlunoBinding;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.alunos.AlunoPerfil;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.metodo_avaliativo.Perfilizar;
import com.luandkg.guilherme.escola.calendarios.ESTANCIA3_3BIMESTRE;
import com.luandkg.guilherme.listas.Lista_BuscandoAlunos;
import com.luandkg.guilherme.utils.Strings;

import java.util.ArrayList;


public class BuscarAlunoFragment extends Fragment {

    private FragmentBuscarAlunoBinding mInterface;

    private TextInputEditText mBuscar;
    private Button mBuscarAcao;
    private ListView mListagem;


    private ArrayList<AlunoResultado> mAlunos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInterface = FragmentBuscarAlunoBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        mBuscar = (TextInputEditText) mInterface.BUSCARALUNOET;
        mBuscarAcao = (Button) mInterface.BUSCARALUNOBUSCAR;
        mListagem = (ListView) mInterface.BUSCARALUNOLISTAGEM;

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

        mBuscarAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String aluno_procurando = mBuscar.getText().toString().toLowerCase();

                if (aluno_procurando.length() > 0) {

                    ArrayList<AlunoResultado> alunos_continuos = mAlunos;
                    ArrayList<AlunoPerfil> ret = new ArrayList<AlunoPerfil>();

                    for (AlunoResultado aa : alunos_continuos) {
                        if (aa.getNome().toLowerCase().startsWith(aluno_procurando)) {

                            AlunoPerfil perfil = Perfilizar.getPerfil(aa.getID(), ESTANCIA3_3BIMESTRE.getSemanas());

                            ret.add(perfil);
                            if (ret.size() > 30) {
                                break;
                            }
                        }
                    }


                    mListagem.setAdapter(new Lista_BuscandoAlunos(v.getContext(), ret));


                }


            }
        });

        return root;
    }
}