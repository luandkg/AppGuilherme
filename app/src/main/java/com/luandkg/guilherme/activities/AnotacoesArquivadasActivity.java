package com.luandkg.guilherme.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.anotacoes.Anotacao;
import com.luandkg.guilherme.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.utils.Acao;
import com.luandkg.guilherme.utils.CaixaSimNao;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;

public class AnotacoesArquivadasActivity extends AppCompatActivity {

    private ListView LISTA_AVISOS;
    private Button BTN_REMOVER_TODOS;

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacoes_arquivadas);

        LISTA_AVISOS = (ListView) findViewById(R.id.anotacoes_arquivadas_listagem);
        BTN_REMOVER_TODOS = (Button) findViewById(R.id.anotacoes_arquivadas_removertodos);

        mContexto = getBaseContext();


        BTN_REMOVER_TODOS.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                CaixaSimNao.perguntar(mContexto, "Remover Todos", "Deseja remover todas as anotações arquivadas ?", new Acao() {
                    @Override
                    public void fazer() {

                        SistemaDeAnotacoes.removerTodosArquivados();

                        ArrayList<Anotacao> anotacaos = SistemaDeAnotacoes.listar_arquivadas();
                        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, anotacaos.size(), onItem(anotacaos)));


                    }
                });


            }
        });


        ArrayList<Anotacao> anotacaos = SistemaDeAnotacoes.listar_arquivadas();

        LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, anotacaos.size(), onItem(anotacaos)));

        RECARREGAR_LISTA = getRecarregar();

        iniciar(getRecarregar());

    }

    public Acao getRecarregar() {
        return new Acao() {
            @Override
            public void fazer() {

                ArrayList<Anotacao> anotacaos = SistemaDeAnotacoes.listar_arquivadas();

                LISTA_AVISOS.setAdapter(new ListaGenerica(mContexto, anotacaos.size(), onItem(anotacaos)));
            }
        };
    }

    public Itenizador onItem(ArrayList<Anotacao> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Anotacao eAnotacao = eLista.get(position);


                Widget mWidget = new Widget(R.layout.item_anotacao_arquivado, inflater, parent);

                TextView texto = mWidget.getTextView(R.id.item_anotacao_arquivado_texto);
                Button desarquivar = mWidget.getButton(R.id.item_anotacao_arquivado_desarquivar);
                Button remover = mWidget.getButton(R.id.item_anotacao_arquivado_remover);


                texto.setText(eAnotacao.getMensagem());

                desarquivar.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        SistemaDeAnotacoes.desarquivar(eAnotacao.getID());
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


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }

}