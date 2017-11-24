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

public class EvaluacionDocenteAdapter extends ArrayAdapter<EvaluacionDocentes> {
    Context context;
    int layout;
    EvaluacionDocentes cursos[] = null;
    public EvaluacionDocenteAdapter(Context con,int lay,EvaluacionDocentes[] curs){
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
            holder.Nivel = (TextView)row.findViewById(R.id.Nivel);
            holder.Rut = (TextView)row.findViewById(R.id.Rut);
            holder.Asignatura = (TextView)row.findViewById(R.id.Asignatura);
            holder.Calificacion = (TextView)row.findViewById(R.id.Calificacion);
            holder.Nota = (TextView)row.findViewById(R.id.Nota);
            row.setTag(holder);
        }
        else{
            holder = (DocenteAreaHolder)row.getTag();
        }
        EvaluacionDocentes curso = cursos[pos];
        holder.Nivel.setText(curso.getNivel());
        holder.Rut.setText(String.valueOf(curso.getRut()));
        holder.Nota.setText(String.valueOf(curso.getNotafinal()));
        holder.Asignatura.setText(curso.getAsignatura());
        holder.Calificacion.setText(curso.getClasificacion());
        return row;
    }
    static class DocenteAreaHolder{
        TextView Nivel,Rut,Asignatura,Calificacion,Nota;
    }
}
