package com.luandkg.guilherme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.Versionador;
import com.luandkg.guilherme.libs.tempo.Data;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);


        TextView TV_VERSAO = (TextView) findViewById(R.id.sobre_versao);
        TextView TV_DESENVOLVEDOR = (TextView) findViewById(R.id.sobre_desenvolvedor);
        TextView TV_DATA = (TextView) findViewById(R.id.sobre_data);

        Versionador v = new Versionador();
        v.init();


        TV_VERSAO.setText(v.getVersaoCompleta());
        TV_DESENVOLVEDOR.setText(v.getAutor());
        TV_DATA.setText(Data.toData(v.getData()).getFluxo());

    }
}