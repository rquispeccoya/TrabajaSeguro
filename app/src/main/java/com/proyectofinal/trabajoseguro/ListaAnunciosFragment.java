package com.proyectofinal.trabajoseguro;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.proyectofinal.trabajoseguro.databinding.FragmentListaAnunciosBinding;
import com.proyectofinal.trabajoseguro.model.Anuncio;
import com.proyectofinal.trabajoseguro.model.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.iComunicaFragments;

import java.util.ArrayList;


public class ListaAnunciosFragment extends Fragment implements SearchView.OnQueryTextListener{
    ArrayList<Anuncio> elements;
    RecyclerView recyclerView;
    AnunciosAdapter anunciosAdapter;
    SearchView txtBuscar;
    FragmentListaAnunciosBinding binding;
    iComunicaFragments interfaceComunicaFragments;
    Activity actividad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListaAnunciosBinding.inflate(inflater,container,false);
        View vista =binding.getRoot();

        init();

        return vista;
    }

    public void init(){
       // elements=new ArrayList<>();
        DataAnuncio dataAnuncio = new DataAnuncio(getContext());
        elements = dataAnuncio.listaAnuncios();
        //recyclerView=root.findViewById(R.id.ReciclerViewListaAnuncios);
        recyclerView=binding.ReciclerViewListaAnuncios;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        elements.add(new Anuncio("Cocinero Con Experiencia","El Perla Arequipa,Arequipa"));
        elements.add(new Anuncio("Necesitamos cocinero a tiempo Completo","La Parriche Arequipa,Arequipa"));
        elements.add(new Anuncio("Cocinera Cama adentro","Corial Incorporated Arequipa,Arequipa"));
        elements.add(new Anuncio("Cajero","Legatitas Arequipa,Arequipa"));
        anunciosAdapter=new AnunciosAdapter(elements);
        recyclerView.setAdapter(anunciosAdapter);
        txtBuscar = binding.txtBuscar;
        //txtBuscar=root.findViewById(R.id.txtBuscar);
        txtBuscar.setOnQueryTextListener(this);
        //recyclerView.setHasFixedSize(true);
        anunciosAdapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String nombre = elements.get(recyclerView.getChildAdapterPosition(view)).getTitulo();
                //txtnombre.setText(nombre);
                Toast.makeText(getContext(), "Seleccionó: "+elements.get(recyclerView.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                interfaceComunicaFragments.enviarAnuncio(elements.get(recyclerView.getChildAdapterPosition(view)));
            }
        });



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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            interfaceComunicaFragments= (iComunicaFragments) this.actividad;
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }

       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }
}