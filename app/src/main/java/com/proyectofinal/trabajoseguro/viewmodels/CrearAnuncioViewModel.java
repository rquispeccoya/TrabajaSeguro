package com.proyectofinal.trabajoseguro.viewmodels;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.proyectofinal.trabajoseguro.BR;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataCategoria;

public class CrearAnuncioViewModel extends BaseObservable {
    private Anuncio anuncio;
    private Context context;
    private int idEmpresa;
    private String men = "hola";
    @Bindable
    private int selectedItemPosition;
    public CrearAnuncioViewModel(Context context,String id) {
        this.anuncio = new Anuncio();
        this.context = context;
        this.idEmpresa=Integer.parseInt(id);
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
        anuncio.setIdUsuario(idEmpresa);
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
