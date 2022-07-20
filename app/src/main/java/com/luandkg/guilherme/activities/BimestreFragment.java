package com.luandkg.guilherme.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.luandkg.guilherme.escola.Professor;
import com.luandkg.guilherme.escola.render.BimestreImagem;
import com.luandkg.guilherme.databinding.FragmentSlideshowBinding;
import com.luandkg.guilherme.escola.alunos.Aluno;
import com.luandkg.guilherme.escola.render.FluxoDeEntrega;
import com.luandkg.guilherme.escola.tempo.BimestreTemporal;
import com.luandkg.guilherme.escola.Escola;
import com.luandkg.guilherme.escola.tempo.SEDF_22;
import com.luandkg.guilherme.professores.Professores;
import com.luandkg.guilherme.libs.tempo.Calendario;
import com.luandkg.guilherme.libs.tempo.Data;

import java.util.ArrayList;


public class BimestreFragment extends Fragment {

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        ImageView FLUXO_IV_ATIVIDADES = binding.FRAGFLUXOIVATIVIDADES;
        ImageView FLUXO_IV_TEMPO = binding.FRAGFLUXOIVTEMPO;
        TextView TV_BIMESTRE_INICIO = binding.FRAGFLUXOTVBIMESTREINICIO;
        TextView TV_BIMESTRE_FIM = binding.FRAGFLUXOTVBIMESTREFIM;

        ArrayList<Aluno> alunos = Escola.getAlunosVisiveis();


        SEDF_22 eSEDF_22 = new SEDF_22();

        ArrayList<Data> datas = eSEDF_22.getSegundo();
        String hoje = Calendario.getADMComBarras();


        if (eSEDF_22.getSegundo().size() > 0) {
            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(datas).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(datas).getTempoLegivel());
        }


        FLUXO_IV_ATIVIDADES.setImageBitmap(FluxoDeEntrega.criarFluxoDeEntrega(alunos, datas));

        Professor mProfessor = Professores.getProfessorCorrente();

        String HOJE_DATA = Calendario.getADM();

        if (mProfessor.estou_em_ferias(Data.toData(HOJE_DATA))) {


            FLUXO_IV_TEMPO.setImageBitmap(BimestreImagem.onFerias(mProfessor.ferias_passou(Data.toData(HOJE_DATA)), mProfessor.getFerias().size(),"FÉRIAS"));

            TV_BIMESTRE_INICIO.setText(Calendario.filtrar_primeira(mProfessor.getFerias()).getTempoLegivel());
            TV_BIMESTRE_FIM.setText(Calendario.filtrar_ultima(mProfessor.getFerias()).getTempoLegivel());

        } else {

            int acabar = BimestreTemporal.getDiasParaAcabar(hoje, datas);
            int progresso = BimestreTemporal.getPorcentagem(hoje, datas);

            FLUXO_IV_TEMPO.setImageBitmap(BimestreImagem.onBimestre(progresso, acabar));

        }



        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}