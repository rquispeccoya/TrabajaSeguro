package com.proyectofinal.trabajoseguro.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataLogin {
    private Context context;
    public DataLogin (Context context) {this.context = context;}
    public boolean comprobarDatos(Login login) {
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM Empresa WHERE usuario = '" + login.getUsuario() + "' AND contrasenia = '" + login.getContrasenia() + "'";
        Cursor cursor = db.rawQuery(query, null);

        return cursor.getCount() == 1 ? true : false;
    }

}
