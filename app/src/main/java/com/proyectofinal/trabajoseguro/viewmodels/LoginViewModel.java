package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.InterfazRegistroActivity;
import com.proyectofinal.trabajoseguro.InterfazUsuarioActivity;
import com.proyectofinal.trabajoseguro.model.DAO.DataLogin;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;
import com.proyectofinal.trabajoseguro.model.entity.Login;

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

    public void onIniciarSesion() {

        DataLogin dataLogin = new DataLogin(context.getApplicationContext());
        Empresa empresa = dataLogin.buscarEmpresa(login);

        if(empresa != null){
            Bundle bundle = new Bundle();
            bundle.putInt("idEmpresa", empresa.getId_empresa());
            bundle.putString("nombreEmpresa",empresa.getNombre());
            bundle.putString("encargadoEmpresa",empresa.getEncargado());

            Intent intent = new Intent(context, InterfazUsuarioActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else{
            Toast.makeText(context.getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();

        }
    }

    public void onFormularioRegistro() {
        Intent intent = new Intent(context, InterfazRegistroActivity.class);
        context.startActivity(intent);
    }

}












//boolean correct = dataLogin.comprobarDatos(login);
        /*
        if(correct) {
            Bundle parmetros = new Bundle();
            parmetros.putString("usuario", "ROYER");

            Intent intent = new Intent(context, InterfazUsuarioActivity.class);
            intent.putExtras(parmetros);
            context.startActivity(intent);


        } else {
            Toast.makeText(context.getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }*/