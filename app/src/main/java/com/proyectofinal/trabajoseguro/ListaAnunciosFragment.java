package com.proyectofinal.trabajoseguro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofinal.trabajoseguro.model.Anuncio;

import java.util.ArrayList;


public class ListaAnunciosFragment extends Fragment {
    ArrayList<Anuncio> elements;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_lista_anuncios,container,false);

        init(vista);
        return vista;
    }

    public void init(View root){
        elements=new ArrayList<>();
        recyclerView=root.findViewById(R.id.ReciclerViewListaAnuncios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        elements.add(new Anuncio("ga","ne"));
        elements.add(new Anuncio("ba","ne"));
        elements.add(new Anuncio("sa","ne"));
        elements.add(new Anuncio("qa","ne"));
        AnunciosAdapter anunciosAdapter=new AnunciosAdapter(elements);
        recyclerView.setAdapter(anunciosAdapter);
        //recyclerView.setHasFixedSize(true);


    }
}