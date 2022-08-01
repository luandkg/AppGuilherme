package com.luandkg.guilherme.professores;

import com.luandkg.guilherme.escola.calendarios.SEDF_22;
import com.luandkg.guilherme.escola.organizacao.AtividadeEspecial;
import com.luandkg.guilherme.escola.organizacao.Professor;
import com.luandkg.guilherme.escola.organizacao.TurmaItem;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;
import com.luandkg.guilherme.libs.tempo.DiaSemanal;
import com.luandkg.guilherme.libs.tempo.TempoEstampa;


public class Guilherme {

    public static Professor getGuilherme() {

        Professor eProfessor = new Professor();
        eProfessor.setNome("Guilherme Gustavin'");
        eProfessor.setSigla("GG");

       // eProfessor.definirCoordenacaoArea("TERCA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
       // eProfessor.definirCoordenacaoColetiva("QUARTA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
       /// eProfessor.definirCoordenacaoInidivudal("SEGUNDA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        eProfessor.adicionar_ferias(Calendario.filtrar( new SEDF_22().getDatas(),new Data(2022,7,10, DiaSemanal.Domingo),new Data(2022,7,29,DiaSemanal.Domingo)));

        AtividadeEspecial ca = eProfessor.criarAtividade("TERCA", "CA", "Coordenação de Área", "Estou coordenando em área ....",  new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial cc = eProfessor.criarAtividade("QUARTA", "CC", "Coordenação Coletiva", "Estou em coordenação coletiva ....",  new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial ci = eProfessor.criarAtividade("SEGUNDA", "CI", "Coordenação Individual", "Estou coordenando individualmente ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        ca.mostrarIndo("Estou indo para coordenação de área....");
        cc.mostrarIndo("Estou indo para coordenação coletiva ....");
        ci.mostrarIndo("Estou indo para coordenação de individual....");

        eProfessor.criarCargaDeTrabalho(Calendario.SEGUNDA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.TERCA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUARTA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUINTA,new TempoEstampa(13, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.SEXTA,new TempoEstampa(13, 0), new TempoEstampa(18, 0));


        eProfessor.definirAlmoco(new TempoEstampa(12, 0), new TempoEstampa(12, 45));


        eProfessor.adicionar_turma("7H");
        eProfessor.adicionar_turma("7I");
        eProfessor.adicionar_turma("7J");
        eProfessor.adicionar_turma("7K");
        eProfessor.adicionar_turma("7L");
        eProfessor.adicionar_turma("7M");
        eProfessor.adicionar_turma("7N");

        eProfessor.adicionar_turma("PD7H");
        eProfessor.adicionar_turma("PD7I");


        TempoEstampa ENTRADA_PRIMEIRO = new TempoEstampa(13, 0);
        TempoEstampa ENTRADA_SEGUNDO = new TempoEstampa(13, 45);
        TempoEstampa ENTRADA_TERCEIRO = new TempoEstampa(14, 30);

        TempoEstampa ENTRADA_QUARTO = new TempoEstampa(15, 50);
        TempoEstampa ENTRADA_QUINTO = new TempoEstampa(16, 30);
        TempoEstampa ENTRADA_SEXTO = new TempoEstampa(17, 15);

        TempoEstampa SAIDA_PRIMEIRO = new TempoEstampa(13, 45);
        TempoEstampa SAIDA_SEGUNDO = new TempoEstampa(14, 30);
        TempoEstampa SAIDA_TERCEIRO = new TempoEstampa(15, 30);

        TempoEstampa SAIDA_QUARTO = new TempoEstampa(16, 30);
        TempoEstampa SAIDA_QUINTO = new TempoEstampa(17, 15);
        TempoEstampa SAIDA_SEXTO = new TempoEstampa(18, 00);

        TempoEstampa INTERVALO_INICIO = new TempoEstampa(15, 30);
        TempoEstampa INTERVALO_FIM = new TempoEstampa(15, 50);

        String SALA_I = "08";
        String SALA_L = "11";
        String SALA_H = "07";

        String SALA_N = "T15";
        String SALA_K = "10";
        String SALA_M = "12";
        String SALA_J = "09";

        int SIMPLES = 1;

        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7I", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, 2, SALA_I));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7L", "CN", ENTRADA_TERCEIRO, SAIDA_TERCEIRO, 1, SALA_L));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "I", "IN", INTERVALO_INICIO, INTERVALO_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7L", "CN", ENTRADA_QUARTO, SAIDA_QUARTO, 1, SALA_L));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7H", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, 2, SALA_H));

        eProfessor.definirAula(new TurmaItem("TERCA", "7L", "CN", ENTRADA_PRIMEIRO, SAIDA_PRIMEIRO, 1, SALA_L));
        eProfessor.definirAula(new TurmaItem("TERCA", "7N", "CN", ENTRADA_SEGUNDO, SAIDA_TERCEIRO, 2, SALA_N));
        eProfessor.definirAula(new TurmaItem("TERCA", "I", "IN", INTERVALO_INICIO, INTERVALO_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("TERCA", "7H", "PD", ENTRADA_QUARTO, SAIDA_QUARTO, 1, SALA_H));
        eProfessor.definirAula(new TurmaItem("TERCA", "7I", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, 2, SALA_I));

        eProfessor.definirAula(new TurmaItem("QUARTA", "7I", "PD", ENTRADA_PRIMEIRO, SAIDA_PRIMEIRO, 1, SALA_I));
        eProfessor.definirAula(new TurmaItem("QUARTA", "7K", "CN", ENTRADA_SEGUNDO, SAIDA_TERCEIRO, 2, SALA_K));
        eProfessor.definirAula(new TurmaItem("QUARTA", "I", "IN", INTERVALO_INICIO, INTERVALO_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUARTA", "7L", "CN", ENTRADA_QUARTO, SAIDA_QUARTO, 1, SALA_L));
        eProfessor.definirAula(new TurmaItem("QUARTA", "7N", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, 2, SALA_N));

        eProfessor.definirAula(new TurmaItem("QUINTA", "7K", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, 2, SALA_K));
        eProfessor.definirAula(new TurmaItem("QUINTA", "7M", "CN", ENTRADA_TERCEIRO, SAIDA_TERCEIRO, 1, SALA_M));
        eProfessor.definirAula(new TurmaItem("QUINTA", "I", "IN", INTERVALO_INICIO, INTERVALO_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUINTA", "7M", "CN", ENTRADA_QUARTO, SAIDA_QUARTO, 1, SALA_M));
        eProfessor.definirAula(new TurmaItem("QUINTA", "7J", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, 1, SALA_J));

        eProfessor.definirAula(new TurmaItem("SEXTA", "7M", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, 2, SALA_M));
        eProfessor.definirAula(new TurmaItem("SEXTA", "7H", "CN", ENTRADA_TERCEIRO, SAIDA_TERCEIRO, 1, SALA_H));
        eProfessor.definirAula(new TurmaItem("SEXTA", "I", "IN", INTERVALO_INICIO, INTERVALO_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEXTA", "7H", "CN", ENTRADA_QUARTO, SAIDA_QUARTO, 1, SALA_H));
        eProfessor.definirAula(new TurmaItem("SEXTA", "7J", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, 2, SALA_J));

        return eProfessor;
    }


}
