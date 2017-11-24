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

public class IntergracionAdapter extends ArrayAdapter<Intergracion> {
    Context context;
    int layout;
    Intergracion cursos[] = null;
    public IntergracionAdapter(Context con,int lay,Intergracion[] curs){
        super(con,lay,curs);
        context = con;
        layout = lay;
        cursos = curs;
    }
    public View getView(int pos, View cv, ViewGroup parent){
        View row = cv;
        IntergracionHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout,parent,false);
            holder = new IntergracionHolder();
            holder.tipo = (TextView)row.findViewById(R.id.Tipo);
            holder.cantidad = (TextView)row.findViewById(R.id.Cantidad);
            row.setTag(holder);
        }
        else{
            holder = (IntergracionHolder)row.getTag();
        }
        Intergracion curso = cursos[pos];
        holder.tipo.setText(curso.getTipo());
        holder.cantidad.setText(String.valueOf(curso.getCantidad()));
        return row;
    }
    static class IntergracionHolder{
        TextView tipo,cantidad;
    }
}
