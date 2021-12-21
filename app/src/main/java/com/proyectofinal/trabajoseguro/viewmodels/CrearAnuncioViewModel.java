package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.proyectofinal.trabajoseguro.BR;
import com.proyectofinal.trabajoseguro.model.FireBaseConexion;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataCategoria;

import java.util.UUID;

public class CrearAnuncioViewModel extends BaseObservable {
    private Anuncio anuncio;
    private Context context;
    private String idEmpresa;
    //FireBaseConexion fireBaseConexion;
    @Bindable
    private int selectedItemPosition;

    public CrearAnuncioViewModel(Context context, String id) {
        this.anuncio = new Anuncio();
        this.context = context;
        this.idEmpresa = id;
       // fireBaseConexion = new FireBaseConexion(context);
        //fireBaseConexion.initFirebase();
    }

    public void setCrearAnuncioTitulo(String titulo) {
        anuncio.setTitulo(titulo);
        notifyPropertyChanged(BR.crearAnuncioTitulo);
    }

    public void setCrearAnuncioDescripcion(String descripcion) {
        anuncio.setDescripcion(descripcion);
        notifyPropertyChanged(BR.crearAnuncioDescripcion);
    }

    public void setCrearAnuncioCategoria(int categoria) {
        anuncio.setCategoria(categoria);
    }

    public void setCrearAnuncioLatitud(String latitud) {
        anuncio.setLatitud(Double.parseDouble(latitud));
        notifyPropertyChanged(BR.crearAnuncioLatitud);
    }

    public void setCrearAnuncioLongitud(String longitud) {
        anuncio.setLongitud(Double.parseDouble(longitud));

    }

    public void setCrearAnuncioIdUsuario(String idUsuario) {
        anuncio.setIdUsuario(idUsuario);
    }

    @Bindable
    public String getCrearAnuncioTitulo() {
        return anuncio.getTitulo();
    }

    @Bindable
    public String getCrearAnuncioDescripcion() {
        return anuncio.getDescripcion();
    }

    @Bindable
    public int getCrearAnuncioCategoria() {
        return anuncio.getCategoria();
    }

    @Bindable
    public String getCrearAnuncioLatitud() {
        return String.valueOf(anuncio.getLatitud());
    }

    @Bindable
    public String getCrearAnuncioLongitud() {
        return String.valueOf(anuncio.getLongitud());
    }

    @Bindable
    public String getCrearAnuncioIdUsuario() {
        return anuncio.getIdUsuario();
    }

    public void onClickRegistrarAnuncio() {


        if (getCrearAnuncioTitulo() == null || getCrearAnuncioDescripcion() == null||getCrearAnuncioTitulo() == "" || getCrearAnuncioDescripcion() == "") {
            Toast.makeText(context.getApplicationContext(), "!! Rellene los campos !!", Toast.LENGTH_SHORT).show();

        } else if (Double.parseDouble(getCrearAnuncioLatitud()) == 0.0) {
            Toast.makeText(context.getApplicationContext(), "!! Seleccione ubicacion del negocio !!", Toast.LENGTH_SHORT).show();

        } else {
            DataAnuncio dataAnuncio = new DataAnuncio(context.getApplicationContext());
            anuncio.setIdUsuario(idEmpresa);
            anuncio.setCategoria(getSelectedItemPosition() + 1);
            dataAnuncio.guardarAnuncio(anuncio);

            // test
            FirebaseDatabase firebaseDatabase;
            DatabaseReference databaseReference;
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference();
            databaseReference.child("Anuncio").child(UUID.randomUUID().toString()).setValue(anuncio);

            //fireBaseConexion.AddAnuncio(anuncio);
            //fireBaseConexion.UpdateAnunciosTable();
            Toast.makeText(context.getApplicationContext(), "!! Anuncio Registrado !!", Toast.LENGTH_SHORT).show();
            setCrearAnuncioTitulo("");
            setCrearAnuncioDescripcion("");
            setCrearAnuncioLatitud(String.valueOf(0.0));
        }
    }

    @Bindable
    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }

    public void setSelectedItemPosition(int selectedItemPosition) {
        this.selectedItemPosition = selectedItemPosition;
        notifyPropertyChanged(BR.selectedItemPosition);
    }

    public String[] categorias() {
        DataCategoria dataCategoria = new DataCategoria(context.getApplicationContext());
        return dataCategoria.listaCategorias();
    }
}
