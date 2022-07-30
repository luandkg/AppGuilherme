package com.luandkg.guilherme.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.alunos.AlunoPerfil;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.gg.ESTANCIA3_3BIMESTRE;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.metodo_avaliativo.Perfilizar;
import com.luandkg.guilherme.fragments.AnotadorFragment;
import com.luandkg.guilherme.fragments.Lista_SelecionarUmAluno;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.listas.Lista_BuscandoAlunos;
import com.luandkg.guilherme.utils.CaixaDeDialogoSelecionarData;
import com.luandkg.guilherme.utils.Strings;

import java.util.ArrayList;
import java.util.Calendar;

public class LancarAtestadoActivity extends AppCompatActivity {

    private Button mOK;
    private Button mCancelar;

    private Button BTN_DATA;
    private Button mBuscarAcao;
    private com.google.android.material.textfield.TextInputEditText mBuscar;
    private ListView mListagem;

    private Data DATA_SELECIONADA = new Data(0, 0, 0);
    private ArrayList<AlunoResultado> mAlunos;
    private Lista_SelecionarUmAluno mEscolhedorDeAluno;

    private LancarAtestadoActivity mContexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancar_atestado);

        mOK = (Button) findViewById(R.id.Anotacao_lancar_atestado_ok);
        mCancelar = (Button) findViewById(R.id.Anotacao_lancar_atestado_cancelar);


        BTN_DATA = (Button) findViewById(R.id.Anotacao_lancar_atestado);
        mBuscarAcao = (Button) findViewById(R.id.LANCAR_ATESTADO_BUSCAR_ALUNO_BUSCAR);
        mBuscar = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.LANCAR_ATESTADO_BUSCAR_ALUNO_ET);
        mListagem = (ListView) findViewById(R.id.LANCAR_ATESTADO_BUSCAR_ALUNO_LISTAGEM);

        DATA_SELECIONADA.set(Calendario.getData());

        System.out.println(DATA_SELECIONADA.getFluxo());

        BTN_DATA.setText(DATA_SELECIONADA.getFluxo());

        mEscolhedorDeAluno = new Lista_SelecionarUmAluno(getBaseContext(), new ArrayList<AlunoPerfil>());

        mContexto = this;

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

                    mEscolhedorDeAluno = new Lista_SelecionarUmAluno(v.getContext(), ret);
                    mListagem.setAdapter(mEscolhedorDeAluno);


                }


            }
        });


        BTN_DATA.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                CaixaDeDialogoSelecionarData.DatePickerFragment newFragment = new CaixaDeDialogoSelecionarData.DatePickerFragment();
                newFragment.BTN_DATA = BTN_DATA;
                newFragment.DATA_SELECIONADA = DATA_SELECIONADA;
                newFragment.show(getSupportFragmentManager(), "datePicker");


            }


        });


        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContexto.finish();

            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mEscolhedorDeAluno.temSelecionado()) {

                    SistemaDeAnotacoes.criar(mEscolhedorDeAluno.getSelecionado().getNome() + " :: " + DATA_SELECIONADA.getFluxo());

                    AnotadorFragment.FAZER();

                    mContexto.finish();

                }


            }
        });

    }


}