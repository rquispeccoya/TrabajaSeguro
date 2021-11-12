package com.proyectofinal.trabajoseguro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.proyectofinal.trabajoseguro.databinding.ActivityLoginBinding;
import com.proyectofinal.trabajoseguro.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActivityLoginBinding activityLoginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setLogin(new LoginViewModel(this));
        activityLoginBinding.executePendingBindings();
    }
}