package com.luandkg.guilherme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.luandkg.guilherme.utils.FS;

public class MainActivity extends AppCompatActivity {

    private Button BTN_TURMA_H;
    private Button BTN_TURMA_I;
    private Button BTN_TURMA_J;
    private Button BTN_TURMA_K;
    private Button BTN_TURMA_L;
    private Button BTN_TURMA_M;
    private Button BTN_TURMA_N;

    private Button BTN_TURMA_PD_H;
    private Button BTN_TURMA_PD_I;

    private Button BTN_RESUMO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        FS.pedirPermissoes(this);
        FS.verifyStoragePermissions(this);

        BTN_TURMA_H = (Button) findViewById(R.id.BTN_TURMA_01);
        BTN_TURMA_I = (Button) findViewById(R.id.BTN_TURMA_02);
        BTN_TURMA_J = (Button) findViewById(R.id.BTN_TURMA_03);
        BTN_TURMA_K = (Button) findViewById(R.id.BTN_TURMA_04);
        BTN_TURMA_L = (Button) findViewById(R.id.BTN_TURMA_05);
        BTN_TURMA_M = (Button) findViewById(R.id.BTN_TURMA_06);
        BTN_TURMA_N = (Button) findViewById(R.id.BTN_TURMA_07);

        BTN_TURMA_PD_H = (Button) findViewById(R.id.BTN_TURMA_08);
        BTN_TURMA_PD_I = (Button) findViewById(R.id.BTN_TURMA_09);

        BTN_RESUMO = (Button) findViewById(R.id.BTN_RESUMO);

        Local.organizarPastas();


        turma_clicar(BTN_TURMA_H, "7H");
        turma_clicar(BTN_TURMA_I, "7I");
        turma_clicar(BTN_TURMA_J, "7J");
        turma_clicar(BTN_TURMA_K, "7K");
        turma_clicar(BTN_TURMA_L, "7L");
        turma_clicar(BTN_TURMA_M, "7M");
        turma_clicar(BTN_TURMA_N, "7N");

        turma_clicar(BTN_TURMA_PD_H, "PD7H");
        turma_clicar(BTN_TURMA_PD_I, "PD7I");


        BTN_RESUMO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), FluxoActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(intent);

            }
        });

    }


    public void turma_clicar(Button eBotaoTurma, String eTurma) {

        eBotaoTurma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), Atividades.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("TURMA", eTurma);

                v.getContext().startActivity(intent);

            }
        });

    }


}