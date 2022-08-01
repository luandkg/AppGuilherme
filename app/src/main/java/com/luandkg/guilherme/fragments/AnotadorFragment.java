package com.luandkg.guilherme.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.activities.AnotacaoCriarActivity;
import com.luandkg.guilherme.activities.AnotacaoEditarActivity;
import com.luandkg.guilherme.activities.AnotacoesArquivadasActivity;
import com.luandkg.guilherme.activities.LancarAtestadoActivity;
import com.luandkg.guilherme.escola.anotacoes.Anotacao;
import com.luandkg.guilherme.escola.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.databinding.FragmentAnotadorBinding;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.utils.Acao;
import com.luandkg.guilherme.utils.CaixaSimNao;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;


public class AnotadorFragment extends Fragment {

    private FragmentAnotadorBinding mInterface;

    private ListView LISTA_AVISOS;
    private Button BTN_ARQUIVADAS;

    private Button BTN_CRIAR_ANOTACAO;
    private Button BTN_LANCAR_ATESTADO;

    private Context mContexto;
    private Acao RECARREGAR_LISTA;

    public static boolean TEM = false;
    public static Acao ACAO = null;

    public static void iniciar(Acao eAcao) {
        TEM = true;
        ACAO = eAcao;
    }

    public static void FAZER() {
        ACAO.fazer();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInterface = FragmentAnotadorBinding.inflate(inflater, container, false);
        View root = mInterface.getRoot();

        LISTA_AVISOS = mInterface.anotadorListagem;
        BTN_CRIAR_ANOTACAO = mInterface.anotadorCriar;
        BTN_LANCAR_ATESTADO = mInterface.anotadorLancarAtestado;

        BTN_ARQUIVADAS = mInterface.anotadorArquivadas;

        BTN_CRIAR_ANOTACAO.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), AnotacaoCriarActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);


            }
        });


        BTN_LANCAR_ATESTADO.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), LancarAtestadoActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);


            }
        });


        BTN_ARQUIVADAS.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), AnotacoesArquivadasActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);

            }
        });

        mContexto = this.getContext();

        ArrayList<Anotacao> anotacaos = SistemaDeAnotacoes.listar();

        RECARREGAR_LISTA = getRecarregar();
        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, anotacaos.size(), onItem(anotacaos)));

        iniciar(getRecarregar());


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<Anotacao> anotacaos = SistemaDeAnotacoes.listar();
        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, anotacaos.size(), onItem(anotacaos)));

    }

    public Acao getRecarregar() {
        return new Acao() {
            @Override
            public void fazer() {

                ArrayList<Anotacao> anotacaos = SistemaDeAnotacoes.listar();

                LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, anotacaos.size(), onItem(anotacaos)));
            }
        };
    }


    public Itenizador onItem(ArrayList<Anotacao> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Anotacao eAnotacao = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_anotacao, inflater, parent);

                TextView texto = mWidget.getTextView(R.id.item_aviso_texto);
                Button arquivar = mWidget.getButton(R.id.item_aviso_arquivar);
                Button remover = mWidget.getButton(R.id.item_aviso_remover);


                texto.setText(eAnotacao.getMensagem());

                arquivar.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        SistemaDeAnotacoes.arquivar(eAnotacao.getID());
                        RECARREGAR_LISTA.fazer();

                    }
                });

                remover.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        CaixaSimNao.perguntar(mContexto, "Excluir", "Deseja remover a anotação ?", new Acao() {
                            @Override
                            public void fazer() {

                                SistemaDeAnotacoes.remover(eAnotacao.getID());
                                RECARREGAR_LISTA.fazer();

                            }
                        });


                    }
                });

                texto.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), AnotacaoEditarActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("ID", eAnotacao.getID());
                        view.getContext().startActivity(intent);


                    }
                });


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mInterface = null;
    }

}