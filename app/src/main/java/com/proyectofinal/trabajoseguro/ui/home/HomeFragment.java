package com.proyectofinal.trabajoseguro.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectofinal.trabajoseguro.AnunciosAdapter;
import com.proyectofinal.trabajoseguro.R;
import com.proyectofinal.trabajoseguro.databinding.FragmentHomeBinding;
import com.proyectofinal.trabajoseguro.model.Anuncio;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ArrayList<Anuncio> elements;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //binding = FragmentHomeBinding.inflate(inflater, container, false);
        View vista =inflater.inflate(R.layout.fragment_home,container,false);

        init(vista);
        return vista;
    }
    public void init(View root){
        elements=new ArrayList<>();
        RecyclerView recyclerView=root.findViewById(R.id.ReciclerViewAnuncios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        elements.add(new Anuncio("ga","ne"));
        elements.add(new Anuncio("ba","ne"));
        elements.add(new Anuncio("sa","ne"));
        elements.add(new Anuncio("qa","ne"));
        AnunciosAdapter anunciosAdapter=new AnunciosAdapter(elements);
        recyclerView.setAdapter(anunciosAdapter);
        //recyclerView.setHasFixedSize(true);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}