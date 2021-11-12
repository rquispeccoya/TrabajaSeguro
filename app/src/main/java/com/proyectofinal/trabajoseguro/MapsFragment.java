package com.proyectofinal.trabajoseguro;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
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

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerDragListener,OnMapReadyCallback {
    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION = 1;
    private GoogleMap mMap;
    private Circle circle;


    private void getMap() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled (true);

        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {

                LocationManager mLocationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

                // Get the best provider between gps, network and passive
                Criteria criteria = new Criteria();
                String mProviderName = mLocationManager.getBestProvider(criteria, true);
                @SuppressLint("MissingPermission") Location location = mLocationManager.getLastKnownLocation(mProviderName);
                LatLng ubicacion= new LatLng(location.getLatitude(),location.getLongitude());
                mMap.addMarker(new MarkerOptions().position(ubicacion).title("MI UBICACION")).setDraggable(true);

                //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,10));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacion,15));
                mMap.getUiSettings().setMyLocationButtonEnabled (false);
               // textView.setText(String.valueOf(ubicacion.latitude)+" "+String.valueOf(ubicacion.longitude));
                /*circle=mMap.addCircle(new CircleOptions()
                        .center(ubicacion)
                        .radius(100)
                        .strokeColor(R.color.rojo)
                        .fillColor(R.color.rojo));*/
                return true;
            }
        });

        mMap.setOnMarkerDragListener(this);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_ACCESS_FINE_LOCATION: {
                // Si el usuario acepta los permisos
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getMap();
                } else {
                    // Si el usuario no brinda los permisos
                    Toast.makeText(getParentFragment().getContext(), "Permiso denegado", Toast.LENGTH_SHORT).show();
                    System.out.println("HOLA");
                }
                return;
            }
        }
    }

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        Bundle result = new Bundle();
        result.putString("la", String.valueOf(marker.getPosition().latitude));
        result.putString("lo",String.valueOf(marker.getPosition().longitude));

        // The child fragment needs to still set the result on its parent fragment manager
        getParentFragmentManager().setFragmentResult("requestKey", result);
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        // A partir de las versión 6 en adelante cambia las políticas sobre los permisos
        // Hay que verificar los permisos e informar al usuario si va a brindar los accesos correspondientes

        // Validamos la versión
        if( Build.VERSION.SDK_INT >= 23) {
            // Validamos si ACCESS_FINE_LOCATION tiene permisos otorgados por el usuario
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // Informamos al usuario sobre que permisos se le van a solicitar.
                ActivityCompat.requestPermissions( getActivity(), new String[] {  Manifest.permission.ACCESS_FINE_LOCATION  }, MY_PERMISSION_ACCESS_FINE_LOCATION);
                return;
            } else {
                // Esta parte se ejecuta cuando los permisos son otorgados por el usuario
                getMap();
            }
        } else {
            // Esta bloque se ejecuta cuando una versión de android es inferior a la 6 o API 23, obtiene la información sobre los permisos
            // del AndroidManifest.xml
            getMap();
        }
    }
}