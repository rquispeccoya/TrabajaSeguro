package com.proyectofinal.trabajoseguro.model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.proyectofinal.trabajoseguro.model.ConexionSQLite;
import com.proyectofinal.trabajoseguro.model.FireBaseConexion;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;

public class DataEmpresa {
    private Context context;
    public DataEmpresa(Context context) {
        this.context = context;
    }
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

    public void registrarEmpresaSqlite(Empresa empresa) {
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
        db.close();
    }

    public Empresa buscarEmpresa(String id){

        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT*FROM Empresa WHERE usuario='"+id+"'", null);
        if(cursor.getCount()==1) {
            cursor.moveToFirst();
            Empresa empresa = new Empresa();
            empresa.setId_empresa(Integer.parseInt(cursor.getString(0)));
            empresa.setNombre(cursor.getString(1));
            empresa.setEncargado(cursor.getString(2));
            empresa.setCorreo(cursor.getString(3));
            empresa.setTelefono(cursor.getString(4));
            empresa.setRuc(cursor.getString(5));
            empresa.setUsuario(cursor.getString(6));
            empresa.setContrasenia(cursor.getString(7));
            empresa.setDescripcion(cursor.getString(8));
            empresa.setDireccion(cursor.getString(9));
            empresa.setDepartamento(cursor.getString(10));
            db.close();
            return empresa;
        }
        else
            return null;
    }
}
