package com.proyectofinal.trabajoseguro.ui.graficoBarrasTrabajador;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.customVista.GraficoBarras;
import com.proyectofinal.trabajoseguro.R;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;

public class GraficoBarrasTrabajadorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_grafico_barras, container, false);
        DataAnuncio dataAnuncio = new DataAnuncio(getContext());
        Anuncio [] anuncios = dataAnuncio.listaAnuncioxCategoria();
        //System.out.println(anuncios[0].getNombreCategoria()+" PORTE");
        //System.out.println(anuncios[0].getCategoria()+" PORTE");
        //System.out.println("TAMAÑO PORTE"+ anuncios.length);
        //int [] cantidad ={5,60,80,10,4,3,8,20,86,91};
        //int [] cantidad ={70};
        //String[]tags={"perro","gato","perico","elefante","liebre","pericote","raton","xx","yy","nn"};
        //String []tags={"perro"};
        int [] cantidad= new int[anuncios.length];
        String [] tags = new String[anuncios.length];

        for (int i=0;i<anuncios.length;i++){
            tags[i]= anuncios[i].getNombreCategoria();
            cantidad[i]=anuncios[i].getCategoria();
        }
        int color= ContextCompat.getColor(getContext(), R.color.rojo);
        return  new GraficoBarras(container.getContext(),cantidad,tags,color);
    }
}