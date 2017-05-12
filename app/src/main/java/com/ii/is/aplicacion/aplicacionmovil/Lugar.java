package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 25-04-2017.
 */

public class Lugar {

    private String nombre,contacto,ubicacion,disponibilidad,tipo,rutpropietario,comuna,Detalle;
    private int promedio_lugar;
    public Lugar(){

    }
    public void setDetalle(String s){Detalle = s;}
    public String getDetalle(){return Detalle;}
    public void setNombre(String s){
        nombre = s;
    }
    public void setContacto(String s){
        contacto = s;
    }
    public void setUbicacion(String s){
        ubicacion = s;
    }
    public void setDisponibilidad(String s){
        disponibilidad = s;
    }
    public void setTipo(String s){
        tipo = s;
    }
    public void setrutpropietario(String s){
        rutpropietario = s;
    }
    public void setcomuna(String s){
        comuna = s;
    }
    public void setpromediolugar(int s){
        promedio_lugar = s;
    }
    public String getNombre(){
        return nombre;
    }
    public String getContacto(){
        return contacto;
    }
    public String getUbicacion(){
        return ubicacion;
    }
    public String getDisponibilidad(){
        return disponibilidad;
    }
    public String getTipo(){
        return tipo;
    }
    public String getrutpropietario(){
        return rutpropietario;
    }
    public String getcomuna(){
        return comuna;
    }
    public int getpromediolugar(){
        return promedio_lugar;
    }
}

