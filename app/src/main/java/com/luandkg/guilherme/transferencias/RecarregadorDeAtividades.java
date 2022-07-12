package com.luandkg.guilherme.transferencias;

import com.luandkg.guilherme.utils.Acao;

public class RecarregadorDeAtividades {

    public static boolean TEM = false;
    public static Acao ACAO = null;

    public static void iniciar(Acao eAcao) {
        TEM = true;
        ACAO = eAcao;
    }

    public static void FAZER() {
        ACAO.fazer();
    }
}
