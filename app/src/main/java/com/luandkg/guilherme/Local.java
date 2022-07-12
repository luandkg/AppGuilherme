package com.luandkg.guilherme;


import com.luandkg.guilherme.utils.FS;

public class Local {

    public static final String LOCAL = "GG";
    public static final String LOCAL_AVALIACOES = "GG/Avaliacoes";
    public static final String LOCAL_CACHE = "GG/Cache";


    public final static String BIMESTRE = "01";


    public final static String ARQUIVO_ALUNOS = "alunos.dkg";
    public final static String ARQUIVO_TURMAS = "turmas.dkg";
    public final static String ARQUIVO_ATIVIDADES = "atividades.dkg";


    public static void organizarPastas() {

        FS.dirCriar(Local.LOCAL);
        FS.dirCriar(Local.LOCAL_AVALIACOES);
        FS.dirCriar(Local.LOCAL_CACHE);

    }


}
