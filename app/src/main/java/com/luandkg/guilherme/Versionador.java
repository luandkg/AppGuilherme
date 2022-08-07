package com.luandkg.guilherme;

import com.luandkg.guilherme.libs.verkuz.Verkuz;

public class Versionador extends Verkuz {


    public Versionador() {

        setEstagio(Verkuz.RELEASE);

        setAutor("Launni Corporation");
        setModo(Verkuz.SIMPLES);

    }


    public void init() {

        DEV("2022_08_07", "Agenda do mês");


        DEV("2022_07_30", "Lançamento de atestados");
        DEV("2022_07_30", "Selecionador de aluno");
        DEV("2022_07_30", "Selecionador de data");


        DEV("2022_07_26", "Sistema de anotações - Remover todos os arquivados");
        DEV("2022_07_26", "Sistema de anotações - Arquivar / Desarquivar");
        DEV("2022_07_26", "Sistema de anotações - Editar anotação");
        DEV("2022_07_26", "Sistema de anotações - Listar anotação");
        DEV("2022_07_26", "Sistema de anotações - Remover anotação");
        DEV("2022_07_26", "Sistema de anotações - Criar anotação");
        DEV("2022_07_26", "Construção do Sistema de anotações");


        DEV("2022_07_25", "Exportar atividades de cada aluno e notas atribuida a turma");
        DEV("2022_07_25", "Correção do metodo de gerar notas de todos os alunos");
        DEV("2022_07_25", "Exportar notas dos alunos em arquivo !");
        DEV("2022_07_25", "Buscar alunos de forma rapida");

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
