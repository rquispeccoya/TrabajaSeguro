package com.proyectofinal.trabajoseguro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.proyectofinal.trabajoseguro.databinding.ActivityLoginBinding;
import com.proyectofinal.trabajoseguro.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private SharePreferenceHandler sharePreferenceHandler;
    public static Context contextOfApplication;
    SharedPreferences prefs;
    boolean sesion=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharePreferenceHandler=new SharePreferenceHandler(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
       sesion=getSesion();

        if(sesion){
            Bundle bundle = new Bundle();
            bundle.putInt("idEmpresa",prefs.getInt("idEmpresa",1));
            bundle.putString("nombreEmpresa",prefs.getString("nombreEmpresa",""));
            bundle.putString("encargadoEmpresa",prefs.getString("encargadoEmpresa",""));
            System.out.println("Nombre de la empresa:"+prefs.getString("nombreEmpresa",""));
            System.out.println("Id de la empresa:"+prefs.getInt("idEmpresa",7));
          Intent intent = new Intent(this, InterfazUsuarioActivity.class);
           intent.putExtras(bundle);
          this.startActivity(intent);
        }
        contextOfApplication = getApplicationContext();
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