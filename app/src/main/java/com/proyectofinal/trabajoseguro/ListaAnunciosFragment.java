package com.proyectofinal.trabajoseguro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.proyectofinal.trabajoseguro.model.Anuncio;
import com.proyectofinal.trabajoseguro.model.DataAnuncio;

import java.util.ArrayList;


public class ListaAnunciosFragment extends Fragment implements SearchView.OnQueryTextListener{
    ArrayList<Anuncio> elements;
    RecyclerView recyclerView;
    AnunciosAdapter anunciosAdapter;
    SearchView txtBuscar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista =inflater.inflate(R.layout.fragment_lista_anuncios,container,false);

        init(vista);


        return vista;
    }

    public void init(View root){
       // elements=new ArrayList<>();
        DataAnuncio dataAnuncio = new DataAnuncio(getContext());
        elements = dataAnuncio.listaAnuncios();
        recyclerView=root.findViewById(R.id.ReciclerViewListaAnuncios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        /*
        elements.add(new Anuncio("ga","ne"));
        elements.add(new Anuncio("ba","ne"));
        elements.add(new Anuncio("sa","ne"));
        elements.add(new Anuncio("qa","ne"));*/
        anunciosAdapter=new AnunciosAdapter(elements);
        recyclerView.setAdapter(anunciosAdapter);
        txtBuscar=root.findViewById(R.id.txtBuscar);
        txtBuscar.setOnQueryTextListener(this);
        //recyclerView.setHasFixedSize(true);


    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        anunciosAdapter.filtrado(s);
        return false;
    }
}