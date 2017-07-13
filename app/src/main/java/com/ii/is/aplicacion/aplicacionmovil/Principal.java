package com.ii.is.aplicacion.aplicacionmovil;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Principal extends AppCompatActivity implements OnClickListener, AdapterView.OnItemClickListener,Servicios.OnFragmentInteractionListener,Itinerarios.OnFragmentInteractionListener,ServiciosFragment.OnFragmentInteractionListener{

    private String user,buscar,nombre = "";
    private int tipo = 0,turista;

    private ControlBase CB;
    private ListView lateral;
    private Vector<String> prueba;
    private DrawerLayout mDrawer;
    private boolean entro,cambio = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = (String) bundle.get("USER");
        turista = (int) bundle.get("TIPO");
        nombre = (String) bundle.get("NOMBRE");
        CB = new ControlBase(this);
        prueba =  new Vector<String>();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_principal);
        lateral = (ListView)findViewById(R.id.left_drawer);
        ArrayList<DrawerItemPrincipal> items = new ArrayList<DrawerItemPrincipal>();
        if(turista != 1){
            items.add(new DrawerItemPrincipal("0",R.drawable.propios));
            prueba.add("Mis Propiedades");
        }
        items.add(new DrawerItemPrincipal("A",R.drawable.lugares));

        items.add(new DrawerItemPrincipal("C",R.drawable.itinerarios));

        prueba.add("Lugares");

        prueba.add("Itinerarios");

        lateral.setAdapter(new DrawerItemPrincipalAdapter(this, items));
        lateral.setOnItemClickListener(this);
        entro = false;

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Fragment fragment = null;
        switch (prueba.elementAt(i)){
            case "Lugares":
                fragment = new Servicios();
                Bundle data3 = new Bundle();
                Log.e("redirect","Imprimire el nombre");
                Log.e("redirect",nombre);
                data3.putString("Nombre", nombre);
                fragment.setArguments(data3);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, fragment)
                        .commit();
                break;
            case "Servicios":
                break;
            case "Itinerarios":
                fragment = new Itinerarios();
                Bundle data = new Bundle();
                data.putString("Nombre", nombre);
                fragment.setArguments(data);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, fragment)
                        .commit();
                break;
            case "Marcadores":
                break;
            case "Mis Propiedades":
                fragment = new ServiciosFragment();
                Bundle data2 = new Bundle();
                data2.putString("USER", user);
                data2.putString("TIPO", buscar);
                data2.putString("NOMBRE", nombre);
                fragment.setArguments(data2);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contenedor, fragment)
                        .commit();
                break;
            default:
                break;
        }

        mDrawer.closeDrawers();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                if (mDrawer.isDrawerOpen(lateral)){
                    mDrawer.closeDrawers();
                }else{
                    mDrawer.openDrawer(lateral);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
