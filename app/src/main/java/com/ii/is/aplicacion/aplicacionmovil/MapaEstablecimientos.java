package com.ii.is.aplicacion.aplicacionmovil;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
public class MapaEstablecimientos extends FragmentActivity implements OnMapReadyCallback ,View.OnClickListener{
    /*
    ESTA CLASE SE SUPONE QUE ME ARROJA UN MAPA CON TODOS LOS LUGARES EN ELLA
    USA LA CONSULTA 29
     */
    private GoogleMap mMap;
    private Vector<Establecimiento> establecimientos;
    private String comuna;
    private Controlador CB;
    private Marker marcador;
    private String comparar = "";
    private MapaComuna mapaComuna;
    private String usuario;
    private Button Unis;
    private List<Marker> originMarkers = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_comuna);
        Unis = (Button)findViewById(R.id.universidades);
        Unis.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        establecimientos = new Vector<Establecimiento>();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(status == ConnectionResult.SUCCESS){
            CB = new Controlador(this);
            CB.setTipo(2);
            CB.ejecutar();
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else {

        }
    }
    public void add(Establecimiento g){
        establecimientos.add(g);
    }
    public void agregarpuntos(){
        for(int i = 0; i < establecimientos.size();i++){
           // if(establecimientos.elementAt(i).getRbd() != 17750)continue;
            LatLng coordenadas = new LatLng(establecimientos.elementAt(i).getLat(),establecimientos.elementAt(i).getLng());
            mMap.addMarker(new MarkerOptions().position(coordenadas).title(establecimientos.elementAt(i).getNombre()));
            CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,12);
            mMap.moveCamera(miUbicacion);
        }
        Log.e("redirect", "Termine agregar puntos   " + establecimientos.size() );
    }
    //public void igualar(Establecimiento l){
//        lugar = l;
 //   }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.e("redirect", "Cree mapa");
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
               if(comparar.compareTo(marker.getTitle()) == 0){
                    try {
                        Intent button_uno = new Intent (MapaEstablecimientos.this, DetalleEstablecimiento.class);
                        button_uno.putExtra("Nombre",marker.getTitle());
                        for(int i = 0; i < establecimientos.size();i++){
                            if(marker.getTitle().compareTo(establecimientos.elementAt(i).getNombre())==0){
                                button_uno.putExtra("Rbd",establecimientos.elementAt(i).getRbd());
                                button_uno.putExtra("Estado",establecimientos.elementAt(i).getEstado());
                                break;
                            }
                        }
                        startActivity(button_uno);
                    } catch (Throwable throwable) {
                    }
                }
                else {
                    comparar = marker.getTitle();
                }
                return false;
            }
        });
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        // Add a marker in Sydney and move the camera
  //        LatLng sydney = new LatLng(-34, 151);
   //        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
    //      mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onClick(View v) {
        Log.e("redirect", "Detecte Click");
        switch (v.getId()){
            case R.id.universidades:
                Log.e("redirect", "Abrire listado universidades");
                Intent button_uno = new Intent (MapaEstablecimientos.this, ListaUniversidades.class);
                startActivity(button_uno);
        }
    }
}
