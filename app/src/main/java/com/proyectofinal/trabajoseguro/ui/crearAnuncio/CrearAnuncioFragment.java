package com.proyectofinal.trabajoseguro.ui.crearAnuncio;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyectofinal.trabajoseguro.databinding.FragmentCrearAnuncioBinding;
import com.proyectofinal.trabajoseguro.databinding.FragmentHomeBinding;


public class CrearAnuncioFragment extends Fragment {


    private FragmentCrearAnuncioBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCrearAnuncioBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }
}