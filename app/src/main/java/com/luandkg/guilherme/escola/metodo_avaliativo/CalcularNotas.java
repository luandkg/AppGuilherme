package com.luandkg.guilherme.escola.metodo_avaliativo;

import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.alunos.AlunoComNota;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.utils.Strings;

import java.util.ArrayList;

public class CalcularNotas {

    public static ArrayList<AlunoResultado> calcular(){

        ArrayList<String> turmas = new ArrayList<String>();

        turmas.add("7H");
        turmas.add("7I");
        turmas.add("7J");
        turmas.add("7K");
        turmas.add("7L");
        turmas.add("7M");
        turmas.add("7N");

        turmas.add("PD7H");
        turmas.add("PD7I");

        ArrayList<AlunoResultado> mAlunos = new ArrayList<AlunoResultado>();

        for (String turma : turmas) {

            ArrayList<AlunoResultado> mAlunosDaTurma = Escola.getAlunosComResultado(turma);

            String AVALIADOR_VALOR_STRING = Strings.seVazioEntao(Avaliador.getAvaliacao(turma), "1,0");
            double AVALIADOR_VALOR = Double.parseDouble(AVALIADOR_VALOR_STRING.replace(",", "."));

            Avaliador.avaliar_resultado(turma, AVALIADOR_VALOR, mAlunosDaTurma);

            mAlunos.addAll(mAlunosDaTurma);

        }

        return mAlunos;
    }
}
