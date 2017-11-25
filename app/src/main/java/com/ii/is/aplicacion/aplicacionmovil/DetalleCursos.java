package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class DetalleCursos extends AppCompatActivity {

    private ListView ListaCursos;
    private Controlador CB;
    private Cursos cursos[];
    private Vector <Cursos> lcursos;
    private int Rbd;
    private CursosAdapter adapter;
    private TextView cant_alumnos,promedio_notas,promedio_asist;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_cursos);
        ListaCursos = (ListView)findViewById(R.id.ListCursos);
        cant_alumnos = (TextView)findViewById(R.id.cant_alumnos);
        promedio_notas = (TextView)findViewById(R.id.promedio_nota);
        promedio_asist = (TextView)findViewById(R.id.promedio_asit);
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
        int total = 0;
        double nota = 0,asist = 0;
        for(int i = 0; i < lcursos.size();i++){
            cursos[i] = new Cursos();
            cursos[i].setGrado(lcursos.elementAt(i).getGrado());
            cursos[i].setLetra(lcursos.elementAt(i).getLetra());
            cursos[i].setAlumnos(lcursos.elementAt(i).getAlumnos());
            cursos[i].setAsistencia(lcursos.elementAt(i).getAsistencia());
            cursos[i].setPromedio(lcursos.elementAt(i).getPromedio());
            total += lcursos.elementAt(i).getAlumnos();
            nota += lcursos.elementAt(i).getPromedio();
            asist += lcursos.elementAt(i).getAsistencia();
        }
        nota /=(double)lcursos.size();
        asist /=(double)lcursos.size();
        cant_alumnos.setText(String.valueOf(total));
        promedio_asist.setText(String.valueOf(asist));
        promedio_notas.setText(String.valueOf(nota));
        adapter = new CursosAdapter(this,R.layout.layoutcursos,cursos);
        ListaCursos.setAdapter(adapter);
    }
}
