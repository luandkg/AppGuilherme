package com.luandkg.guilherme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.luandkg.guilherme.escola.metodo_avaliativo.Atividade;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.listas.onAvaliacao;
import com.luandkg.guilherme.transferencias.RecarregadorDeAtividades;
import com.luandkg.guilherme.transferencias.RenomeadorDeAtividade;
import com.luandkg.guilherme.utils.Acao;
import com.luandkg.guilherme.utils.CaixaSimNao;
import com.luandkg.guilherme.widgets.CaixaDeAtividade;

public class Avaliacao extends AppCompatActivity {

    private String mTurma = "";
    private String mAtividadeArquivo = "";

    private TextView AVALIACAO_TITULO;
    private Button AVALIACAO_SALVAR;
    private Button AVALIACAO_EXCLUIR;
    private Button AVALIACAO_RENOMEAR;
    private TextView AVALIACAO_NOME;

    private ListView AVALIACAO_LISTAGEM;
    private CaixaDeAtividade AVALIACAO_CAIXA;

    private onAvaliacao onAvaliacao;


    private Avaliacao mAvaliacao = null;
    private Atividade mAtividade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliacao);


        Intent it = getIntent();
        mTurma = it.getStringExtra("TURMA");
        mAtividadeArquivo = it.getStringExtra("ATIVIDADE");


        AVALIACAO_TITULO = (TextView) findViewById(R.id.AVALIACAO_TITULO);
        AVALIACAO_SALVAR = (Button) findViewById(R.id.AVALIACAO_SALVAR);
        AVALIACAO_EXCLUIR = (Button) findViewById(R.id.AVALIACAO_EXCLUIR);
        AVALIACAO_RENOMEAR = (Button) findViewById(R.id.AVALIACAO_RENOMEAR);
        AVALIACAO_NOME = (TextView) findViewById(R.id.AVALIACAO_NOME);

        AVALIACAO_LISTAGEM = (ListView) findViewById(R.id.AVALIACAO_LISTAGEM);
        AVALIACAO_CAIXA = (CaixaDeAtividade) findViewById(R.id.AVALIACAO_CAIXA);


        AVALIACAO_TITULO.setText("TURMA : " + mTurma);

        mAvaliacao = this;


        System.out.println("Turma :: " + mTurma);


        mAtividade = new Atividade(mAtividadeArquivo);

        mAtividade.adicionar_alunos(Escola.getAlunos(mTurma));
        mAtividade.organizar();


        AVALIACAO_NOME.setText(mAtividade.getNome());

        onAvaliacao = new onAvaliacao(this, mAtividade.getAlunos());
        AVALIACAO_LISTAGEM.setAdapter(onAvaliacao);


        AVALIACAO_CAIXA.setMudar(mAtividade.getAlunos().size(), mAtividade.contarPositivos(), mAtividade.getAlunos().size());


        AVALIACAO_SALVAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAtividade.salvar();

                AVALIACAO_CAIXA.setMudar(mAtividade.getAlunos().size(), mAtividade.contarPositivos(), mAtividade.getAlunos().size());

                if (RecarregadorDeAtividades.TEM) {
                    RecarregadorDeAtividades.FAZER();
                }

                Toast.makeText(v.getContext(), "Notas guardadas !", Toast.LENGTH_SHORT).show();


            }
        });


        AVALIACAO_EXCLUIR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CaixaSimNao.perguntar(v.getContext(), "EXCLUIR ATIVIDADE", "Deseja excluir essa atividade ?", new Acao() {
                    @Override
                    public void fazer() {

                        Avaliador.atividade_excluir(mAtividadeArquivo);

                        if (RecarregadorDeAtividades.TEM) {
                            RecarregadorDeAtividades.FAZER();
                        }

                        mAvaliacao.finish();

                    }
                });


            }
        });


        AVALIACAO_RENOMEAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RenomeadorDeAtividade.iniciar(new Acao() {
                    @Override
                    public void fazer() {


                        AVALIACAO_NOME.setText(RenomeadorDeAtividade.NOME_ATUAL);
                        mAtividade.setNome(RenomeadorDeAtividade.NOME_ATUAL);

                    }
                });

                RenomeadorDeAtividade.NOME_ATUAL = mAtividade.getNome();

                Intent intent = new Intent(v.getContext(), DlgAtividadeRenomear.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("ATIVIDADE", mAtividadeArquivo);
                intent.putExtra("NOME", mAtividade.getNome());

                v.getContext().startActivity(intent);


            }
        });

    }
}