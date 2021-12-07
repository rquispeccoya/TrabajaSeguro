package com.proyectofinal.trabajoseguro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        Spinner staticSpinner = (Spinner) findViewById(R.id.static_spinner);

        ArrayAdapter<CharSequence> staticAdapter = ArrayAdapter
                .createFromResource(this, R.array.departamentos,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        staticAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(staticAdapter);
    }



}