package com.proyectofinal.trabajoseguro.viewmodels;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyectofinal.trabajoseguro.InterfazRegistroActivity;
import com.proyectofinal.trabajoseguro.InterfazTrabajadorActivity;
import com.proyectofinal.trabajoseguro.InterfazUsuarioActivity;
import com.proyectofinal.trabajoseguro.LoginActivity;
import com.proyectofinal.trabajoseguro.model.ConexionSQLite;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataEmpresa;
import com.proyectofinal.trabajoseguro.model.DAO.DataLogin;
import com.proyectofinal.trabajoseguro.model.FireBaseConexion;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;
import com.proyectofinal.trabajoseguro.model.entity.Login;

import java.util.UUID;

public class LoginViewModel extends BaseObservable {
    private Context context;
    private Login login;
    Context applicationContext = LoginActivity.getContextOfApplication() ;
    SharedPreferences prefs;

    public LoginViewModel(Context context){
        this.context=context;
        prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext);
        login = new Login();
    }

    public void setLoginUsuario(String usuario) {login.setUsuario(usuario);}
    public void setLoginContrasenia(String contrasenia) {login.setContrasenia(contrasenia);}

    @Bindable public String getLoginUsuario() {return login.getUsuario();}
    @Bindable public String getLoginContrasenia() {return login.getContrasenia();}

    public void onIniciarSesion() {
        SharedPreferences.Editor editor=prefs.edit();
        DataLogin dataLogin = new DataLogin(context.getApplicationContext());
        Empresa empresa = dataLogin.buscarEmpresa(login);

        if(empresa != null){
            Bundle bundle = new Bundle();
            bundle.putString("idUsuario",empresa.getUsuario());
            bundle.putInt("idEmpresa", empresa.getId_empresa());
            bundle.putString("nombreEmpresa",empresa.getNombre());
            bundle.putString("encargadoEmpresa",empresa.getEncargado());
            editor.putInt("idEmpresa",empresa.getId_empresa());
            editor.putString("nombreEmpresa",empresa.getNombre()+" ");
            editor.putString("encargadoEmpresa",empresa.getEncargado()+" ");
            editor.commit();
            Intent intent = new Intent(context, InterfazUsuarioActivity.class);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }else{
            Toast.makeText(context.getApplicationContext(), "Datos incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void onFormularioRegistro() {
        if(isConnected()){
            Intent intent = new Intent(context, InterfazRegistroActivity.class);
            context.startActivity(intent);
        }else {
          Toast.makeText(context.getApplicationContext(), "Conectate a internet", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBuscarTrabajo() {
        Intent intent = new Intent(context, InterfazTrabajadorActivity.class);
        context.startActivity(intent);
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
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