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

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Principal extends AppCompatActivity implements OnClickListener{

    private String user,buscar;
    private int tipo = 0,turista;
    private Button Buscar;
    private Button lugares;
    private Button itinerarios;
    private Button servicios;
    private Button marcadores;
    private Button propios;
    private Spinner opcion;
    private ControlBase CB;
    private List busqueda = new ArrayList();
    private ListView lista;
    private Lugares lista_lugares[];
    private Vector<Lugar> services;
    private boolean entro;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        CB = new ControlBase(this);
        services = new Vector<Lugar>();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user = (String) bundle.get("USER");
        turista = (int) bundle.get("TIPO");
        Buscar = (Button) findViewById(R.id.Buscar);
        lugares = (Button) findViewById(R.id.Lugares);
        itinerarios = (Button) findViewById(R.id.Itinerarios);
        servicios = (Button) findViewById(R.id.Servicios);
        marcadores = (Button) findViewById(R.id.Marcadores);
        propios = (Button) findViewById(R.id.Propiedades);
        opcion = (Spinner) findViewById(R.id.Spinner);
        Buscar.setOnClickListener(this);
        propios.setOnClickListener(this);
        if (turista == 1) propios.setVisibility(View.INVISIBLE);
        lista = (ListView) findViewById(R.id.Lista);
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
                lista.setVisibility(View.VISIBLE);
                break;
            case R.id.Servicios:
                break;
            case R.id.Itinerarios:
                break;
            case R.id.Marcadores:
                break;
            case R.id.Propiedades:
                Log.e("redirect","abrire basura");
                Intent button_uno = new Intent (Principal.this, Empresario_vista.class);
                button_uno.putExtra("USER",user);
                button_uno.putExtra("TIPO",buscar);
                startActivity(button_uno);
                break;

        }
    }
}
