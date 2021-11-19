package com.proyectofinal.trabajoseguro.ui.crearAnuncio;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
        //binding = DataBindingUtil.setContentView(getActivity(),R.layout.fragment_crear_anuncio);
        //View view = inflater.inflate(R.layout., container, false);

        Activity a = getActivity();
        TextView r =a.findViewById(R.id.idEmpresa);

        binding.setCrearAnuncio(new CrearAnuncioViewModel(getContext(), (String) r.getText()));
        View root = binding.getRoot();

        binding.getCrearAnuncio().setSelectedItemPosition(0);

        //Toast.makeText(getActivity(),String.valueOf(binding.getCrearAnuncio().getSelectedItemPosition()),Toast.LENGTH_LONG).show();
        //b=root.findViewById(R.id.buttonCrearAnuncio);
        //binding.editTextLatitud.getText();
        //EditText latitud = root.findViewById(R.id.editTextLatitud);
        //EditText longitud = root.findViewById(R.id.editTextLongitud);

        getChildFragmentManager().setFragmentResultListener("requestKey", getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String a = result.getString("la");
                String b = result.getString("lo");
                binding.editTextLatitud.setText(a);
                binding.editTextLongitud.setText(b);
                //latitud.setText(a);
                //longitud.setText(b);
                //Toast.makeText(getParentFragment().getContext(), a+" "+b,Toast.LENGTH_LONG).show();
            }
        });


        return root;
    }




}