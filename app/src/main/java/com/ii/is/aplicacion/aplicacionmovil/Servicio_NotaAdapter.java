package com.ii.is.aplicacion.aplicacionmovil;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by diego on 23-05-2017.
 */

public class Servicio_NotaAdapter extends ArrayAdapter<Servicio_Nota> {
    Context context;
    int LayoutId;
    Servicio_Nota data[] = null;

    public Servicio_NotaAdapter(Context c,int Lid,Servicio_Nota[] datos){
        super(c,Lid,datos);
        LayoutId = Lid;
        context = c;
        data = datos;
    }

    public View getView(int position, View ConvertView, ViewGroup parent){
        View row = ConvertView;
        Servicio_NotaHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(LayoutId,parent,false);
            holder = new Servicio_NotaHolder();
            holder.Titulo = (TextView)row.findViewById(R.id.Servicio);
            holder.Nota = (RatingBar)row.findViewById(R.id.Nota);
            row.setTag(holder);
        }
        else {
            holder = (Servicio_NotaHolder)row.getTag();
        }
        Servicio_Nota lugares = data[position];
        holder.Titulo.setText(lugares.getServicio());
        holder.Nota.setRating((float) lugares.getNota());
        holder.Nota.setIsIndicator(true);
        return row;
    }
    static class Servicio_NotaHolder{
        TextView Titulo;
        RatingBar Nota;
    }
}

