package com.proyectofinal.trabajoseguro;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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



public class InterfazUsuarioActivity extends AppCompatActivity{
    public String idEmpresa;
    private SharePreferenceHandler sharePreferenceHandler;
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityInterfazUsuarioBinding binding;
    private TextView textView, textView2, textView3;
    SharedPreferences prefs;
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
                R.id.nav_home, R.id.nav_crear_anuncio, R.id.nav_ver_anuncio)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_interfaz_usuario);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        idEmpresa = getIntent().getStringExtra("idUsuario");
        TextView loco = findViewById(R.id.idEmpresa);
        loco.setText(idEmpresa);

        sharePreferenceHandler=new SharePreferenceHandler(this);
        sharePreferenceHandler.saveValue("sesion",true);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        System.out.println(sharePreferenceHandler.getValueBoolean("sesion"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.interfaz_usuario, menu);

        //recibe los datos enviados desde el login
        idEmpresa = getIntent().getStringExtra("idUsuario");
        String nombreEmp = (getIntent().getStringExtra("nombreEmpresa"));
        String encargadoEmp = (getIntent().getStringExtra("encargadoEmpresa"));
        textView = findViewById(R.id.nombreInterfazUsuario);
        textView2 = findViewById(R.id.encargadoInterfazUsuario);
        textView3 = findViewById(R.id.idInterfazUsuario);

        textView.setText(nombreEmp);
        textView2.setText(encargadoEmp);
        textView3.setText("" + idEmpresa);

        return true;
    }

    //establecemos la funcion de cerrar sesion
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent1 = new Intent(this, LoginActivity.class);
            this.startActivity(intent1);
            sharePreferenceHandler.deleteValue("sesion");
            sharePreferenceHandler.deleteValue("idEmpresa");
            sharePreferenceHandler.deleteValue("nombreEmpresa");
            sharePreferenceHandler.deleteValue("encargadoEmpresa");
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_interfaz_usuario);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}









/*
    //enviamos los datos del fragment anuncio al segundo fragment
    @Override
    public void enviarAnuncio(Anuncio anuncio) {
        System.out.println("ENTRO EN ANUNCIO ENVIARRRR");
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
}*/