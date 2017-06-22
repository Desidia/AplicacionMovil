package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Itinerarios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */

public class Itinerarios extends Fragment implements View.OnClickListener {

    private OnFragmentInteractionListener mListener;
    private View vista;
    private ControlBase CB;
    private ListView listaItiner;
    private Itinerario lista_itinerarios[];
    private Vector<Itinerario> vector_itiner;
    private String nombre;
    private Button crear;
    public Itinerarios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        vista = inflater.inflate(R.layout.fragment_itinerarios, container, false);
        nombre = getArguments().getString("Nombre");
        Log.e("redirect",nombre);
        listaItiner = (ListView)vista.findViewById(R.id.ListaDeItinierario);
        crear = (Button)vista.findViewById(R.id.CrearItiner);
        crear.setOnClickListener(this);
        vector_itiner = new Vector<Itinerario>();
        CB = new ControlBase(this);
        CB.setTipo(11);
        CB.ejecutar();
        listaItiner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("redirect","detecte el MALDITO CLICK");
                TextView v = (TextView)view.findViewById(R.id.NombreAct);
                Intent button_uno = new Intent (vista.getContext(), DetalleItinerario.class);
                button_uno.putExtra("NOMBRE",v.getText());
                button_uno.putExtra("CREADOR",vector_itiner.elementAt(position).getPoseedor());
                button_uno.putExtra("TEMPORADA",vector_itiner.elementAt(position).getTemporada());
                button_uno.putExtra("USUARIO",nombre);
                startActivity(button_uno);
            }
        });
        return vista;
    }

    public void agregarItinerario(Itinerario s){
        vector_itiner.add(s);
    }

    public void desplegar2(){
        lista_itinerarios = new Itinerario[vector_itiner.size()];
        for(int i = 0; i < vector_itiner.size();i++){
            lista_itinerarios[i] = vector_itiner.elementAt(i);
        }
        ItinerarioAdapter adapter = new ItinerarioAdapter(vista.getContext(),R.layout.listaitinerarios,lista_itinerarios);
        // View header = (View)getLayoutInflater().inflate(R.layout.header_list,null);
        // if(!entro)lista.addHeaderView(header);
        listaItiner.setAdapter(adapter);
        //     entro = true;
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
        Intent button_uno = new Intent (vista.getContext(), CrearItinerario.class);
        button_uno.putExtra("NOBMRE",nombre);
        startActivity(button_uno);
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
