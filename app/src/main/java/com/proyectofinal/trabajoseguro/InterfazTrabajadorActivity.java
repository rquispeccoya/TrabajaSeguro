package com.proyectofinal.trabajoseguro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectofinal.trabajoseguro.ui.graficoBarrasTrabajador.GraficoBarrasTrabajadorFragment;
import com.proyectofinal.trabajoseguro.ui.listaTrabajador.ListaTrabajadorFragment;
import com.proyectofinal.trabajoseguro.ui.mapaTrabajador.MapaTrabajadorFragment;

public class InterfazTrabajadorActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle(R.string.tituloBuscar);
        setContentView(R.layout.activity_interfaz_trabajador);

        bottomNavigationView=findViewById(R.id.bottomNavigationOption);

        bottomNavigationView.setOnNavigationItemSelectedListener(nav);
        getSupportFragmentManager().
                beginTransaction().
                replace(R.id.fragment_container_trabajador,new MapaTrabajadorFragment()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener nav = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment=null;
            switch (item.getItemId()){
                case R.id.nav_buscar_mapa:
                    fragment=new MapaTrabajadorFragment();
                    break;

                case R.id.nav_grafico_barra:
                    fragment=new GraficoBarrasTrabajadorFragment();
                    break;

            }

            getSupportFragmentManager().
                    beginTransaction().
                    replace(R.id.fragment_container_trabajador,fragment).commit();
            return  true;
        }
    };
}