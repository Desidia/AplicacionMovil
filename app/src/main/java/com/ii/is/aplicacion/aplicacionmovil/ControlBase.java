package com.ii.is.aplicacion.aplicacionmovil;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class ControlBase extends AsyncTask<Void, Void, Void> {
    private Connection c;
    private Statement stmt;
    private String nombre,contraseña,query,rut;
    private Login login;
    private int tipo;
    private int estado;
    private String ejemplo,categoria,titulo,usuario;
    private boolean conectado = false,entro = false;
    private Principal principal;
    private DetalleActivity Detalle;
    private Lugar agregar;
    private Empresario_vista Empresario;
    ResultSet rs;
    public ControlBase(Empresario_vista empresario){
        c = null;
        stmt = null;
        this.Empresario  = empresario;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(Login log){
        c = null;
        stmt = null;
        this.login  = log;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(Principal principal){
        c = null;
        stmt = null;
        this.principal  = principal;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(DetalleActivity detalle){
        c = null;
        stmt = null;
        this.Detalle  = detalle;
        estado = -1;
        ejemplo = "";
    }
    public void setCategoria(String c){
        this.categoria = c;
    }
    public void setNombre(String c){
        this.titulo = c;
    }
    public void setUsuario(String c){this.usuario = c; }
    public void ConectarBaseDeDatos() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc","UbdIc", "udb2016c");
            c.setAutoCommit(false);
            conectado = true;
            c.close();

            conectado = true;
        } catch (ClassNotFoundException | SQLException e) {
            Log.e("redirect", "Falleeeeeeeeeeeee");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            conectado = false;
        }
    }

    public void ejecutar() {
        Log.e("redirect","execute");
        execute();
    }
    @Override
    protected Void doInBackground(Void ... params) {
        Log.e("redirect","llegue al try");
        try {
            this.ConectarBaseDeDatos();
            Log.e("redirect","conecte?");
            if (conectado) {
                Log.e("redirect","llegue al switch");
                switch (tipo) {
                    case 1:
                         nombre = login.getNombreUser(); contraseña = login.getContrasenaUser();
                         query = "SELECT U.usuario as usuario,U.contraseña as contraseña FROM gratificante.turista as U"
                                + " WHERE U.usuario  = '" + nombre + "' AND U.contraseña = '" + contraseña + "';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc", "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        entro = false;
                        while (rs.next()) {
                            entro = true;
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 2:
                        nombre =login.getNombreUser() ;contraseña = login.getContrasenaUser();rut = login.getturismorut();
                        query = "SELECT U.nombre as nombre,U.contraseña as contraseña,U.rut as rut FROM gratificante.empresario as U"
                                + " WHERE U.rut  = '" + nombre  +"' AND U.contraseña = '" + contraseña + "';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc", "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        login.setturista();
                        entro = false;
                        while (rs.next()){
                            login.setturista(rs.getString("nombre"), rs.getString("contraseña"),rs.getString("rut"));
                            entro = true;
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 3:
                        Log.e("redirect", "Entre caso 3");
                        query = "SELECT DISTINCT U.tipo as tipo  FROM gratificante.lugar as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            principal.AgregarSet(rs.getString("tipo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 4:
                        //Log.e("redirect",categoria);
                        query = "SELECT U.tipo as tipo,U.nombre as nombre, U.ubicacion as ubicacion, U.contacto as contacto,U.comuna as comuna,U.disponibilidad as disponibilidad,U.rutpropietario as rutpropietario,U.promedio_lugar as promedio_lugar"
                            + "  FROM gratificante.lugar as U"
                            + " WHERE U.tipo = '" +categoria  +"';" ;
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Log.e("redirect","entre 1");
                            Lugar agregar = new Lugar();
                            agregar.setNombre(rs.getString("nombre"));
                            agregar.setContacto(rs.getString("contacto"));
                            agregar.setUbicacion(rs.getString("ubicacion"));
                            agregar.setDisponibilidad(rs.getString("disponibilidad"));
                            agregar.setTipo(rs.getString("tipo"));
                            agregar.setrutpropietario(rs.getString("rutpropietario"));
                            agregar.setcomuna(rs.getString("comuna"));
                            agregar.setpromediolugar(rs.getInt("promedio_lugar"));
                            principal.agregarServicio(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 5:
                        Log.e("redirect","consultare?????");
                        query = "SELECT U.tipo as tipo,U.nombre as nombre, U.ubicacion as ubicacion, U.contacto as contacto,U.comuna as comuna,U.disponibilidad as disponibilidad,U.rutpropietario as rutpropietario,U.promedio_lugar as promedio_lugar"
                                + "  FROM gratificante.lugar as U"
                                + " WHERE U.nombre = '"+ titulo+"';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        Log.e("redirect","entrare?????");
                        while (rs.next()) {
                            Log.e("redirect","entreeeeeeeeeeeeeeeeee");
                            agregar = new Lugar();
                            agregar.setNombre(rs.getString("nombre"));
                            agregar.setContacto(rs.getString("contacto"));
                            Log.e("redirect",rs.getString("nombre"));
                            agregar.setUbicacion(rs.getString("ubicacion"));
                            agregar.setDisponibilidad(rs.getString("disponibilidad"));
                            agregar.setTipo(rs.getString("tipo"));
                            agregar.setrutpropietario(rs.getString("rutpropietario"));
                            agregar.setcomuna(rs.getString("comuna"));
                            agregar.setpromediolugar(rs.getInt("promedio_lugar"));
                        }
                        Log.e("redirect","termine");
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 6:
                        query = "SELECT U.tipo as tipo,U.nombre as nombre, U.ubicacion as ubicacion, U.contacto as contacto,U.comuna as comuna,U.disponibilidad as disponibilidad,U.rutpropietario as rutpropietario,U.promedio_lugar as promedio_lugar"
                                + "  FROM gratificante.lugar as U"
                                + " WHERE U.rutpropietario = '" +usuario  +"';" ;
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        Log.e("redirect",usuario);;
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Log.e("redirect","entre 2");
                            Lugar agregar = new Lugar();
                            agregar.setNombre(rs.getString("nombre"));
                            agregar.setContacto(rs.getString("contacto"));
                            agregar.setUbicacion(rs.getString("ubicacion"));
                            agregar.setDisponibilidad(rs.getString("disponibilidad"));
                            agregar.setTipo(rs.getString("tipo"));
                            agregar.setrutpropietario(rs.getString("rutpropietario"));
                            agregar.setcomuna(rs.getString("comuna"));
                            agregar.setpromediolugar(rs.getInt("promedio_lugar"));
                            Empresario.agregarServicio(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        Log.e("redirect","termine");
                        break;
                    default:
                        Log.e("redirect","llegue al default");
                        c.setAutoCommit(false);
                        c.close();
                        break;
                }
            }
            } catch(SQLException e){
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                estado = 0;
            }
        return null;
    }
    protected void onPostExecute(Void result) {
    switch (tipo){
        case 1:
            login.setConectado(entro);
            login.comprobarConexion();
            break;
        case 2:
            login.setConectado(entro);
            login.comprobarConexion();
            break;
        case 3:
            principal.actualizar();
            break;
        case 4:
            principal.ordenar();
            principal.desplegar();
            break;
        case 5:
            Detalle.setDatos(agregar.getDisponibilidad(),agregar.getcomuna(),agregar.getUbicacion(),agregar.getContacto());
            break;
        case 6:
            Empresario.ordenar();
            Log.e("redirect","desplegare");
            Empresario.desplegar();
            break;
        default:
            break;
    }
    }
    public void setTipo(int x){
        tipo = x;
    }
}




