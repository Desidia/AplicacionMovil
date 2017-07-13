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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ServiciosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ServiciosFragment extends Fragment implements View.OnClickListener{

    private OnFragmentInteractionListener mListener;
    private Button Crear;
    private ControlBase CB;
    private List busqueda = new ArrayList();
    private ListView lista;
    private Lugares lista_lugares[];
    private Vector<Lugar> services;
    private String user,buscar,nombre;
    private Boolean entro;
    private View vista;
    public ServiciosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.fragment_servicios2, container, false);
        Crear = (Button)vista.findViewById(R.id.Crear);
        Crear.setOnClickListener(this);
        user =  getArguments().getString("USER");
        buscar = getArguments().getString("TIPO");
        nombre = getArguments().getString("NOMBRE");
        Log.e("redirect",user);
        lista = (ListView) vista.findViewById(R.id.Lista);
        entro = false;
        services = new Vector<Lugar>();
        Log.e("redirect","cree4");
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView v = (TextView)view.findViewById(R.id.Titulo);
                Intent button_uno = new Intent (vista.getContext(), ModificarDetalle.class);
                button_uno.putExtra("TIPO",user);
                button_uno.putExtra("editable",0);
                button_uno.putExtra("nombre",(String)v.getText());
                startActivity(button_uno);
            }
        });
        services.clear();
        CB = new ControlBase(this);
        CB.setUsuario(user);
        CB.setTipo(6);
        Log.e("redirect","entrare base");
        CB.ejecutar();
        return vista;
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
        for(int i = 0; i < services.size();i++){
            lista_lugares[i] = new Lugares(R.drawable.ic_launcher,services.elementAt(i).getNombre(),services.elementAt(i).getcomuna(),services.elementAt(i).getDisponibilidad(),services.elementAt(i).getImagen1(),services.elementAt(i).getpromediolugar());
        }
        LugaresAdapter adapter = new LugaresAdapter(vista.getContext(),R.layout.list2,lista_lugares);
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
                Intent button_uno = new Intent (vista.getContext(), CrearPropiedad.class);
                button_uno.putExtra("RUT",user);
                button_uno.putExtra("NOMBRE",nombre);
                startActivity(button_uno);
                break;
            default:
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
