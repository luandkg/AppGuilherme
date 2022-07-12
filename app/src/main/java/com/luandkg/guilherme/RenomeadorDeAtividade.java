package com.luandkg.guilherme;

import com.luandkg.guilherme.utils.Acao;

public class RenomeadorDeAtividade {

    public static boolean TEM = false;
    public static Acao ACAO = null;
    public static String NOME_ATUAL = "";

    public static void iniciar(Acao eAcao) {
        TEM = true;
        ACAO = eAcao;
    }

    public static void FAZER() {
        ACAO.fazer();
    }
}
