package com.ii.is.aplicacion.aplicacionmovil;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

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
public class MapaEstablecimientos extends FragmentActivity   implements OnMapReadyCallback ,View.OnClickListener{
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
    private Button Buscar;
    private Spinner Regiones;
    private String region;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_comuna);
        Unis = (Button)findViewById(R.id.universidades);
        Unis.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        establecimientos = new Vector<Establecimiento>();
        Buscar = (Button)findViewById(R.id.Buscar);
        Regiones = (Spinner)findViewById(R.id.Regiones);
        List list = new ArrayList();
        list.add("región de antofagasta");
        list.add("región de arica y parinacota");
        list.add("región de atacama");
        list.add("región de aysén del gral carlos ibáñez del ca?");
        list.add("región de coquimbo");
        list.add("región de la araucanía");
        list.add("región del biobío");
        list.add("región del libertador gral bernardo ohiggins");
        list.add("región del maule");
        list.add("región de los lagos");
        list.add("región de los ríos");
        list.add("región de magallanes y de la antártica chilena");
        list.add("región de tarapacá");
        list.add("región de valparaíso");
        list.add("región metropolitana de santiago");
        list.add("todas las regiones");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        Regiones.setAdapter(arrayAdapter);
        Regiones.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                region = String.valueOf(Regiones.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Buscar.setOnClickListener(this);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(status == ConnectionResult.SUCCESS){
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
                break;
            case R.id.Buscar:
                if(region.compareTo("todas las regiones") == 0){
                    mMap.clear();
                    establecimientos.clear();
                    CB = new Controlador(this);
                    CB.setTipo(2);
                    CB.ejecutar();
                }
                else {
                    Log.e("redirect", region);
                    mMap.clear();
                    establecimientos.clear();
                    CB = new Controlador(this);
                    CB.setTipo(18);
                    CB.setCarrera(region);
                    CB.ejecutar();
                }
        }
    }
}
