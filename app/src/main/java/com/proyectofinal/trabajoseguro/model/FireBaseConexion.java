package com.proyectofinal.trabajoseguro.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataEmpresa;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.model.entity.Empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FireBaseConexion {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    Context context;

    public FireBaseConexion(Context context) {
        this.context = context;
    }

    public void initFirebase() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void AddAnuncio(Anuncio anuncio) {
        databaseReference.child("Anuncio").child(UUID.randomUUID().toString()).setValue(anuncio);
    }

    public void AddEmpresa(Empresa empresa) {
        databaseReference.child("Empresa").child(UUID.randomUUID().toString()).setValue(empresa);
    }

    public void CleanAnuncios() {
        ConexionSQLite conn = new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("delete from " + "Anuncios");
    }
    public void CleanEmpresas() {
        ConexionSQLite conn = new ConexionSQLite(context, "bd_trabajaseguro", null, 1, null);
        SQLiteDatabase db = conn.getWritableDatabase();
        db.execSQL("delete from " + "Empresa");
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

    public void UpdateAnunciosTable() {
        if(isConnected()){
            Toast.makeText(context.getApplicationContext(), "Sincronizando datos", Toast.LENGTH_SHORT).show();
            //anuncios.clear();
            CleanAnuncios();
            databaseReference.child("Anuncio").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot objSnapshot: snapshot.getChildren()){
                        Anuncio anuncio = objSnapshot.getValue(Anuncio.class);
                        guardarAnuncio(anuncio);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("Error update tabla", "loadPost:onCancelled", error.toException());
                }
            });
            Toast.makeText(context.getApplicationContext(), "Sincronizacion exitosa", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context.getApplicationContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }

    public void UpdateEmpresasTable() {
        if(isConnected()){
            Toast.makeText(context.getApplicationContext(), "Sincronizando datos", Toast.LENGTH_SHORT).show();
            CleanEmpresas();
            databaseReference.child("Empresa").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot objSnapshot: snapshot.getChildren()){
                        Empresa empresa = objSnapshot.getValue(Empresa.class);
                        DataEmpresa dataEmpresa = new DataEmpresa(context);
                        dataEmpresa.registrarEmpresaSqlite(empresa);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Log.w("Error update tabla", "loadPost:onCancelled", error.toException());
                }
            });
            Toast.makeText(context.getApplicationContext(), "Sincronizacion exitosa", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context.getApplicationContext(), "Sin conexion", Toast.LENGTH_SHORT).show();
        }
    }


}
