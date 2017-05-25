package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 23-05-2017.
 */

public class OpinionAgregar {
    private String nombre,comentario;
    private float nota;

    public OpinionAgregar() {
        super();
        nota = 0;
    }

    public OpinionAgregar(String nombre, String comentario, float nota) {
        this.nombre = nombre;
        this.comentario = comentario;
        this.nota = nota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }
}
