package com.luandkg.guilherme.professores;


import com.luandkg.guilherme.horario.AtividadeEspecial;
import com.luandkg.guilherme.horario.Professor;
import com.luandkg.guilherme.horario.TempoEstampa;
import com.luandkg.guilherme.horario.TurmaItem;
import com.luandkg.guilherme.utils.tempo.Calendario;

public class Freitas {

    public static Professor getLucas() {

        Professor eProfessor = new Professor();
        eProfessor.setNome("Lucas Freitas");
        eProfessor.setSigla("FREITAS");

        AtividadeEspecial ca = eProfessor.criarAtividade("TERCA", "CA", "Coordenação de Área", "Estou coordenando em área ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial cc = eProfessor.criarAtividade("QUARTA", "CC", "Coordenação Coletiva", "Estou em coordenação coletiva ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial ci = eProfessor.criarAtividade("QUINTA", "CI", "Coordenação Individual", "Estou coordenando individualmente ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        ca.mostrarIndo("Estou indo para coordenação de área....");
        cc.mostrarIndo("Estou indo para coordenação coletiva ....");
        ci.mostrarIndo("Estou indo para coordenação de individual....");

        //  eProfessor.definirCoordenacaoArea("TERCA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        // eProfessor.definirCoordenacaoColetiva("QUARTA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        //   eProfessor.definirCoordenacaoInidivudal("QUINTA", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        eProfessor.definirAlmoco(new TempoEstampa(12, 0), new TempoEstampa(12, 45));


        eProfessor.criarCargaDeTrabalho(Calendario.SEGUNDA,new TempoEstampa(13, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.TERCA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUARTA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUINTA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.SEXTA,new TempoEstampa(13, 0), new TempoEstampa(18, 0));



        TempoEstampa ENTRADA_PRIMEIRO = new TempoEstampa(13, 0);
        TempoEstampa ENTRADA_SEGUNDO = new TempoEstampa(13, 45);
        TempoEstampa ENTRADA_TERCEIRO = new TempoEstampa(14, 30);

        TempoEstampa ENTRADA_QUARTO = new TempoEstampa(15, 15);
        TempoEstampa ENTRADA_QUINTO = new TempoEstampa(15, 30);
        TempoEstampa ENTRADA_SEXTO = new TempoEstampa(16, 15);
        TempoEstampa ENTRADA_SETIMO = new TempoEstampa(16, 30);
        TempoEstampa ENTRADA_OITAVO = new TempoEstampa(17, 15);

        TempoEstampa SAIDA_PRIMEIRO = new TempoEstampa(13, 45);
        TempoEstampa SAIDA_SEGUNDO = new TempoEstampa(14, 30);
        TempoEstampa SAIDA_TERCEIRO = new TempoEstampa(15, 15);

        TempoEstampa SAIDA_QUARTO = new TempoEstampa(15, 30);
        TempoEstampa SAIDA_QUINTO = new TempoEstampa(16, 15);
        TempoEstampa SAIDA_SEXTO = new TempoEstampa(16, 30);
        TempoEstampa SAIDA_SETIMO = new TempoEstampa(17, 15);
        TempoEstampa SAIDA_OITAVO = new TempoEstampa(18, 0);

        TempoEstampa INTERVALO_A_INICIO = new TempoEstampa(15, 15);
        TempoEstampa INTERVALO_A_FIM = new TempoEstampa(15, 30);

        TempoEstampa INTERVALO_B_INICIO = new TempoEstampa(16, 15);
        TempoEstampa INTERVALO_B_FIM = new TempoEstampa(16, 30);

        int SIMPLES = 1;
        int DUPLA = 2;
        int TRIPLA = 3;


        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7F", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7G", "CN", ENTRADA_TERCEIRO, SAIDA_QUINTO, DUPLA));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "I", "IN", INTERVALO_B_INICIO, INTERVALO_B_FIM, SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEGUNDA", "7C", "CN", ENTRADA_SETIMO, SAIDA_OITAVO, DUPLA));

        eProfessor.definirAula(new TurmaItem("TERCA", "7A", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("TERCA", "7B", "PD", ENTRADA_TERCEIRO, SAIDA_TERCEIRO, SIMPLES));
        eProfessor.definirAula(new TurmaItem("TERCA", "I", "IN", INTERVALO_A_INICIO, INTERVALO_A_FIM, SIMPLES));
        eProfessor.definirAula(new TurmaItem("TERCA", "7A", "PD", ENTRADA_QUINTO, SAIDA_SEXTO, SIMPLES));
        eProfessor.definirAula(new TurmaItem("TERCA", "7B", "CN", ENTRADA_SETIMO, SAIDA_OITAVO, DUPLA));

        eProfessor.definirAula(new TurmaItem("QUARTA", "7G", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("QUARTA", "7D", "CN", ENTRADA_TERCEIRO, SAIDA_QUINTO, DUPLA));
        eProfessor.definirAula(new TurmaItem("QUARTA", "I", "IN", INTERVALO_B_INICIO, INTERVALO_B_FIM, SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUARTA", "7E", "CN", ENTRADA_SETIMO, SAIDA_OITAVO, DUPLA));


        eProfessor.definirAula(new TurmaItem("QUINTA", "7B", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("QUINTA", "7C", "CN", ENTRADA_TERCEIRO, SAIDA_QUINTO, DUPLA));
        eProfessor.definirAula(new TurmaItem("QUINTA", "I", "IN", INTERVALO_B_INICIO, INTERVALO_B_FIM, SIMPLES));
        eProfessor.definirAula(new TurmaItem("QUINTA", "7F", "CN", ENTRADA_SETIMO, SAIDA_OITAVO, DUPLA));

        eProfessor.definirAula(new TurmaItem("SEXTA", "7E", "CN", ENTRADA_PRIMEIRO, SAIDA_SEGUNDO, DUPLA));
        eProfessor.definirAula(new TurmaItem("SEXTA", "7A", "CN", ENTRADA_TERCEIRO, SAIDA_TERCEIRO, SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEXTA", "I", "IN", INTERVALO_A_INICIO, INTERVALO_A_FIM, SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEXTA", "7A", "CN", ENTRADA_QUINTO, SAIDA_SEXTO, SIMPLES));
        eProfessor.definirAula(new TurmaItem("SEXTA", "7D", "CN", ENTRADA_SETIMO, SAIDA_OITAVO, DUPLA));

        return eProfessor;
    }


}
