package com.luandkg.guilherme.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.Versionador;
import com.luandkg.guilherme.anotacoes.Anotacao;
import com.luandkg.guilherme.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.verkuz.Commit;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.listas.Lista_Commits;
import com.luandkg.guilherme.utils.Acao;
import com.luandkg.guilherme.utils.CaixaSimNao;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;

public class SobreActivity extends AppCompatActivity {

    private Lista_Commits mLista_Commits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);


        TextView TV_VERSAO = (TextView) findViewById(R.id.sobre_versao);
        TextView TV_DESENVOLVEDOR = (TextView) findViewById(R.id.sobre_desenvolvedor);
        TextView TV_DATA = (TextView) findViewById(R.id.sobre_data);
        ListView LISTA = (ListView) findViewById(R.id.sobre_listagem);

        Versionador v = new Versionador();
        v.init();


        TV_VERSAO.setText(v.getVersaoCompleta());
        TV_DESENVOLVEDOR.setText(v.getAutor());
        TV_DATA.setText(Data.toData(v.getData()).getFluxo());

        String ultima_data = v.getData();

        mLista_Commits = new Lista_Commits(getBaseContext(), v.getCommits(), ultima_data);

        LISTA.setAdapter(mLista_Commits);

    }


}