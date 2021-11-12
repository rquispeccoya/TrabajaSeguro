package com.proyectofinal.trabajoseguro.ui.crearAnuncio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.proyectofinal.trabajoseguro.R;
import com.proyectofinal.trabajoseguro.databinding.FragmentCrearAnuncioBinding;
import com.proyectofinal.trabajoseguro.databinding.FragmentHomeBinding;
import com.proyectofinal.trabajoseguro.viewmodels.CrearAnuncioViewModel;


public class CrearAnuncioFragment extends Fragment {


    private FragmentCrearAnuncioBinding binding;
    Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCrearAnuncioBinding.inflate(inflater, container, false);
        //View view = inflater.inflate(R.layout., container, false);
        View root = binding.getRoot();

        binding.setCrearAnuncio(new CrearAnuncioViewModel(getContext()));
        binding.getCrearAnuncio().setSelectedItemPosition(1);

        Toast.makeText(getActivity(),String.valueOf(binding.getCrearAnuncio().getSelectedItemPosition()),Toast.LENGTH_LONG).show();
        b=root.findViewById(R.id.buttonCrearAnuncio);
        EditText e = root.findViewById(R.id.editTextUbicacion);
        getChildFragmentManager().setFragmentResultListener("requestKey", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String a = result.getString("la");
                String b = result.getString("lo");
                e.setText(a+"   "+b);
                //Toast.makeText(getParentFragment().getContext(), a+" "+b,Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }




}