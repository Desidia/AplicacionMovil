package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import static com.ii.is.aplicacion.aplicacionmovil.R.drawable.happy_face_liked;

public class DetalleItinerario extends AppCompatActivity {
    String nombre;
    private TextView TituloItinerario,Creador,Disponibilidad;
    private ListView lista;
    private Lista actividades [];
    private Vector<Lista>vector_lista;
    private ControlBase CB;
    private ImageButton like;
    private boolean estado_like = false,liked = false;
    private String usuario;
    private DetalleItinerario pasar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_itinerario);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        like = (ImageButton)findViewById(R.id.boton_like);
        pasar = DetalleItinerario.this;
        like.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(!liked) {
                    Log.e("redirect","entre a agregar");
                    CB = new ControlBase(pasar);
                    CB.setTipo(33);
                    CB.setComuna(usuario);
                    CB.setNombrecito(nombre);
                    CB.ejecutar();
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.happy_face_liked);
                    like.setImageBitmap(bmp);
                    liked=true;
                }
                else if(liked ){
                    CB = new ControlBase(pasar);
                    CB.setTipo(34);
                    CB.setComuna(usuario);
                    CB.setNombrecito(nombre);
                    CB.ejecutar();
                    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.happy_face);
                    like.setImageBitmap(bmp);
                    liked = false;
                }
            }
        });
        usuario = (String) bundle.get("USUARIO");
        nombre = (String) bundle.get("NOMBRE");
        TituloItinerario = (TextView)findViewById(R.id.titutloitinerario);
        Creador = (TextView)findViewById(R.id.creadoritinerario);
        Disponibilidad = (TextView)findViewById(R.id.disponibilidaditinerario);
        lista = (ListView)findViewById(R.id.viewitinerario);
        TituloItinerario.setText(nombre);
        Creador.setText((String) bundle.get("CREADOR"));
        Disponibilidad.setText((String) bundle.get("TEMPORADA"));
        vector_lista = new Vector<Lista>();
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent button_uno = new Intent (DetalleItinerario.this, MapaItinerarios.class);
                button_uno.putExtra("Nombre",nombre);
                button_uno.putExtra("Usuario",usuario);
                startActivity(button_uno);
            }
        });
        CB = new ControlBase(this);
        CB.setTipo(32);
        CB.setComuna(usuario);
        CB.setNombrecito(nombre);
        CB.ejecutar();
    }

    public void setliked(){
        liked = true;
        estado_like = true;
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.happy_face_liked);
        like.setImageBitmap(bmp);
        segundaconsulta();
    }
    public void segundaconsulta(){
        CB = new ControlBase(this);
        CB.setTipo(12);
        CB.setNombre_Itinerario(nombre);
        CB.ejecutar();
    }
    public void agregarlista(Lista agregar){
        vector_lista.add(agregar);
    }
    public void desplegar(){
        actividades = new Lista[vector_lista.size()];
        Toast.makeText(getApplicationContext(),"tamaño: " + vector_lista.size(), Toast.LENGTH_SHORT).show();
        for(int i = 0; i < vector_lista.size();i++){
            actividades[i] = vector_lista.elementAt(i);
        }
        ListaAdapter adapter = new ListaAdapter(this,R.layout.listactividades,actividades);
        // View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        // if(!entro)lista.addHeaderView(header);
        lista.setAdapter(adapter);
        //     entro = true;
        Log.e("redirect","TERMINEEEEEEEEEEEE");
    }
}
