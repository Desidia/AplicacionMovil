package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListaUniversidades extends AppCompatActivity {

    private Controlador CB;
    private ListView lista;
    private ArrayAdapter<String> adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_universidade);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1);
        lista = (ListView)findViewById(R.id.Univer);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String pasar = adapter.getItem(position);
                Intent button_uno = new Intent (ListaUniversidades.this, ListaCarrera.class);
                button_uno.putExtra("Nombre",pasar);
                startActivity(button_uno);
            }
        });
        CB = new Controlador(this);
        CB.setTipo(8);
        CB.ejecutar();
    }
    public void add(String s){
        adapter.add(s);
    }
    public void actualiza(){
        lista.setAdapter(adapter);
    }
}
