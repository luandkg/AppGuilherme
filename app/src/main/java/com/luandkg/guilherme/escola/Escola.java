package com.luandkg.guilherme.escola;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.dkg.DKG;
import com.luandkg.guilherme.dkg.DKGObjeto;
import com.luandkg.guilherme.escola.alunos.Aluno;
import com.luandkg.guilherme.escola.alunos.AlunoAtividade;
import com.luandkg.guilherme.escola.alunos.AlunoComNota;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.utils.FS;

import java.io.File;
import java.util.ArrayList;

public class Escola {


    public static ArrayList<Aluno> getAlunos() {

        ArrayList<Aluno> mAlunos = new ArrayList<Aluno>();

        Local.organizarPastas();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ARQUIVO_ALUNOS);

        File eArquivo = new File(eArquivoLocal);

        DKG eDocumento = new DKG();

        System.out.println("Carregar :: Alunos");

        if (eArquivo.exists()) {
            eDocumento.abrir(eArquivoLocal);

            System.out.println("Carregar :: Alunos Existe " + eDocumento.getObjetos().size());

            DKGObjeto eAlunos = eDocumento.unicoObjeto("Alunos");

            System.out.println("Carregar :: Alunos Existe " + eAlunos.getObjetos().size());

            for (DKGObjeto ePacote : eAlunos.getObjetos()) {

                String eID = ePacote.identifique("ID").getValor();

                String eTurma = ePacote.identifique("Turma").getValor();
                String eNome = ePacote.identifique("Nome").getValor();
                String eVisibilidade = ePacote.identifique("Visibilidade").getValor();


                if (eVisibilidade.length() == 0) {
                    eVisibilidade = "SIM";
                }

                mAlunos.add(new Aluno(eID, eTurma, eNome, eVisibilidade));

            }


        } else {
            eDocumento.salvar(eArquivoLocal);
        }


       // fakes(mAlunos);

        System.out.println("Carregar :: Alunos " + mAlunos.size());

        return mAlunos;
    }

    public static void fakes(ArrayList<Aluno> mAlunos) {


        mAlunos.add(new Aluno("01", "7H", "COISINHA DE TAL", "SIM"));
        mAlunos.add(new Aluno("02", "7H", "FULANO DE AQUI", "SIM"));
        mAlunos.add(new Aluno("03", "7H", "BELTRANO DA ESQUINA", "SIM"));
        mAlunos.add(new Aluno("04", "7H", "CICLANO EM VOZ", "SIM"));
        mAlunos.add(new Aluno("05", "7H", "RENANO DALI", "SIM"));

        mAlunos.add(new Aluno("06", "7I", "AISSA DALI", "SIM"));
        mAlunos.add(new Aluno("07", "7I", "MARCOS RICHO", "SIM"));

    }


    public static ArrayList<Aluno> getAlunosVisiveis() {
        ArrayList<Aluno> ret = new ArrayList<Aluno>();

        for (Aluno eAluno : getAlunos()) {
            if (eAluno.getVisibilidade().contentEquals("SIM")) {
                ret.add(eAluno);
            }
        }

        return ret;
    }

    public static ArrayList<AlunoComNota> getAlunos(String qual_turma) {

        ArrayList<AlunoComNota> mAlunos = new ArrayList<AlunoComNota>();

        Local.organizarPastas();

        for (Aluno aluno : getAlunos()) {

            if (aluno.getTurma().contentEquals(qual_turma)) {

                String eVisibilidade = aluno.getVisibilidade();

                if (eVisibilidade.contentEquals("SIM")) {
                    mAlunos.add(new AlunoComNota(aluno.getID(), aluno.getTurma(), aluno.getNome(), "", ""));
                }
            }


        }

        return mAlunos;
    }

    public static ArrayList<AlunoResultado> getAlunosComResultado(String qual_turma) {

        ArrayList<AlunoResultado> mAlunos = new ArrayList<AlunoResultado>();

        Local.organizarPastas();

        for (Aluno aluno : getAlunos()) {

            if (aluno.getTurma().contentEquals(qual_turma)) {

                String eVisibilidade = aluno.getVisibilidade();

                if (eVisibilidade.contentEquals("SIM")) {
                    mAlunos.add(new AlunoResultado(aluno.getID(), aluno.getTurma(), aluno.getNome()));
                }
            }


        }

        return mAlunos;
    }



    public static ArrayList<AlunoResultado> getAlunosComResultadoDaEscola( ) {

        ArrayList<AlunoResultado> mAlunos = new ArrayList<AlunoResultado>();

        Local.organizarPastas();

        for (Aluno aluno : getAlunos()) {


                String eVisibilidade = aluno.getVisibilidade();

                if (eVisibilidade.contentEquals("SIM")) {
                    mAlunos.add(new AlunoResultado(aluno.getID(), aluno.getTurma(), aluno.getNome()));
                }



        }

        return mAlunos;
    }

    public static ArrayList<AlunoAtividade> getAlunosComAtividadesDaEscola( ) {

        ArrayList<AlunoAtividade> mAlunos = new ArrayList<AlunoAtividade>();

        Local.organizarPastas();

        for (Aluno aluno : getAlunos()) {


            String eVisibilidade = aluno.getVisibilidade();

            if (eVisibilidade.contentEquals("SIM")) {
                mAlunos.add(new AlunoAtividade(aluno.getID(), aluno.getTurma(), aluno.getNome(),"",""));
            }



        }

        return mAlunos;
    }

}
