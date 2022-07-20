package com.luandkg.guilherme.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.metodo_avaliativo.Atividade;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.transferencias.RecarregadorDeAtividades;
import com.luandkg.guilherme.utils.CaixaSimNao;
import com.luandkg.guilherme.listas.Listar_AtividadesDaTurma;

import java.io.File;
import java.util.ArrayList;

import com.luandkg.guilherme.utils.Acao;
import com.luandkg.guilherme.utils.FS;

public class AtividadesActivity extends AppCompatActivity {

    private String mTurma;
    private TextView ATIVIDADES_TITULO;
    private Button ATIVIDADES_CRIAR_ATIVIDADES;
    private Button ATIVIDADES_VER_NOTAS;

    private ListView ATIVIDADES_LISTAGEM;

    private ArrayList<Atividade> mAtividades;
    private Listar_AtividadesDaTurma Listar_AtividadesDaTurma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        Intent it = getIntent();
        mTurma = it.getStringExtra("TURMA");


        ATIVIDADES_TITULO = (TextView) findViewById(R.id.ATIVIDADES_TITULO);
        ATIVIDADES_CRIAR_ATIVIDADES = (Button) findViewById(R.id.ATIVIDADES_CRIAR_ATIVIDADE);
        ATIVIDADES_LISTAGEM = (ListView) findViewById(R.id.ATIVIDADES_LISTAGEM);
        ATIVIDADES_VER_NOTAS = (Button) findViewById(R.id.ATIVIDADES_VER_NOTAS);


        ATIVIDADES_TITULO.setText("TURMA : " + mTurma);


        mAtividades = new ArrayList<Atividade>();
        Listar_AtividadesDaTurma = new Listar_AtividadesDaTurma(this, mAtividades);


        atividades_carregar();


        RecarregadorDeAtividades.iniciar(new Acao() {
            @Override
            public void fazer() {
                atividades_carregar();
            }
        });


        ATIVIDADES_CRIAR_ATIVIDADES.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                CaixaSimNao.perguntar(view.getContext(), "CRIAR ATIVIDADE", "Deseja aplicar uma nova atividade ?", new Acao() {
                    @Override
                    public void fazer() {

                        Avaliador.criarAtividade(mTurma);

                        atividades_carregar();


                    }
                });


            }
        });


        ATIVIDADES_VER_NOTAS.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.R)
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(view.getContext(), QuadroDeNotasActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("TURMA", mTurma);

                view.getContext().startActivity(intent);


            }
        });


    }


    public void atividades_carregar() {

        mAtividades.clear();

        File pasta = FS.getPasta(Local.LOCAL_AVALIACOES);

        for (File ativiade : pasta.listFiles()) {

            if (ativiade.getName().startsWith(mTurma)) {
                mAtividades.add(new Atividade(ativiade.getName()));
            }


        }

        ATIVIDADES_LISTAGEM.setAdapter(Listar_AtividadesDaTurma);

    }


}