package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CrearPropiedad extends AppCompatActivity implements View.OnClickListener {

    private String Rut,Tipo,Comuna,nombre;
    private Button Crear,añadir;
    private Spinner tipo,comuna;
    private EditText Nombre,Contacto,Disponibilidad,comentario,Direccion;
    private List busqueda = new ArrayList();
    private List ciudad = new ArrayList();
    private ListView servicios;
    private ControlBase CB;
    private String[] elementos;
    private Vector<String> elements;
    private ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_propiedad);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Rut = (String) bundle.get("RUT");
        nombre = (String) bundle.get("NOMBRE");
        Crear = (Button)findViewById(R.id.Crea);
        añadir = (Button)findViewById(R.id.Añade);
        tipo = (Spinner)findViewById(R.id.TipoActividad);
        comuna = (Spinner)findViewById(R.id.Comuna);
        Nombre = (EditText)findViewById(R.id.Nombre);
        Contacto = (EditText)findViewById(R.id.Contacto);
        Direccion = (EditText)findViewById(R.id.Direccion);
        Disponibilidad = (EditText)findViewById(R.id.Disponibilidad);
        comentario = (EditText)findViewById(R.id.Comentario);
        servicios = (ListView)findViewById(R.id.ListServicios);
        elements = new Vector<String>();
        CB = new ControlBase(this);
        busqueda.clear();
        CB.setTipo(7);
        CB.ejecutar();
        CB = new ControlBase(this);
        CB.setTipo(8);
        CB.ejecutar();
        Crear.setOnClickListener(this);
        añadir.setOnClickListener(this);
        servicios.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
    }
    public void AgregarSet(String agregar){
        busqueda.add(agregar);
    }
    public void AgregarC(String agregar){
        ciudad.add(agregar);
    }
    public void actualizar(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,busqueda);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(arrayAdapter);
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Tipo =  tipo.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void actualizarC(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,ciudad);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        comuna.setAdapter(arrayAdapter);

        comuna.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Comuna =  comuna.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    public void comprobar(){
        if(!elements.contains(Tipo)){
            elements.add(Tipo);
            elementos = new String[elements.size()];
            for(int i = 0; i < elements.size();i++)elementos[i] = elements.elementAt(i);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,elementos);
            servicios.setAdapter(adapter);
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Crea:
                for(int i = 0; i < elements.size(); i++) {
                    CB = new ControlBase(this);
                    CB.setTipo(9);
                    CB.setRut(Rut);
                    CB.setNombrelugar(Nombre.getText().toString());
                    CB.setActividad(elements.elementAt(i));
                    CB.setComentario(comentario.getText().toString());
                    CB.setComuna(comuna.getSelectedItem().toString());
                    CB.setContacto(Contacto.getText().toString());
                    CB.setDisponibilidad(Disponibilidad.getText().toString());
                    CB.setUbicacion(Direccion.getText().toString());
                    CB.ejecutar();
                }
                break;
            case R.id.Añade:
                comprobar();
                break;
            default:
                break;
        }
    }
}
