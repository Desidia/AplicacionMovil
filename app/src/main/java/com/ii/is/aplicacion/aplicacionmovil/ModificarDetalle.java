package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

public class ModificarDetalle extends AppCompatActivity implements View.OnClickListener{

    private ControlBase CB;
    private int mod;
    private String tipo,nombre,usuario;
    private EditText Titulo,Comuna,Direccion,Telefono,Disponibilidad,original,NuevoComentario;
    private TextView Detalle, Tipo;
    private Button Guardar,Comentar;
    private String Rut;
    private RatingBar RB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_detalle);
        CB = new ControlBase(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        tipo = (String) bundle.get("TIPO");
        nombre = (String) bundle.get("nombre");
        mod = (int)bundle.get("editable");
        Titulo = (EditText) findViewById(R.id.Titulo);
        Titulo.setTextColor(Color.parseColor("#CBF0FE"));
        Comuna = (EditText)findViewById(R.id.Comuna);
        Comuna.setTextColor(Color.parseColor("#CBF0FE"));
        Direccion = (EditText)findViewById(R.id.Direccion);
        Direccion.setTextColor(Color.parseColor("#CBF0FE"));
        Telefono = (EditText)findViewById(R.id.Telefono);
        Telefono.setTextColor(Color.parseColor("#CBF0FE"));
        Disponibilidad = (EditText) findViewById(R.id.Disponibilidad);
        Disponibilidad.setTextColor(Color.parseColor("#CBF0FE"));
        Detalle = (TextView)findViewById(R.id.Descripcion);
        Detalle.setTextColor(Color.parseColor("#CBF0FE"));
        NuevoComentario = (EditText) findViewById(R.id.Comentario_Agregar);
        Guardar = (Button)findViewById(R.id.Guardar);
        RB = (RatingBar)findViewById(R.id.NotaGeneral);
        Tipo = (TextView)findViewById(R.id.Tipo);
        Tipo.setTextColor(Color.parseColor("#CBF0FE"));
        Comentar = (Button) findViewById(R.id.Comentar);
        Guardar.setOnClickListener(this);
        Titulo.setText(nombre);
        Tipo.setText(tipo);
        if(mod == 1){
            Titulo.setKeyListener(null);
            Comuna.setKeyListener(null);
            Direccion.setKeyListener(null);
            Telefono.setKeyListener(null);
            Disponibilidad.setKeyListener(null);
            Detalle.setKeyListener(null);
            Guardar.setVisibility(Button.INVISIBLE);
        }
        CB.setTipo(23);
        CB.setNombre(nombre);
        CB.setCategoria(tipo);
        CB.ejecutar();
    }
    public void setDatos(String disponibilidad,String comuna, String direccion, String telefono,String Comentario,int datos){
        Disponibilidad.setText(disponibilidad);
        Comuna.setText(comuna);
        Direccion.setText(direccion);
        Telefono.setText(telefono);
        Detalle.setText(Comentario);
        RB.setIsIndicator(true);
        RB.setRating(datos);
    }
    public void confirmar(){
        Toast.makeText(getApplicationContext(),"Modificacion guardada", Toast.LENGTH_LONG).show();
    }
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Guardar:
                CB = new ControlBase(this);
                CB.setTitulo(nombre);
                CB.setNombrelugar(Titulo.getText().toString());
                CB.setTipo(24);
                CB.setContacto(Telefono.getText().toString());
                CB.setUbicacion(Direccion.getText().toString());
                CB.setDisponibilidad(Disponibilidad.getText().toString());
                CB.setComentario(Detalle.getText().toString());
                CB.ejecutar();
                break;
            default:
                break;
        }
    }
}
