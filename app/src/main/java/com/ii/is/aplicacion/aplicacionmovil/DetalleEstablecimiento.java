package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Vector;

public class DetalleEstablecimiento extends AppCompatActivity implements View.OnClickListener {
    private TextView Nombre,RBD,Estado,Cant_prof,Prom_Cursos,Intergracion;
    private Button Prof,Cursos,Inter;
    private ListView lista;
    private Controlador CB;
    private Vector<String> tipos;
    private ArrayAdapter<String> adapter;
    Integer Rbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_establecimiento);
        tipos = new Vector<String>();
        Nombre = (TextView)findViewById(R.id.Titulo);
        RBD = (TextView)findViewById(R.id.RBD);
        Estado = (TextView)findViewById(R.id.Estado);
        Cant_prof = (TextView)findViewById(R.id.Cantidad_Prof);
        Prom_Cursos = (TextView)findViewById(R.id.Promedio_Cursos);
        Intergracion = (TextView)findViewById(R.id.Intergracion);
        Prof = (Button)findViewById(R.id.Profesores);
        Cursos= (Button)findViewById(R.id.Curso);
        Inter = (Button)findViewById(R.id.Intergracion_b);
        lista = (ListView) findViewById(R.id.Listado);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Nombre.setText((String)bundle.get("Nombre"));
        Rbd = (Integer) bundle.get("Rbd");
        RBD.setText(Rbd.toString());
        Estado.setText((String)bundle.get("Estado"));
        CB = new Controlador(this);
        CB.setRbd(Rbd);
        CB.setTipo(3);
        CB.ejecutar();
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
        Prof.setOnClickListener(this);
        Cursos.setOnClickListener(this);
        Inter.setOnClickListener(this);
    }
    public void add(String S){
    adapter.add(S);
    }
    public void rellenar(){
        lista.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Profesores:
                Intent button_dos = new Intent (DetalleEstablecimiento.this, DetalleProfesores.class);
                button_dos.putExtra("Rbd",Rbd);
                startActivity(button_dos);
                break;
            case R.id.Curso:
                Intent button_uno = new Intent (DetalleEstablecimiento.this, DetalleCursos.class);
                button_uno.putExtra("Rbd",Rbd);
                startActivity(button_uno);
                break;
            case R.id.Intergracion_b:
                Intent button_tres = new Intent (DetalleEstablecimiento.this, DetalleIntergracion.class);
                button_tres.putExtra("Rbd",Rbd);
                startActivity(button_tres);
                break;
            default:
                break;
        }
    }
}
