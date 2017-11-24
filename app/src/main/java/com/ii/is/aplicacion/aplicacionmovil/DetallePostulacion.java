package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Vector;

public class DetallePostulacion extends AppCompatActivity {

    private ListView ListaCursos;
    private Controlador CB;
    private Carrera cursos[];
    private Vector<Carrera> lcursos;
    private String Nombre,Carrera;
    private CarreraAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_postulacion);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Nombre = bundle.getString("Nombre");
        ListaCursos = (ListView)findViewById(R.id.Alumnos);
        lcursos = new Vector<Carrera>();
        CB = new Controlador(this);
        CB.setTipo(11);
        CB.setCarrera(Nombre);
        CB.ejecutar();
    }
    public void add(Carrera aux){
        lcursos.add(aux);
    }
    public void actualizar(){
        cursos = new Carrera[lcursos.size()];
        for(int i = 0; i < lcursos.size();i++){
            cursos[i] = new Carrera();
            cursos[i].setCantida(lcursos.elementAt(i).getCantida());
            cursos[i].setNombre(lcursos.elementAt(i).getNombre());
            cursos[i].setPuntaje(lcursos.elementAt(i).getPuntaje());
            cursos[i].setVia(lcursos.elementAt(i).getVia());
        }
        adapter = new CarreraAdapter(this,R.layout.layoutdetallecarrera,cursos);
        ListaCursos.setAdapter(adapter);
    }
}
