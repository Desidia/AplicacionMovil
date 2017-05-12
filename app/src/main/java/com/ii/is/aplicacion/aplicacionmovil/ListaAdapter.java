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

/**
 * Created by diego on 11-05-2017.
 */

public class ListaAdapter extends ArrayAdapter<Lista> {
    Context context;
    int LayoutId;
    Lista lista[] = null;
    public ListaAdapter(Context Mycontext, int Lid,Lista[] itiner){
        super(Mycontext,Lid,itiner);
        context = Mycontext;
        LayoutId = Lid;
        lista = itiner;
    }

    public View getView(int posicion, View convertView, ViewGroup parent){
        View row = convertView;
        ListaHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(LayoutId,parent,false);
            holder = new ListaHolder();
            holder.Nombre = (TextView) row.findViewById(R.id.NombreActividad);
            holder.lugar = (TextView) row.findViewById(R.id.Lugar);
            holder.comuna = (TextView) row.findViewById(R.id.ComunaActividad);
            row.setTag(holder);
        }
        else {
            holder = (ListaHolder)row.getTag();
        }
        Lista iter = lista[posicion];
        if(holder == null){ holder = (ListaHolder)row.getTag();
            Log.e("redirect","entre al null");
        }
        holder.Nombre.setText(iter.getNombre_actividad());
        holder.lugar.setText(iter.getLugar_actividad());
        holder.comuna.setText(iter.getComuna_actividad());
        return row;
    }

    static class ListaHolder{
        TextView Nombre,lugar,comuna;
    }
}
