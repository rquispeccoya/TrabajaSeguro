package com.proyectofinal.trabajoseguro.model;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSQLite extends  SQLiteOpenHelper{
    private static final int DATABASE_VERSION =1 ;
    private static final String DATABASE_NOMBRE = "TrabajaSeguro.db";
    //public ConexionSQLite(@Nullable Context context) {

    public ConexionSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        //super(context,DATABASE_NOMBRE,null,DATABASE_VERSION);
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
                "  idUsuario TEXT NOT NULL,\n" +
                "  idCat INTEGER,\n" +
                "  CONSTRAINT Relationship2 FOREIGN KEY (idCat) REFERENCES Categoria (idCat)\n" +
                ")"
        );

        db.execSQL("CREATE INDEX IX_Relationship1 ON Anuncios (idCat)");
        db.execSQL("CREATE TABLE Categoria\n" +
                "(\n" +
                "  idCat INTEGER NOT NULL\n" +
                "        CONSTRAINT PK_Categoria PRIMARY KEY AUTOINCREMENT,\n" +
                "  nombre TEXT NOT NULL\n" +
                ")"
        );

        db.execSQL("CREATE TABLE Empresa\n" +
                "(\n" +
                "  idempresa INTEGER NOT NULL\n" +
                "        CONSTRAINT PK_Empresa PRIMARY KEY AUTOINCREMENT,\n" +
                "  nombre TEXT NOT NULL,\n" +
                "  encargado TEXT NOT NULL,\n" +
                "  correo TEXT NOT NULL,\n" +
                "  telefono TEXT NOT NULL,\n" +
                "  ruc TEXT NOT NULL,\n" +
                "  usuario TEXT NOT NULL,\n" +
                "  contrasenia TEXT NOT NULL,\n" +
                "  descripcion TEXT NOT NULL,\n" +
                "  direccion TEXT NOT NULL,\n" +
                "  departamento TEXT NOT NULL\n" +
                ")"
        );

        db.execSQL("INSERT INTO Categoria (nombre) VALUES('Carpintero')");
        db.execSQL("INSERT INTO Categoria (nombre) VALUES('Soldador')");
        db.execSQL("INSERT INTO Categoria (nombre) VALUES('Mecanico')");
        db.execSQL("INSERT INTO Categoria (nombre) VALUES('Granjero')");
        db.execSQL("INSERT INTO Categoria (nombre) VALUES('Profesor')");
        db.execSQL("INSERT INTO Categoria (nombre) VALUES('Pintor')");
        db.execSQL("INSERT INTO Empresa (nombre, encargado, correo," +
                " telefono, ruc, usuario, contrasenia, descripcion," +
                " direccion, departamento) " +
                "VALUES('Elite', 'Jordan', 'jlaqui@unsa.edu.pe', " +
                "'921242901', '123', 'a', 'a', 'Papeleria', " +
                "'Av 123', 'Arequipa')");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Anuncios");
        db.execSQL("DROP TABLE IF EXISTS Empresa");
        //db.execSQL("DROP TABLE IF EXISTS fichaInscripcion");
        onCreate(db);

    }
}