package com.luandkg.guilherme.professores;


import com.luandkg.guilherme.escola.tempo.Horario;
import com.luandkg.guilherme.escola.Professor;
import com.luandkg.guilherme.escola.coisas.TurmaItem;
import com.luandkg.guilherme.libs.tempo.TempoEstampa;

public class Professores {

    public static Professor getProfessorCorrente() {

        String QUEM = "GG";

        Professor mProfessor = null;

        if (QUEM.contentEquals("LUAN")) {
            mProfessor = Luan.getLuan();
        } else if (QUEM.contentEquals("GG")) {
            mProfessor = Guilherme.getGuilherme();
        } else if (QUEM.contentEquals("FREITAS")) {
            mProfessor = Freitas.getLucas();
        } else if (QUEM.contentEquals("DANTAS")) {
            mProfessor = Dantas.getDantas();
        }

        //testes(mProfessor);

        return mProfessor;
    }

}
