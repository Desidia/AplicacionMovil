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

public class CursosAdapter extends ArrayAdapter<Cursos> {
    Context context;
    int layout;
    Cursos cursos[] = null;
    public CursosAdapter(Context con,int lay,Cursos[] curs){
        super(con,lay,curs);
        context = con;
        layout = lay;
        cursos = curs;
    }
    public View getView(int pos, View cv, ViewGroup parent){
        View row = cv;
        CursosHolder holder = null;
        if(row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layout,parent,false);
            holder = new CursosHolder();
            holder.nombre = (TextView)row.findViewById(R.id.Funcion);
            holder.letra = (TextView)row.findViewById(R.id.Nivel);
            holder.cantidad = (TextView)row.findViewById(R.id.Asignatura);
            holder.promedio = (TextView)row.findViewById(R.id.Cantidad);
            holder.asistencia = (TextView)row.findViewById(R.id.Asistencia);
            row.setTag(holder);
        }
        else{
            holder = (CursosHolder)row.getTag();
        }
        Cursos curso = cursos[pos];
        holder.nombre.setText(curso.getGrado());
        holder.letra.setText(curso.getLetra());
        holder.promedio.setText(String.valueOf(curso.getPromedio()));
        holder.asistencia.setText(String.valueOf(curso.getAsistencia()));
        holder.cantidad.setText(String.valueOf(curso.getAlumnos()));
        return row;
    }
    static class CursosHolder{
        TextView nombre,letra,promedio,asistencia,cantidad;
    }
}
