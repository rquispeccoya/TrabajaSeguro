package com.proyectofinal.trabajoseguro.ui.crearAnuncio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.proyectofinal.trabajoseguro.R;

public class MapsFragment extends Fragment {
    private LocationManager ubicacion;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions = new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude + "" + latLng.longitude);
                        googleMap.clear();
                        //Animar la camara
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng, 11
                        ));
                        googleMap.addMarker(markerOptions);
                        Bundle result = new Bundle();
                        result.putString("la", String.valueOf(latLng.latitude));
                        result.putString("lo", String.valueOf(latLng.longitude));
                        getParentFragmentManager().setFragmentResult("requestKey", result);
                    }


                });
            }
        });

        return view;
    }

    private void localizacion() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (ubicacion != null)
            Log.i("Latitud", String.valueOf(loc.getLatitude()));
        Log.i("Longitud", String.valueOf(loc.getLongitude()));
    }

    private boolean estadoGPS() {
        ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (!ubicacion.isProviderEnabled((LocationManager.GPS_PROVIDER))) {
            Log.d("GPS", "No activado");
        } else {
            Log.d("GPS", "Activado");
        }
        return true;

    }
}