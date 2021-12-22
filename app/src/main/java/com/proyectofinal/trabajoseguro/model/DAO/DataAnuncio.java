package com.proyectofinal.trabajoseguro.model.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyectofinal.trabajoseguro.model.ConexionSQLite;
import com.proyectofinal.trabajoseguro.model.FireBaseConexion;
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

        db.close();
    }

    public void guardarAnuncioSQlite(Anuncio anuncio){
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
    }

    private void CleanAnuncios() {
        ConexionSQLite conn = new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("delete from " + "Anuncios");
    }

    public ArrayList<Anuncio> listaAnuncios(String idEm){
        ArrayList<Anuncio> anuncios= new ArrayList<>();
        //int idEmpresa= Integer.parseInt(idEm);
        ////ArrayList<Anuncio> anuncios= new ArrayList<>();
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

    public Anuncio[] listaAnuncioxCategoria(){
        ArrayList<Anuncio> anuncios= new ArrayList<>();
        ConexionSQLite conn= new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT c.nombre, count(*) FROM Anuncios as a, Categoria as c WHERE c.idCat = a.idCat GROUP by a.idCat", null);
        while(cursor.moveToNext()){
            Anuncio anuncio=new Anuncio();

            anuncio.setNombreCategoria(cursor.getString(0));
            anuncio.setCategoria(Integer.parseInt(cursor.getString(1)));


            anuncios.add(anuncio);
        }
        db.close();
        Anuncio[]anuncios1= new Anuncio[anuncios.size()];
        return anuncios.toArray(anuncios1);
    }

}
