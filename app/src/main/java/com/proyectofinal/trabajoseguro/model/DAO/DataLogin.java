package com.proyectofinal.trabajoseguro.model.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyectofinal.trabajoseguro.model.ConexionSQLite;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;
import com.proyectofinal.trabajoseguro.model.entity.Login;

public class DataLogin {
    private Context context;
    public DataLogin (Context context) {this.context = context;}


    public Empresa buscarEmpresa(Login login) {

        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM Empresa WHERE usuario = '" + login.getUsuario() + "' AND contrasenia = '" + login.getContrasenia() + "'";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.getCount()==1) {
            cursor.moveToFirst();
            Empresa empresa = new Empresa();
            empresa.setId_empresa(Integer.parseInt(cursor.getString(0)));
            empresa.setNombre(cursor.getString(1));
            empresa.setEncargado(cursor.getString(2));
            db.close();
          return empresa;
        }
        else
            return null;

    }

}






   /*public boolean comprobarDatos(Login login) {
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM Empresa WHERE usuario = '" + login.getUsuario() + "' AND contrasenia = '" + login.getContrasenia() + "'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor.getCount() == 1 ? true : false;
    }*/