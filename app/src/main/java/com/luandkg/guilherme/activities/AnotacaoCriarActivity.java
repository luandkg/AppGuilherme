package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.fragments.AnotadorFragment;

public class AnotacaoCriarActivity extends AppCompatActivity {


    private TextInputEditText mTextInputEditText;
    private Button mOK;
    private Button mCancelar;

    private AnotacaoCriarActivity mContexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anotacao_criar);


        mTextInputEditText = (TextInputEditText) findViewById(R.id.Anotacao_criar_texto);
        mOK = (Button) findViewById(R.id.Anotacao_criar_ok);
        mCancelar = (Button) findViewById(R.id.Anotacao_criar_cancelar);

        mTextInputEditText.setText("");

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
                    SistemaDeAnotacoes.criar(mTextInputEditText.getText().toString());

                    AnotadorFragment.FAZER();

                    mContexto.finish();
                }


            }
        });


    }
}