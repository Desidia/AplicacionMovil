package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.Vector;

public class DetalleIntergracion extends AppCompatActivity {

    private int Rbd;
    private Controlador CB;
    private ListView lista;
    private Vector<Intergracion> intergracion;
    private Intergracion inter[];
    private IntergracionAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_intergracion);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Rbd = (Integer) bundle.get("Rbd");
        lista = (ListView)findViewById(R.id.Intergracion);
        intergracion = new Vector<Intergracion>();
        CB = new Controlador(this);
        CB.setRbd(Rbd);
        CB.setTipo(7);
        CB.ejecutar();
    }
    public void add(Intergracion intergra){
        intergracion.add(intergra);
    }
    public void actualizar(){
        inter = new Intergracion[intergracion.size()];
        for(int i = 0; i < intergracion.size();i++){
            inter[i] = new Intergracion();
            inter[i].setCantidad(intergracion.elementAt(i).getCantidad());
            inter[i].setTipo(intergracion.elementAt(i).getTipo());
        }
        adapter = new IntergracionAdapter(this,R.layout.layoutintergracion,inter);
        lista.setAdapter(adapter);
    }
}
