package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 25-04-2017.
 */

public class Lugar {

    private String nombre,contacto,ubicacion,disponibilidad,tipo,rutpropietario,comuna,Detalle,Imagen1,Imagen2,Imagen3;
    private int promedio_lugar;
    private double lat,lng;
    public Lugar(){

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getImagen1() {
        return Imagen1;
    }

    public void setImagen1(String imagen1) {
        Imagen1 = imagen1;
    }

    public String getImagen2() {
        return Imagen2;
    }

    public void setImagen2(String imagen2) {
        Imagen2 = imagen2;
    }

    public String getImagen3() {
        return Imagen3;
    }

    public void setImagen3(String imagen3) {
        Imagen3 = imagen3;
    }

    public int getPromedio_lugar() {
        return promedio_lugar;
    }

    public void setPromedio_lugar(int promedio_lugar) {
        this.promedio_lugar = promedio_lugar;
    }

    public String getRutpropietario() {
        return rutpropietario;
    }

    public void setRutpropietario(String rutpropietario) {
        this.rutpropietario = rutpropietario;
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

