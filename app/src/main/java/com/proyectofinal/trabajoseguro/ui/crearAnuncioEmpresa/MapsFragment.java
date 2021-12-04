package com.proyectofinal.trabajoseguro.ui.crearAnuncioEmpresa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.proyectofinal.trabajoseguro.R;

public class MapsFragment extends Fragment implements GoogleMap.OnMarkerDragListener {
    private LocationManager ubicacion;
    private GoogleMap mMap;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap=googleMap;
            getMap();
        }
    };

    private void getMap(){
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull LatLng latLng) {
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title(latLng.latitude + "" + latLng.longitude);
                mMap.clear();
                //Animar la camara
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        latLng, 10
                ));
                mMap.addMarker(markerOptions).setDraggable(true);
                Bundle result = new Bundle();
                result.putString("la", String.valueOf(latLng.latitude));
                result.putString("lo", String.valueOf(latLng.longitude));
                getParentFragmentManager().setFragmentResult("requestKey", result);
            }
        });
        mMap.setOnMarkerDragListener(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maps, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
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

    @Override
    public void onMarkerDrag(@NonNull Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(@NonNull Marker marker) {
        Bundle result = new Bundle();
        result.putString("la", String.valueOf(marker.getPosition().latitude));
        result.putString("lo",String.valueOf(marker.getPosition().longitude));

       getParentFragmentManager().setFragmentResult("requestKey", result);
    }

    @Override
    public void onMarkerDragStart(@NonNull Marker marker) {

    }
}