package com.proyectofinal.trabajoseguro.ui.home;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.proyectofinal.trabajoseguro.R;
import com.proyectofinal.trabajoseguro.databinding.FragmentHomeBinding;
import com.proyectofinal.trabajoseguro.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment {

    //ArrayList<Anuncio> elements;

    private FragmentHomeBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

       /* binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;*/
        Activity a = getActivity();
        TextView r =a.findViewById(R.id.idEmpresa);

         binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_home, container, false);
         //binding.setHome(new HomeViewModel((String) r.getText(),getContext()));
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        Activity a = getActivity();
        TextView r =a.findViewById(R.id.idEmpresa);
        binding.setHome(new HomeViewModel((String) r.getText(),getContext()));

        //DataEmpresa dataEmpresa = new DataEmpresa(getContext());
        //Empresa empresa=dataEmpresa.buscarEmpresa(Integer.parseInt((String) r.getText()));
        //binding.textViewEmpresa.setText(empresa.getNombre());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}