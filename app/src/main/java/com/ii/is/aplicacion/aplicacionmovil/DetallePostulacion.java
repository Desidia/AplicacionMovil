package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class DetallePostulacion extends AppCompatActivity {

    private ListView ListaCursos;
    private Controlador CB;
    private Carrera cursos[];
    private Vector<Carrera> lcursos;
    private String Nombre,Carrera;
    private CarreraAdapter adapter;
    private TextView PsuMate,PsuLeng,PsuCien,PsuHist,Nomb;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_postulacion);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Nombre = bundle.getString("Nombre");
        ListaCursos = (ListView)findViewById(R.id.Alumnos);
        Nomb = (TextView)findViewById(R.id.Nombre);
        Nomb.setText(Nombre);
        PsuMate = (TextView)findViewById(R.id.PsuMat);
        PsuLeng = (TextView)findViewById(R.id.PsuLeng);
        PsuCien = (TextView)findViewById(R.id.PsuCien);
        PsuHist = (TextView)findViewById(R.id.PsuHist);
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
        CB = new Controlador(this);
        CB.setTipo(14);
        CB.setCarrera(Nombre);
        CB.ejecutar();
    }
    public void setPsuMate(String s){
        PsuMate.setText(s);
    }
    public void setPsuLeng(String s){
        PsuLeng.setText(s);
    }
    public void setPsuCien(String s){
        PsuCien.setText(s);
    }
    public void setPsuHist(String s){
        PsuHist.setText(s);
    }
    public void leng(){
        CB = new Controlador(this);
        CB.setTipo(15);
        CB.setCarrera(Nombre);
        CB.ejecutar();
    }
    public void cien(){
        CB = new Controlador(this);
        CB.setTipo(17);
        CB.setCarrera(Nombre);
        CB.ejecutar();
    }
    public void hist(){
        CB = new Controlador(this);
        CB.setTipo(16);
        CB.setCarrera(Nombre);
        CB.ejecutar();
    }

}
