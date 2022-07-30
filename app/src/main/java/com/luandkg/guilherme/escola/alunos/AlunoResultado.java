package com.luandkg.guilherme.escola.alunos;

import com.luandkg.guilherme.TesteAlteradorDeData;
import com.luandkg.guilherme.Versionador;
import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeRealizada;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.verkuz.Verkuz;
import com.luandkg.guilherme.utils.Matematica;

import java.util.ArrayList;

public class AlunoResultado {

    private String mID;
    private String mTurma;
    private String mNome;

    private int mAtividades;
    private int mRealizadas;
    private String mNotaFinal;
    private double mNotaFinalDouble;

    private String mNotaCompleta;
    private String mNotaAtrasadas;

    private ArrayList<AtividadeRealizada> mAtividadesLista;

    public AlunoResultado(String eID, String eTurma, String eNome) {
        mID = eID;

        mTurma = eTurma;
        mNome = eNome;
        mAtividades = 0;
        mRealizadas = 0;

        mNotaFinal = "";
        mNotaCompleta = "";
        mNotaAtrasadas = "";

        mNotaFinalDouble = 0.0;

        mAtividadesLista = new ArrayList<AtividadeRealizada>();
    }

    public String getID() {
        return mID;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }

    public void avaliar(String eData, String eArquivo, String eDataEntregue, boolean realizada, boolean mis_atestado) {
        mAtividades += 1;
        if (realizada) {
            mRealizadas += 1;
        }


        mAtividadesLista.add(new AtividadeRealizada(eData, eArquivo, eDataEntregue, realizada, mis_atestado));
    }

    public ArrayList<AtividadeRealizada> getAtividadesRealizadas() {
        return mAtividadesLista;
    }

    public boolean temAtividadeSim(String eData) {
        boolean ret = false;

        for (AtividadeRealizada atv : mAtividadesLista) {
            if (Data.toData(atv.getData()).isIgual(Data.toData(eData))) {
                if (atv.getStatus()) {
                    ret = true;
                }
                break;
            }
        }

        return ret;
    }


    public int getAtividades() {
        return mAtividades;
    }

    public int getRealizadas() {
        return mRealizadas;
    }

    public void calcular(double maximo) {

        double tx = (double) maximo / (double) mAtividades;
        double eNotaCompleta = (double) mRealizadas * tx;

        boolean temAtrasadas = false;

        int atrasadas_quantidade = 0;
        int atrasadas_sem_atestado_quantidade = 0;
        int atrasadas_com_atestado_quantidade = 0;

        String tem = "NAO";
        String tem_atestado = "NAO";

        for (AtividadeRealizada aa : mAtividadesLista) {
            if (aa.getStatus()) {
                if (aa.isAtrasada()) {
                    temAtrasadas = true;
                    atrasadas_quantidade += 1;
                    tem = "SIM";
                    if (aa.temAtestado()) {
                        tem_atestado = "SIM";
                        atrasadas_com_atestado_quantidade += 1;
                    } else {
                        atrasadas_sem_atestado_quantidade += 1;
                    }
                }
            }
        }

        if (Verkuz.isTeste(new Versionador())) {
            if (mNome.contentEquals("RENANO DALI")) {
                atrasadas_quantidade = 0;
            }
        }


        double descontar_atrasadas = (double) atrasadas_sem_atestado_quantidade * (tx / 4.0);

        if (eNotaCompleta > 0.0) {
            mNotaFinalDouble = eNotaCompleta - descontar_atrasadas;
        } else {
            mNotaFinalDouble = 0.0;
        }


        mNotaCompleta = Matematica.getNumeroRealPTBR(eNotaCompleta);
        mNotaFinal = Matematica.getNumeroRealPTBR(mNotaFinalDouble);


        System.out.println("Aluno : " + mNome);
        System.out.println("\t - Atividades : " + mAtividadesLista.size());
        System.out.println("\t - Nota : " + mNotaCompleta);
        System.out.println("\t - Tem atrasadas : " + tem);
        System.out.println("\t - Com atestado : " + tem_atestado);

        if (temAtrasadas) {

            mNotaAtrasadas = Matematica.getNumeroRealPTBR(descontar_atrasadas);

            System.out.println("\t - Atrasadas Todas : " + atrasadas_quantidade);
            System.out.println("\t - Atrasadas Sem Atestado : " + atrasadas_sem_atestado_quantidade);
            System.out.println("\t - Atrasadas Com Atestado : " + atrasadas_com_atestado_quantidade);
            System.out.println("\t - Descontar : " + mNotaAtrasadas);
            System.out.println("\t - Nova Nota : " + mNotaFinal);


        }

    }


    public ArrayList<AtividadeRealizada> getAtividadesAtrasadas() {

        ArrayList<AtividadeRealizada> ret = new ArrayList<AtividadeRealizada>();

        for (AtividadeRealizada aa : mAtividadesLista) {
            if (aa.getStatus()) {
                if (aa.isAtrasada()) {
                    ret.add(aa);
                }
            }
        }

        return ret;
    }

    public ArrayList<AtividadeRealizada> getAtividadesAtrasadasComAtestado() {

        ArrayList<AtividadeRealizada> ret = new ArrayList<AtividadeRealizada>();

        for (AtividadeRealizada aa : mAtividadesLista) {
            if (aa.getStatus()) {
                if (aa.isAtrasada()) {
                    if (aa.temAtestado()) {
                        ret.add(aa);
                    }
                }
            }
        }

        return ret;
    }


    public ArrayList<AtividadeRealizada> getAtividadesAtrasadasSemAtestado() {

        ArrayList<AtividadeRealizada> ret = new ArrayList<AtividadeRealizada>();

        for (AtividadeRealizada aa : mAtividadesLista) {
            if (aa.getStatus()) {
                if (aa.isAtrasada()) {
                    if (!aa.temAtestado()) {
                        ret.add(aa);
                    }
                }
            }
        }

        return ret;
    }


    public String getNotaCompleta() {
        return mNotaCompleta;
    }

    public String getNotaAtrasadas() {
        return mNotaAtrasadas;
    }

    public String getNotaFinal() {
        return mNotaFinal;
    }

    public double getNotaFinalDouble() {
        return mNotaFinalDouble;
    }


    public String getFrase() {
        String v = String.valueOf(getRealizadas() + " de " + getAtividades());
        return v;
    }

}
