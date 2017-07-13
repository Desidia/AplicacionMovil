package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CrearItinerario extends AppCompatActivity implements View.OnClickListener {

    private String nombre_user,temp;
    private EditText Nombre;
    private Spinner actividad,lugar,temporada;
    private Button agregar,Crear;
    private ListView visualiza;
    private List Disponibilidad = new ArrayList();
    private List actividades = new ArrayList();
    private List lugares = new ArrayList();
    private ControlBase CB;
    private  String NombreAgregar,LugarAgregar,Comuna;
    private Lista actividade [];
    private Vector<Lista>vector_lista;
    private  int posicion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_itinerario);
        posicion = 1;
        vector_lista = new Vector<Lista>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        nombre_user = (String) bundle.get("NOBMRE");
        Log.e("redirect",nombre_user);
        Crear = (Button)findViewById(R.id.Agregar);
        Nombre = (EditText)findViewById(R.id.NombreActividad);
        temporada= (Spinner)findViewById(R.id.disponibilidadactividad);
        actividad = (Spinner)findViewById(R.id.actividad);
        lugar = (Spinner)findViewById(R.id.lugaractividad);
        agregar = (Button)findViewById(R.id.AgregarActividad);
        visualiza = (ListView)findViewById(R.id.visualizaactividad);
        agregar.setOnClickListener(this);
        Crear.setOnClickListener(this);
        this.rellenar();
    }
    public void rellenar(){
        actividades.clear();
        lugares.clear();
        CB =  new ControlBase(this);
        CB.setTipo(13);
        CB.ejecutar();
    }
    public void llenar(){
        CB = new ControlBase(this);
        CB.setTipo(14);
        CB.setNombre_Actividad(NombreAgregar);
        CB.ejecutar();
    }

    public void setComuna(String comuna) {
        Comuna = comuna;
    }
    public void agregarlista(Lista agregar){
        agregar.setItinerario(Nombre.getText().toString());
        agregar.setPos(posicion);
        posicion++;
        vector_lista.add(agregar);
    }
    public void AgregarSet1(String agregar){
        actividades.add(agregar);
    }
    public void AgregarSet2(String agregar){
        lugares.add(agregar);
    }
    public  void actualizarT(){
        Disponibilidad.clear();
        Disponibilidad.add("Seleccione temporada activa");
        Disponibilidad.add("Todo el año");
        Disponibilidad.add("Invierno");
        Disponibilidad.add("Verano");
        Disponibilidad.add("Verano-Otoño");
        Disponibilidad.add("Primavera-Otroño");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner_crear_itinerario,Disponibilidad);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_crear_itinerario);
        temporada.setAdapter(arrayAdapter);
        temporada.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                temp =  temporada.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void actualizar1(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,R.layout.spinner_crear_itinerario,actividades);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_crear_itinerario);
        actividad.setAdapter(arrayAdapter);

        actividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NombreAgregar =  actividad.getSelectedItem().toString();
                lugares.clear();
                llenar();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void actualizar2(){
        ArrayAdapter arrayAdapter2 = new ArrayAdapter(this,R.layout.spinner_crear_itinerario,lugares);
        arrayAdapter2.setDropDownViewResource(R.layout.spinner_crear_itinerario);
        lugar.setAdapter(arrayAdapter2);
        lugar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                LugarAgregar =  lugar.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void ejecutarllenado(){
        Log.e("redirect","Ejecute funcion ejecutarllenado");
        CB = new ControlBase(this);
        CB.setTipo(15);
        CB.setNombre_Actividad(NombreAgregar);
        CB.setUbicacion(LugarAgregar);
        CB.setComuna(Comuna);
        CB.ejecutar();
    }
    public void actualiza(){
        actividade = new Lista[vector_lista.size()];
        Toast.makeText(getApplicationContext(),"tamaño: " + vector_lista.size(), Toast.LENGTH_SHORT).show();
        for(int i = 0; i < vector_lista.size();i++){
            actividade[i] = vector_lista.elementAt(i);
        }
        ListaAdapter adapter = new ListaAdapter(this,R.layout.listactividades,actividade);
        // View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        // if(!entro)lista.addHeaderView(header);
        visualiza.setAdapter(adapter);
        //     entro = true;
    }
    public void creaactividades(){
        CB = new ControlBase(this);
        CB.setTipo(18);
        CB.setMylista(vector_lista);
        Log.e("redirect","Rellenare con actividades");
        CB.ejecutar();
    }
    public void confirmar(){
        Toast.makeText(getApplicationContext(),"Itinerario Creado", Toast.LENGTH_LONG).show();
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.AgregarActividad:
                CB = new ControlBase(this);
                CB.setTipo(16);
                CB.setNombre_Actividad(NombreAgregar);
                CB.setUbicacion(LugarAgregar);
                Log.e("redirect",NombreAgregar);
                Log.e("redirect",LugarAgregar);
                CB.ejecutar();
            break;
            case R.id.Agregar:
                CB = new ControlBase(this);
                CB.setTipo(17);
                CB.setDisponibilidad(temporada.getSelectedItem().toString());
                CB.setId(Nombre.getText().toString());
                CB.setPoseedor(nombre_user);
                Log.e("redirect",nombre_user);
                Log.e("redirect","Creare el itinerario");
                CB.ejecutar();
                break;
            default:
                break;

        }
    }
}
