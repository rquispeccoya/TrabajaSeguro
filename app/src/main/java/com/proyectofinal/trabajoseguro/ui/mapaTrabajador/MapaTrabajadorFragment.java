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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.proyectofinal.trabajoseguro.R;
import com.proyectofinal.trabajoseguro.model.DAO.DataAnuncio;
import com.proyectofinal.trabajoseguro.model.DAO.DataCategoria;
import com.proyectofinal.trabajoseguro.model.entity.Anuncio;
import com.proyectofinal.trabajoseguro.model.entity.Categoria;

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
    ArrayList<Anuncio> anuncios ;
    String[] categorias ;
    List<Marker> AllMarkers = new ArrayList<Marker>();
    Spinner sp;
    Location bardistancia;
    Button buscarAnuncios;
    int  distancia;
    TextView dismostrar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Permisos();



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //bar.setClickable(false);
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mapa_trabajador, container, false);
        seekBar=view.findViewById(R.id.slader);
        buscarAnuncios=view.findViewById(R.id.buscarTrabajosCercanos);
        sp=(Spinner)view.findViewById(R.id.spinnerCat);
        dismostrar=(TextView) view.findViewById(R.id.distanciaMarcada);
        DataCategoria dataCategoria= new DataCategoria(getContext());
        categorias= dataCategoria.listaCategorias();

        sp.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, categorias));
        sp.setSelection(0);




        //Inicializamos map fragment
        supportMapFragment=(SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                removeAllMarkers();

                distancia = i*1000;
                //actualiza el radio
                circle.setRadius(distancia);
                //creamos el location de la posicion del usuario
                bardistancia= new Location("MI posicion");
                bardistancia.setLatitude(latLng.latitude);
                bardistancia.setLongitude(latLng.longitude);
                int diskm=distancia/1000;
                dismostrar.setText(diskm+"km");
            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        buscarAnuncios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataAnuncio dataAnuncio= new DataAnuncio(getContext());
                //recuperamos todos los anuncios de la bd
                ArrayList<Anuncio> anuncios= dataAnuncio.listaAnunciosGenerales();
                for (int j=0;j<anuncios.size();j++){
                    LatLng latLng1=new LatLng(anuncios.get(j).getLatitud(),anuncios.get(j).getLongitud());
                    String cat= sp.getSelectedItem().toString();
                    //creamos el location de los diferentes anuncios
                    Location ot = new Location("OTROS");
                    ot.setLatitude(latLng1.latitude);
                    ot.setLongitude(latLng1.longitude);





                    //verificamos las distancias
                    if(bardistancia.distanceTo(ot)<=distancia  && anuncios.get(j).getCategoria()== sp.getSelectedItemPosition()){
                        //Log.d("categorias",anuncios.get(j).getNombreCategoria());
                        Log.d("categoria marcada",sp.getSelectedItemPosition()+"");
                        MarkerOptions markerOptions2=new MarkerOptions();
                        markerOptions2.title(latLng1.latitude+":"+latLng1.longitude);
                        markerOptions2.position(latLng1);
                        // markerOptions2.setTag();
                        Marker mLocationMarker = googleMap.addMarker(markerOptions2);
                        mLocationMarker.setTag(anuncios.get(j));
                        //almacenamos los marcadores cercanos
                        AllMarkers.add(mLocationMarker);

                    }
                }
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

                MarkerOptions markerOptions=new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.title("Mi posicion");
                googleMap.clear();
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                googleMap.addMarker(markerOptions);
                circle=googleMap.addCircle(new CircleOptions()
                        .center(latLng)
                        .radius(100)
                        .strokeColor(R.color.rojo)
                        .fillColor(R.color.rojo));
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerName = marker.getTitle();
                        if(!markerName.equalsIgnoreCase("Mi posicion")) {
                            Anuncio s= (Anuncio) marker.getTag();
                            Bundle datosAEnviar = new Bundle();
                            datosAEnviar.putString("latLong", s.getCategoria()+"");
                            Fragment fragmento = new AnuncioVistaMapa();
                            fragmento.setArguments(datosAEnviar);
                            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.replace(R.id.fragment_container_trabajador, fragmento);
                            fragmentTransaction.addToBackStack(null);
                            fragmentTransaction.commit();
                        }

                        // Toast.makeText(getActivity(), "Tu apretaste la ubicacion " + markerName, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });

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