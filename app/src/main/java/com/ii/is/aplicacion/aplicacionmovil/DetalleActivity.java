package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.security.Guard;

public class DetalleActivity extends AppCompatActivity {
    private ControlBase CB;
    private int mod;
    private String tipo,nombre;
    private EditText Titulo,Comuna,Direccion,Telefono,Disponibilidad;
    private TextView Detalle;
    private Button Guardar;
    private String Rut;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        CB = new ControlBase(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tipo = (String) bundle.get("TIPO");
        nombre = (String) bundle.get("nombre");
        mod = (int)bundle.get("editable");
        Titulo = (EditText) findViewById(R.id.Titulo);
        Comuna = (EditText)findViewById(R.id.Comuna);
        Direccion = (EditText)findViewById(R.id.Direccion);
        Telefono = (EditText)findViewById(R.id.Telefono);
        Disponibilidad = (EditText) findViewById(R.id.Disponibilidad);
        Detalle = (TextView)findViewById(R.id.Descripcion);
        Guardar = (Button)findViewById(R.id.Guardar);
        Titulo.setText(nombre);
        if(mod == 1){
            Titulo.setKeyListener(null);
            Comuna.setKeyListener(null);
            Direccion.setKeyListener(null);
            Telefono.setKeyListener(null);
            Disponibilidad.setKeyListener(null);
            Detalle.setKeyListener(null);
            Guardar.setVisibility(Button.INVISIBLE);
        }
        CB.setTipo(5);
        CB.setNombre(nombre);
        CB.setCategoria(tipo);
        CB.ejecutar();
    }
    public void setDatos(String disponibilidad,String comuna, String direccion, String telefono){
        Disponibilidad.setText(disponibilidad);
        Comuna.setText(comuna);
        Direccion.setText(direccion);
        Telefono.setText(telefono);
    }
}
