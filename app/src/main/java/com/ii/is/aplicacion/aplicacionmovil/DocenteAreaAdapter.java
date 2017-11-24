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

public class DocenteAreaAdapter extends ArrayAdapter<DocentesArea> {
    Context context;
    int layout;
    DocentesArea cursos[] = null;
    public DocenteAreaAdapter(Context con,int lay,DocentesArea[] curs){
        super(con,lay,curs);
        context = con;
        layout = lay;
        cursos = curs;
    }
    public View getView(int pos, View cv, ViewGroup parent){
        View row = cv;
        DocenteAreaHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout,parent,false);
            holder = new DocenteAreaHolder();
            holder.nombre = (TextView)row.findViewById(R.id.Funcion);
            holder.letra = (TextView)row.findViewById(R.id.Nivel);
            holder.cantidad = (TextView)row.findViewById(R.id.Asignatura);
            holder.promedio = (TextView)row.findViewById(R.id.Cantidad);
            row.setTag(holder);
        }
        else{
            holder = (DocenteAreaHolder)row.getTag();
        }
        DocentesArea curso = cursos[pos];
        holder.nombre.setText(curso.getFuncion());
        holder.letra.setText(curso.getNivel());
        holder.promedio.setText(String.valueOf(curso.getCantidad()));
        holder.cantidad.setText(curso.getAsignatura());
        return row;
    }
    static class DocenteAreaHolder{
        TextView nombre,letra,promedio,cantidad;
    }
}
