package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 23-05-2017.
 */

public class Servicio_Nota {
    private double nota;
    private String servicio;
    public Servicio_Nota(){
        super();
        nota = 0.0;
    }
    public Servicio_Nota(String s){
        this.servicio = s;
    }
    public Servicio_Nota(double d, String s){
        nota = d;
        servicio = s;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }
}
