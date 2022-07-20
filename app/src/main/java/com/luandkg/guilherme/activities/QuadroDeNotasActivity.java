package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.alunos.OrdenarAlunos;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.utils.CoresDeValidacao;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.utils.Matematica;

import java.util.ArrayList;

import com.luandkg.guilherme.listas.Listar_Notas;
import com.luandkg.guilherme.utils.Strings;

public class QuadroDeNotasActivity extends AppCompatActivity {

    private TextView QUADRO_DE_NOTAS_TITULO;


    private TextInputEditText QUADRO_DE_NOTAS_TEXTO;
    private Button QUADRO_DE_NOTAS_ALTERAR;

    private ListView QUADRO_DE_NOTAS_LISTAGEM;

    private String mTurma;
    private String mAvaliacao;

    private double AVALIADOR_VALOR = 0.0;
    private boolean AVALIADOR_VALIDO;

    private ArrayList<AlunoResultado> mAlunos;
    private Listar_Notas Listar_Notas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quadro_de_notas);

        Intent it = getIntent();
        mTurma = it.getStringExtra("TURMA");


        QUADRO_DE_NOTAS_TITULO = (TextView) findViewById(R.id.QUADRO_DE_NOTAS_TITULO);

        QUADRO_DE_NOTAS_TEXTO = (TextInputEditText) findViewById(R.id.QUADRO_DE_NOTAS_TEXTO);
        QUADRO_DE_NOTAS_ALTERAR = (Button) findViewById(R.id.QUADRO_DE_NOTAS_ALTERAR);


        QUADRO_DE_NOTAS_LISTAGEM = (ListView) findViewById(R.id.QUADRO_DE_NOTAS_LISTAGEM);

        QUADRO_DE_NOTAS_TITULO.setText("TURMA : " + mTurma);

        mAvaliacao = Strings.seVazioEntao(Avaliador.getAvaliacao(mTurma), "1,0");

        QUADRO_DE_NOTAS_TEXTO.setText(mAvaliacao);

        validar();

        listar();

        clicar();


    }

    public void validar() {

        mAvaliacao = QUADRO_DE_NOTAS_TEXTO.getText().toString();

        if (Matematica.isNumeroReal(mAvaliacao)) {
            AVALIADOR_VALOR = Double.parseDouble(mAvaliacao.replace(",", "."));
            AVALIADOR_VALIDO = true;
        } else {
            AVALIADOR_VALOR = 0.0;
            AVALIADOR_VALIDO = false;
        }
    }

    public void listar() {

        if (AVALIADOR_VALIDO) {
            QUADRO_DE_NOTAS_ALTERAR.setBackgroundColor(Color.parseColor(CoresDeValidacao.VALIDO));

            mAlunos = Escola.getAlunosComResultado(mTurma);
            Avaliador.avaliar_resultado(mTurma, AVALIADOR_VALOR, mAlunos);
            Listar_Notas = new Listar_Notas(this, OrdenarAlunos.ordendarComResultados(mAlunos));
            QUADRO_DE_NOTAS_LISTAGEM.setAdapter(Listar_Notas);

        } else {
            QUADRO_DE_NOTAS_ALTERAR.setBackgroundColor(Color.parseColor(CoresDeValidacao.INVALIDO));

            Listar_Notas = new Listar_Notas(this, new ArrayList<AlunoResultado>());
            QUADRO_DE_NOTAS_LISTAGEM.setAdapter(Listar_Notas);
        }


    }

    public void clicar() {

        QUADRO_DE_NOTAS_ALTERAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                validar();

                Avaliador.mudarAvaliacao(mTurma, mAvaliacao);

                listar();


            }
        });

    }

}