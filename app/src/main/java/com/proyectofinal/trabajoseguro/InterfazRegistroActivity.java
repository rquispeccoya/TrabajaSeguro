package com.proyectofinal.trabajoseguro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.proyectofinal.trabajoseguro.databinding.ActivityInterfazRegistroBinding;
import com.proyectofinal.trabajoseguro.viewmodels.InterfazRegistroViewModel;

public class InterfazRegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaz_registro);
        ActivityInterfazRegistroBinding activityInterfazRegistroBinding = DataBindingUtil.setContentView(this, R.layout.activity_interfaz_registro);
        activityInterfazRegistroBinding.setRegistro(new InterfazRegistroViewModel(this));
        activityInterfazRegistroBinding.executePendingBindings();
    }
}