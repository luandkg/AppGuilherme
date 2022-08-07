package com.luandkg.guilherme.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.luandkg.guilherme.AvisoData;
import com.luandkg.guilherme.R;
import com.luandkg.guilherme.escola.anotacoes.Anotacao;
import com.luandkg.guilherme.escola.anotacoes.SistemaDeAnotacoes;
import com.luandkg.guilherme.escola.utils.Emblemador;
import com.luandkg.guilherme.escola.utils.PaletaDeCores;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.tempo.DiaSemanal;
import com.luandkg.guilherme.listas.Itenizador;
import com.luandkg.guilherme.listas.ListaGenerica;
import com.luandkg.guilherme.utils.Widget;

import java.util.ArrayList;

public class AgendaActivity extends AppCompatActivity {

    private ArrayList<TextView> mSemana;
    private ArrayList<TextView> mDias;
    private ArrayList<TextView> mStatus;

    private ArrayList<TextView> marcarDias;
    private ArrayList<TextView> marcarStatus;

    private ArrayList<AvisoData> avisos_com_data;

    private ListView LISTA;


    private int COR_FUNDO_LIMPO = 0;

    private ArrayList<Data> mAno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        LISTA = (ListView) findViewById(R.id.agenda_lista);

        mSemana = new ArrayList<TextView>();
        mDias = new ArrayList<TextView>();
        mStatus = new ArrayList<TextView>();

        marcarDias = new ArrayList<TextView>();
        marcarStatus = new ArrayList<TextView>();

        avisos_com_data = new ArrayList<AvisoData>();

        mAno = Calendario.construirAno(2022, DiaSemanal.Sabado, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);

        pegar_semana();
        pegar_dias();
        pegar_status();

        COR_FUNDO_LIMPO = mDias.get(0).getDrawingCacheBackgroundColor();

        limpar_tudo();

        Data eHoje = Calendario.getDataHoje();

        Data mes_primeiro = new Data(1, 1, 1, DiaSemanal.Domingo);
        int ultimo_dia_do_mes = 0;

        for (Data data : mAno) {
            if (data.getMes() == eHoje.getMes() && ultimo_dia_do_mes == 0) {
                mes_primeiro = data;
                ultimo_dia_do_mes = data.getDia();
            } else if (data.getMes() == eHoje.getMes()) {
                ultimo_dia_do_mes = data.getDia();
            }
        }

        int pular = 0;

        if (mes_primeiro.getDiaSemanal() == DiaSemanal.Segunda) {
            pular = 1;
        } else if (mes_primeiro.getDiaSemanal() == DiaSemanal.Terca) {
            pular = 2;
        } else if (mes_primeiro.getDiaSemanal() == DiaSemanal.Quarta) {
            pular = 3;
        } else if (mes_primeiro.getDiaSemanal() == DiaSemanal.Quinta) {
            pular = 4;
        } else if (mes_primeiro.getDiaSemanal() == DiaSemanal.Sexta) {
            pular = 5;
        } else if (mes_primeiro.getDiaSemanal() == DiaSemanal.Sabado) {
            pular = 6;
        }

        int contar = 1;
        int pulando = 0;

        for (TextView tv : mDias) {

            if (pulando == pular && contar <= ultimo_dia_do_mes) {
                tv.setText(String.valueOf(contar));
                marcarDias.add(tv);

                clicar(tv, contar, mes_primeiro.getMes());

                contar += 1;
            } else {
                tv.setText("");
                pulando += 1;
            }

        }


        pulando = 0;
        for (TextView tv : mStatus) {

            if (pulando == pular) {
                marcarStatus.add(tv);
            } else {
                pulando += 1;
            }

        }

        String esse_mes = String.valueOf(mes_primeiro.getMes());
        if (esse_mes.length() == 1) {
            esse_mes = "0" + esse_mes;
        }

        ArrayList<Anotacao> avisos = SistemaDeAnotacoes.listar();

        for (Anotacao avisar : avisos) {
            String msg = avisar.getMensagem().toLowerCase();
            if (msg.contains("#agenda")) {
                if (msg.contains("/" + esse_mes)) {

                    String dia = getDiaDoMes(msg, esse_mes);

                    if (dia.length() > 0) {

                        int i_dia = Integer.parseInt(dia);

                        if (i_dia >= 0 && i_dia < marcarStatus.size()) {
                            marcarStatus.get(i_dia - 1).setBackgroundColor(PaletaDeCores.VERMELHO);
                            avisos_com_data.add(new AvisoData(i_dia, mes_primeiro.getMes(), avisar));
                        }

                    }

                }
            }
        }


    }


    public void clicar(TextView eBotao, int dia, int mes) {


        eBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<AvisoData> filtrados = new ArrayList<AvisoData>();

                for (AvisoData aviso : avisos_com_data) {
                    if (aviso.getDia() == dia && aviso.getMes() == mes) {
                        filtrados.add(aviso);
                    }
                }

                LISTA.setAdapter(new ListaGenerica(v.getContext(), filtrados.size(), onItem(filtrados)));

            }
        });


    }

    public Itenizador onItem(ArrayList<AvisoData> eLista) {
        return new Itenizador() {

            @Override
            public View onItem(LayoutInflater inflater, ViewGroup parent, int position) {


                Anotacao eAviso = eLista.get(position).getAviso();


                Widget mWidget = new Widget(R.layout.item_aviso_agendado, inflater, parent);

                TextView texto = mWidget.getTextView(R.id.item_aviso_agendado_texto);
                ImageView icone = mWidget.getImageView(R.id.item_aviso_agendado_icone);

                icone.setImageBitmap(Emblemador.criarItemAgenda("3"));

                texto.setText(eAviso.getMensagem().replace("#agenda", ""));

                texto.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.R)
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(view.getContext(), AnotacaoEditarActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("ID", eAviso.getID());
                        view.getContext().startActivity(intent);


                    }
                });


                mWidget.setAuto();


                return mWidget.getView();

            }

        };
    }


    public String getDiaDoMes(String msg, String esse_mes) {
        String ret = "";

        int i = 0;
        int o = msg.length();

        String montando = "";

        while (i < o) {
            String letra = String.valueOf(msg.charAt(i));
            if (letra.contentEquals(" ")) {
                if (montando.endsWith("/" + esse_mes)) {
                    ret = montando.replace("/" + esse_mes, "");
                    break;
                }
                montando = "";
            } else {
                montando += letra;
            }
            i += 1;
        }

        return ret;
    }


    public void limpar_tudo() {
        for (TextView tv : mDias) {
            tv.setBackgroundColor(COR_FUNDO_LIMPO);
        }
        for (TextView tv : mStatus) {
            tv.setBackgroundColor(COR_FUNDO_LIMPO);
        }
    }

    public void pegar_semana() {

        mSemana.add((TextView) findViewById(R.id.semana_domingo));
        mSemana.add((TextView) findViewById(R.id.semana_segunda));
        mSemana.add((TextView) findViewById(R.id.semana_terca));
        mSemana.add((TextView) findViewById(R.id.semana_quarta));
        mSemana.add((TextView) findViewById(R.id.semana_quinta));
        mSemana.add((TextView) findViewById(R.id.semana_sexta));
        mSemana.add((TextView) findViewById(R.id.semana_sabado));
    }

    public void pegar_dias() {

        mDias.add((TextView) findViewById(R.id.semana_01_01));
        mDias.add((TextView) findViewById(R.id.semana_01_02));
        mDias.add((TextView) findViewById(R.id.semana_01_03));
        mDias.add((TextView) findViewById(R.id.semana_01_04));
        mDias.add((TextView) findViewById(R.id.semana_01_05));
        mDias.add((TextView) findViewById(R.id.semana_01_06));
        mDias.add((TextView) findViewById(R.id.semana_01_07));

        mDias.add((TextView) findViewById(R.id.semana_02_01));
        mDias.add((TextView) findViewById(R.id.semana_02_02));
        mDias.add((TextView) findViewById(R.id.semana_02_03));
        mDias.add((TextView) findViewById(R.id.semana_02_04));
        mDias.add((TextView) findViewById(R.id.semana_02_05));
        mDias.add((TextView) findViewById(R.id.semana_02_06));
        mDias.add((TextView) findViewById(R.id.semana_02_07));

        mDias.add((TextView) findViewById(R.id.semana_03_01));
        mDias.add((TextView) findViewById(R.id.semana_03_02));
        mDias.add((TextView) findViewById(R.id.semana_03_03));
        mDias.add((TextView) findViewById(R.id.semana_03_04));
        mDias.add((TextView) findViewById(R.id.semana_03_05));
        mDias.add((TextView) findViewById(R.id.semana_03_06));
        mDias.add((TextView) findViewById(R.id.semana_03_07));

        mDias.add((TextView) findViewById(R.id.semana_04_01));
        mDias.add((TextView) findViewById(R.id.semana_04_02));
        mDias.add((TextView) findViewById(R.id.semana_04_03));
        mDias.add((TextView) findViewById(R.id.semana_04_04));
        mDias.add((TextView) findViewById(R.id.semana_04_05));
        mDias.add((TextView) findViewById(R.id.semana_04_06));
        mDias.add((TextView) findViewById(R.id.semana_04_07));

        mDias.add((TextView) findViewById(R.id.semana_05_01));
        mDias.add((TextView) findViewById(R.id.semana_05_02));
        mDias.add((TextView) findViewById(R.id.semana_05_03));
        mDias.add((TextView) findViewById(R.id.semana_05_04));
        mDias.add((TextView) findViewById(R.id.semana_05_05));
        mDias.add((TextView) findViewById(R.id.semana_05_06));
        mDias.add((TextView) findViewById(R.id.semana_05_07));

    }

    public void pegar_status() {

        mStatus.add((TextView) findViewById(R.id.status_01_01));
        mStatus.add((TextView) findViewById(R.id.status_01_02));
        mStatus.add((TextView) findViewById(R.id.status_01_03));
        mStatus.add((TextView) findViewById(R.id.status_01_04));
        mStatus.add((TextView) findViewById(R.id.status_01_05));
        mStatus.add((TextView) findViewById(R.id.status_01_06));
        mStatus.add((TextView) findViewById(R.id.status_01_07));

        mStatus.add((TextView) findViewById(R.id.status_02_01));
        mStatus.add((TextView) findViewById(R.id.status_02_02));
        mStatus.add((TextView) findViewById(R.id.status_02_03));
        mStatus.add((TextView) findViewById(R.id.status_02_04));
        mStatus.add((TextView) findViewById(R.id.status_02_05));
        mStatus.add((TextView) findViewById(R.id.status_02_06));
        mStatus.add((TextView) findViewById(R.id.status_02_07));

        mStatus.add((TextView) findViewById(R.id.status_03_01));
        mStatus.add((TextView) findViewById(R.id.status_03_02));
        mStatus.add((TextView) findViewById(R.id.status_03_03));
        mStatus.add((TextView) findViewById(R.id.status_03_04));
        mStatus.add((TextView) findViewById(R.id.status_03_05));
        mStatus.add((TextView) findViewById(R.id.status_03_06));
        mStatus.add((TextView) findViewById(R.id.status_03_07));

        mStatus.add((TextView) findViewById(R.id.status_04_01));
        mStatus.add((TextView) findViewById(R.id.status_04_02));
        mStatus.add((TextView) findViewById(R.id.status_04_03));
        mStatus.add((TextView) findViewById(R.id.status_04_04));
        mStatus.add((TextView) findViewById(R.id.status_04_05));
        mStatus.add((TextView) findViewById(R.id.status_04_06));
        mStatus.add((TextView) findViewById(R.id.status_04_07));

        mStatus.add((TextView) findViewById(R.id.status_05_01));
        mStatus.add((TextView) findViewById(R.id.status_05_02));
        mStatus.add((TextView) findViewById(R.id.status_05_03));
        mStatus.add((TextView) findViewById(R.id.status_05_04));
        mStatus.add((TextView) findViewById(R.id.status_05_05));
        mStatus.add((TextView) findViewById(R.id.status_05_06));
        mStatus.add((TextView) findViewById(R.id.status_05_07));

    }
}