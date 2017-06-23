package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Scroller;
import android.widget.Spinner;
import android.widget.TextView;

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
    private String buscar,nombre = "";
    private List busqueda = new ArrayList();
    private View vista;
    private ControlBase CB;
    private ListView lista;
    private Lugares lista_lugares[];
    private Vector<Lugar> services;
    private Button Busca;
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
        this.rellenar();

        Busca.setOnClickListener(this);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)view.findViewById(R.id.Titulo);
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

    public void rellenar(){
        busqueda.clear();
        CB = new ControlBase(this);
        CB.setTipo(3);
        CB.ejecutar();
    }

    public void AgregarSet(String agregar){
        busqueda.add(agregar);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buscarlugar:
                services.clear();
                CB = new ControlBase(this);
                CB.setCategoria(buscar);
                CB.setTipo(4);
                CB.ejecutar();
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
