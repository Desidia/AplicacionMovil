package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class CrearPropiedad extends AppCompatActivity {

    private String Rut;
    private Button Crear;
    private Spinner tipo,comuna;
    private TextView Nombre,Contacto,Disponibilidad;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_propiedad);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Rut = (String) bundle.get("RUT");
        Crear = (Button)findViewById(R.id.Crea);
        tipo = (Spinner)findViewById(R.id.TipoActividad);
        comuna = (Spinner)findViewById(R.id.Comuna);
        Nombre = (TextView)findViewById(R.id.Nombre);
        Contacto = (TextView)findViewById(R.id.Contacto);
        Disponibilidad = (TextView)findViewById(R.id.Disponibilidad);
    }
}
