package com.proyectofinal.trabajoseguro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofinal.trabajoseguro.databinding.FragmentAnuncioDetalleBinding;
import com.proyectofinal.trabajoseguro.model.Anuncio;


public class AnuncioDetalleFragment extends Fragment {
    FragmentAnuncioDetalleBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAnuncioDetalleBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        Bundle bundle = getArguments();

        Anuncio anuncio=null;
        if(bundle !=null) {
            anuncio=(Anuncio)bundle.getSerializable("objeto");
            binding.editTextDATitulo.setText(anuncio.getTitulo());
            binding.editTextDADescripcion.setText(anuncio.getDescripcion());
            binding.editTextDACategoria.setText(String.valueOf(anuncio.getCategoria()));
        }
        return view;
    }
}