package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Servicios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Servicios extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;
    private Spinner categorias;
    private String buscar,nombre = "%%",lugar = "%%",nota = "0",servicio = "%%",temporada="%%",comuna="%%";
    private List busqueda = new ArrayList();
    private List notitas = new ArrayList();
    private List comunitas = new ArrayList();
    private List Disp = new ArrayList();
    private List Serv = new ArrayList();
    private View vista;
    private ControlBase CB;
    private ListView lista;
    private Lugares lista_lugares[];
    private Vector<Lugar> services;
    private Button Busca;
    private Button filtro;
    public Servicios() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_servicios, container, false);
        nombre = getArguments().getString("Nombre");
        services = new Vector<Lugar>();
        lista = (ListView)vista.findViewById(R.id.ListaLugares);
        categorias = (Spinner)vista.findViewById(R.id.Categorias);
        Busca = (Button)vista.findViewById(R.id.buscarlugar);
        filtro = (Button)vista.findViewById(R.id.filtrado);
        Serv.add("Seleccione servicio a buscar");
        this.rellenar();
        Busca.setOnClickListener(this);
        filtro.setOnClickListener(this);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)view.findViewById(R.id.Titulo);
                buscar = services.elementAt(position).getTipo();
                Intent button_uno = new Intent (vista.getContext(), DetalleActivity.class);
                button_uno.putExtra("TIPO",buscar);
                button_uno.putExtra("editable",1);
                button_uno.putExtra("nombre",v.getText());
                button_uno.putExtra("USUARIO",nombre);
                startActivity(button_uno);

            }
        });
        return vista;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

    public void rellenar(){
        busqueda.clear();
        busqueda.add("Seleccione tipo de lugar");
        CB = new ControlBase(this);
        CB.setTipo(3);
        CB.ejecutar();
    }

    public void AgregarSet(String agregar){
        busqueda.add(agregar);
    }
    public void AgregarSet2(String agregar){
        Serv.add(agregar);
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
    public void desplegar(){
        lista_lugares = new Lugares[services.size()];
        for(int i = 0; i < services.size();i++){
            lista_lugares[i] = new Lugares(R.drawable.ic_launcher,services.elementAt(i).getNombre(),services.elementAt(i).getcomuna(),services.elementAt(i).getDisponibilidad(),services.elementAt(i).getImagen1());
        }
        LugaresAdapter adapter = new LugaresAdapter(vista.getContext(),R.layout.list,lista_lugares);
        lista.setAdapter(adapter);
    }
    public void agregarServicio(Lugar s){
        services.add(s);
    }
    public void actualizar(){
        ArrayAdapter arrayAdapter = new ArrayAdapter(vista.getContext(),android.R.layout.simple_dropdown_item_1line,busqueda);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(arrayAdapter);

        categorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                buscar =  categorias.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        CB = new ControlBase(this);
        CB.setTipo(28);
        CB.ejecutar();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    public void buscar(){
        services.clear();
        CB = new ControlBase(this);
        if(buscar.compareTo("Seleccione tipo de lugar") == 0) buscar = "%%";
        CB.setCategoria(buscar);
        CB.setNombrecito(lugar);
        CB.setComuna(comuna);
        CB.setTemporada(temporada);
        CB.setNota(Integer.parseInt(nota));
        CB.setTipo_actividad(servicio);
        CB.setTipo(4);
        CB.ejecutar();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buscarlugar:
                services.clear();
                CB = new ControlBase(this);
                if(buscar.compareTo("Seleccione tipo de lugar") == 0) buscar = "%%";
                CB.setCategoria(buscar);
                CB.setNombrecito(lugar);
                CB.setComuna(comuna);
                CB.setTemporada(temporada);
                CB.setNota(Integer.parseInt(nota));
                CB.setTipo_actividad(servicio);
                CB.setTipo(4);
                CB.ejecutar();
                break;
            case R.id.filtrado:
                notitas.clear();
                comunitas.clear();
                Disp.clear();
                AlertDialog.Builder builder = new AlertDialog.Builder(vista.getContext());
                View vist = LayoutInflater.from(vista.getContext()).inflate(R.layout.activity_filtros,null);
                final EditText Nlugar = (EditText)vist.findViewById(R.id.Nombre);
                final Spinner comunas = (Spinner)vist.findViewById(R.id.Comuna);
                final Spinner notas = (Spinner)vist.findViewById(R.id.Nota);
                final Spinner temporadas = (Spinner)vist.findViewById(R.id.Servicio);
                final Spinner servicios = (Spinner)vist.findViewById(R.id.Disponibilidad);
                notitas.add("Seleccione nota minima de lugar");
                notitas.add("0");
                notitas.add("1");
                notitas.add("2");
                notitas.add("3");
                notitas.add("4");
                notitas.add("5");
                ArrayAdapter arrayAdapter1 = new ArrayAdapter(vist.getContext(),android.R.layout.simple_dropdown_item_1line,notitas);
                arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                notas.setAdapter(arrayAdapter1);
                notas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        nota = notas.getSelectedItem().toString();
                        if(nota.compareTo("Seleccione nota minima de lugar") == 0)nota = "0";
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                comunitas.add("Seleccion comuna");
                comunitas.add("ARAUCO");
                comunitas.add("CAÑETE");
                comunitas.add("CONTULMO");
                comunitas.add("CURANILAHUE");
                comunitas.add("LEBU");
                comunitas.add("LOS ALAMOS");
                comunitas.add("TIRUA");
                ArrayAdapter arrayAdapter2 = new ArrayAdapter(vist.getContext(),android.R.layout.simple_dropdown_item_1line,comunitas);
                arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                comunas.setAdapter(arrayAdapter2);
                comunas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        comuna = comunas.getSelectedItem().toString();
                        if(comuna.compareTo("Seleccion comuna") == 0)comuna = "%%";
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        comuna = "%%";
                    }
                });
                Disp.add("Seleccione temporada activa");
                Disp.add("Todo el año");
                Disp.add("Invierno");
                Disp.add("Verano");
                Disp.add("Verano-Otoño");
                Disp.add("Primavera-Otroño");
                ArrayAdapter arrayAdapter3 = new ArrayAdapter(vist.getContext(),android.R.layout.simple_dropdown_item_1line,Disp);
                arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                temporadas.setAdapter(arrayAdapter3);
                temporadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        temporada = temporadas.getSelectedItem().toString();
                        if(temporada.compareTo("Seleccione temporada activa") == 0)temporada = "%%";
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        temporada = "%%";
                    }
                });
                ArrayAdapter arrayAdapter4 = new ArrayAdapter(vist.getContext(),android.R.layout.simple_dropdown_item_1line,Serv);
                arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                servicios.setAdapter(arrayAdapter4);
                servicios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        servicio = servicios.getSelectedItem().toString();
                        if(servicio.compareTo("Seleccione servicio a buscar") == 0)servicio = "%%";
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        servicio = "%%";
                    }
                });
                builder.setMessage("Opciones de filtro").setView(vist).setPositiveButton("Buscar",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        lugar = Nlugar.getText().toString();
                        if(Nlugar.getText().toString().compareTo("") == 0)lugar = "%%";
                        Log.e("redirect",lugar);
                        Log.e("redirect",servicio);
                        Log.e("redirect",temporada);
                        Log.e("redirect",comuna);
                        Log.e("redirect", nota);
                        buscar();

                    }
                }).setNegativeButton("Cancel",null);
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
