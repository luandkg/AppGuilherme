package com.luandkg.guilherme.escola.metodo_avaliativo;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.TesteAlteradorDeData;
import com.luandkg.guilherme.Versionador;
import com.luandkg.guilherme.libs.dkg.DKG;
import com.luandkg.guilherme.libs.dkg.DKGObjeto;
import com.luandkg.guilherme.escola.alunos.Aluno;
import com.luandkg.guilherme.escola.alunos.AlunoAtividade;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.tempo.ContandoData;
import com.luandkg.guilherme.utils.FS;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;

import java.io.File;
import java.util.ArrayList;

public class Avaliador {

    public static final String ATIVIDADE_SIM = "SIM";
    public static final String ATIVIDADE_NAO = "NAO";

    public static void criarAtividade(String turma) {


        Local.organizarPastas();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ARQUIVO_ATIVIDADES);

        DKG eDocumento = new DKG();


        if (FS.arquivoExisteDireto(eArquivoLocal)) {
            eDocumento.abrir(eArquivoLocal);
        }

        DKGObjeto atividades = eDocumento.unicoObjeto("Atividades");

        int sequencia = atividades.identifique(turma).getInteiro(0);
        sequencia += 1;

        atividades.identifique(turma).setInteiro(sequencia);

        eDocumento.salvar(eArquivoLocal);


        String arquivo_atividade = FS.getArquivoLocal(Local.LOCAL_AVALIACOES + "/" + turma + "_ATV_" + sequencia + ".dkg");

        DKG dkg_atividade = new DKG();
        DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");
        atividade_cabecalho.identifique("ID", sequencia);
        atividade_cabecalho.identifique("Turma", turma);
        atividade_cabecalho.identifique("Nome").setValor("ATIVIDADE " + sequencia);
        atividade_cabecalho.identifique("Tempo").setValor(Calendario.getADM());
        atividade_cabecalho.identifique("Avaliar", "01");


        dkg_atividade.salvar(arquivo_atividade);


    }


    public static void atividade_excluir(String arquivo_avaliacao) {


        String arquivo_atividade = FS.getArquivoLocal(Local.LOCAL_AVALIACOES + "/" + arquivo_avaliacao);

        if (FS.arquivoExiste(Local.LOCAL_AVALIACOES, arquivo_avaliacao)) {
            File a = new File(arquivo_atividade);
            a.delete();
        }
    }

    public static void mudarAvaliacao(String turma, String avaliar) {

        DKG dkg_atividade = new DKG();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ARQUIVO_ATIVIDADES);

        File eArquivo = new File(eArquivoLocal);

        if (eArquivo.exists()) {
            dkg_atividade.abrir(eArquivoLocal);
        }


        DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividades");
        DKGObjeto avaliacoes = atividade_cabecalho.unicoObjeto("Avaliacoes");

        avaliacoes.identifique(turma).setValor(avaliar);

        dkg_atividade.salvar(eArquivoLocal);


    }

    public static String getAvaliacao(String turma) {

        DKG dkg_atividade = new DKG();

        String eArquivoLocal = FS.getArquivoLocal(Local.LOCAL + "/" + Local.ARQUIVO_ATIVIDADES);

        File eArquivo = new File(eArquivoLocal);

        if (eArquivo.exists()) {
            dkg_atividade.abrir(eArquivoLocal);
        }


        DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividades");
        DKGObjeto avaliacoes = atividade_cabecalho.unicoObjeto("Avaliacoes");

        return avaliacoes.identifique(turma).getValor();


    }

    public static void atividade_renomear(String arquivo_avaliacao, String nome) {

        String arquivo_atividade = FS.getArquivoLocal(Local.LOCAL_AVALIACOES + "/" + arquivo_avaliacao);

        DKG dkg_atividade = new DKG();

        File eArquivo = new File(arquivo_atividade);

        if (eArquivo.exists()) {
            dkg_atividade.abrir(arquivo_atividade);
        }


        DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

        atividade_cabecalho.identifique("Nome").setValor(nome);

        dkg_atividade.salvar(arquivo_atividade);

    }

    public static void avaliar_resultado(String mTurma, double mAvaliacao, ArrayList<AlunoResultado> mAlunos) {

        double MAXIMO = mAvaliacao;

        File pasta = FS.getPasta(Local.LOCAL_AVALIACOES);

        for (File atividade : pasta.listFiles()) {

            int contagem = 0;

            if (atividade.getName().startsWith(mTurma)) {


                DKG dkg_atividade = new DKG();

                dkg_atividade.abrir(atividade.getAbsolutePath());

                DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

                String ATIVIDADE_ARQUIVO = atividade.getAbsolutePath();
                String ATIVIDADE_DATA = atividade_cabecalho.identifique("Tempo").getValor();
                String ATIVIDADE_NOME = atividade_cabecalho.identifique("Nome").getValor();


                ArrayList<String> listar_alunos = new ArrayList<String>();

                boolean isPrimeiro = true;
                String data_primeiro = "";

                String data_inicial = "";
                String data_final = "";


                for (AlunoResultado aluno : mAlunos) {

                    boolean atividade_realizada = false;

                    for (DKGObjeto proc : atividade_cabecalho.getObjetos()) {
                        if (aluno.getID().contentEquals(proc.identifique("ID").getValor())) {

                            String nota = proc.identifique("Nota").getValor();
                            String data = proc.identifique("Data").getValor();
                            String atestado = proc.identifique("Atestado").getValor();

                            boolean teve_atestado = false;
                            if(atestado.contentEquals("SIM")){
                                teve_atestado=true;
                            }

                            if (Versionador.isTeste()) {
                                data = TesteAlteradorDeData.alterar(data);
                            }


                            if (nota.contentEquals(ATIVIDADE_SIM)) {
                                atividade_realizada = true;
                                contagem += 1;

                                listar_alunos.add("\t - ALUNO :: " + aluno.getNome() + " :: " + data);

                                if (isPrimeiro) {
                                    data_primeiro = data;
                                    data_inicial = data;
                                    data_final = data;

                                    isPrimeiro = false;
                                } else {

                                    if (Data.toData(data).isMenor(Data.toData(data_inicial))) {
                                        data_inicial = data;
                                    }

                                    if (Data.toData(data).isMaior(Data.toData(data_final))) {
                                        data_final = data;
                                    }
                                }

                            }

                            aluno.avaliar(ATIVIDADE_DATA, ATIVIDADE_ARQUIVO, data, atividade_realizada,teve_atestado);

                            break;
                        }

                    }


                }

                System.out.println("\n");
                System.out.println("ATIVIDADE :: " + ATIVIDADE_NOME);
                System.out.println(">> INICIO :: " + data_inicial);
                System.out.println(">> FIM    :: " + data_final);

                for (AlunoResultado aluno : mAlunos) {
                    for (AtividadeRealizada atv : aluno.getAtividadesRealizadas()) {
                        if (atv.getArquivo().contentEquals(ATIVIDADE_ARQUIVO)) {
                            if (atv.getStatus()) {

                                if (atv.getDataEntregue().contentEquals(data_inicial)) {
                                } else {
                                    atv.marcarAtrasada();
                                }

                            }
                        }
                    }
                }

                for (AlunoResultado aluno : mAlunos) {
                    for (AtividadeRealizada atv : aluno.getAtividadesRealizadas()) {
                        if (atv.getArquivo().contentEquals(ATIVIDADE_ARQUIVO)) {
                            if (atv.getStatus()) {

                                if (atv.isAtrasada()) {
                                    System.out.println("\t - " + com(aluno.getNome(), 20) + " - " + com(atv.getDataEntregue(), 12) + "  #ATRASADA");
                                } else {
                                    System.out.println("\t - " + com(aluno.getNome(), 20) + " - " + com(atv.getDataEntregue(), 12));
                                }
                            }
                        }
                    }
                }


                // System.out.println("ARQUIVO :: " + atividade.getAbsolutePath() + " -->> " + contagem);

            }


        }


        for (AlunoResultado aluno : mAlunos) {
            aluno.calcular(MAXIMO);
        }


    }

    public static String com(String a, int v) {
        while (a.length() < v) {
            a += " ";
        }

        return a;
    }

    public static void avaliar_atividades(String mTurma, ArrayList<Data> eDatas, ArrayList<Aluno> mAlunos, ArrayList<AlunoAtividade> mAtividades) {


        File pasta = FS.getPasta(Local.LOCAL_AVALIACOES);

        for (File atividade : pasta.listFiles()) {

            int contagem = 0;

            if (atividade.getName().startsWith(mTurma)) {

                System.out.println("ARQUIVO :: " + atividade.getAbsolutePath() + " -->> " + contagem);

                DKG dkg_atividade = new DKG();

                dkg_atividade.abrir(atividade.getAbsolutePath());

                DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

                String ATIVIDADE_DATA = atividade_cabecalho.identifique("Tempo").getValor();

                for (Aluno aluno : mAlunos) {

                    if (aluno.getTurma().contentEquals(mTurma)) {

                        boolean atividade_realizada = false;

                        for (DKGObjeto proc : atividade_cabecalho.getObjetos()) {
                            if (aluno.getID().contentEquals(proc.identifique("ID").getValor())) {

                                String nota = proc.identifique("Nota").getValor();
                                String data = proc.identifique("Data").getValor();

                                boolean temData = false;

                                String qual_data = Data.organizarData(data);

                                for (Data eData : eDatas) {
                                    if (eData.isIgual(qual_data)) {
                                        temData = true;
                                        break;
                                    }
                                }


                                if (temData) {


                                    if (nota.contentEquals(ATIVIDADE_SIM)) {

                                        System.out.println(aluno.getNome() + " :: " + data + " -->> " + nota);

                                        atividade_realizada = true;
                                        contagem += 1;
                                        mAtividades.add(new AlunoAtividade(aluno.getID(), aluno.getTurma(), aluno.getNome(), nota, data));
                                    }

                                }


                                break;
                            }

                        }

                    }


                }


            }


        }


    }


    public static ArrayList<ContandoData> getFluxoDeEntrega(ArrayList<Aluno> alunos, ArrayList<Data> quais_datas) {


        String ATIVIDADE_SIM = "SIM";
        String ATIVIDADE_NAO = "NAO";

        ArrayList<ContandoData> contadores = new ArrayList<ContandoData>();
        for (Data data : quais_datas) {
            contadores.add(new ContandoData(data.getTempo()));
            System.out.println("CRIANDO CONTADOR :: " + data.getTempo());
        }

        for (File atividade : FS.getListaDeArquivos(Local.LOCAL_AVALIACOES)) {


            DKG dkg_atividade = new DKG();

            dkg_atividade.abrir(atividade.getAbsolutePath());

            System.out.println("-->> " + atividade.getName());
            System.out.println(dkg_atividade.toString());

            DKGObjeto atividade_cabecalho = dkg_atividade.unicoObjeto("Atividade");

            for (Aluno aluno : alunos) {

                for (DKGObjeto proc : atividade_cabecalho.getObjetos()) {
                    if (aluno.getID().contentEquals(proc.identifique("ID").getValor())) {


                        String nota = proc.identifique("Nota").getValor();
                        String data = proc.identifique("Data").getValor();

                        if (Versionador.isTeste()) {
                            data = TesteAlteradorDeData.alterar(data);

                            System.out.println("ENCONTREI :: " + aluno.getID() + " :: " + data);
                        }


                        if (nota.contentEquals(ATIVIDADE_SIM)) {
                            for (ContandoData cont : contadores) {
                                if (cont.getData().contentEquals(data)) {
                                    cont.aumentar();
                                    break;
                                }
                            }

                        }

                        break;
                    }

                }

            }


        }

        if (Versionador.isTeste()) {
            for (ContandoData contador : contadores) {
                System.out.println("MOSTRANDO CONTADOR :: " + contador.getData() + " -->> " + contador.getValor());
            }
        }

        return contadores;
    }
}
