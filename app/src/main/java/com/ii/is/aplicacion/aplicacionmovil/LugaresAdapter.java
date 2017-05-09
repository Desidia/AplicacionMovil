package com.ii.is.aplicacion.aplicacionmovil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by diego on 25-04-2017.
 */

public class LugaresAdapter extends ArrayAdapter<Lugares> {
    Context context;
    int LayoutId;
    Lugares data[] = null;

    public LugaresAdapter(Context c,int Lid,Lugares[] datos){
        super(c,Lid,datos);
        LayoutId = Lid;
        context = c;
        data = datos;
    }

    public View getView(int position, View ConvertView, ViewGroup parent){
        View row = ConvertView;
        LugaresHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(LayoutId,parent,false);
            holder = new LugaresHolder();
            holder.imagen = (ImageView)row.findViewById(R.id.imagen);
            holder.Titulo = (TextView)row.findViewById(R.id.Titulo);
            holder.Direccion = (TextView)row.findViewById(R.id.Direccion);
            holder.Descripcion = (TextView)row.findViewById(R.id.Descripcion);
            row.setTag(holder);
        }
        else {
            holder = (LugaresHolder)row.getTag();
        }
        Lugares lugares = data[position];
        holder.Titulo.setText(lugares.title);
        holder.Direccion.setText(lugares.direccion);
        holder.Descripcion.setText(lugares.descripcion);
        holder.imagen.setImageResource(lugares.icon);
        return row;
    }
    static class LugaresHolder{
        ImageView imagen;
        TextView Titulo;
        TextView Direccion;
        TextView Descripcion;
    }
}
