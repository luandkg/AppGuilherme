package com.luandkg.guilherme.escola;

import android.content.Context;
import android.graphics.Bitmap;

import com.luandkg.guilherme.escola.organizacao.AtividadeEspecial;
import com.luandkg.guilherme.escola.organizacao.Professor;
import com.luandkg.guilherme.escola.organizacao.TurmaItem;
import com.luandkg.guilherme.escola.render.Logo;
import com.luandkg.guilherme.utils.Notificar;

public class Informante {


    public static void limpar(Professor mProfessor, String HOJE_DIA) {


        for (AtividadeEspecial ti : mProfessor.getAtividades()) {
            if (!ti.getDiaDaSemana().contentEquals(HOJE_DIA)) {
                ti.desavisar();
            }
        }

        for (TurmaItem ti : mProfessor.getTurmas()) {
            if (!ti.getDiaDaSemana().contentEquals(HOJE_DIA)) {
                ti.desavisar();
            }
        }


    }


    public static void notificar(Context mContexto, TurmaItem ti, Professor mProfessor) {

        if (!ti.foiAvisado()) {

            ti.avisar();

            String eTitulo = "";
            String eAviso = "";
            String eModo = "";

            if (ti.getNome().contentEquals("I")) {

                eModo = "INTERVALO";
                eTitulo = "Intervalo";
                eAviso = "Vamos descansar um pouco....";

            } else {

                eModo = "AULA";

                eTitulo = "Trocar de Turma";
                eAviso = "Ir para a turma " + ti.getNome();

                if (mProfessor.getSigla().contentEquals("GG")) {
                    eTitulo = "Ir para turma " + ti.getNome() + " sala " + ti.getSala();
                    eAviso = "Hora de trocar de sala, vamos para a turma " + ti.getNome() + " na sala " + ti.getSala();
                }

            }

            Bitmap meu_icone = Logo.criarLogo(eModo, ti.getNome());

            Notificar.notifique(mContexto, "AVISOS", eTitulo, eAviso, meu_icone);

        }


    }

    public static void notificarAlgo(Context mContexto, String sigla,String eTitulo,String texto) {

        Bitmap meu_icone = Logo.criarLogo("INTERVALO",sigla);

        Notificar.notifique(mContexto, "AVISOS", eTitulo, texto, meu_icone);

    }

    public static void notificarIndo(Context mContexto, AtividadeEspecial ti, Professor mProfessor) {

        if (!ti.foiAvisado()) {

            ti.avisar();

            String eTitulo = "";
            String eAviso = "";
            String eModo = "";


            eModo = "AULA";

            eTitulo = "Ir trabalhar";
            eAviso = "Hora de ir para a " + ti.getNome();


            Bitmap meu_icone = Logo.criarLogo(eModo, ti.getSigla());

            Notificar.notifique(mContexto, "AVISOS", eTitulo, eAviso, meu_icone);

        }


    }

}
