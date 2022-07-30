package com.luandkg.guilherme.escola.tempo;

import com.luandkg.guilherme.escola.coisas.TurmaItem;
import com.luandkg.guilherme.professores.Professores;

import java.util.ArrayList;

public class Hoje {

    public static ArrayList<String> getTurmas(String eHoje) {

        ArrayList<String> turmas_hoje = new ArrayList<String>();


        for (TurmaItem turma : Professores.getProfessorCorrente().getTurmas()) {
            if (turma.getDiaDaSemana().contentEquals(eHoje)) {
                if (!turma.getTipo().contentEquals("IN")) {
                    if (!turmas_hoje.contains(turma.getNome())) {
                        turmas_hoje.add(turma.getNome());
                    }
                }
            }
        }

        return turmas_hoje;

    }

}
