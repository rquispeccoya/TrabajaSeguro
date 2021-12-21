package com.proyectofinal.trabajoseguro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyectofinal.trabajoseguro.databinding.ActivityLoginBinding;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.FireBaseConexion;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.viewmodels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LoginActivity extends AppCompatActivity {
    private SharePreferenceHandler sharePreferenceHandler;
    public static Context contextOfApplication;
    SharedPreferences prefs;
    boolean sesion=false;
    FireBaseConexion fireBaseConexion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharePreferenceHandler=new SharePreferenceHandler(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        sesion=getSesion();
        contextOfApplication = getApplicationContext();

        fireBaseConexion = new FireBaseConexion(this);
        fireBaseConexion.initFirebase();
        fireBaseConexion.UpdateEmpresasTable();

        ActivityLoginBinding activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setLogin(new LoginViewModel(this));
        activityLoginBinding.executePendingBindings();
        System.out.println(sharePreferenceHandler.getValueBoolean("sesion"));


    }
    public boolean getSesion(){
        return sharePreferenceHandler.getValueBoolean("sesion");
    }
    public static Context getContextOfApplication(){ return contextOfApplication; }

}
