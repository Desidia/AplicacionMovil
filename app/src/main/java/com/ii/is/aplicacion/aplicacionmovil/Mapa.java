package com.ii.is.aplicacion.aplicacionmovil;
import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
/*
SOLO FALTA PASARLE EL NOMBRE CORRECTO
 */

public class Mapa extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener,GoogleMap.OnMyLocationButtonClickListener,ActivityCompat.OnRequestPermissionsResultCallback,LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private Marker marcador;
    private boolean entrar = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int LocalPermission = 1;
    private  ControlBase CB;
    private  String nombre;
    private double lat,lng;
    private Location location;
    private Lugar lugar = new Lugar();
    private Mapa mapa;
    private String usuario,comparar = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapa = this;
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = (String)bundle.get("Nombre");
        usuario = (String)bundle.get("Usuario");
     //   nombre = "ALMA RESTOBAR";
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private void sendRequest() {
        String origin = marcador.getPosition().latitude + ","+marcador.getPosition().longitude;
        String destination = lat + ","+lng;
        try {
            Log.e("redirect","Entre al try");
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void igualar(Lugar l){
        lugar = l;
    }
    public void iniciar(){
        Intent button_uno = new Intent (Mapa.this, DetalleActivity.class);
        button_uno.putExtra("TIPO",lugar.getTipo());
        button_uno.putExtra("nombre",lugar.getNombre());
        button_uno.putExtra("USUARIO",usuario);
        startActivity(button_uno);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    /*    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(comparar.compareTo(marker.getTitle()) == 0){
                    try {
                        CB = new ControlBase(mapa);
                        CB.setTipo(35);
                        CB.setComuna(marker.getTitle());
                        CB.ejecutar();
                    } catch (Throwable throwable) {
                    }
                }
                else {
                    comparar = marker.getTitle();
                }
                return false;
            }
        });

*/
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);
        location = mlocManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
     //   Log.e("redirect","mi locacion = "+location.getLongitude());
     //   Log.e("redirect","mi locacion = "+location.getLatitude());
        Log.e("redirect", "Latitud "+lat);
        Log.e("redirect", "longitud "+ lng);
       /* ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LocalPermission);
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET},
                LocalPermission);*/
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setZoomControlsEnabled(true);
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            return;
        }
      //  LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
       // Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      //  agregarMarcador(location.getLatitude(),location.getLongitude());
        //LatLng hcmus = new LatLng(10.762963, 106.682394);
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 18));
       // originMarkers.add(mMap.addMarker(new MarkerOptions()
         //       .title("Đại học Khoa học tự nhiên")
//                .position(hcmus)));

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline:polylinePaths ) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();
        Log.e("redirect","Marcare el camino");
        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                    .title("Mi ubicacion")
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                    .title(nombre)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }
    private void agregarMarcador(double lat, double lng){
        LatLng coordenadas = new LatLng(lat,lng);
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi posicion actual"));
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(!entrar) {
            agregarMarcador(location.getLatitude(), location.getLongitude());
            CB = new ControlBase(this);
            CB.setTipo(30);
            CB.setComuna(nombre);
            CB.ejecutar();
            entrar = true;
        }
    }
    public void ejecutar(){
        Log.e("redirect", "consultare camino");
        sendRequest();
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.e("redirect", "GPS Activado");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.e("redirect", "GPS Desactivado");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}