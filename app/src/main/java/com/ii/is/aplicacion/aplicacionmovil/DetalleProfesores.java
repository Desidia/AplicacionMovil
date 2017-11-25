package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class DetalleProfesores extends AppCompatActivity {


    private ListView Area,Evaluacion;
    private Controlador CB;
    private Vector<DocentesArea> docentes;
    private Vector<EvaluacionDocentes> evaluacion;
    private DocentesArea Docent[];
    private EvaluacionDocentes EvaDocent[];
    private int RBD;
    private DocenteAreaAdapter adapter1;
    private EvaluacionDocenteAdapter adapter2;
    private TextView Profes,asistentes;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_profesore);
        Area = (ListView)findViewById(R.id.Area);
        Evaluacion = (ListView)findViewById(R.id.Evaluacion);
        asistentes = (TextView)findViewById(R.id.asistentes);
        CB = new Controlador(this);
        docentes = new Vector<DocentesArea>();
        evaluacion = new Vector<EvaluacionDocentes>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        RBD = (Integer) bundle.get("Rbd");
        CB.setRbd(RBD);
        CB.setTipo(5);
        CB.ejecutar();
    }
    public void addEval(EvaluacionDocentes eva){
        evaluacion.add(eva);
    }
    public void addArea(DocentesArea area){
        docentes.add(area);
    }
    public void Ejecutar6(){
        CB = new Controlador(this);
        CB.setRbd(RBD);
        CB.setTipo(6);
        CB.ejecutar();
    }
    public void actualizar(){
        Docent = new DocentesArea[docentes.size()];
        for(int i = 0; i < docentes.size();i++){
            Docent[i] = new DocentesArea();
            Docent[i].setAsignatura(docentes.elementAt(i).getAsignatura());
            Docent[i].setCantidad(docentes.elementAt(i).getCantidad());
            Docent[i].setFuncion(docentes.elementAt(i).getFuncion());
            Docent[i].setNivel(docentes.elementAt(i).getNivel());
        }
        adapter1 = new DocenteAreaAdapter(this,R.layout.layoutarea,Docent);
        Area.setAdapter(adapter1);
        EvaDocent = new EvaluacionDocentes[evaluacion.size()];
        for(int i = 0; i < evaluacion.size();i++){
            EvaDocent[i] = new EvaluacionDocentes();
            EvaDocent[i].setAsignatura(evaluacion.elementAt(i).getAsignatura());
            EvaDocent[i].setNivel(evaluacion.elementAt(i).getNivel());
            EvaDocent[i].setClasificacion(evaluacion.elementAt(i).getClasificacion());
            EvaDocent[i].setNotafinal(evaluacion.elementAt(i).getNotafinal());
            EvaDocent[i].setRut(evaluacion.elementAt(i).getRut());
        }
        adapter2 = new EvaluacionDocenteAdapter(this,R.layout.layoutevaluacion,EvaDocent);
        Evaluacion.setAdapter(adapter2);
        CB = new Controlador(this);
        CB.setRbd(RBD);
        CB.setTipo(12);
        CB.ejecutar();
    }
    public void setProfes(String p){
        this.cambiaprofe(p);
    }
    public void cambiaprofe(String s){
        Profes = (TextView)findViewById(R.id.profes);
        Profes.setText(s);
    }
    public void setAsistentes(String p){
        asistentes.setText(p);
    }
    public void ejecutaAsis(){
        CB = new Controlador(this);
        CB.setRbd(RBD);
        CB.setTipo(13);
        CB.ejecutar();
    }
}
