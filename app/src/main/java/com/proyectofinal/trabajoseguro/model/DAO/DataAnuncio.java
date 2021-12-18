package com.proyectofinal.trabajoseguro.model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.proyectofinal.trabajoseguro.model.ConexionSQLite;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;

import java.util.ArrayList;

public class DataAnuncio {

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

    public ArrayList<Anuncio> listaAnuncios(String idEm){
        //int idEmpresa= Integer.parseInt(idEm);
        ArrayList<Anuncio> anuncios= new ArrayList<>();
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        //Cursor cursor = db.rawQuery(" SELECT*FROM Anuncios WHERE idUsuario ="+idEmpresa, null);
        Cursor cursor = db.rawQuery(" SELECT a.titulo,a.descripcion,c.nombre FROM Anuncios as a, Categoria as c WHERE a.idCat = c.idCat AND idUsuario ='"+idEm+"'", null);
        while(cursor.moveToNext()){
            Anuncio anuncio=new Anuncio();

            anuncio.setTitulo(cursor.getString(0));
            anuncio.setDescripcion(cursor.getString(1));
            anuncio.setNombreCategoria(cursor.getString(2));
            System.out.println(anuncio.getTitulo());
            System.out.println(anuncio.getDescripcion());
            System.out.println(anuncio.getNombreCategoria());
            /*
            anuncio.setIdUsuario(Integer.parseInt(cursor.getString(6)));
            anuncio.setTitulo(cursor.getString(1));
            anuncio.setDescripcion(cursor.getString(2));
            anuncio.setLatitud(Double.parseDouble(cursor.getString(3)));
            anuncio.setLongitud(Double.parseDouble(cursor.getString(4)));
            anuncio.setEstado(Integer.parseInt(cursor.getString(5)));
            anuncio.setCategoria(Integer.parseInt(cursor.getString(7)));*/
            anuncios.add(anuncio);
        }

        db.close();

        return anuncios;
    }
}
