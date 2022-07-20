package com.luandkg.guilherme.escola.metodo_avaliativo;

import com.luandkg.guilherme.escola.alunos.AlunoAtividade;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.libs.tempo.Data;

import java.util.ArrayList;

public class AtividadesAnalisador {

    public static int contar(ArrayList<Data> datas, ArrayList<AlunoResultado> alunos) {

        int contando = 0;

        ArrayList<String> alunos_id = new ArrayList<String>();

        for (Data eData : datas) {


            for (AlunoResultado aluno : alunos) {

                int enc = 0;

                for(AtividadeRealizada atv : aluno.getAtividadesRealizadas()){
                    System.out.println("\t - ATV :: " + atv.getData());
                }

                if (aluno.temAtividadeSim(eData.getTempo())) {

                    String s_id = String.valueOf(aluno.getID());

                    if (!alunos_id.contains(s_id)) {
                        alunos_id.add(s_id);
                        contando += 1;
                    }

                    System.out.println("DT :: " + eData.getFluxo() + " -->> " + aluno.getNome());
                    enc += 1;
                }

                System.out.println("Procurando data :: " + eData.getTempo() + " :: " + aluno.getAtividadesRealizadas().size() + " -->> " + enc);

            }
        }

        return contando;
    }

    public static ArrayList<AlunoAtividade> get(ArrayList<Data> datas, ArrayList<AlunoAtividade> alunos) {


        ArrayList<AlunoAtividade> ret = new ArrayList<AlunoAtividade>();

        for (Data eData : datas) {


            for (AlunoAtividade aluno : alunos) {

                if (aluno.getData().contentEquals(eData.getTempo())) {

                    ret.add(aluno);


                }

            }
        }

        return ret;
    }

}
