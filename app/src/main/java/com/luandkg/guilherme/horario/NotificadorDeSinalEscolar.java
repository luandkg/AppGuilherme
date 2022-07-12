package com.luandkg.guilherme.horario;

import android.content.Context;
import android.os.Handler;
import android.widget.TextView;

import com.luandkg.guilherme.utils.tempo.Data;
import com.luandkg.guilherme.utils.tempo.Tempo;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class NotificadorDeSinalEscolar {


    private Professor mProfessor;

    final Handler myHandler = new Handler();
    private Timer temporizador;
    private boolean sextou = false;
    private Context mContexto;

    public NotificadorDeSinalEscolar(Context eContexto, Professor eProfessor) {
        mContexto = eContexto;
        mProfessor = eProfessor;
    }


    public void run() {
        temporizador = new Timer();
        temporizador.schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.post(myRunnable);
            }
        }, 0, 500);
    }

    public Runnable myRunnable = new Runnable() {
        public void run() {


            // ---------------- LIMAR TUDO -----------------------


            // ----------------------------------------------------


            String HOJE_DIA = Calendario.getDiaAtual();
            int HOJE_TEMPO = Calendario.getTempoDoDia();
            String HOJE_DATA = Tempo.getADM();

            Informante.limpar(mProfessor, HOJE_DIA);


            if (mProfessor.estou_em_ferias(Data.toData(HOJE_DATA))) {

            } else {

                ArrayList<AtividadeEspecial> atividades = AtividadeEspecial.filtrar(HOJE_DIA, mProfessor.getAtividades());

                boolean isSexta = false;
                int ultima_aula_10min_antes = 0;
                int ultima_aula = 0;

                if (mProfessor.existeCargaDeTrabalho(Calendario.SEXTA)) {

                    // CODIGO ESPECIAL PARA SEXTAR

                    if (Calendario.isDiferente(HOJE_DIA, Calendario.SEXTA)) {
                        sextou = false;
                    }

                    if (Calendario.isIgual(HOJE_DIA, Calendario.SEXTA)) {

                        ArrayList<TurmaItem> na_sexta = TurmaItem.filtrarTurmas(Calendario.SEXTA, mProfessor.getTurmas());

                        isSexta = true;
                        if (na_sexta.size() > 0) {
                            ultima_aula_10min_antes = na_sexta.get(na_sexta.size() - 1).getFim() - (10 * 60);
                            ultima_aula = na_sexta.get(na_sexta.size() - 1).getFim();
                        }
                    }

                }


                if (Calendario.isDiaDaSemana(Calendario.getDiaAtual())) {

                    if (mProfessor.existeCargaDeTrabalho(HOJE_DIA)) {

                        CargaDeTrabalho carga = mProfessor.getCargaDeTrabalho(HOJE_DIA);

                        if (carga.isDentro(HOJE_DIA, HOJE_TEMPO)) {

                            progressoDaEscola(carga.getInicio().getValor(), carga.getFim().getValor());


                        }

                    }

                    for (AtividadeEspecial atividade : atividades) {

                        if (atividade.isMostrarIndo()) {
                            if (atividade.isDentroIndo(HOJE_TEMPO)) {

                                Informante.notificarIndo(mContexto, atividade, mProfessor);

                                progressoDaCoordenacao(atividade.getIndo_Inicio(), atividade.getIndo_Fim());

                            }
                        }

                        if (atividade.isDentro(HOJE_TEMPO)) {


                            progressoDaCoordenacao(atividade.getInicio(), atividade.getFim());

                        }

                    }


                    // ALMOCO
                    if (mProfessor.estouAlmocando(HOJE_TEMPO)) {


                        progressoDaCoordenacao(mProfessor.getAlmocoInicio().getValor(), mProfessor.getAlmocoFim().getValor());

                    }


                    if (mProfessor.estaEmRegencia(HOJE_DIA, HOJE_TEMPO)) {


                        ArrayList<TurmaItem> hoje = TurmaItem.filtrarTurmas(HOJE_DIA, mProfessor.getTurmas());


                        boolean estaTendoAula = false;


                        for (TurmaItem horario_da_turma : hoje) {


                            if (horario_da_turma.isQuantosMinutosAntes(HOJE_TEMPO, 1)) {
                                Informante.notificar(mContexto, horario_da_turma, mProfessor);
                            }


                            if (horario_da_turma.isDentro(HOJE_TEMPO)) {

                                //    mSigla.setText(horario_da_turma.getNome());
                                progressoDaAula(horario_da_turma.getInicio(), horario_da_turma.getFim());

                                if (horario_da_turma.getTipo().contentEquals("IN")) {
                                    //   mFazendo.setText("Estou no intervalo !");
                                } else {

                                    //  mFazendo.setText("Estou em regência....");


                                    // CODIGO ESPECIAL PARA SEXTA-FEIRA
                                    if (isSexta) {
                                        if (HOJE_TEMPO >= ultima_aula_10min_antes) {
                                            int falta_v = falta(HOJE_TEMPO, horario_da_turma.getFim());

                                            if (falta_v > 50) {
                                                //      mFazendo.setText("Estou em regência - QUASE SEXTANDO \n FALTA " + falta_v + " segundos !");
                                            } else if (falta_v <= 50 && falta_v > 10) {
                                                //       mFazendo.setText("Estou em regência - VAI SEXTAR \n FALTA " + falta_v + " segundos !");
                                            } else {
                                                //     mFazendo.setText("Estou em regência - VAI SEXTAR \n CHEGANDO " + falta_v + " segundos !");

                                                if (sextou == false) {
                                                    Informante.notificarAlgo(mContexto, "S", "Sextouuuuu", "Hora de beber todas crlh....");
                                                    sextou = true;
                                                }


                                            }

                                        }
                                    }


                                }

                                estaTendoAula = true;
                                break;
                            }
                        }

                        //   progressoDaEscola(mProfessor.getRegenicia_Inicio().getValor(), mProfessor.getRegenicia_Fim().getValor());

                    }

                    if (mProfessor.existeCargaDeTrabalho(HOJE_DIA)) {

                        // INDO PARA CASA
                        if (mProfessor.estouIndoParaCasa(HOJE_DIA, HOJE_TEMPO)) {

                            // mFazendo.setText("Estou indo para casa, amém !");
                            // mSigla.setText("CASA");

                            progressoDaCoordenacao(mProfessor.getIndoParaCasa_Inicio(HOJE_DIA), mProfessor.getIndoParaCasa_Fim(HOJE_DIA));

                        }

                    }


                    if (isSexta) {
                        if (HOJE_TEMPO >= ultima_aula) {
                            //    mFazendo.setText("SEXTOUUUUUUUU......");
                        }

                    }

                } else {
                    //   progressoFimDeSemana(mProgressante, HOJE_DIA, HOJE_TEMPO);
                }

            }


            // -------------- TESTES -----------------
            // testar(true);

        }
    };

    public int falta(int agora, int ate) {
        return ate - agora;
    }


    public void progressoDaAula(int eInicio, int eFim) {

        int eAgora = Calendario.getTempoDoDia();

        //    progressarPequeno(mProgressante, eAgora, eInicio, eFim);

    }

    public void progressoDaCoordenacao(int eInicio, int eFim) {

        int eAgora = Calendario.getTempoDoDia();

        //  progressarPequeno(mProgressante, eAgora, eInicio, eFim);

    }

    public void progressoDaEscola(int eInicio, int eFim) {

        int eAgora = Calendario.getTempoDoDia();

        //    progressar(mProgressante, eAgora, eInicio, eFim);

    }


    public void mostrarProgressoInterno(int inicio, int fim) {

        int total = fim - inicio;

        int eAgora = Calendario.getTempoDoDia();

        if (eAgora >= inicio && eAgora < fim) {

            double restante = (((double) eAgora - (double) inicio) / (double) total);
            double prop = restante * 100.0;
            int iprop = (int) prop;

            //   mSigla.setText("" + iprop);

        }

    }

    public void progressar(PG eProgressante, int eAgora, int eInicio, int eFim) {

        if (eAgora >= eInicio && eAgora < eFim) {

            double agora = (double) eAgora;

            double inicio = (double) eInicio;
            double fim = (double) eFim;

            double total = fim - inicio;

            double restante = (((double) agora - inicio) / total);
            double prop = restante * 100.0;

            eProgressante.setProgressGrande((int) prop);
            //  mContador.setText(""+prop );

        }

    }

    public void progressarPequeno(PG eProgressante, int eAgora, int eInicio, int eFim) {

        if (eAgora >= eInicio && eAgora < eFim) {

            double agora = (double) eAgora;

            double inicio = (double) eInicio;
            double fim = (double) eFim;

            double total = fim - inicio;

            double restante = (((double) agora - inicio) / total);
            double prop = restante * 100.0;

            eProgressante.setProgressPequeno((int) prop);
            //  mContador.setText(""+prop );

        }

    }

    public void progressoFimDeSemana(PG eProgressante, String eDia, int eAgora) {

        int metade = ((24 * 60 * 60));
        int total = ((24 * 60 * 60) * 2);

        if (eDia.contentEquals(Calendario.SABADO)) {

            double agora = ((double) eAgora);

            double d_total = (double) total;

            double restante = (((double) agora) / d_total);
            double prop = restante * 100.0;

            eProgressante.setProgressGrande((int) prop);
            //  mSigla.setText("SÁB");

        } else {
            double agora = ((double) eAgora) + (double) metade;

            double d_total = (double) total;

            double restante = (((double) agora) / d_total);
            double prop = restante * 100.0;

            eProgressante.setProgressGrande((int) prop);
            //     mSigla.setText("DOM");

        }

        // mFazendo.setText("Estou curtindo o final de semana ...");

    }
}
