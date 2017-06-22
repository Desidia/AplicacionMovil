package com.ii.is.aplicacion.aplicacionmovil;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Scroller;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Servicios.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Servicios extends Fragment {

    private OnFragmentInteractionListener mListener;
    private Spinner categorias;
    private String buscar;
    private List busqueda = new ArrayList();
    private View vista;
    private ControlBase CB;
    public Servicios() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_servicios, container, false);
        categorias = (Spinner)vista.findViewById(R.id.Categorias);
        this.rellenar();
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
