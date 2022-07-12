package com.luandkg.guilherme.professores;

import com.luandkg.guilherme.horario.AtividadeEspecial;
import com.luandkg.guilherme.horario.Calendario;
import com.luandkg.guilherme.horario.Horario;
import com.luandkg.guilherme.horario.Professor;
import com.luandkg.guilherme.horario.TempoEstampa;
import com.luandkg.guilherme.horario.TempoEstampaDuracao;


public class Luan {

    public static Professor getLuan() {

        Professor eProfessor = new Professor();

        eProfessor.setSiglaComNome("LUAN", "Luan Freitas");

        AtividadeEspecial ca = eProfessor.criarAtividade("TERCA", "CA", "Coordenação de Área", "Estou coordenando em área ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial cc = eProfessor.criarAtividade("QUARTA", "CC", "Coordenação Coletiva", "Estou em coordenação coletiva ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));
        AtividadeEspecial ci = eProfessor.criarAtividade("QUINTA", "CI", "Coordenação Individual", "Estou coordenando individualmente ....", new TempoEstampa(9, 0), new TempoEstampa(12, 0));

        ca.mostrarIndo("Estou indo para coordenação de área....");
        cc.mostrarIndo("Estou indo para coordenação coletiva ....");
        ci.mostrarIndo("Estou indo para coordenação de individual....");


        eProfessor.definirAlmoco(new TempoEstampa(12, 0), new TempoEstampa(12, 45));

      eProfessor.criarCargaDeTrabalho(Calendario.SEGUNDA,new TempoEstampa(13, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.TERCA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUARTA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.QUINTA,new TempoEstampa(9, 0), new TempoEstampa(18, 0));
        eProfessor.criarCargaDeTrabalho(Calendario.SEXTA,new TempoEstampa(13, 0), new TempoEstampa(18, 0));


        // HORARIOS

        TempoEstampaDuracao PRIMEIRO = new TempoEstampaDuracao(new TempoEstampa(13, 0), new TempoEstampa(13, 50));
        TempoEstampaDuracao SEGUNDO = new TempoEstampaDuracao(new TempoEstampa(13, 50), new TempoEstampa(14, 35));
        TempoEstampaDuracao TERCEIRO = new TempoEstampaDuracao(new TempoEstampa(14, 35), new TempoEstampa(15, 20));

        TempoEstampaDuracao INTERVALO = new TempoEstampaDuracao(new TempoEstampa(15, 20), new TempoEstampa(15, 40));

        TempoEstampaDuracao QUARTO = new TempoEstampaDuracao(new TempoEstampa(15, 40), new TempoEstampa(16, 30));
        TempoEstampaDuracao QUINTO = new TempoEstampaDuracao(new TempoEstampa(16, 30), new TempoEstampa(17, 15));
        TempoEstampaDuracao SEXTO = new TempoEstampaDuracao(new TempoEstampa(17, 15), new TempoEstampa(18, 0));


        // DISCIPLINAS

        String PD = "PD";
        String CN = "CN";


        int SIMPLES = 1;
        int DUPLA = 2;
        int TRIPLA = 3;


        // SEGUNDA
        eProfessor.definirAulaSegunda_Simples("9E", CN, Horario.entrar(PRIMEIRO), Horario. sair(PRIMEIRO));
        eProfessor.definirAulaSegunda_Simples("9G", CN,  Horario.entrar(SEGUNDO),  Horario.sair(SEGUNDO));
        eProfessor.definirAulaSegunda_Simples("9D", CN,  Horario.entrar(TERCEIRO), Horario. sair(TERCEIRO));
        eProfessor.definirAulaSegunda_Intervalo( Horario.entrar(INTERVALO),  Horario.sair(INTERVALO));
        eProfessor.definirAulaSegunda_Simples("9F", CN,  Horario.entrar(QUARTO),  Horario.sair(QUARTO));
        eProfessor.definirAulaSegunda_Dupla("9C", CN, Horario.entrar(QUINTO), Horario. sair(SEXTO));


        // TERCA
        eProfessor.definirAulaTerca_Simples("9E", CN,  Horario.entrar(PRIMEIRO),  Horario.sair(PRIMEIRO));
        eProfessor.definirAulaTerca_Simples("9C", CN,  Horario.entrar(SEGUNDO), Horario. sair(SEGUNDO));
        eProfessor.definirAulaTerca_Simples("9B", CN,  Horario.entrar(TERCEIRO), Horario. sair(TERCEIRO));
        eProfessor.definirAulaTerca_Intervalo( Horario.entrar(INTERVALO),  Horario.sair(INTERVALO));
        eProfessor.definirAulaTerca_Simples("9A", CN,  Horario.entrar(QUARTO), Horario. sair(QUARTO));
        eProfessor.definirAulaTerca_Simples("9D", CN,  Horario.entrar(QUINTO),  Horario.sair(QUINTO));
        eProfessor.definirAulaTerca_Simples("9G", CN,  Horario.entrar(SEXTO),  Horario.sair(SEXTO));


        // QUARTA
        eProfessor.definirAulaQuarta_Simples("9B", CN, Horario. entrar(PRIMEIRO),  Horario.sair(PRIMEIRO));
        eProfessor.definirAulaQuarta_Dupla("9D", CN,  Horario.entrar(SEGUNDO), Horario. sair(TERCEIRO));
        eProfessor.definirAulaQuarta_Intervalo( Horario.entrar(INTERVALO), Horario.sair(INTERVALO));
        eProfessor.definirAulaQuarta_Dupla("9G", CN,  Horario.entrar(QUARTO), Horario. sair(QUINTO));
        eProfessor.definirAulaQuarta_Simples("8D", PD,  Horario.entrar(SEXTO),  Horario.sair(SEXTO));


        // QUINTA
        eProfessor.definirAulaQuinta_Simples("9F", CN,  Horario.entrar(PRIMEIRO), Horario. sair(PRIMEIRO));
        eProfessor.definirAulaQuinta_Dupla("9E", CN,  Horario.entrar(SEGUNDO),  Horario.sair(TERCEIRO));
        eProfessor.definirAulaQuinta_Intervalo( Horario.entrar(INTERVALO),  Horario.sair(INTERVALO));
        eProfessor.definirAulaQuinta_Dupla("9A", CN,  Horario.entrar(QUARTO),  Horario.sair(QUINTO));
        eProfessor.definirAulaQuinta_Simples("9C", CN, Horario. entrar(SEXTO), Horario. sair(SEXTO));


        // SEXTA
        eProfessor.definirAulaSexta_Simples("9A", CN,  Horario.entrar(PRIMEIRO),  Horario.sair(PRIMEIRO));
        eProfessor.definirAulaSexta_Dupla("9B", CN,  Horario.entrar(SEGUNDO),  Horario.sair(TERCEIRO));
        eProfessor.definirAulaSexta_Intervalo( Horario.entrar(INTERVALO),  Horario.sair(INTERVALO));
        eProfessor.definirAulaSexta_Simples("7C", PD,  Horario.entrar(QUARTO), Horario. sair(QUARTO));
        eProfessor.definirAulaSexta_Dupla("9F", CN,  Horario.entrar(QUINTO),  Horario.sair(SEXTO));


        return eProfessor;
    }

}
