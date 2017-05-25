package com.ii.is.aplicacion.aplicacionmovil;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Vector;

/**
 * Created by diego on 23-05-2017.
 */

public class OpinionAdapter extends ArrayAdapter<OpinionAgregar> {
    Context context;
    int LayoutId;
    OpinionAgregar data[] = null;

    public OpinionAdapter(Context c,int Lid,OpinionAgregar[] datos){
        super(c,Lid,datos);
        context = c;
        LayoutId = Lid;
        data = datos;
    }
    public View getView(final int position, View ConvertView, ViewGroup parent){
        View row = ConvertView;
        OpinionHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(LayoutId,parent,false);
            holder = new OpinionHolder();
            holder.Usuario = (TextView)row.findViewById(R.id.Nombre);
            holder.Comentario = (TextView)row.findViewById(R.id.Comentario);
            holder.nota = (RatingBar)row.findViewById(R.id.Nota);
            row.setTag(holder);
        }
        else {
            holder = (OpinionHolder)row.getTag();
        }
        OpinionAgregar lugares = data[position];
        holder.Usuario.setText(lugares.getNombre());
        holder.Comentario.setText(lugares.getComentario());
        holder.nota.setRating(lugares.getNota());
        holder.nota.setIsIndicator(true);
        return row;
    }
    static class OpinionHolder{
        TextView Usuario;
        TextView Comentario;
        RatingBar nota;

    }
}

