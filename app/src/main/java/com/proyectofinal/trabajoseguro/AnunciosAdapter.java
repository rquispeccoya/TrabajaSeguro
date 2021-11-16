package com.proyectofinal.trabajoseguro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.proyectofinal.trabajoseguro.model.Anuncio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.ViewHolder> {

    private ArrayList<Anuncio> mData;
    private ArrayList<Anuncio> mDataCopia;
    //private LayoutInflater mInflater;
    private Context context;

    public AnunciosAdapter(ArrayList<Anuncio> itemList){
        //this.mInflater=LayoutInflater.from(context);

        this.mData=itemList;
        mDataCopia = new ArrayList<Anuncio>();
        mDataCopia.addAll(mData);
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.disenio_anuncio,null,false);
        return new AnunciosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnunciosAdapter.ViewHolder holder, int position) {
            //holder.bindData(mData.get(position));
            holder.titulo.setText(mData.get(position).getTitulo());
            holder.descripcion.setText(mData.get(position).getDescripcion());

            holder.categoria.setText(String.valueOf(mData.get(position).getCategoria()));
    }

    public void filtrado(final String txtBuscar) {
        int longitud = txtBuscar.length();
        if (longitud == 0) {
            mData.clear();
            mData.addAll(mDataCopia);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Anuncio> collecion = mData.stream()
                        .filter(i -> i.getDescripcion().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                mData.clear();
                mData.addAll(collecion);
            } else {
                for (Anuncio c : mDataCopia) {
                    if (c.getDescripcion().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        mData.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo,descripcion,categoria;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo=itemView.findViewById(R.id.textViewTitulo);
            descripcion=itemView.findViewById(R.id.textViewDescripcion);
            categoria=itemView.findViewById(R.id.textViewCategoria);
        }

    }
}
