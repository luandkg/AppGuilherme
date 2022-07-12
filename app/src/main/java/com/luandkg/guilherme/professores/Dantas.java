package com.luandkg.guilherme.professores;


import com.luandkg.guilherme.horario.AtividadeEspecial;
import com.luandkg.guilherme.horario.Professor;
import com.luandkg.guilherme.horario.TempoEstampa;
import com.luandkg.guilherme.horario.TurmaItem;
import com.luandkg.guilherme.utils.tempo.Calendario;

public class Dantas {

    public static Professor getDantas() {

        Professor eProfessor = new Professor();
        eProfessor.setNome("Abner Dantas");
        eProfessor.setSigla("DANTAS");

        // eProfessor.definirCoordenacaoArea("TERCA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial cc = eProfessor.criarAtividade("QUARTA", "CC", "Coordenação Coletiva", "Estou em coordenação coletiva ....", new TempoEstampa(13, 0), new TempoEstampa(16, 0));
        //  eProfessor.definirCoordenacaoInidivudal("QUINTA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        //ca.mostrarIndo("Estou indo para coordenação de área....");
        //cc.mostrarIndo("Estou indo para coordenação coletiva ....");
        //ci.mostrarIndo("Estou indo para coordenação de individual....");



        eProfessor.criarCargaDeTrabalho(Calendario.SEGUNDA,new TempoEstampa(7, 30), new TempoEstampa(12, 30));
        eProfessor.criarCargaDeTrabalho(Calendario.TERCA,new TempoEstampa(7, 30), new TempoEstampa(12, 30));
        eProfessor.criarCargaDeTrabalho(Calendario.QUARTA,new TempoEstampa(10, 5), new TempoEstampa(16, 0));
       // eProfessor.criarCargaDeTrabalho(Calendario.QUINTA,new TempoEstampa(7, 30), new TempoEstampa(18, 0));
       // eProfessor.criarCargaDeTrabalho(Calendario.SEXTA,new TempoEstampa(7, 30), new TempoEstampa(18, 0));


        TempoEstampa ENTRADA_PRIMEIRO = new TempoEstampa(7, 30);
        TempoEstampa ENTRADA_SEGUNDO = new TempoEstampa(8, 20);
        TempoEstampa ENTRADA_TERCEIRO = new TempoEstampa(9, 20);

        TempoEstampa ENTRADA_QUARTO = new TempoEstampa(10, 5);
        TempoEstampa ENTRADA_QUINTO = new TempoEstampa(11, 0);
        TempoEstampa ENTRADA_SEXTO = new TempoEstampa(11, 45);


        TempoEstampa SAIDA_PRIMEIRO = new TempoEstampa(8, 20);
        TempoEstampa SAIDA_SEGUNDO = new TempoEstampa(9, 10);
        TempoEstampa SAIDA_TERCEIRO = new TempoEstampa(10, 5);

        TempoEstampa SAIDA_QUARTO = new TempoEstampa(10, 50);
        TempoEstampa SAIDA_QUINTO = new TempoEstampa(11, 45);
        TempoEstampa SAIDA_SEXTO = new TempoEstampa(12, 30);


        TempoEstampa INTERVALO_A_INICIO = new TempoEstampa(9, 10);
        TempoEstampa INTERVALO_A_FIM = new TempoEstampa(9, 20);

        TempoEstampa INTERVALO_B_INICIO = new TempoEstampa(10, 50);
        TempoEstampa INTERVALO_B_FIM = new TempoEstampa(11, 00);

        int SIMPLES = 1;
        int DUPLA = 2;
        int TRIPLA = 3;


        eProfessor.definirAula(new TurmaItem("SEGUNDA", "8B", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "I", "IN", INTERVALO_A_INICIO, INTERVALO_A_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "8A", "CN", ENTRADA_TERCEIRO, SAIDA_QUARTO, DUPLA));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "I", "IN", INTERVALO_B_INICIO, INTERVALO_B_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "8C", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, DUPLA));

        eProfessor.definirAula(new TurmaItem("TERCA", "8C", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("TERCA", "I", "IN", INTERVALO_A_INICIO, INTERVALO_A_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("TERCA", "8B", "CN", ENTRADA_TERCEIRO, SAIDA_QUARTO, DUPLA));
        eProfessor.definirAula(new TurmaItem("TERCA", "I", "IN", INTERVALO_B_INICIO, INTERVALO_B_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("TERCA", "8A", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, DUPLA));


        eProfessor.definirAula(new TurmaItem("QUARTA", "8A", "PD", ENTRADA_QUARTO, SAIDA_QUARTO,SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUARTA", "I", "IN", INTERVALO_B_INICIO, INTERVALO_B_FIM,SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUARTA", "8C", "PD", ENTRADA_QUINTO, SAIDA_QUINTO,SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUARTA", "8B", "PD", ENTRADA_SEXTO, SAIDA_SEXTO,SIMPLES));


        return eProfessor;
    }


}
