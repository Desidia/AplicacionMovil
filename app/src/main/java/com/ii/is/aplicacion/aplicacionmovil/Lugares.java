package com.ii.is.aplicacion.aplicacionmovil;

/**
 * Created by diego on 25-04-2017.
 */

public class Lugares {
    public int icon;
    public String title,direccion,descripcion,imagen;
    public int nota;

    public Lugares(int n,String s,String l, String d,String m,int a){
        super();
        icon = n;
        title = s;
        direccion = l;
        descripcion = d;
        imagen = m;
        nota = a;
    }
}
