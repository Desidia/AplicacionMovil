package com.ii.is.aplicacion.aplicacionmovil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by diego on 23-11-2017.
 */

public class CarreraAdapter extends ArrayAdapter<Carrera> {
    Context context;
    int layout;
    Carrera cursos[] = null;
    public CarreraAdapter(Context con,int lay,Carrera[] curs){
        super(con,lay,curs);
        context = con;
        layout = lay;
        cursos = curs;
    }
    public View getView(int pos, View cv, ViewGroup parent){
        View row = cv;
        CarreraHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout,parent,false);
            holder = new CarreraHolder();
            holder.nombre = (TextView)row.findViewById(R.id.nombre);
            holder.via = (TextView)row.findViewById(R.id.via);
            holder.cantidad = (TextView)row.findViewById(R.id.cantidad);
            holder.puntaje = (TextView)row.findViewById(R.id.puntaje);
            row.setTag(holder);
        }
        else{
            holder = (CarreraHolder)row.getTag();
        }
        Carrera curso = cursos[pos];
        holder.nombre.setText(curso.getNombre());
        holder.via.setText(curso.getVia());
        holder.puntaje.setText(String.valueOf(curso.getPuntaje()));
        holder.cantidad.setText(String.valueOf(curso.getCantida()));
        return row;
    }
    static class CarreraHolder{
        TextView nombre,via,puntaje,cantidad;
    }
}
