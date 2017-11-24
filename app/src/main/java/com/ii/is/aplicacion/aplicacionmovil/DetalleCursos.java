package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Vector;

public class DetalleCursos extends AppCompatActivity {

    private ListView ListaCursos;
    private Controlador CB;
    private Cursos cursos[];
    private Vector <Cursos> lcursos;
    private int Rbd;
    private CursosAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cursos);
        ListaCursos = (ListView)findViewById(R.id.ListCursos);
        lcursos = new Vector<Cursos>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Rbd = (Integer) bundle.get("Rbd");
        CB = new Controlador(this);
        CB.setTipo(4);
        CB.setRbd(Rbd);
        CB.ejecutar();
    }
    public void add(Cursos aux){
        lcursos.add(aux);
    }
    public void actualizar(){
        cursos = new Cursos[lcursos.size()];
        for(int i = 0; i < lcursos.size();i++){
            cursos[i] = new Cursos();
            cursos[i].setGrado(lcursos.elementAt(i).getGrado());
            cursos[i].setLetra(lcursos.elementAt(i).getLetra());
            cursos[i].setAlumnos(lcursos.elementAt(i).getAlumnos());
            cursos[i].setAsistencia(lcursos.elementAt(i).getAsistencia());
            cursos[i].setPromedio(lcursos.elementAt(i).getPromedio());
        }
        adapter = new CursosAdapter(this,R.layout.layoutcursos,cursos);
        ListaCursos.setAdapter(adapter);
    }
}
