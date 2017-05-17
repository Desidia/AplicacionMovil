package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Principal extends AppCompatActivity implements OnClickListener{

    private String user,buscar,nombre;
    private int tipo = 0,turista;
    private Button Buscar;
    private Button lugares;
    private Button itinerarios;
    private Button servicios;
    private Button marcadores,crear;
    private Button propios;
    private Spinner opcion;
    private ControlBase CB;
    private List busqueda = new ArrayList();
    private ListView lista,listaItiner;
    private Lugares lista_lugares[];
    private Itinerario lista_itinerarios[];
    private Vector<Lugar> services;
    private Vector<Itinerario>vector_itiner;
    private ViewSwitcher VS;
    private boolean entro,cambio = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        CB = new ControlBase(this);
        vector_itiner = new Vector<Itinerario>();
        services = new Vector<Lugar>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        VS = (ViewSwitcher)findViewById(R.id.Switch);
        user = (String) bundle.get("USER");
        turista = (int) bundle.get("TIPO");
        nombre = (String) bundle.get("NOMBRE");
        Buscar = (Button) findViewById(R.id.Buscar);
        lugares = (Button) findViewById(R.id.Lugares);
        itinerarios = (Button) findViewById(R.id.Itinerarios);
        servicios = (Button) findViewById(R.id.Servicios);
        marcadores = (Button) findViewById(R.id.Marcadores);
        crear = (Button) findViewById(R.id.crearitinerario);
        propios = (Button) findViewById(R.id.Propiedades);
        opcion = (Spinner) findViewById(R.id.Spinner);
        listaItiner = (ListView)findViewById(R.id.ListIter);
        Buscar.setOnClickListener(this);
        itinerarios.setOnClickListener(this);
        propios.setOnClickListener(this);
        crear.setOnClickListener(this);
        lugares.setOnClickListener(this);
        if (turista == 1) propios.setVisibility(View.INVISIBLE);
        lista = (ListView) findViewById(R.id.Lista);
        crear.setVisibility(View.INVISIBLE);

        listaItiner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("redirect","detecte el MALDITO CLICK");
                TextView v = (TextView)view.findViewById(R.id.NombreAct);
                Intent button_uno = new Intent (Principal.this, DetalleItinerario.class);
                button_uno.putExtra("NOMBRE",v.getText());
                button_uno.putExtra("CREADOR",vector_itiner.elementAt(position).getPoseedor());
                button_uno.putExtra("TEMPORADA",vector_itiner.elementAt(position).getTemporada());
                startActivity(button_uno);
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)view.findViewById(R.id.Titulo);
                Intent button_uno = new Intent (Principal.this, DetalleActivity.class);
                button_uno.putExtra("TIPO",buscar);
                button_uno.putExtra("editable",1);
                button_uno.putExtra("nombre",v.getText());
                startActivity(button_uno);
            }
        });

    entro = false;
        this.rellenar();
    }
    public void rellenar(){
        busqueda.clear();
        CB.setTipo(3+tipo);
        CB.ejecutar();
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
    public void AgregarSet(String agregar){
        busqueda.add(agregar);
    }
    public void agregarServicio(Lugar s){
        services.add(s);
    }
    public void agregarItinerario(Itinerario s){
        vector_itiner.add(s);
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

    public void desplegar2(){
        lista_itinerarios = new Itinerario[vector_itiner.size()];
        Toast.makeText(getApplicationContext(),"tamaño: " + vector_itiner.size(), Toast.LENGTH_SHORT).show();
        for(int i = 0; i < vector_itiner.size();i++){
            lista_itinerarios[i] = vector_itiner.elementAt(i);
        }
        ItinerarioAdapter adapter = new ItinerarioAdapter(this,R.layout.listaitinerarios,lista_itinerarios);
       // View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
       // if(!entro)lista.addHeaderView(header);
        listaItiner.setAdapter(adapter);
   //     entro = true;
    }

    public void actualizar(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,busqueda);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        opcion.setAdapter(arrayAdapter);

        opcion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                buscar =  opcion.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Buscar:
                services.clear();
                CB = new ControlBase(this);
                CB.setCategoria(buscar);
                CB.setTipo(4);
                CB.ejecutar();
                break;
            case R.id.Lugares:
                if(cambio) {
                    VS.showNext();
                    crear.setVisibility(View.INVISIBLE);
                    cambio = false;
                }
                break;
            case R.id.Servicios:
                break;
            case R.id.Itinerarios:
                Log.e("redirect","detecte el botoncillo");
                if(!cambio) {
                    VS.showNext();
                    vector_itiner.clear();
                    crear.setVisibility(View.VISIBLE);
                    CB = new ControlBase(this);
                    CB.setTipo(11);
                    CB.ejecutar();
                    cambio = true;
                }
                break;
            case R.id.Marcadores:
                break;
            case R.id.Propiedades:
                Log.e("redirect","abrire basura");
                Intent button_uno = new Intent (Principal.this, Empresario_vista.class);
                button_uno.putExtra("USER",user);
                button_uno.putExtra("TIPO",buscar);
                button_uno.putExtra("NOMBRE",nombre);
                startActivity(button_uno);
                break;
            case R.id.crearitinerario:
                Intent uno = new Intent (Principal.this, CrearItinerario.class);
                Log.e("redirect","El nombre que le pasare");
                Log.e("redirect",nombre);
                uno.putExtra("NOBMRE",nombre);
                startActivity(uno);
                break;

        }
    }
}
