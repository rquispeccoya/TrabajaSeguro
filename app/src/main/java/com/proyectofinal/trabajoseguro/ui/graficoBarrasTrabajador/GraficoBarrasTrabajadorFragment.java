package com.proyectofinal.trabajoseguro.ui.graficoBarrasTrabajador;

import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofinal.trabajoseguro.GraficoBarras;
import com.proyectofinal.trabajoseguro.R;

public class GraficoBarrasTrabajadorFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_grafico_barras, container, false);
        int [] cantidad ={5,60,80,10,4,3,8,20,86,91};
        //int [] cantidad ={70};
        String[]tags={"perro","gato","perico","elefante","liebre","pericote","raton","xx","yy","nn"};
        //String []tags={"perro"};
        int color= ContextCompat.getColor(getContext(), R.color.rojo);
        return  new GraficoBarras(container.getContext(),cantidad,tags,color);
    }
}