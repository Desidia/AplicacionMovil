package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Empresario_vista extends AppCompatActivity  implements View.OnClickListener {
    private Button Propiedades,Crear;
    private ControlBase CB;
    private List busqueda = new ArrayList();
    private ListView lista;
    private Lugares lista_lugares[];
    private Vector<Lugar> services;
    private String user,buscar,nombre;
    private Boolean entro;
    protected void onCreate(Bundle savedInstanceState) {               Log.e("redirect","cree");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresario_vista);
        Propiedades = (Button)findViewById(R.id.Lugares);
        Crear = (Button)findViewById(R.id.Crear);
        Propiedades.setOnClickListener(this);
        Crear.setOnClickListener(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = (String) bundle.get("USER");
        buscar = (String) bundle.get("TIPO");
        nombre = (String) bundle.get("NOMBRE");
        lista = (ListView) findViewById(R.id.Lista);
        entro = false;
        services = new Vector<Lugar>();
        Log.e("redirect","cree4");
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)view.findViewById(R.id.Titulo);
                Intent button_uno = new Intent (Empresario_vista.this, ModificarDetalle.class);
                button_uno.putExtra("TIPO",user);
                button_uno.putExtra("editable",0);
                button_uno.putExtra("nombre",(String)v.getText());
                startActivity(button_uno);
            }
        });
    }
    public void ordenar(){
        Lugar auxiliar;
        for(int i = 0; i < services.size();i++){
            for(int j = i+1; j < services.size();j++){
                if(services.elementAt(i).getpromediolugar() < services.elementAt(j).getpromediolugar()){
                    auxiliar = services.elementAt(j);
                    services.set(j,services.elementAt(i));
                    services.set(i,auxiliar);
                }
            }
        }
    }
    public void agregarServicio(Lugar s){
        services.add(s);
    }
    public void desplegar(){
        lista_lugares = new Lugares[services.size()];
        Toast.makeText(getApplicationContext(),"tamaño: " + services.size(), Toast.LENGTH_SHORT).show();
        for(int i = 0; i < services.size();i++){
            lista_lugares[i] = new Lugares(R.drawable.ic_launcher,services.elementAt(i).getNombre(),services.elementAt(i).getcomuna(),services.elementAt(i).getDisponibilidad());
        }
        LugaresAdapter adapter = new LugaresAdapter(this,R.layout.list,lista_lugares);
        View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        if(!entro)lista.addHeaderView(header);
        lista.setAdapter(adapter);
        entro = true;
    }
    @Override
    public void onClick(View v) {
        Log.e("redirect","toque boton?");
        switch (v.getId()) {
            case R.id.Lugares:
                services.clear();
                CB = new ControlBase(this);
                CB.setUsuario(user);
                CB.setTipo(6);
                Log.e("redirect","entrare base");
                CB.ejecutar();
                break;
            case R.id.Crear:
                Intent button_uno = new Intent (Empresario_vista.this, CrearPropiedad.class);
                button_uno.putExtra("RUT",user);
                button_uno.putExtra("NOMBRE",nombre);
                startActivity(button_uno);
                break;
            default:
                break;
        }
    }
}
