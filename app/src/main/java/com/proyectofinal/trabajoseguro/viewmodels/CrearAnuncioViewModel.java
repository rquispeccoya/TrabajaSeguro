package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.BR;
import com.proyectofinal.trabajoseguro.model.Anuncio;
import com.proyectofinal.trabajoseguro.model.Categoria;
import com.proyectofinal.trabajoseguro.model.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.DataCategoria;

public class CrearAnuncioViewModel extends BaseObservable {
    private Anuncio anuncio;
    private Context context;
    private String men = "hola";

    public CrearAnuncioViewModel(Context context) {
        this.anuncio = new Anuncio();
        this.context = context;
    }

    public void setCrearAnuncioTitulo(String titulo) {
        anuncio.setTitulo(titulo);
    }

    public void setCrearAnuncioDescripcion(String descripcion) {
        anuncio.setDescripcion(descripcion);
    }

    public void setCrearAnuncioCategoria(int categoria) {
        anuncio.setCategoria(categoria);
    }

    public void setCrearAnuncioLatitud(String latitud) {
        anuncio.setLatitud(Double.parseDouble(latitud));
    }

    public void setCrearAnuncioLongitud(String longitud) {

        anuncio.setLongitud(Double.parseDouble(longitud));
    }

    public void setCrearAnuncioIdUsuario(int idUsuario) {
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
    public int getCrearAnuncioIdUsuario() {
        return  anuncio.getIdUsuario();
    }

    public void onClickRegistrarAnuncio() {
        setMen(anuncio.getTitulo());
        DataAnuncio dataAnuncio = new DataAnuncio(context.getApplicationContext());
        anuncio.setIdUsuario(1);
        anuncio.setCategoria(getSelectedItemPosition()+1);

        dataAnuncio.guardarAnuncio(anuncio);
        System.out.println(anuncio.toString());
        setCrearAnuncioTitulo("");
        setCrearAnuncioDescripcion("");
    }

    @Bindable
    public String getMen() {
        return men;
    }

    public void setMen(String men) {
        this.men = men;
        notifyPropertyChanged(BR.men);
    }


    @Bindable
    private int selectedItemPosition;

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
