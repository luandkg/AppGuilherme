package com.luandkg.guilherme.libs.dkg;

import java.util.ArrayList;

public class DocumentoDKG {

    private String mTexto;

    private final String ALFABETO_INICIAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.123456789";
    private final String ALFABETO_FINAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_@.0123456789";

    private String mDocumento;
    private int mIndex;
    private int mMaximo;

    private ArrayList<String> mErros;

    public DocumentoDKG() {


        mTexto = "";

        mDocumento = "";
        mIndex = 0;
        mMaximo = 0;
        mErros = new ArrayList<String>();


    }


    private  String codifica(String e) {

        e = e.replace("@", "_A");
        e = e.replace("'", "_S");
        e = e.replace("\"", "_D");
        e = e.replace("-", "_H");

        return e;
    }

    private  String decodifica(String e) {
        e = e.replace("_H", "-");
        e = e.replace("_D", "\"");
        e = e.replace("_S", "'");
        e = e.replace("_A", "@");

        return e;
    }

    private void adicionarLinha(String eLinha) {
        mTexto += eLinha + "\n";
    }

    private void adicionar(String eMais) {
        mTexto += eMais;
    }

    public String toString() {
        return mTexto;
    }

    public String getTexto() {
        return mTexto;
    }


    public void montar(String ePrefixo, ArrayList<DKGObjeto> lsPacotes) {

        for (DKGObjeto PacoteC : lsPacotes) {

            if (PacoteC.getObjetos().size() == 0 && PacoteC.getAtributos().size() == 0) {

                adicionarLinha(ePrefixo + "!" + codifica(PacoteC.getNome()) + " :: { } ");

            } else if (PacoteC.getObjetos().size() == 0 && PacoteC.getAtributos().size() > 0) {

                String eIdentificadores = "";

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    eIdentificadores += " @" + codifica(IdentificadorC.getNome()) + " = " + "\"" + codifica(IdentificadorC.getValor()) + "\"";
                }

                adicionarLinha(ePrefixo + "!" + codifica(PacoteC.getNome()) + " :: { " + eIdentificadores + " } ");

            } else if (PacoteC.getObjetos().size() > 0) {

                adicionarLinha(ePrefixo + "!" + codifica(PacoteC.getNome()) + " :: { ");

                for (DKGAtributo IdentificadorC : PacoteC.getAtributos()) {
                    adicionarLinha("  @" + codifica(IdentificadorC.getNome()) + " = " + "\"" + codifica(IdentificadorC.getValor()) + "\"");
                }

                montar(ePrefixo + "  ", PacoteC.getObjetos());

                adicionarLinha(ePrefixo+ "}");

            }


        }

    }


    public ArrayList<String> getErros() {
        return mErros;
    }



    // PARSER

    public void parse(String eDocumento, DKG eEmpacotador) {

        mErros.clear();

        eEmpacotador.getObjetos().clear();

        // mPacotes = new ArrayList<Pacote>();

        mDocumento = eDocumento;
        mIndex = 0;
        mMaximo = eDocumento.length();

        //   String eAnterior = "";

        while (mIndex < mMaximo) {
            String l = String.valueOf(eDocumento.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {
            } else if (l.contentEquals("\r")) {

            } else if (l.contentEquals("!")) {

                mIndex += 1;
                parserObjeto(eEmpacotador.getObjetos());

            } else {

            }

            mIndex += 1;
        }

    }

    private String obterPalavra() {

        String p = String.valueOf(mDocumento.charAt(mIndex));
        String ret = p;

        if (ALFABETO_INICIAL.contains(p)) {

            mIndex += 1;

            while (mIndex < mMaximo) {

                String d = String.valueOf(mDocumento.charAt(mIndex));

                if (ALFABETO_FINAL.contains(d)) {
                    ret += d;
                } else {
                    mIndex -= 1;
                    break;
                }

                mIndex += 1;

            }

        }

        return ret;
    }


    private String esperarPalavra() {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mDocumento.charAt(mIndex));

            if (ALFABETO_INICIAL.contains(l)) {
                ret = obterPalavra();
                break;

            } else if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else {

            }

            mIndex += 1;

        }

        return ret;
    }

    private boolean EsperarPor(String Esperando) {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        boolean ret = false;

        while (mIndex < mMaximo) {

            String l = String.valueOf(mDocumento.charAt(mIndex));

            if (Esperando.contentEquals(l)) {
                ret = true;
                mIndex += 1;
                break;

            } else if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else {

            }

            mIndex += 1;

        }

        return ret;
    }

    private void parserObjeto(ArrayList<DKGObjeto> lsPacotes) {

        String Palavra = decodifica(esperarPalavra());

        DKGObjeto NovoPacote = new DKGObjeto(Palavra);
        lsPacotes.add(NovoPacote);

        boolean esperar_dp1 = EsperarPor(":");
        boolean esperar_dp2 = EsperarPor(":");
        boolean esperar_abrir = EsperarPor("{");

        if (esperar_dp1 && esperar_dp2 && esperar_abrir) {

            dentroObjeto(NovoPacote);

        } else {
            mErros.add("ERRO : Era esperado :: { ");

        }

    }

    private void dentroObjeto(DKGObjeto eDKGObjetoCorrente) {

        while (mIndex < mMaximo) {
            String l = String.valueOf(mDocumento.charAt(mIndex));

            if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else if (l.contentEquals("!")) {

                mIndex += 1;
                parserObjeto(eDKGObjetoCorrente.getObjetos());

            } else if (l.contentEquals("@")) {

                mIndex += 1;
                parserAtributo(eDKGObjetoCorrente.getAtributos());

            } else if (l.contentEquals("}")) {

                break;

            } else {
                mErros.add(mIndex + " : " + l);

            }

            mIndex += 1;
        }

    }


    private String esperarTexto() {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mDocumento.charAt(mIndex));

            if (l.contentEquals("'")) {
                mIndex += 1;

                ret = buscandoTexto("'");
                break;
            } else if (l.contentEquals("\"")) {
                mIndex += 1;

                ret = buscandoTexto("\"");
                break;
            } else if (l.contentEquals(" ")) {
            } else if (l.contentEquals("\n")) {
            } else if (l.contentEquals("\t")) {

            } else {

            }

            mIndex += 1;

        }

        return ret;
    }

    private String buscandoTexto(String Finalizador) {

        // System.out.println("Aqui " + String.valueOf(mDocumento.charAt(mIndex)));

        String ret = "";

        while (mIndex < mMaximo) {

            String l = String.valueOf(mDocumento.charAt(mIndex));

            if (l.contentEquals(Finalizador)) {
                mIndex += 1;
                break;

            } else {
                ret += l;
            }

            mIndex += 1;

        }

        return ret;
    }

    private void parserAtributo(ArrayList<DKGAtributo> ls_Identificadores) {

        String NomeIdentificador = decodifica(esperarPalavra());

        DKGAtributo IDC = new DKGAtributo(NomeIdentificador);
        ls_Identificadores.add(IDC);

        if (EsperarPor("=")) {
            // System.out.println("Achou =");

            String eValor = esperarTexto();
            IDC.setValor(decodifica(eValor));
            // Parser_DentroPacote(NovoPacote);

        } else {
            System.out.println("ERRO : Era esperado =");

        }

    }

}

