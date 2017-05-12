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
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CrearPropiedad extends AppCompatActivity implements View.OnClickListener {

    private String Rut,Tipo,Comuna,nombre;
    private Button Crear;
    private Spinner tipo,comuna;
    private EditText Nombre,Contacto,Disponibilidad,comentario,Direccion;
    private List busqueda = new ArrayList();
    private List ciudad = new ArrayList();
    private ControlBase CB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_propiedad);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Rut = (String) bundle.get("RUT");
        nombre = (String) bundle.get("NOMBRE");
        Crear = (Button)findViewById(R.id.Crea);
        tipo = (Spinner)findViewById(R.id.TipoActividad);
        comuna = (Spinner)findViewById(R.id.Comuna);
        Nombre = (EditText)findViewById(R.id.Nombre);
        Contacto = (EditText)findViewById(R.id.Contacto);
        Direccion = (EditText)findViewById(R.id.Direccion);
        Disponibilidad = (EditText)findViewById(R.id.Disponibilidad);
        comentario = (EditText)findViewById(R.id.Comentario);
        CB = new ControlBase(this);
        busqueda.clear();
        CB.setTipo(7);
        CB.ejecutar();
        CB = new ControlBase(this);
        CB.setTipo(8);
        CB.ejecutar();
        Crear.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Crea:
                CB = new ControlBase(this);
                CB.setTipo(9);
                CB.setRut(Rut);
                CB.setNombrelugar(Nombre.getText().toString());
                CB.setActividad(tipo.getSelectedItem().toString());
                CB.setComentario(comentario.getText().toString());
                CB.setComuna(comuna.getSelectedItem().toString());
                CB.setContacto(Contacto.getText().toString());
                CB.setDisponibilidad(Disponibilidad.getText().toString());
                CB.setUbicacion(Direccion.getText().toString());
                CB.ejecutar();
                break;
            default:
                break;

        }
    }
}
