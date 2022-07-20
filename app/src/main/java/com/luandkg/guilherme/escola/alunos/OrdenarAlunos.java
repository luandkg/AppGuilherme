package com.luandkg.guilherme.escola.alunos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class OrdenarAlunos {


    public static ArrayList<Aluno> ordendar(ArrayList<Aluno> alunos) {

        Collections.sort(alunos, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return ((Aluno) objeto_um).getNome().compareTo(((Aluno) objeto_dois).getNome());
            }
        });

        return alunos;
    }

    public static ArrayList<AlunoComNota> ordendarComNotas(ArrayList<AlunoComNota> alunos) {

        Collections.sort(alunos, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return ((AlunoComNota) objeto_um).getNome().compareTo(((AlunoComNota) objeto_dois).getNome());
            }
        });

        return alunos;
    }

    public static ArrayList<AlunoResultado> ordendarComResultados(ArrayList<AlunoResultado> alunos) {

        Collections.sort(alunos, new Comparator() {
            @Override
            public int compare(Object objeto_um, Object objeto_dois) {
                return ((AlunoResultado) objeto_um).getNome().compareTo(((AlunoResultado) objeto_dois).getNome());
            }
        });

        return alunos;
    }

}
