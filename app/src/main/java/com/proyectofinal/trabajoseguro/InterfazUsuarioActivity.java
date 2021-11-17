package com.proyectofinal.trabajoseguro;

import android.os.Bundle;
import android.view.View;
import android.view.Menu;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.proyectofinal.trabajoseguro.databinding.ActivityInterfazUsuarioBinding;
import com.proyectofinal.trabajoseguro.model.Anuncio;
import com.proyectofinal.trabajoseguro.model.iComunicaFragments;

public class InterfazUsuarioActivity extends AppCompatActivity  implements iComunicaFragments {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityInterfazUsuarioBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityInterfazUsuarioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarInterfazUsuario.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_crear_anuncio,R.id.nav_detalle_anuncio)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_interfaz_usuario);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.interfaz_usuario, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_interfaz_usuario);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


    @Override
    public void enviarAnuncio(Anuncio anuncio) {
        System.out.println("ENTRO EN ANUNCIO ENVIARRRR");
            //gracias a hbaer implementado de la interface "iComunicaFragments" se tiene la implementacion del metodo enviarPersona
            //o mejor dicho este metodo.
            //Aqui se realiza toda la logica necesaria para poder realizar el envio
            AnuncioDetalleFragment anuncioDetalleFragment = new AnuncioDetalleFragment();
            //objeto bundle para transportar la informacion
            Bundle bundleEnvio = new Bundle();
            //se manda el objeto que le esta llegando:
            bundleEnvio.putSerializable("objeto",anuncio);
            anuncioDetalleFragment.setArguments(bundleEnvio);

            //CArgar fragment en el activity
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.nav_host_fragment_content_interfaz_usuario, anuncioDetalleFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

    }
}