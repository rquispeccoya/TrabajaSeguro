package com.proyectofinal.trabajoseguro.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends  SQLiteOpenHelper{
    private static final int DATABASE_VERSION =1 ;
    private static final String DATABASE_NOMBRE = "TrabajaSeguro";
    public ConexionSQLite(@Nullable Context context) {

    //public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        //super(context, name, factory, version, errorHandler);
        super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Anuncios\n" +
                "(\n" +
                "  id INTEGER NOT NULL\n" +
                "        CONSTRAINT PK_Anuncios PRIMARY KEY AUTOINCREMENT,\n" +
                "  titulo TEXT NOT NULL,\n" +
                "  descripcion TEXT NOT NULL,\n" +
                "  latitud REAL NOT NULL,\n" +
                "  longitud REAL NOT NULL,\n" +
                "  estado INTEGER NOT NULL DEFAULT 1,\n" +
                "  idUsuario INTEGER NOT NULL,\n" +
                "  id NUMERIC,\n" +
                "  CONSTRAINT Relationship1 FOREIGN KEY (id) REFERENCES Categoria (id)\n" +
                ");");
        db.execSQL("CREATE TABLE Categoria\n" +
                "(\n" +
                "  id NUMERIC NOT NULL\n" +
                "        CONSTRAINT PK_Categoria PRIMARY KEY AUTOINCREMENT,\n" +
                "  nombre TEXT NOT NULL\n" +
                ");");
        db.execSQL("CREATE INDEX IX_Relationship1 ON Anuncios (id);");
        //  db.execSQL("CREATE INDEX IX_Relationship3 ON fichaInscripcion (DepCod);");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Anuncios");
        db.execSQL("DROP TABLE IF EXISTS Categoria");
        //db.execSQL("DROP TABLE IF EXISTS fichaInscripcion");
        onCreate(db);

    }
}