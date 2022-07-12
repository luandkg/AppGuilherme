package com.luandkg.guilherme.professores;


import com.luandkg.guilherme.escola.tempo.Horario;
import com.luandkg.guilherme.escola.Professor;
import com.luandkg.guilherme.escola.coisas.TurmaItem;
import com.luandkg.guilherme.utils.tempo.TempoEstampa;

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

    public static void testes(Professor eProfessor) {


        eProfessor.getTurmas().clear();

        TempoEstampa comecar = new TempoEstampa(21, 5);
        TempoEstampa terminar = new TempoEstampa(21, 10);


        eProfessor.definirAula(new TurmaItem("QUARTA", "7I", "CN", comecar, terminar, 2, "12"));
        eProfessor.definirAula(new TurmaItem("QUARTA", "7F", "CN", Horario.auto_mais(comecar, 5), Horario.auto_mais(terminar, 5), 2, "13"));
        eProfessor.definirAula(new TurmaItem("QUARTA", "I", "IN", Horario.auto_mais(comecar, 5), Horario.auto_mais(terminar, 5), 1, "20"));
        eProfessor.definirAula(new TurmaItem("QUARTA", "9N", "CN", Horario.auto_mais(comecar, 5), Horario.auto_mais(terminar, 5), 1, "08"));
        eProfessor.definirAula(new TurmaItem("QUARTA", "8A", "CN", Horario.auto_mais(comecar, 5), Horario.auto_mais(terminar, 5), 1, "08"));
        eProfessor.definirAula(new TurmaItem("QUARTA", "9C", "CN", Horario.auto_mais(comecar, 5), Horario.auto_mais(terminar, 5), 2, "08"));



    }


}
