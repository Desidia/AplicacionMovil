package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 23-05-2017.
 */

public class Opinion {
    private float nota;
    private String comentario,usuario,lugar,comuna,direccion;

    public Opinion() {
        super();
    }

    public Opinion(float nota, String comentario, String usuario, String lugar, String comuna, String direccion) {
        this.nota = nota;
        this.comentario = comentario;
        this.usuario = usuario;
        this.lugar = lugar;
        this.comuna = comuna;
        this.direccion = direccion;
    }

    public float getNota() {
        return nota;
    }

    public void setNota(float nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
