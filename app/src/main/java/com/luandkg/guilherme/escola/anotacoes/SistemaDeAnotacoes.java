package com.luandkg.guilherme.escola.anotacoes;


import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.libs.dkg.DKG;
import com.luandkg.guilherme.libs.dkg.DKGObjeto;
import com.luandkg.guilherme.libs.sigmac.SigmaCollection;

import java.util.ArrayList;

public class SistemaDeAnotacoes {

    public static final String COLECAO_ANOTACOES = "@GG::ANOTACOES";

    public static ArrayList<Anotacao> listar() {

        Local.organizarPastas();

        ArrayList<Anotacao> mAnotacaos = new ArrayList<Anotacao>();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            String mensagem = ePacote.id_string("Mensagem");
            String arquivada = ePacote.id_string("Arquivada");

            if (arquivada.contentEquals("SIM")) {

            } else {
                mAnotacaos.add(new Anotacao(eID, mensagem));
            }

        }


        return ordenar(mAnotacaos);
    }

    public static ArrayList<Anotacao> listar_arquivadas() {

        Local.organizarPastas();

        ArrayList<Anotacao> mAnotacaos = new ArrayList<Anotacao>();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");

            if (ePacote.identifique("Arquivada").isValor("SIM")) {

                String mensagem = ePacote.id_string("Mensagem");
                mAnotacaos.add(new Anotacao(eID, mensagem));

            }

        }


        return ordenar(mAnotacaos);
    }

    public static void criar(String eMensagem) {


        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        DKGObjeto avisos = eDocumento.unicoObjeto("Avisos");


        DKGObjeto aviso = avisos.criarObjeto("Aviso");
        aviso.identifique("ID").setInteiro(SigmaCollection.INDEX(avisos, "ID"));
        aviso.identifique("Mensagem").setValor(eMensagem);


        SigmaCollection.WRITE_COLLECTION(COLECAO_ANOTACOES, eDocumento);

    }


    public static Anotacao getAnotacao(String sID) {

        Local.organizarPastas();


        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        Anotacao ret = null;

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(sID)) {

                String mensagem = ePacote.id_string("Mensagem");

                ret = new Anotacao(eID, mensagem);
                break;
            }


        }


        return ret;
    }

    public static void alterar(String sID, String eMensagem) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(sID)) {
                ePacote.identifique("Mensagem", eMensagem);

                break;
            }


        }

        SigmaCollection.WRITE_COLLECTION(COLECAO_ANOTACOES, eDocumento);
    }

    public static void arquivar(String sID) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(sID)) {
                ePacote.identifique("Arquivada", "SIM");
                break;
            }


        }

        SigmaCollection.WRITE_COLLECTION(COLECAO_ANOTACOES, eDocumento);
    }

    public static void desarquivar(String sID) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        for (DKGObjeto ePacote : eDocumento.unicoObjeto("Avisos").getObjetos()) {

            String eID = ePacote.id_string("ID");
            if (eID.contentEquals(sID)) {
                ePacote.identifique("Arquivada", "NAO");
                break;
            }


        }

        SigmaCollection.WRITE_COLLECTION(COLECAO_ANOTACOES, eDocumento);
    }

    public static void remover(String removerID) {

        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        ArrayList<DKGObjeto> objetos = eDocumento.unicoObjeto("Avisos").getObjetos();

        for (DKGObjeto ePacote : objetos) {
            if (ePacote.identifique("ID").isValor(removerID)) {
                objetos.remove(ePacote);
                break;
            }
        }


        SigmaCollection.WRITE_COLLECTION(COLECAO_ANOTACOES, eDocumento);
    }

    public static void removerTodosArquivados() {


        Local.organizarPastas();

        DKG eDocumento = SigmaCollection.REQUIRED_COLLECTION_OR_BUILD(COLECAO_ANOTACOES);

        ArrayList<DKGObjeto> objetos = eDocumento.unicoObjeto("Avisos").getObjetos();

        boolean temArquivada = false;

        for (DKGObjeto ePacote : objetos) {
            if (ePacote.identifique("Arquivada").isValor("SIM")) {
                temArquivada = true;
                break;
            }
        }

        while (temArquivada) {

            for (DKGObjeto ePacote : objetos) {
                if (ePacote.identifique("Arquivada").isValor("SIM")) {
                    objetos.remove(ePacote);
                    break;
                }
            }

            temArquivada = false;

            for (DKGObjeto ePacote : objetos) {
                if (ePacote.identifique("Arquivada").isValor("SIM")) {
                    temArquivada = true;
                    break;
                }
            }

        }


        SigmaCollection.WRITE_COLLECTION(COLECAO_ANOTACOES, eDocumento);

    }


    // UTILITARIO

    public static ArrayList<Anotacao> ordenar(ArrayList<Anotacao> entrada) {

        int todos = entrada.size();
        Anotacao aux = null;

        for (int i = 0; i < todos; i++) {
            for (int j = 0; j < (todos - 1); j++) {
                if (entrada.get(j).getIDNumerico() < entrada.get(j + 1).getIDNumerico()) {
                    aux = entrada.get(j);
                    entrada.set(j, entrada.get(j + 1));
                    entrada.set(j + 1, aux);
                }
            }
        }

        return entrada;

    }


}
