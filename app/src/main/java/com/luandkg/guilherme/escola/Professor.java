package com.luandkg.guilherme.escola;

import com.luandkg.guilherme.escola.coisas.AtividadeEspecial;
import com.luandkg.guilherme.escola.coisas.CargaDeTrabalho;
import com.luandkg.guilherme.escola.coisas.TurmaItem;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.tempo.TempoEstampa;

import java.util.ArrayList;

public class Professor {


    private ArrayList<AtividadeEspecial> atividades;
    private ArrayList<TurmaItem> turmas;
    private ArrayList<CargaDeTrabalho> mCargasDeTrabalho;


    private String mNome;
    private String mSigla;

    private boolean mTemHorarioDeAlmoco;
    private TempoEstampa mAlmoco_Inicio;
    private TempoEstampa mAlmoco_Fim;

    private ArrayList<Data> mFerias;
    private ArrayList<String> mTurmas;

    public Professor() {

        mNome = "";


        atividades = new ArrayList<AtividadeEspecial>();
        turmas = new ArrayList<TurmaItem>();
        mCargasDeTrabalho = new ArrayList<CargaDeTrabalho>();


        mSigla = "";

        mTemHorarioDeAlmoco = false;
        mAlmoco_Inicio = new TempoEstampa(0, 0);
        mAlmoco_Fim = new TempoEstampa(0, 0);

        mFerias = new ArrayList<Data>();
        mTurmas = new ArrayList<String>();

    }

    public void adicionar_turma(String eTurma){
        mTurmas.add(eTurma);
    }

    public  ArrayList<String> listar_turmas() {
        return mTurmas;
    }

        public String getNome() {
        return mNome;
    }

    public void setNome(String eNome) {
        mNome = eNome;
    }

    public void setSiglaComNome(String eSigla, String eNome) {
        mSigla = eSigla;
        mNome = eNome;
    }

    public ArrayList<TurmaItem> getTurmas() {
        return turmas;
    }

    public ArrayList<AtividadeEspecial> getAtividades() {
        return atividades;
    }

    public boolean existeAtividade(String eDiaSemana) {
        boolean ret = false;
        for (AtividadeEspecial a : atividades) {
            if (a.getDiaDaSemana().contentEquals(eDiaSemana)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public ArrayList<AtividadeEspecial> getAtividades(String eDiaSemana) {
        ArrayList<AtividadeEspecial> ret = new ArrayList<>();
        for (AtividadeEspecial a : atividades) {
            if (a.getDiaDaSemana().contentEquals(eDiaSemana)) {
                ret.add(a);
                break;
            }
        }
        return ret;
    }

    public ArrayList<CargaDeTrabalho> getCargasDeTrabalho() {
        return mCargasDeTrabalho;
    }

    public boolean existeCargaDeTrabalho(String eDia) {
        boolean enc = false;

        for (CargaDeTrabalho eCarga : mCargasDeTrabalho) {
            if (Calendario.isIgual(eCarga.getDia(), eDia)) {
                enc = true;
                break;
            }
        }

        return enc;
    }


    public CargaDeTrabalho getCargaDeTrabalho(String eDia) {
        CargaDeTrabalho enc = null;

        for (CargaDeTrabalho eCarga : mCargasDeTrabalho) {
            if (Calendario.isIgual(eCarga.getDia(), eDia)) {
                enc = eCarga;
                break;
            }
        }

        return enc;
    }


    public void criarCargaDeTrabalho(String eDia, TempoEstampa eInicio, TempoEstampa eFim) {
        mCargasDeTrabalho.add(new CargaDeTrabalho(eDia, eInicio, eFim));
    }


    public AtividadeEspecial criarAtividade(String eDiaSemana, String eSigla, String eNome, String eTipo, TempoEstampa entrada, TempoEstampa saida) {
        AtividadeEspecial a = new AtividadeEspecial(eDiaSemana, eSigla, eNome, eTipo, entrada, saida);
        atividades.add(a);
        return a;
    }


    public void definirAula(TurmaItem eAula) {
        turmas.add(eAula);
    }


    public void definirAlmoco(TempoEstampa eInicio, TempoEstampa eFim) {

        mTemHorarioDeAlmoco = true;
        mAlmoco_Inicio = eInicio;
        mAlmoco_Fim = eFim;

    }


    public boolean estaEmRegencia(String HOJE_DIA, int tempo) {

        boolean ret = false;

        if (existeCargaDeTrabalho(HOJE_DIA)) {

            boolean is_primeira = true;
            TurmaItem primeira = null;
            TurmaItem ultima = null;

            for (TurmaItem horario_da_turma : turmas) {
                if (horario_da_turma.getDiaDaSemana().contentEquals(HOJE_DIA)) {

                    if (is_primeira) {
                        is_primeira = false;
                        primeira = horario_da_turma;
                    } else {
                        ultima = horario_da_turma;
                    }

                }
            }


            if (!is_primeira) {

                // System.out.println("-->> Regencia :: " +primeira.getFaixa() + " ate  " +ultima.getFaixa()  );

                if (tempo >= primeira.getInicio() && tempo < ultima.getFim()) {
                    return true;
                }
            }

        }

        return ret;
    }

    public int regencia_iniciar(String HOJE_DIA) {

        int ret = 0;

        if (existeCargaDeTrabalho(HOJE_DIA)) {

            boolean is_primeira = true;
            TurmaItem primeira = null;
            TurmaItem ultima = null;

            for (TurmaItem horario_da_turma : turmas) {
                if (horario_da_turma.getDiaDaSemana().contentEquals(HOJE_DIA)) {

                    if (is_primeira) {
                        is_primeira = false;
                        primeira = horario_da_turma;
                        ret = horario_da_turma.getInicio();
                        break;
                    } else {
                        ultima = horario_da_turma;
                    }

                }
            }
        }

        return ret;
    }

    public int regencia_terminar(String HOJE_DIA) {

        int ret = 0;

        if (existeCargaDeTrabalho(HOJE_DIA)) {

            boolean is_primeira = true;
            TurmaItem primeira = null;
            TurmaItem ultima = null;

            for (TurmaItem horario_da_turma : turmas) {
                if (horario_da_turma.getDiaDaSemana().contentEquals(HOJE_DIA)) {

                    if (is_primeira) {
                        is_primeira = false;
                        primeira = horario_da_turma;
                        ret = horario_da_turma.getFim();

                    } else {
                        ultima = horario_da_turma;
                        ret = horario_da_turma.getFim();
                    }

                }
            }
        }

        return ret;
    }


    // CASA

    public boolean estouIndoParaCasa(String HOJE_DIA, int tempo) {

        boolean ret = false;


        int indo_valor = getCargaDeTrabalho(HOJE_DIA).getFim().getValor();
        int cheguei = getCargaDeTrabalho(HOJE_DIA).getFim().getDepois(30);

        if (tempo >= indo_valor && tempo < cheguei) {
            ret = true;
        }


        return ret;
    }

    public int getIndoParaCasa_Inicio(String eDia) {
        int v = getCargaDeTrabalho(eDia).getFim().getValor();
        return v;
    }

    public int getIndoParaCasa_Fim(String eDia) {
        int v = getCargaDeTrabalho(eDia).getFim().getDepois(30);
        return v;
    }

    public String getSigla() {
        return mSigla;
    }

    public void setSigla(String eSigla) {
        mSigla = eSigla;
    }


    public boolean estouAlmocando(int tempo) {

        boolean ret = false;

        if (mTemHorarioDeAlmoco) {
            if (tempo >= mAlmoco_Inicio.getValor() && tempo < mAlmoco_Fim.getValor()) {
                ret = true;
            }
        }

        return ret;
    }

    public TempoEstampa getAlmocoInicio() {
        return mAlmoco_Inicio;
    }

    public TempoEstampa getAlmocoFim() {
        return mAlmoco_Fim;
    }


    public void definirAulaSegunda_Simples(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("SEGUNDA", eTurma, eTipo, eInicio, eFim, 1));
    }

    public void definirAulaSegunda_Dupla(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("SEGUNDA", eTurma, eTipo, eInicio, eFim, 2));
    }

    public void definirAulaSegunda_Intervalo(TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("SEGUNDA", "I", "IN", eInicio, eFim, 1));
    }

    // TERCA

    public void definirAulaTerca_Simples(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("TERCA", eTurma, eTipo, eInicio, eFim, 1));
    }

    public void definirAulaTerca_Dupla(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("TERCA", eTurma, eTipo, eInicio, eFim, 2));
    }

    public void definirAulaTerca_Intervalo(TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("TERCA", "I", "IN", eInicio, eFim, 1));
    }


    // QUARTA

    public void definirAulaQuarta_Simples(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("QUARTA", eTurma, eTipo, eInicio, eFim, 1));
    }

    public void definirAulaQuarta_Dupla(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("QUARTA", eTurma, eTipo, eInicio, eFim, 2));
    }

    public void definirAulaQuarta_Intervalo(TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("QUARTA", "I", "IN", eInicio, eFim, 1));
    }

    // QUINTA

    public void definirAulaQuinta_Simples(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("QUINTA", eTurma, eTipo, eInicio, eFim, 1));
    }

    public void definirAulaQuinta_Dupla(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("QUINTA", eTurma, eTipo, eInicio, eFim, 2));
    }

    public void definirAulaQuinta_Intervalo(TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("QUINTA", "I", "IN", eInicio, eFim, 1));
    }

    // SEXTA

    public void definirAulaSexta_Simples(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("SEXTA", eTurma, eTipo, eInicio, eFim, 1));
    }

    public void definirAulaSexta_Dupla(String eTurma, String eTipo, TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("SEXTA", eTurma, eTipo, eInicio, eFim, 2));
    }

    public void definirAulaSexta_Intervalo(TempoEstampa eInicio, TempoEstampa eFim) {
        definirAula(new TurmaItem("SEXTA", "I", "IN", eInicio, eFim, 1));
    }

    public void adicionar_ferias(ArrayList<Data> datas) {
        mFerias = datas;
    }

    public boolean estou_em_ferias(Data eData) {
        boolean ret = false;

        for (Data dt : mFerias) {
            if (dt.isIgual(eData)) {
                ret = true;
                break;
            }
        }
        return ret;
    }

    public int ferias_passou(Data eData) {
        int ret = 0;

        for (Data dt : mFerias) {
            if (dt.isIgual(eData)) {
                break;
            }
            ret += 1;
        }

        return ret;
    }

    public ArrayList<Data> getFerias() {
        return mFerias;
    }
}
