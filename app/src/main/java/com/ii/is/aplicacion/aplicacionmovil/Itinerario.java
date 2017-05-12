package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 10-05-2017.
 */

public class Itinerario {
    private String poseedor,temporada,nombre;
    private int nota;
    public Itinerario(){
        super();
    }
    public Itinerario(String poseedor, String temporada, String nombre, int nota){
        this.poseedor = poseedor;
        this.temporada = temporada;
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNota() {
        return nota;
    }

    public String getPoseedor() {
        return poseedor;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setPoseedor(String poseedor) {
        this.poseedor = poseedor;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }
}
