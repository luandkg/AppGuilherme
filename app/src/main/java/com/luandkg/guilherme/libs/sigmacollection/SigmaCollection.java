package com.luandkg.guilherme.libs.sigmacollection;


import com.luandkg.guilherme.libs.dkg.AutoDKG;
import com.luandkg.guilherme.libs.dkg.DKG;
import com.luandkg.guilherme.libs.dkg.DKGObjeto;
import com.luandkg.guilherme.utils.FS;

import java.io.File;
import java.util.ArrayList;

public class SigmaCollection {

    private static String EXTENSAO = "dkg";

    public static String organizar(String caminho) {

        caminho = caminho.replace(" ", "");
        caminho = caminho.replace("\t", "");

        // @ESCOLA::ALUNOS
        // @ESCOLA->CACHE::NOTAS

        // @ESCOLA->AVALIACOES::2022_10_05  -->> Escola/Avaliacoes/2022_10_05.dkg
        // @ESCOLA->CHAMADAS::2022_10_12    -->> Escola/Chamadas/2022_10_12.dkg

        String DOMINIO = "";
        String PASTA = "";
        String ARQUIVO = "";

        int i = 0;
        int o = caminho.length();

        int e = 0;

        // OBTER DOMINIO
        while (i < o) {
            String letra = String.valueOf(caminho.charAt(i));
            if (letra.contentEquals("@")) {
                i += 1;
                e = 1;
                break;
            }
            i += 1;
        }

        if (e == 1) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals("-")) {
                    i += 1;
                    e = 2;
                    break;
                } else if (letra.contentEquals(":")) {
                    i += 1;
                    e = 3;
                    break;
                } else {
                    DOMINIO += letra;
                }
                i += 1;
            }
        }

        if (e == 2) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(">")) {
                    i += 1;
                    e = 4;
                    break;
                }
                i += 1;
            }
        }


        // EXISTE PASTA ENTAO VAMOS OBTE-LA
        if (e == 4) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(":")) {
                    i += 1;
                    e = 3;
                    break;
                } else {
                    PASTA += letra;
                }
                i += 1;
            }
        }

        if (e == 3) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(":")) {
                    i += 1;
                    e = 5;
                    break;
                }
                i += 1;
            }
        }

        // OBTER ARQUIVO
        if (e == 5) {
            while (i < o) {
                String letra = String.valueOf(caminho.charAt(i));
                if (letra.contentEquals(":")) {
                    i += 1;
                    e = 6;
                    break;
                } else {
                    ARQUIVO += letra;
                }
                i += 1;
            }
        }


        String transformado = "";

        if (PASTA.length() == 0) {
            transformado = toCapital(DOMINIO) + "/" + toAbaixo(ARQUIVO) + "." + EXTENSAO;
        } else {
            transformado = toCapital(DOMINIO) + "/" + toCapital(PASTA) + "/" + toAbaixo(ARQUIVO) + "." + EXTENSAO;
        }


        System.out.println("PATH :: " + caminho);
        System.out.println("\t - DOMINIO :: " + DOMINIO);

        if (PASTA.length() > 0) {
            System.out.println("\t - PASTA :: " + PASTA);
        }

        System.out.println("\t - ARQUIVO :: " + ARQUIVO);
        System.out.println("\t - SAIDA :: " + transformado);


        return transformado;
    }

    private static String toCapital(String entrada) {

        int i = 0;
        int o = entrada.length();

        String saida = "";


        while (i < o) {
            String letra = String.valueOf(entrada.charAt(i));

            if (i == 0) {
                saida += letra.toUpperCase();
            } else {
                saida += letra.toLowerCase();
            }

            i += 1;
        }

        return saida;
    }

    private static String toAbaixo(String entrada) {
        return entrada.toLowerCase();
    }

    private static String toAcima(String entrada) {
        return entrada.toUpperCase();
    }


    // COLLECTION

    public static String getARQUIVO(String caminho) {
        String arquivo_caminho = FS.getArquivoLocal(organizar(caminho));
        return arquivo_caminho;
    }


    public static DKG REQUIRED_COLLECTION(String caminho) {
        return AutoDKG.get(FS.getArquivoLocal(organizar(caminho)));
    }

    public static DKG REQUIRED_COLLECTION_OR_BUILD(String caminho) {

        DKG eDocumento = new DKG();

        if (SigmaCollection.EXISTS_COLLECTION(caminho)) {
            eDocumento = SigmaCollection.REQUIRED_COLLECTION(caminho);
        }

        return eDocumento;
    }


    public static ArrayList<DKGObjeto> REQUIRED_COLLECTION(String caminho, String lista) {
        return REQUIRED_COLLECTION(caminho).unicoObjeto(lista).getObjetos();
    }

    public static boolean EXISTS_COLLECTION(String caminho) {

        String arquivo_caminho = FS.getArquivoLocal(organizar(caminho));

        File eArquivo = new File(arquivo_caminho);
        return eArquivo.exists();
    }

    public static void BUILD_COLLECTION(String caminho) {

        String arquivo_caminho = FS.getArquivoLocal(organizar(caminho));

        DKG doc = new DKG();
        doc.salvar(arquivo_caminho);

    }

    public static void BUILD_COLLECTION(String caminho, String lista) {

        String arquivo_caminho = FS.getArquivoLocal(organizar(caminho));

        DKG doc = new DKG();
        doc.unicoObjeto(lista);
        doc.salvar(arquivo_caminho);

    }

    public static DKG INIT_COLLECTION(String caminho) {

        String arquivo_caminho = FS.getArquivoLocal(organizar(caminho));
        DKG doc = new DKG();
        return doc;
    }


    public static DKG CREATE_COLLECTION(String lista) {

        DKG eDocumento = new DKG();
        DKGObjeto raiz = eDocumento.unicoObjeto(lista);

        return eDocumento;
    }

    public static void WRITE_COLLECTION(String caminho, DKG colecao) {

        String arquivo_caminho = FS.getArquivoLocal(organizar(caminho));
        colecao.salvar(arquivo_caminho);

    }

    public static int INDEX(DKGObjeto raiz, String eIndex) {

        int idGeral = raiz.identifique(eIndex).getInteiro(0);
        raiz.identifique(eIndex).setInteiro(idGeral + 1);

        return idGeral;
    }
}
