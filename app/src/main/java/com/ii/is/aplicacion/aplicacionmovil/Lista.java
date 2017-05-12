package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 11-05-2017.
 */

public class Lista {
    private String nombre_actividad, tipo_actividad, lugar_actividad, ubicacion_actividad, comuna_actividad,Itinerario;
    private int pos;

    public Lista() {
        super();
    }

    public Lista(String nombre_actividad, String tipo_actividad, String lugar_actividad, String ubicacion_actividad, String comuna_actividad, int pos) {
        this.nombre_actividad = nombre_actividad;
        this.tipo_actividad = tipo_actividad;
        this.lugar_actividad = lugar_actividad;
        this.comuna_actividad = comuna_actividad;
        this.ubicacion_actividad = ubicacion_actividad;
        this.pos = pos;
    }

    public String getItinerario() {
        return Itinerario;
    }

    public void setItinerario(String itinerario) {
        Itinerario = itinerario;
    }

    public int getPos() {
        return pos;
    }

    public String getComuna_actividad() {
        return comuna_actividad;
    }

    public String getLugar_actividad() {
        return lugar_actividad;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public String getUbicacion_actividad() {
        return ubicacion_actividad;
    }

    public void setComuna_actividad(String comuna_actividad) {
        this.comuna_actividad = comuna_actividad;
    }

    public void setLugar_actividad(String lugar_actividad) {
        this.lugar_actividad = lugar_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public void setUbicacion_actividad(String ubicacion_actividad) {
        this.ubicacion_actividad = ubicacion_actividad;
    }
}


