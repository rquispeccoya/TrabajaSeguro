package com.proyectofinal.trabajoseguro.ui.mapaTrabajador;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectofinal.trabajoseguro.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnuncioVistaMapa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnuncioVistaMapa extends Fragment {
    BottomNavigationView v;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @Override
    public void onResume() {
        super.onResume();
        v=(BottomNavigationView)getActivity().findViewById(R.id.bottomNavigationOption);
        v.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onPause() {
        super.onPause();
        v.setVisibility(View.VISIBLE);
    }

    public AnuncioVistaMapa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnuncioVistaMapa.
     */
    // TODO: Rename and change types and number of parameters
    public static AnuncioVistaMapa newInstance(String param1, String param2) {
        AnuncioVistaMapa fragment = new AnuncioVistaMapa();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        Bundle datosRecuperados = getArguments();
        if (datosRecuperados == null) {
            // No hay datos, manejar excepción
            return;
        }

        // Y ahora puedes recuperar usando get en lugar de put
        // long id = datosRecuperados.getLong("id");
        // int edad = datosRecuperados.getInt("edad");
        String nombre = datosRecuperados.getString("latLong");
        // Imprimimos, pero en tu caso haz lo necesario
        // Log.d("GastosFragmentEditar", "El ID: " + id);
        //Log.d("GastosFragmentEditar", "La edad: " + edad);
        Log.d("GastosFragmentEditar", "El nombre: " + nombre);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anuncio_vista_mapa, container, false);
    }
}