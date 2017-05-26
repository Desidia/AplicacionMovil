package com.ii.is.aplicacion.aplicacionmovil;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Vector;
public class DetalleActivity extends AppCompatActivity implements View.OnClickListener{
    private ControlBase CB;
    private int mod;
    private String tipo,nombre,usuario;
    private EditText Titulo,Comuna,Direccion,Telefono,Disponibilidad,original,Tipo,NuevoComentario;
    private TextView Detalle;
    private Button Guardar,Comentar;
    private String Rut;
    private RatingBar RB;
    private Vector <String> Servicios;
    private Vector <Integer> SS,CS,Apariciones;
    private ListView Comentarios,Notas,AgregarNota;
    private Vector<evaluacion> Evaluaciones;
    private Vector<Opinion> Opiniones;
    private Servicio_Nota SerNota[];
    private OpinionAgregar Opinionesagregar[];
    private Servicio_NotaAdapter adapter;
    private Servicio_NotaAdapter2 adapter2;
    private OpinionAdapter adapter3;
    private Boolean creeunaOp = false,romper = false;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        Evaluaciones = new Vector<evaluacion>();
        Opiniones = new Vector<Opinion>();
        CB = new ControlBase(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Servicios= new Vector<String>();
        SS= new Vector<Integer>();
        CS= new Vector<Integer>();
        Apariciones= new Vector<Integer>();
        Comentarios = (ListView)findViewById(R.id.ListComentarios);
        Notas = (ListView)findViewById(R.id.ListNotas);
        AgregarNota = (ListView)findViewById(R.id.ListEvaluar);
        tipo = (String) bundle.get("TIPO");
        nombre = (String) bundle.get("nombre");
        mod = (int)bundle.get("editable");
        usuario = (String)bundle.get("USUARIO");
        Log.e("redirect",usuario);
        Titulo = (EditText) findViewById(R.id.Titulo);
        Comuna = (EditText)findViewById(R.id.Comuna);
        Direccion = (EditText)findViewById(R.id.Direccion);
        Telefono = (EditText)findViewById(R.id.Telefono);
        Disponibilidad = (EditText) findViewById(R.id.Disponibilidad);
        Detalle = (TextView)findViewById(R.id.Descripcion);
        NuevoComentario = (EditText) findViewById(R.id.Comentario_Agregar);
        Guardar = (Button)findViewById(R.id.Guardar);
        RB = (RatingBar)findViewById(R.id.NotaGeneral);
        Tipo = (EditText)findViewById(R.id.Tipo);
        Comentar = (Button) findViewById(R.id.Comentar);
        Comentar.setOnClickListener(this);
        Guardar.setOnClickListener(this);
        Titulo.setText(nombre);
        Tipo.setText(tipo);
        Notas.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        AgregarNota.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }
                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });
        if(mod == 1){
            Titulo.setKeyListener(null);
            Comuna.setKeyListener(null);
            Direccion.setKeyListener(null);
            Telefono.setKeyListener(null);
            Disponibilidad.setKeyListener(null);
            Detalle.setKeyListener(null);
            Tipo.setKeyListener(null);
            Guardar.setVisibility(Button.INVISIBLE);
        }
        CB.setTipo(5);
        CB.setNombre(nombre);
        CB.setCategoria(tipo);
        CB.ejecutar();
    }
    public void agregarEval(evaluacion agrega){
        Evaluaciones.add(agrega);
    }
    public void agregaOpinion(Opinion op){
        Opiniones.add(op);
    }
    public void agregarSN(String s){
        Servicios.add(s);
    }
    public void setDatos(String disponibilidad,String comuna, String direccion, String telefono,String Comentario,int datos){
        Disponibilidad.setText(disponibilidad);
        Comuna.setText(comuna);
        Direccion.setText(direccion);
        Telefono.setText(telefono);
        Detalle.setText(Comentario);
        RB.setIsIndicator(true);
        RB.setRating(datos);
        iniciarComentarios();
    }
    public void iniciarComentarios(){
        CB = new ControlBase(this);
        CB.setTipo(19);
        CB.setNombre(nombre);
        Log.e("redirect",nombre);
        CB.ejecutar();
    }
    public void evaluaciones(){
        CB = new ControlBase(this);
        CB.setTipo(20);
        CB.setNombre(nombre);
        CB.ejecutar();
    }
    public void ListaOpiniones(){
        CB = new ControlBase(this);
        CB.setTipo(21);
        CB.setNombre(nombre);
        CB.ejecutar();
    }
    public void generarOpiniones(){
        Opinionesagregar = new OpinionAgregar[Opiniones.size()];
        for(int i = 0; i < Opiniones.size(); i++){
            Opinionesagregar[i] = new OpinionAgregar();
            Opinionesagregar[i].setNombre(Opiniones.elementAt(i).getUsuario());
            Opinionesagregar[i].setComentario(Opiniones.elementAt(i).getComentario());
            Opinionesagregar[i].setNota(Opiniones.elementAt(i).getNota());
        }
        adapter3 = new OpinionAdapter(this,R.layout.comentarios,Opinionesagregar);
        //View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        Comentarios.setAdapter(adapter3);
        Evaluaciones.clear();
        Opiniones.clear();
        Servicios.clear();
        if(creeunaOp)crearEvaluacion();
    }
    public void crearServicio_Nota(){
        SerNota = new Servicio_Nota[Servicios.size()];
        Servicio_Nota SerNota2[] = new Servicio_Nota[Servicios.size()];
        for(int j = 0; j < Servicios.size();j++) {
            int sum = 0;
            int cant = 0;
            for (int i = 0; i < Evaluaciones.size(); i++) {
                Log.e("redirect",Evaluaciones.elementAt(i).getLugar().toString());
                Log.e("redirect",Servicios.elementAt(j).toString());
                if(Evaluaciones.elementAt(i).getTipo_servicio().toString().equalsIgnoreCase(Servicios.elementAt(j).toString())){
                    sum+=Evaluaciones.elementAt(i).getNota();
                    cant++;
                }
            }
            SerNota[j] = new Servicio_Nota();
            SerNota2[j] = new Servicio_Nota();
            if(cant != 0)SerNota[j].setNota(sum/cant);
            SerNota[j].setServicio(Servicios.elementAt(j));
            SerNota2[j].setServicio(Servicios.elementAt(j));
            Toast.makeText(getApplicationContext(), "Nota servicio : " + Servicios.elementAt(j) + "  : "+ sum, Toast.LENGTH_SHORT).show();
        }

         adapter = new Servicio_NotaAdapter(this,R.layout.notaservicio,SerNota);
         adapter2 = new Servicio_NotaAdapter2(this,R.layout.notaservicio,SerNota2);
        //View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        Notas.setAdapter(adapter);
        AgregarNota.setAdapter(adapter2);
        ListaOpiniones();
    }
    public void crearEvaluacion(){
        if(!romper) {
            CB = new ControlBase(this);
            Log.e("redirect", usuario);
            CB.setUsuario(usuario);
            CB.setComentario(NuevoComentario.getText().toString());
            CB.setLugar_actividad(Titulo.getText().toString());
            CB.setComuna(Comuna.getText().toString());
            CB.setDireccion(Direccion.getText().toString());
            CB.setTipo(25);
            int basura = 0;
            for(int i = 0; i < 1000000000;i++)basura++;
            System.out.println("Ejecutare caso 25 ");
            for (int i = 0; i < adapter2.getCount(); i++) {
                CB.addNotita((int) adapter2.getNota2(i));
                CB.addTipos(SerNota[i].getServicio());
            }
            romper = true;
            CB.ejecutar();
        }
    }
    public  void crearOpinion(){
        if(!romper) {
            CB = new ControlBase(this);
            Log.e("redirect", usuario);
            CB.setUsuario(usuario);
            CB.setComentario(NuevoComentario.getText().toString());
            CB.setLugar_actividad(Titulo.getText().toString());
            CB.setComuna(Comuna.getText().toString());
            CB.setDireccion(Direccion.getText().toString());
            CB.setTipo(22);
            System.out.println("Ejecutare caso 22 ");
            float suma = 0;
            for (int i = 0; i < adapter2.getCount(); i++) {
                suma += adapter2.getNota2(i);
            }
            Toast.makeText(getApplicationContext(), "suma: " + (int)suma, Toast.LENGTH_SHORT).show();
            if (adapter2.getCount() != 0) suma /= adapter2.getCount();
            CB.setNota((int) suma);
            CB.ejecutar();
        }
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.Guardar:
                CB = new ControlBase(this);
                CB.setTitulo(nombre);
                CB.setNombrelugar(Titulo.getText().toString());
                CB.setTipo(10);
                CB.setContacto(Telefono.getText().toString());
                CB.setUbicacion(Direccion.getText().toString());
                CB.setDisponibilidad(Disponibilidad.getText().toString());
                CB.setComentario(Detalle.getText().toString());
                CB.ejecutar();
                break;
            case R.id.Comentar:
                if(!creeunaOp) {
                    creeunaOp = true;
                    crearOpinion();
                }
                break;
            default:
                break;
        }
    }
}
