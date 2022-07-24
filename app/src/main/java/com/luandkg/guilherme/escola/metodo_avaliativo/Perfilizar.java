package com.luandkg.guilherme.escola.metodo_avaliativo;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.libs.dkg.DKG;
import com.luandkg.guilherme.libs.dkg.DKGObjeto;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.alunos.Aluno;
import com.luandkg.guilherme.escola.alunos.AlunoPerfil;
import com.luandkg.guilherme.escola.tempo.SemanaContinua;
import com.luandkg.guilherme.utils.FS;
import com.luandkg.guilherme.utils.Strings;

import java.io.File;
import java.util.ArrayList;

public class Perfilizar {

    public static AlunoPerfil getPerfil(String id, ArrayList<SemanaContinua> eSemanas) {

        AlunoPerfil perfil = new AlunoPerfil();

        String mTurma = "";


        for (Aluno eAluno : Escola.getAlunos()) {
            if (eAluno.getID().contentEquals(id)) {

                perfil.set(id, eAluno.getNome(), eAluno.getTurma());
                mTurma = eAluno.getTurma();

                break;
            }
        }

        String AVALIADOR_VALOR_STRING = Strings.seVazioEntao(Avaliador.getAvaliacao(mTurma), "1,0");
        double AVALIADOR_VALOR = Double.parseDouble(AVALIADOR_VALOR_STRING.replace(",", "."));


        double MAXIMO = AVALIADOR_VALOR;

        File pasta = FS.getPasta(Local.LOCAL_AVALIACOES);

        String ATIVIDADE_SIM = "SIM";

        for (File atividade : pasta.listFiles()) {

            int contagem = 0;

            if (atividade.getName().startsWith(mTurma)) {


                DKG dkg_atividade = new DKG();

                dkg_atividade.abrir(atividade.getAbsolutePath());

                DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

                String ATIVIDADE_ARQUIVO = atividade.getAbsolutePath();

                String ATIVIDADE_DATA = atividade_cabecalho.identifique("Tempo").getValor();


                boolean atividade_realizada = false;

                for (DKGObjeto proc : atividade_cabecalho.getObjetos()) {
                    if (id.contentEquals(proc.identifique("ID").getValor())) {

                        String nota = proc.identifique("Nota").getValor();
                        String data = proc.identifique("Data").getValor();
                        String atestado = proc.identifique("Atestado").getValor();

                        boolean teve_atestado = false;
                        if(atestado.contentEquals("SIM")){
                            teve_atestado=true;
                        }
                        if (nota.contentEquals(ATIVIDADE_SIM)) {
                            atividade_realizada = true;
                            contagem += 1;
                        }

                        perfil.avaliar(ATIVIDADE_DATA,ATIVIDADE_ARQUIVO,data, atividade_realizada,teve_atestado);

                        break;
                    }

                }


                System.out.println("ARQUIVO :: " + atividade.getAbsolutePath() + " -->> " + contagem);

            }


        }


        perfil.calcular(MAXIMO);


        return perfil;

    }
}
