package com.luandkg.guilherme.escola.metodo_avaliativo;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.libs.dkg.DKG;
import com.luandkg.guilherme.libs.dkg.DKGObjeto;
import com.luandkg.guilherme.escola.alunos.AlunoComNota;
import com.luandkg.guilherme.utils.FS;

import java.util.ArrayList;

public class Atividade {

    private String mArquivo;
    private String mTurma;
    private String mNome;
    private int mFizeram;
    private String mTempo;
    private int mTotal;
    private String mAvaliar;

    private static final String ATIVIDADE_SIM = "SIM";

    private ArrayList<AlunoComNota> mAlunos;

    public Atividade(String arquivo) {

        mArquivo = arquivo;
        mNome = "";
        mAvaliar = "";

        DKG eDocumento = new DKG();


        if (FS.arquivoExiste(Local.LOCAL_AVALIACOES, mArquivo)) {
            String arquivo_atividade = FS.getArquivoLocal(Local.LOCAL_AVALIACOES + "/" + mArquivo);
            eDocumento.abrir(arquivo_atividade);
        }

        DKGObjeto atividade_cabecalho = eDocumento.unicoObjeto("Atividade");


        mTurma = atividade_cabecalho.identifique("Turma").getValor();

        mTempo = atividade_cabecalho.identifique("Tempo").getValor();

        mFizeram = atividade_cabecalho.identifique("Fizeram").getInteiro(0);
        mTotal = atividade_cabecalho.identifique("Total").getInteiro(0);

        mNome = atividade_cabecalho.identifique("Nome").getValor();

        if (mNome.length() == 0) {
            mNome = mNome.replace("_ATV_", "");
            mNome = mNome.replace(".dkg", "");
            mNome = "ATIVIDADE " + mNome;
        }

    }

    public void adicionar_alunos(ArrayList<AlunoComNota> eAlunos) {
        mAlunos = eAlunos;
    }

    public ArrayList<AlunoComNota> getAlunos() {
        return mAlunos;
    }

    public String getTurma() {
        return mTurma;
    }

    public String getNome() {
        return mNome;
    }

    public void setNome(String e) {
        mNome = e;
    }

    public String getTempo() {
        return mTempo;
    }

    public String getStatus() {
        String s = "Nenhum aluno realizou atividade.";

        if (mFizeram == 1) {
            s = "1 aluno realizou a atividade.";
        } else if (mFizeram > 1) {
            s = mFizeram + " alunos realizaram a atividade.";
        }

        return s;
    }

    public String getArquivo() {
        return mArquivo;
    }


    public int getFizeram() {
        return mFizeram;
    }

    public int getTotal() {
        return mTotal;
    }


    public void organizar() {

        DKG dkg_atividade = new DKG();

        String arquivo_atividade = FS.getArquivoLocal(Local.LOCAL_AVALIACOES + "/" + mArquivo);

        if (FS.arquivoExiste(Local.LOCAL_AVALIACOES, mArquivo)) {
            dkg_atividade.abrir(arquivo_atividade);
        }

        // System.out.println("obtendo notas :: " + arquivo_avaliacao);
        DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

        mTempo = atividade_cabecalho.identifique("Tempo").getValor();
        mAvaliar = atividade_cabecalho.identifique("Avaliar").getValor();
        mNome = atividade_cabecalho.identifique("Nome").getValor();

        System.out.println("Carregar Arquivo :: " + arquivo_atividade);

        for (AlunoComNota aluno : mAlunos) {

            for (DKGObjeto proc : atividade_cabecalho.getObjetos()) {
                if (aluno.getID().contentEquals(proc.identifique("ID").getValor())) {

                    //   System.out.println("obtendo nota de :: " + aluno.getNome() + " para :: " + proc.identifique("Nota").getValor());

                    aluno.setNota(proc.identifique("Nota").getValor());
                    aluno.setData(proc.identifique("Data").getValor());

                    System.out.println("\t - Lendo :: " + aluno.getNome() + " ->> " + aluno.getNota());

                    break;
                }

            }


        }


    }

    public void salvar() {

        DKG dkg_atividade = new DKG();

        String arquivo_atividade = FS.getArquivoLocal(Local.LOCAL_AVALIACOES + "/" + mArquivo);

        DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

        int fizeram = 0;

        System.out.println("Montar Arquivo :: " + arquivo_atividade);

        for (AlunoComNota aluno : mAlunos) {

            DKGObjeto aluno_objeto = atividade_cabecalho.criarObjeto("Aluno");

            aluno_objeto.identifique("ID", aluno.getID());
            aluno_objeto.identifique("Nome", aluno.getNome());
            aluno_objeto.identifique("Nota", aluno.getNota());
            aluno_objeto.identifique("Data", aluno.getData());

            if (aluno.getNota().contentEquals(ATIVIDADE_SIM)) {
                fizeram += 1;
            }

            System.out.println("\t - salvando :: " + aluno.getNome() + " ->> " + aluno.getNota());
        }


        atividade_cabecalho.identifique("Turma").setValor(mTurma);
        atividade_cabecalho.identifique("Nome").setValor(mNome);

        atividade_cabecalho.identifique("Fizeram").setInteiro(fizeram);
        atividade_cabecalho.identifique("Total").setInteiro(mAlunos.size());

        atividade_cabecalho.identifique("Tempo").setValor(mTempo);
        atividade_cabecalho.identifique("Avaliar").setValor(mAvaliar);

        dkg_atividade.salvar(arquivo_atividade);

        System.out.println(dkg_atividade.toString());

    }

    public int contarPositivos() {

        int v = 0;

        for (AlunoComNota aluno : mAlunos) {
            if (aluno.getNota().contentEquals(ATIVIDADE_SIM)) {
                v += 1;
            }
        }

        return v;

    }


}
