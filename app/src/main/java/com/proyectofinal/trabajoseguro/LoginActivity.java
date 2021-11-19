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









// PARA VER EN CONSOLA LAS EMPRESAS REGISTRADAS
       /* ArrayList<Empresa> EmpresasRegistradas = new ArrayList<>();
        ConexionSQLite c = new ConexionSQLite(this, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase bd = c.getReadableDatabase();
        Cursor cursor = bd.rawQuery(" SELECT*FROM Empresa ", null);
        while(cursor.moveToNext()) {
            Empresa aux = new Empresa();
            aux.setId_empresa(cursor.getInt(0));
            aux.setNombre(cursor.getString(1));
            aux.setEncargado(cursor.getString(2));
            aux.setCorreo(cursor.getString(3));
            aux.setTelefono(cursor.getString(4));
            aux.setRuc(cursor.getString(5));
            aux.setUsuario(cursor.getString(6));
            aux.setContrasenia(cursor.getString(7));
            aux.setDescripcion(cursor.getString(8));
            aux.setDireccion(cursor.getString(9));
            aux.setDepartamento(cursor.getString(10));
            EmpresasRegistradas.add(aux);
        }
        bd.close();
        for (int i = 0; i < EmpresasRegistradas.size(); ++i) {
            Log.i("Empresa ", EmpresasRegistradas.get(i).toString());
        }*/