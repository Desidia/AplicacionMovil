package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 25-04-2017.
 */

public class Servicio {
    private String tipo,acceso,lugar,direccion,comuna;
    double promedio;
    public Servicio(){
        tipo = "";
        acceso = "";
        lugar = "";
        direccion = "";
        comuna = "";
        promedio = 0.0;
    }
    public String gettipo(){
        return tipo;
    }
    public String getacceso(){
        return acceso;
    }
    public String getlugar(){
        return lugar;
    }
    public String getdireccion(){
        return direccion;
    }
    public String getcomuna(){
        return comuna;
    }
    public double getpromedio(){
        return promedio;
    }
    public void settipo(String s){
        tipo = s;
    }
    public void setacceso(String s){
        acceso = s;
    }
    public void setlugar(String s){
        lugar = s;
    }
    public void setdireccion(String s){
        direccion = s;
    }
    public void setcomuna(String s){
        comuna = s;
    }
    public void setpromedio(double d){
        promedio = d;
    }

}

