package com.luandkg.guilherme.activities;

import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.luandkg.guilherme.R;
import com.luandkg.guilherme.databinding.ActivityTelaDeNavegacaoBinding;
import com.luandkg.guilherme.escola.NotificadorDeSinalEscolar;
import com.luandkg.guilherme.escola.Professor;
import com.luandkg.guilherme.professores.Professores;
import com.luandkg.guilherme.utils.FS;
import com.luandkg.guilherme.utils.Notificar;

public class TelaDeNavegacao extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityTelaDeNavegacaoBinding binding;
    private NotificadorDeSinalEscolar mNotificadorDeSinalEscolar;
    private Professor mProfessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityTelaDeNavegacaoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarTelaDeNavegacao.toolbar);

        //  binding.appBarTelaDeNavegacao.fab.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //       public void onClick(View view) {
        //         Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                  .setAction("Action", null).show();
        //      }
        //  });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_semanas,R.id.nav_desempenho)
                .setOpenableLayout(drawer)
                .build();


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_tela_de_navegacao);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        FS.pedirPermissoes(this);
        FS.verifyStoragePermissions(this);

        Notificar.criarCanal(getBaseContext(), "AVISOS", "AVISOS");

        mProfessor = Professores.getProfessorCorrente();

        mNotificadorDeSinalEscolar = new NotificadorDeSinalEscolar(this.getBaseContext(), mProfessor);

        mNotificadorDeSinalEscolar.run();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tela_de_navegacao, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_tela_de_navegacao);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }
}