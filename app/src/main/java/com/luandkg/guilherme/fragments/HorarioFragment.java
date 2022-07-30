package com.luandkg.guilherme.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.guilherme.databinding.FragmentHorarioBinding;
import com.luandkg.guilherme.escola.utils.PaletaDeCores;
import com.luandkg.guilherme.escola.Professor;
import com.luandkg.guilherme.escola.render.Temporizador;
import com.luandkg.guilherme.professores.Professores;
import com.luandkg.guilherme.escola.TocadorDeSinalEscolar;
import com.luandkg.guilherme.listas.Listar_Turmas;
import com.luandkg.guilherme.escola.coisas.TurmaItem;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;

import java.util.ArrayList;


public class HorarioFragment extends Fragment {

    private FragmentHorarioBinding binding;

    private ImageView mProgresoEscola;
    private Temporizador mTemporrizador;

    private Button mHoje;
    private Button mSegunda;
    private Button mTerca;
    private Button mQuarta;
    private Button mQuinta;
    private Button mSexta;
    private ListView mLista;
    private TextView mSigla;
    private TextView mFazendo;
    private TextView mRodape;

    private TocadorDeSinalEscolar mTocadorDeSinalEscolar;
    private String mSelecionado = "";
    private Listar_Turmas mListarTurmas;
    private Professor mProfessor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHorarioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mProgresoEscola = binding.progressoGeral;

        mHoje = binding.hoje;
        mSegunda = binding.segunda;
        mTerca = binding.terca;
        mQuarta = binding.quarta;
        mQuinta = binding.quinta;
        mSexta = binding.sexta;
      //  mSigla = binding.contador;
        mFazendo = binding.fazendo;
        mRodape = binding.rodape;

        mLista = binding.lista;


        mTemporrizador = new Temporizador();
        mTemporrizador.setProgressGrande(0);

        mProgresoEscola.setBackgroundColor(Color.TRANSPARENT);

        mSelecionado = "HOJE";

        mProfessor = Professores.getProfessorCorrente();

        mRodape.setText("Professor " + mProfessor.getNome());

        mListarTurmas = new Listar_Turmas(getContext(), new ArrayList<TurmaItem>(), mProfessor);


        menu();


        mHoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = "HOJE";
                menu();
            }
        });

        mSegunda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.SEGUNDA;
                menu();
            }
        });

        mTerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.TERCA;
                menu();
            }
        });

        mQuarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.QUARTA;
                menu();
            }
        });

        mQuinta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.QUINTA;
                menu();
            }
        });

        mSexta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelecionado = Calendario.SEXTA;
                menu();
            }
        });


        mTemporrizador.set(Calendario.getDiaAtual(), Calendario.getTempoDoDia(), false, mProfessor);
        mTemporrizador.podeDesenhar();

        mTocadorDeSinalEscolar = new TocadorDeSinalEscolar( mFazendo, mProgresoEscola, mTemporrizador,mProfessor);

        mTocadorDeSinalEscolar.run();

        return root;
    }

    public void menu() {


        int VERDE = PaletaDeCores.VERDE;
        int VERMELHO = PaletaDeCores.VERMELHO;
        int AMARELO = PaletaDeCores.AMARELO;

        mHoje.setBackgroundColor(VERMELHO);
        mSegunda.setBackgroundColor(VERMELHO);
        mTerca.setBackgroundColor(VERMELHO);
        mQuarta.setBackgroundColor(VERMELHO);
        mQuinta.setBackgroundColor(VERMELHO);
        mSexta.setBackgroundColor(VERMELHO);


        if (mSelecionado.contentEquals("HOJE")) {

            mHoje.setBackgroundColor(VERDE);

            String eHoje = Calendario.getDiaAtual();

            if (Calendario.isIgual(Calendario.SEGUNDA, eHoje)) {
                mSegunda.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.TERCA, eHoje)) {
                mTerca.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.QUARTA, eHoje)) {
                mQuarta.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.QUINTA, eHoje)) {
                mQuinta.setBackgroundColor(AMARELO);
            } else if (Calendario.isIgual(Calendario.SEXTA, eHoje)) {
                mSexta.setBackgroundColor(AMARELO);
            }

            ArrayList<TurmaItem> hoje_aulas = TurmaItem.filtrarTurmas(Calendario.getDiaAtual(), mProfessor.getTurmas());


            if (mProfessor.estou_em_ferias(Data.toData(Calendario.getADM()))) {
                hoje_aulas.clear();
            }

            mListarTurmas = new Listar_Turmas(getContext(), hoje_aulas, mProfessor);

            mLista.setAdapter(mListarTurmas);

        } else if (Calendario.isIgual(Calendario.SEGUNDA, mSelecionado)) {

            organizar(mHoje, mSegunda, Calendario.SEGUNDA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.TERCA, mSelecionado)) {

            organizar(mHoje, mTerca, Calendario.TERCA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.QUARTA, mSelecionado)) {

            organizar(mHoje, mQuarta, Calendario.QUARTA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.QUINTA, mSelecionado)) {

            organizar(mHoje, mQuinta, Calendario.QUINTA, VERDE, AMARELO);

        } else if (Calendario.isIgual(Calendario.SEXTA, mSelecionado)) {

            organizar(mHoje, mSexta, Calendario.SEXTA, VERDE, AMARELO);

        }


    }


    public void organizar(Button eHoje, Button eButton, String eDia, int VERDE, int AMARELO) {

        eButton.setBackgroundColor(VERDE);

        ArrayList<TurmaItem> hoje_aulas = TurmaItem.filtrarTurmas(eDia, mProfessor.getTurmas());

        if (mProfessor.estou_em_ferias(Data.toData(Calendario.getADM()))) {
            hoje_aulas.clear();
        }

        mListarTurmas = new Listar_Turmas(getContext(), hoje_aulas, mProfessor);
        mLista.setAdapter(mListarTurmas);

        if (Calendario.getDiaAtual().contentEquals(eDia)) {
            eHoje.setBackgroundColor(VERDE);
            eButton.setBackgroundColor(AMARELO);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}