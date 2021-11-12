package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.BR;
import com.proyectofinal.trabajoseguro.model.Anuncio;

public class CrearAnuncioViewModel extends BaseObservable {
    private Anuncio anuncio;
    private Context context;
    private String men="hola";

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

    public void setCrearAnuncioCategoria(String categoria) {
        anuncio.setCategoria(categoria);
    }

    public void setCrearAnuncioUbicacion(String ubicacion) {
        anuncio.setUbicacion(ubicacion);
    }

    public void setCrearAnuncioEstado(int estado) {
        anuncio.setEstado(estado);
    }


    public String getCrearAnuncioTitulo() {
        return anuncio.getTitulo();
    }

    public void onClickRegistrarAnuncio() {
        setMen(anuncio.getTitulo());
        System.out.println(anuncio.getTitulo() + "  WAAAAAAAAAAAAAA");
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
        String[] categoria = new String[4];
        categoria[0] = "mecanico";
        categoria[1] = "soldador";
        categoria[2] = "albañil";
        categoria[3] = "profesor";

        return categoria;
    }


}
