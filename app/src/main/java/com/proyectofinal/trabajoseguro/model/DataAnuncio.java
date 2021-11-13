package com.proyectofinal.trabajoseguro.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DataAnuncio {

    //private Anuncio anuncio;
    //private  conn;
    private  Context context;
    public  DataAnuncio (Context context){
        this.context=context;
    }
    public void guardarAnuncio(Anuncio anuncio){
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo",anuncio.getTitulo());
        values.put("descripcion",anuncio.getDescripcion());
        values.put("latitud",anuncio.getLatitud());
        values.put("longitud",anuncio.getLongitud());
        values.put("idUsuario",anuncio.getIdUsuario());
        values.put("idCat",anuncio.getCategoria());
        Long idResultante = db.insert("Anuncios", "id", values);
        System.out.println("EXITOOOOO");
        db.close();
    }
}
