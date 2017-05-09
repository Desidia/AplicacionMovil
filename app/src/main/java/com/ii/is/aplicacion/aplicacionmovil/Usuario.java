package com.ii.is.aplicacion.aplicacionmovil;
public class Usuario {
    private String nombre,contrasena,rut;
    public Usuario(){
        nombre = "";
        rut = "";
        contrasena = "";
    }
    public Usuario(String nombre, String contrasena){
        this.nombre = nombre;
        this.contrasena = contrasena;
        rut = "";
    }
    public Usuario(String nombre, String contrasena,String rut){
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rut = rut;
    }
    public String getnombre(){
        return nombre;
    }
    public String getcontrasena(){
        return contrasena;
    }
    public String getrut(){
        return rut;
    }
    public void setnombre(String g){
        nombre=g;
    }
    public void setcontrasena(String g){
        contrasena = g;
    }
    public void setrut(String g){
        rut = g;
    }

}
