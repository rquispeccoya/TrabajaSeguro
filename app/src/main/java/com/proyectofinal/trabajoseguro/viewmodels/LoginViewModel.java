package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.InterfazRegistroActivity;
import com.proyectofinal.trabajoseguro.InterfazUsuarioActivity;
import com.proyectofinal.trabajoseguro.model.DataLogin;
import com.proyectofinal.trabajoseguro.model.Empresa;
import com.proyectofinal.trabajoseguro.model.Login;

public class LoginViewModel extends BaseObservable {
    private Context context;
    private Login login;

    public LoginViewModel(Context context){
        this.context=context;
        login = new Login();
    }

    public void setLoginUsuario(String usuario) {login.setUsuario(usuario);}
    public void setLoginContrasenia(String contrasenia) {login.setContrasenia(contrasenia);}

    @Bindable public String getLoginUsuario() {return login.getUsuario();}
    @Bindable public String getLoginContrasenia() {return login.getContrasenia();}

    public void onFormularioPaciente() {

        DataLogin dataLogin = new DataLogin(context.getApplicationContext());
        boolean correct = dataLogin.comprobarDatos(login);

        if(correct) {
            Intent intent = new Intent(context, InterfazUsuarioActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context.getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void onFormularioRegistro() {
        Intent intent = new Intent(context, InterfazRegistroActivity.class);
        context.startActivity(intent);
    }
}
