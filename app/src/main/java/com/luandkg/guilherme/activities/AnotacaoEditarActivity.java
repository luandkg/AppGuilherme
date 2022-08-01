package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.anotacoes.Anotacao;
import com.luandkg.guilherme.escola.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.fragments.AnotadorFragment;

public class AnotacaoEditarActivity extends AppCompatActivity {

    private TextInputEditText mTextInputEditText;
    private Button mOK;
    private Button mCancelar;

    private AnotacaoEditarActivity mContexto;
    private String mID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacao_editar);

        mTextInputEditText = (TextInputEditText) findViewById(R.id.Anotacao_editar_texto);
        mOK = (Button) findViewById(R.id.Anotacao_editar_ok);
        mCancelar = (Button) findViewById(R.id.Anotacao_editar_cancelar);


        Intent it = getIntent();
        mID = it.getStringExtra("ID");

        Anotacao anotacao = SistemaDeAnotacoes.getAnotacao(mID);

        mTextInputEditText.setText(anotacao.getMensagem());

        mContexto = this;

        mCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContexto.finish();

            }
        });

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTextInputEditText.getText().toString().length() > 0) {

                    SistemaDeAnotacoes.alterar(mID, mTextInputEditText.getText().toString());

                    AnotadorFragment.FAZER();

                    mContexto.finish();
                }


            }
        });
    }
}