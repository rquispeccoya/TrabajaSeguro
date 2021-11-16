package com.proyectofinal.trabajoseguro.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

    public ArrayList<Anuncio> listaAnuncios(){

        ArrayList<Anuncio> anuncios= new ArrayList<>();
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT*FROM Anuncios ", null);
        while(cursor.moveToNext()){
            Anuncio anuncio=new Anuncio();

            anuncio.setIdUsuario(Integer.parseInt(cursor.getString(6)));
            anuncio.setTitulo(cursor.getString(1));
            anuncio.setDescripcion(cursor.getString(2));
            anuncio.setLatitud(Double.parseDouble(cursor.getString(3)));
            anuncio.setLongitud(Double.parseDouble(cursor.getString(4)));
            anuncio.setEstado(Integer.parseInt(cursor.getString(5)));
            anuncio.setCategoria(Integer.parseInt(cursor.getString(7)));
            anuncios.add(anuncio);
        }

        db.close();

        return anuncios;
    }
}
