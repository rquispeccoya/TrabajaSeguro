package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;

import com.proyectofinal.trabajoseguro.InterfazUsuarioActivity;

public class LoginViewModel extends BaseObservable {
    private Context context;
    public LoginViewModel(Context context){
        this.context=context;
    }

    public void onFormularioPaciente() {

        Intent intent = new Intent(context, InterfazUsuarioActivity.class);
        context.startActivity(intent);
    }
}
