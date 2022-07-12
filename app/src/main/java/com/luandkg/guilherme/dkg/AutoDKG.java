package com.luandkg.guilherme.dkg;

import java.io.File;

public class AutoDKG {

    public static DKG get(String eArquivoLocal) {
        DKG existente = new DKG();

        File eArquivo = new File(eArquivoLocal);

        if (eArquivo.exists()) {
            existente.abrir(eArquivoLocal);
        }

        return existente;
    }



}
