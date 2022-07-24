package com.luandkg.guilherme;

import com.luandkg.guilherme.libs.verkuz.Verkuz;

public class Versionador extends Verkuz {


    public Versionador() {

        setEstagio(Verkuz.RELEASE);

        setAutor("Launni Corporation");
        setModo(Verkuz.SIMPLES);

    }

    public static boolean isTeste() {
        Versionador eVersionador = new Versionador();
        return eVersionador.getEstagio() == Verkuz.TESTE;
    }

    public static boolean isRelease() {
        Versionador eVersionador = new Versionador();
        return eVersionador.getEstagio() == Verkuz.RELEASE;
    }

    public void init() {

        DEV("2022_07_24", "Migrar para TERCEIRO BIMESTRE");
        DEV("2022_07_24", "Nova Janela - SOBRE");
        DEV("2022_07_24", "Fechar notas sem descontar atividades atrasadas com atestado");
        DEV("2022_07_24", "Marcar atestado em atividades atrasadas");

        DEV("2022_07_15", "Sistema de Versionamento");
        DEV("2022_07_13", "Integração ao GIT HUB");

        DEV("2022_06_25", "Desconto de atividades entregues atrasadas");

        DEV("2022_06_15", "Grafico de desempenho");
        DEV("2022_06_15", "Fluxo de entrega de atividades");


        DEV("2022_04_12", "Calculo de notas");
        DEV("2022_04_10", "Controle de atividades de alunos");

        DEV("2022_03_15", "Notificações implementadas");
        DEV("2022_03_12", "Sistema de horários");
        DEV("2022_03_10", "Inicio do projeto");


    }


}
