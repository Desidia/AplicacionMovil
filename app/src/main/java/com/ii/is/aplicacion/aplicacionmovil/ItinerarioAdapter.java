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
 * Created by diego on 10-05-2017.
 */

public class ItinerarioAdapter extends ArrayAdapter<Itinerario>{
    Context context;
    int LayoutId;
    Itinerario itinerarios[] = null;
    public ItinerarioAdapter(Context Mycontext, int Lid,Itinerario[] itiner){
        super(Mycontext,Lid,itiner);
        context = Mycontext;
        LayoutId = Lid;
        itinerarios = itiner;
    }
    public View getView(int posicion, View convertView, ViewGroup parent){
        View row = convertView;
        ItinerHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(LayoutId,parent,false);
            holder = new ItinerarioAdapter.ItinerHolder();
            //  holder.RT = (RatingBar) row.findViewById(R.id.RBAct);
            holder.Descripcion = (TextView)row.findViewById(R.id.NombreAct);
            row.setTag(holder);
        }
        else {
            holder = (ItinerarioAdapter.ItinerHolder)row.getTag();
        }
        Itinerario iter = itinerarios[posicion];
        holder.Descripcion.setText(iter.getNombre());
        //holder.RT.setRating((float)(iter.getNota()));
        return row;
    }

    static class ItinerHolder{
        TextView Descripcion;
        //  RatingBar RT;
    }
}
