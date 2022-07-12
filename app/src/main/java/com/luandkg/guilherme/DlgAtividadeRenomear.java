package com.luandkg.guilherme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.transferencias.RecarregadorDeAtividades;
import com.luandkg.guilherme.transferencias.RenomeadorDeAtividade;

public class DlgAtividadeRenomear extends AppCompatActivity {

    private String mAtividade;
    private String mNome;

    private TextInputEditText mTextInputEditText;
    private Button mOK;
    private Button mCancelar;

    private DlgAtividadeRenomear mContexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlg_atividade_renomear);

        Intent it = getIntent();
        mAtividade = it.getStringExtra("ATIVIDADE");
        mNome = it.getStringExtra("NOME");

        mTextInputEditText = (TextInputEditText) findViewById(R.id.DLG_ATIVIDADE_RENOMEAR_TEXTO);
        mOK = (Button) findViewById(R.id.DLG_ATIVIDADE_RENOMEAR_OK);
        mCancelar = (Button) findViewById(R.id.DLG_ATIVIDADE_RENOMEAR_CANCELAR);

        mTextInputEditText.setText(mNome);

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

                Avaliador.atividade_renomear(mAtividade, mTextInputEditText.getText().toString());

                RenomeadorDeAtividade.NOME_ATUAL = mTextInputEditText.getText().toString();

                if (RenomeadorDeAtividade.TEM) {
                    RenomeadorDeAtividade.FAZER();
                }

                if (RecarregadorDeAtividades.TEM) {
                    RecarregadorDeAtividades.FAZER();
                }

                mContexto.finish();

            }
        });

    }
}