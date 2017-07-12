package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Vector;

public class MapaComuna extends FragmentActivity implements OnMapReadyCallback {
/*

ESTA CLASE SE SUPONE QUE ME ARROJA UN MAPA CON TODOS LOS LUGARES EN ELLA
USA LA CONSULTA 29


 */
    private GoogleMap mMap;
    private Vector<Lugar> lugares;
    private String comuna;
    private ControlBase CB;
    private Marker marcador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_comuna);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        comuna = (String)bundle.get("Comuna");
        lugares = new Vector<Lugar>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(status == ConnectionResult.SUCCESS){
            CB = new ControlBase(this);
            CB.setTipo(29);
            CB.setComuna(comuna);
            CB.ejecutar();
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else {

        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    public void add(Lugar g){
        lugares.add(g);
    }
    public void agregarpuntos(){
        for(int i = 0; i < lugares.size();i++){
            LatLng coordenadas = new LatLng(lugares.elementAt(i).getLat(),lugares.elementAt(i).getLng());
            CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
            marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title(lugares.elementAt(i).getNombre()));
        }
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
        //  LatLng sydney = new LatLng(-34, 151);
        //   mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
