package com.luandkg.guilherme.escola.metodo_avaliativo;

import com.luandkg.guilherme.Local;
import com.luandkg.guilherme.escola.alunos.AlunoResultado;
import com.luandkg.guilherme.escola.metodo_avaliativo.AtividadeRealizada;
import com.luandkg.guilherme.escola.metodo_avaliativo.Avaliador;
import com.luandkg.guilherme.escola.metodo_avaliativo.CalcularNotas;
import com.luandkg.guilherme.libs.dkg.DKG;
import com.luandkg.guilherme.libs.dkg.DKGObjeto;
import com.luandkg.guilherme.utils.FS;
import com.luandkg.guilherme.utils.Strings;

import java.util.ArrayList;

public class Exportacao {

    public static void exportar(String eArquivo) {

        DKG documento = new DKG();
        DKGObjeto dkg_alunos = documento.unicoObjeto("Notas");

        ArrayList<AlunoResultado> mAlunos = CalcularNotas.calcular();


        for (AlunoResultado aluno : mAlunos) {

            DKGObjeto dkg_aluno = dkg_alunos.criarObjeto("Aluno");


            dkg_aluno.identifique("AlunoID").setValor(aluno.getID());
            dkg_aluno.identifique("Nome").setValor(aluno.getNome());
            dkg_aluno.identifique("Turma").setValor(aluno.getTurma());
            dkg_aluno.identifique("Visibilidade").setValor("SIM");

            dkg_aluno.identifique("Atividades").setInteiro(aluno.getAtividadesRealizadas().size());
            dkg_aluno.identifique("Atrasadas").setInteiro(aluno.getAtividadesAtrasadas().size());
            dkg_aluno.identifique("AtrasadasSemAtestado").setInteiro(aluno.getAtividadesAtrasadasComAtestado().size());
            dkg_aluno.identifique("AtrasadasComAtestado").setInteiro(aluno.getAtividadesAtrasadasSemAtestado().size());


            dkg_aluno.identifique("Nota").setDouble(aluno.getNotaFinalDouble());

            DKGObjeto dkg_atividades = dkg_aluno.unicoObjeto("Atividades");

            for (AtividadeRealizada atv : aluno.getAtividadesRealizadas()) {

                DKGObjeto dkg_atividade = dkg_atividades.criarObjeto("Atividade");
                dkg_atividade.identifique("Data").setValor(atv.getData());
                dkg_atividade.identifique("Entregue").setValor(atv.getDataEntregue());

                if (atv.getStatus()) {
                    dkg_atividade.identifique("Realizada").setValor("SIM");
                } else {
                    dkg_atividade.identifique("Realizada").setValor("NAO");
                }

                if (atv.isAtrasada()) {
                    dkg_atividade.identifique("Atrasada").setValor("SIM");
                } else {
                    dkg_atividade.identifique("Atrasada").setValor("NAO");
                }

                if (atv.temAtestado()) {
                    dkg_atividade.identifique("TemAtestado").setValor("SIM");
                } else {
                    dkg_atividade.identifique("TemAtestado").setValor("NAO");
                }

            }

        }

        DKGObjeto dkg_maximos = documento.unicoObjeto("Maximos");

        ArrayList<String> turmas = new ArrayList<String>();

        turmas.add("7H");
        turmas.add("7I");
        turmas.add("7J");
        turmas.add("7K");
        turmas.add("7L");
        turmas.add("7M");
        turmas.add("7N");

        turmas.add("PD7H");
        turmas.add("PD7I");

        for (String turma : turmas) {

            String AVALIADOR_VALOR_STRING = Strings.seVazioEntao(Avaliador.getAvaliacao(turma), "1,0");
            double AVALIADOR_VALOR = Double.parseDouble(AVALIADOR_VALOR_STRING.replace(",", "."));

            DKGObjeto dkg_atividade = dkg_maximos.criarObjeto("Maximo");
            dkg_atividade.identifique("Turma").setValor(turma);
            dkg_atividade.identifique("Valor").setDouble(AVALIADOR_VALOR);


        }


        documento.salvar(FS.getArquivoLocal(Local.LOCAL + "/" + eArquivo));

        DKG abrir = new DKG();
        abrir.abrir((FS.getArquivoLocal(Local.LOCAL + "/" + eArquivo)));

        System.out.println(abrir.toString());
    }
}
