package com.proyectofinal.trabajoseguro.ui.verAnuncios;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.proyectofinal.trabajoseguro.AnunciosAdapter;
import com.proyectofinal.trabajoseguro.R;
import com.proyectofinal.trabajoseguro.databinding.FragmentListaAnunciosBinding;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;


import java.util.ArrayList;


public class ListaAnunciosFragment extends Fragment implements SearchView.OnQueryTextListener {
    ArrayList<Anuncio> elements;
    RecyclerView recyclerView;
    AnunciosAdapter anunciosAdapter;
    SearchView txtBuscar;
    FragmentListaAnunciosBinding binding;
    TextView idText;
  //  Activity actividad;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentListaAnunciosBinding.inflate(inflater, container, false);
        View vista = binding.getRoot();
        Activity a = getActivity();
        idText = a.findViewById(R.id.idEmpresa);
        init();

        return vista;
    }

    public void init() {
        // elements=new ArrayList<>();
        DataAnuncio dataAnuncio = new DataAnuncio(getContext());

        elements = dataAnuncio.listaAnuncios((String) idText.getText());
        //recyclerView=root.findViewById(R.id.ReciclerViewListaAnuncios);
        recyclerView = binding.ReciclerViewListaAnuncios;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        anunciosAdapter = new AnunciosAdapter(elements);
        recyclerView.setAdapter(anunciosAdapter);
        txtBuscar = binding.txtBuscar;
        txtBuscar.setOnQueryTextListener(this);
        recyclerView.setHasFixedSize(true);
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




/*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //esto es necesario para establecer la comunicacion entre la lista y el detalle
        //si el contexto que le esta llegando es una instancia de un activity:
        if(context instanceof Activity){
            //voy a decirle a mi actividad que sea igual a dicho contesto. castin correspondiente:
            this.actividad= (Activity) context;
            ////que la interface icomunicafragments sea igual ese contexto de la actividad:
            //esto es necesario para establecer la comunicacion entre la lista y el detalle
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


      //recyclerView.setHasFixedSize(true);
        anunciosAdapter.setOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String nombre = elements.get(recyclerView.getChildAdapterPosition(view)).getTitulo();
                //txtnombre.setText(nombre);
                Toast.makeText(getContext(), "Seleccionó: " + elements.get(recyclerView.getChildAdapterPosition(view)).getTitulo(), Toast.LENGTH_SHORT).show();
                //enviar mediante la interface el objeto seleccionado al detalle
                //se envia el objeto completo
                interfaceComunicaFragments.enviarAnuncio(elements.get(recyclerView.getChildAdapterPosition(view)));
            }
        });


}*/