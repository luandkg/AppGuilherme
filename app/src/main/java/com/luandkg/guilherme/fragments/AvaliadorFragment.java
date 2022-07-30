package com.luandkg.guilherme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.Versionador;
import com.luandkg.guilherme.activities.AtividadesActivity;
import com.luandkg.guilherme.activities.FluxoActivity;
import com.luandkg.guilherme.databinding.FragmentHomeBinding;

public class AvaliadorFragment extends Fragment {

    private FragmentHomeBinding binding;

    private Button BTN_TURMA_H;
    private Button BTN_TURMA_I;
    private Button BTN_TURMA_J;
    private Button BTN_TURMA_K;
    private Button BTN_TURMA_L;
    private Button BTN_TURMA_M;
    private Button BTN_TURMA_N;

    private Button BTN_TURMA_PD_H;
    private Button BTN_TURMA_PD_I;

    private Button BTN_RESUMO;
    private TextView TXT_VERSAO;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        BTN_TURMA_H = binding.BTNTURMA01;
        BTN_TURMA_I = binding.BTNTURMA02;
        BTN_TURMA_J = binding.BTNTURMA03;
        BTN_TURMA_K = binding.BTNTURMA04;
        BTN_TURMA_L = binding.BTNTURMA05;
        BTN_TURMA_M = binding.BTNTURMA06;
        BTN_TURMA_N = binding.BTNTURMA07;

        BTN_TURMA_PD_H = binding.BTNTURMA08;

        BTN_TURMA_PD_I = binding.BTNTURMA09;


        BTN_RESUMO = binding.BTNRESUMO;

        TXT_VERSAO = binding.sistemaVersao;

        Local.organizarPastas();


        turma_clicar(BTN_TURMA_H, "7H");
        turma_clicar(BTN_TURMA_I, "7I");
        turma_clicar(BTN_TURMA_J, "7J");
        turma_clicar(BTN_TURMA_K, "7K");
        turma_clicar(BTN_TURMA_L, "7L");
        turma_clicar(BTN_TURMA_M, "7M");
        turma_clicar(BTN_TURMA_N, "7N");

        turma_clicar(BTN_TURMA_PD_H, "PD7H");
        turma_clicar(BTN_TURMA_PD_I, "PD7I");


        BTN_RESUMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), FluxoActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });

        Versionador eVersionador = new Versionador();

        eVersionador.init();

        TXT_VERSAO.setText(eVersionador.getVersaoCompletaComAutor());

        return root;
    }

    public void turma_clicar(Button eBotaoTurma, String eTurma) {

        eBotaoTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), AtividadesActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("TURMA", eTurma);

                v.getContext().startActivity(intent);

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}