package com.proyectofinal.trabajoseguro.ui.mapaTrabajador;


import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
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
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class MapaTrabajadorFragment extends Fragment  implements LocationListener {
    LocationManager locationManager;
    String loc=" ";
    Double lat=0.0,lon=0.0;
    SupportMapFragment supportMapFragment;
    AlertDialog alert = null;
    private Circle circle;
    private SeekBar seekBar;
    private GoogleMap googleMap;
    private LatLng latLng;
    List<Marker> AllMarkers = new ArrayList<Marker>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Permisos();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mapa_trabajador, container, false);
        seekBar=view.findViewById(R.id.slader);
        //Inicializamos map fragment
        supportMapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                removeAllMarkers();

                int distancia = i*1000;
                //actualiza el radio
                circle.setRadius(distancia);
                //creamos el location de la posicion del usuario
                Location a = new Location("MI posicion");
                a.setLatitude(latLng.latitude);
                a.setLongitude(latLng.longitude);

                DataAnuncio dataAnuncio= new DataAnuncio(getContext());
                //recuperamos todos los anuncios de la bd
                ArrayList<Anuncio> anuncios= dataAnuncio.listaAnunciosGenerales();
                for (int j=0;j<anuncios.size();j++){
                    LatLng latLng1=new LatLng(anuncios.get(j).getLatitud(),anuncios.get(j).getLongitud());

                    //creamos el location de los diferentes anuncios
                    Location ot = new Location("OTROS");
                    ot.setLatitude(latLng1.latitude);
                    ot.setLongitude(latLng1.longitude);

                    //verificamos las distancias
                    if(a.distanceTo(ot)<=distancia){
                         MarkerOptions markerOptions2=new MarkerOptions();
                        markerOptions2.title(latLng1.latitude+":"+latLng1.longitude);
                        markerOptions2.position(latLng1);
                        Marker mLocationMarker = googleMap.addMarker(markerOptions2);
                        //almacenamos los marcadores cercanos
                        AllMarkers.add(mLocationMarker);

                    }

                }

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    //metodo para eliminar los marcadores cercanos a la posicion del usuario
    private void removeAllMarkers() {
        for (Marker mLocationMarker: AllMarkers) {
            mLocationMarker.remove();
        }
        AllMarkers.clear();

    }
    protected void Permisos(){
        locationManager=(LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        //Verificando permisos
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        }
        if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
            AlertNoGps();
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, this);



    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();
        System.out.println("COORDENADAS "+lat+"  "+lon);
        supportMapFragment.getMapAsync((new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap gMap) {
                googleMap=gMap;
                 latLng=new LatLng(lat,lon);
                //Cuando hagas click ene el mapa
                //Inicializamos el marcador
                MarkerOptions markerOptions=new MarkerOptions();
                //Colocamos la posicion del marcador
                markerOptions.position(latLng);
                //Colocamos el titulo al marcador
                markerOptions.title(latLng.latitude+":"+latLng.longitude);
                //Eliminamos todos los marcadores anteriores
                googleMap.clear();
                //Animacion  de la camara
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                // Agregadmos el marcador al mapa
                googleMap.addMarker(markerOptions);


                circle=googleMap.addCircle(new CircleOptions()
                        .center(latLng)
                        .radius(100)
                        .strokeColor(R.color.rojo)
                        .fillColor(R.color.rojo));
            }
        }));
    }


    @Override
    public void onLocationChanged(@NonNull List<Location> locations) {

    }

    @Override
    public void onFlushComplete(int requestCode) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }
    //Verificar si el GPS esta activado
    private void AlertNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("El sistema GPS esta desactivado, ¿Desea activarlo?")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        alert = builder.create();
        alert.show();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(alert != null)
        {
            alert.dismiss ();
        }
    }
}