package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

public class DetalleCarrera extends AppCompatActivity {

    private ListView ListaCursos;
    private Controlador CB;
    private Carrera cursos[];
    private Vector<Carrera> lcursos;
    private String Nombre,Carrera;
    private CarreraAdapter adapter;
    private TextView NombreU,NombreCarrera;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_carrera);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Nombre = bundle.getString("Nombre");
        Carrera = bundle.getString("Carrera");
        ListaCursos = (ListView)findViewById(R.id.DetalleC);
        NombreU = (TextView)findViewById(R.id.NombreU);
        NombreCarrera = (TextView)findViewById(R.id.NombreCarrera);
        NombreU.setText(Nombre);
        NombreCarrera.setText(Carrera);
        lcursos = new Vector<Carrera>();
        CB = new Controlador(this);
        CB.setTipo(10);
        CB.setUniversidad(Nombre);
        CB.setCarrera(Carrera);
        CB.ejecutar();
        ListaCursos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pasar = adapter.getItem(position).getNombre();
                Intent button_uno = new Intent (DetalleCarrera.this, DetallePostulacion.class);
                button_uno.putExtra("Nombre",pasar);
                startActivity(button_uno);
            }
        });
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
