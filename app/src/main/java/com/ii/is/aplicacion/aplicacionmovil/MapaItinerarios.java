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
import java.util.Vector;


public class MapaItinerarios extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener,GoogleMap.OnMyLocationButtonClickListener,ActivityCompat.OnRequestPermissionsResultCallback,LocationListener,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private Button btnFindPath;
    private EditText etOrigin;
    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Route> rutas = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    private Marker marcador;
    private String nombre;
    private boolean entrar = false;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private static final int LocalPermission = 1;
    private int horas = 0;
    private int kilometros = 0;
    private Vector latitud = new Vector();
    private Vector lng = new Vector();
    private Vector <String> nombres = new Vector <String>();
    private ControlBase CB;
    private int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = (String)bundle.get("Nombre");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
//        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();
        CB= new ControlBase(this);
        CB.setTipo(31);
        CB.setComuna(nombre);
        CB.ejecutar();
    }
    public void agregarLatitud(double f){
        latitud.add(f);
    }
    public void agregarLng(double f){
        lng.add(f);
    }
    public void agregarNombre(String f){
        nombres.add(f);
    }
    private void sendRequest(String origin,String destination) {
  /*      if (origin.isEmpty()) {
            Toast.makeText(this, "Please enter origin address!", Toast.LENGTH_SHORT).show();
            return;1
        }
        if (destination.isEmpty()) {
            Toast.makeText(this, "Please enter destination address!", Toast.LENGTH_SHORT).show();
            return;
        }*/

        try {
            Log.e("redirect","origen = "+ origin);
            new DirectionFinder(this, origin, destination).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0,this);

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
      //  sendRequest("-37.982305,-73.244355","-37.244582,-73.321285");
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
    public void comenzar(){
        agregarMarcador((double)latitud.elementAt(contador),(double)lng.elementAt(contador),nombres.elementAt(contador));
        sendRequest(""+latitud.elementAt(contador)+","+lng.elementAt(contador),""+latitud.elementAt(contador+1)+","+lng.elementAt(contador+1));
    }
    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        if(contador == latitud.size()-2){
            agregarMarcador((double)latitud.elementAt(contador+1),(double)lng.elementAt(contador+1),nombres.elementAt(contador+1));
            entrar = true;
        }
        for (int i = 0; i < routes.size();i++){
            //horas+=routes.get(i).duration.value;
            rutas.add(routes.get(i));
        }
        progressDialog.dismiss();

        if(entrar) {
            Log.e("redirect","Marcare los caminitos");
            Log.e("redirect","Contador = "+ contador);
            for (Route route : rutas) {
                horas+=route.duration.value;
                kilometros+=route.distance.value;
                String duracion = horas/3600+" hora y " + (horas%3600)/60 + " minutos";
                String distancia = kilometros/1000 +"."+(kilometros%1000)/100+" km";
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
                ((TextView) findViewById(R.id.tvDuration)).setText(duracion);
                ((TextView) findViewById(R.id.tvDistance)).setText(distancia);
                Log.e("redirect",route.distance.text);
                Log.e("redirect","valor =" +route.distance.value);

             /*   originMarkers.add(mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.start_blue))
                        .title(route.startAddress)
                        .position(route.startLocation)));
                destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.end_green))
                        .title(route.endAddress)
                        .position(route.endLocation)));
*/
                PolylineOptions polylineOptions = new PolylineOptions().
                        geodesic(true).
                        color(Color.BLUE).
                        width(10);

                for (int i = 0; i < route.points.size(); i++)
                    polylineOptions.add(route.points.get(i));

                polylinePaths.add(mMap.addPolyline(polylineOptions));
            }
        }
        if(!entrar){
            contador++;
            agregarMarcador((double)latitud.elementAt(contador),(double)lng.elementAt(contador),nombres.elementAt(contador));
           // entrar = true;
            //agregarMarcador(-37.244582,-73.321285);
            sendRequest(""+latitud.elementAt(contador)+","+lng.elementAt(contador),""+latitud.elementAt(contador+1)+","+lng.elementAt(contador+1));
        }
    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }
    private void agregarMarcador(double lat, double lng,String s){
        LatLng coordenadas = new LatLng(lat,lng);
        marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title(s));
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas,16);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(!entrar) {
            //Log.e("redirect", String.valueOf(location.getLatitude()));
            //Log.e("redirect", String.valueOf(location.getLongitude()));
            //agregarMarcador(location.getLatitude(), location.getLongitude());
        //    entrar = true;
            //sendRequest("-37.982305,-73.244355","-37.244582,-73.321285");
        }
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