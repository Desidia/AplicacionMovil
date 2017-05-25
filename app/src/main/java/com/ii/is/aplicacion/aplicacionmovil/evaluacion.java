package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 23-05-2017.
 */

public class evaluacion {
    private double nota;
    private String comentario, tipo_servicio,lugar,comuna,usuario,direccion;
    public evaluacion(){
        super();
    }
    public  evaluacion(double nota, String comentario, String servicio, String lugar, String comuna, String usuario, String direccion){
    this.nota = nota;
        this.comentario = comentario;
        this.tipo_servicio  =servicio;
        this.lugar = lugar;
        this.comuna = comuna;
        this.usuario = usuario;
        this.direccion = direccion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
