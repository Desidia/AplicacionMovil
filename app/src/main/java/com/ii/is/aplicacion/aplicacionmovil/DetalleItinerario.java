package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class DetalleItinerario extends AppCompatActivity {
    String nombre;
    private TextView TituloItinerario,Creador,Disponibilidad;
    private ListView lista;
    private Lista actividades [];
    private Vector<Lista>vector_lista;
    private ControlBase CB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_itinerario);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre = (String) bundle.get("NOMBRE");
        TituloItinerario = (TextView)findViewById(R.id.titutloitinerario);
        Creador = (TextView)findViewById(R.id.creadoritinerario);
        Disponibilidad = (TextView)findViewById(R.id.disponibilidaditinerario);
        lista = (ListView)findViewById(R.id.viewitinerario);
        TituloItinerario.setText(nombre);
        Creador.setText((String) bundle.get("CREADOR"));
        Disponibilidad.setText((String) bundle.get("TEMPORADA"));
        vector_lista = new Vector<Lista>();
        CB = new ControlBase(this);
        CB.setTipo(12);
        CB.setNombre_Itinerario(nombre);
        CB.ejecutar();
    }
    public void agregarlista(Lista agregar){
        vector_lista.add(agregar);
    }
    public void desplegar(){
        actividades = new Lista[vector_lista.size()];
        Toast.makeText(getApplicationContext(),"tamaño: " + vector_lista.size(), Toast.LENGTH_SHORT).show();
        for(int i = 0; i < vector_lista.size();i++){
            actividades[i] = vector_lista.elementAt(i);
        }
        ListaAdapter adapter = new ListaAdapter(this,R.layout.listactividades,actividades);
        // View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        // if(!entro)lista.addHeaderView(header);
        lista.setAdapter(adapter);
        //     entro = true;
    }
}
