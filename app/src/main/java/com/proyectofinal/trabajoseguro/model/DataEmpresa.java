package com.proyectofinal.trabajoseguro.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

public class DataEmpresa {
    private Context context;
    public DataEmpresa(Context context) {this.context = context;}
    public void registrarEmpresa(Empresa empresa) {
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre",empresa.getNombre());
        values.put("encargado",empresa.getEncargado());
        values.put("correo",empresa.getCorreo());
        values.put("telefono",empresa.getTelefono());
        values.put("ruc",empresa.getRuc());
        values.put("usuario",empresa.getUsuario());
        values.put("contrasenia",empresa.getContrasenia());
        values.put("descripcion",empresa.getDescripcion());
        values.put("direccion",empresa.getDireccion());
        values.put("departamento",empresa.getDepartamento());
        Long idResultante = db.insert("Empresa", "id_empresa", values);
        Log.i("Save data", "Successful");
        Toast.makeText(context.getApplicationContext(), "Registro exitoso", Toast.LENGTH_SHORT).show();

        db.close();
    }
}
