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
    private String nombre,contraseña,query,rut,nombrelugar,contacto,ubicacion,disponibilidad,actividad,comuna,comentario,Nombre_Itinerario,Nombre_Actividad;
    private Login login;
    private int tipo;
    private int estado;
    private String ejemplo,categoria,titulo,usuario;
    private boolean conectado = false,entro = false;
    private Principal principal;
    private DetalleActivity Detalle;
    private Lugar agregar;
    private Empresario_vista Empresario;
    private CrearPropiedad CrearPro;
    private DetalleItinerario detalleItinerario;
    private CrearItinerario crearItinerario;
    ResultSet rs;
    public ControlBase(DetalleItinerario detalle){
        c = null;
        stmt = null;
        detalleItinerario  = detalle;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(CrearPropiedad Crearp){
        c = null;
        stmt = null;
        this.CrearPro  = Crearp;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(CrearItinerario Crearp){
        c = null;
        stmt = null;
        this.crearItinerario  = Crearp;
        estado = -1;
        ejemplo = "";
    }
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
    public void setNombre_Actividad(String c){
        Nombre_Actividad = c;
    }
    public void setTitulo(String c){this.titulo = c;}
    public void setCategoria(String c){
        this.categoria = c;
    }
    public void setNombrelugar(String c){
        this.nombrelugar = c;
    }
    public void setUbicacion(String c){
        this.ubicacion = c;
    }
    public void setDisponibilidad(String c){
        this.disponibilidad = c;
    }
    public void setActividad(String c){
        this.actividad = c;
    }
    public void setComuna(String c){
        this.comuna = c;
    }
    public void setComentario(String c){
        this.comentario = c;
    }
    public void setContacto(String c){
        this.contacto = c;
    }
    public void setNombre(String c){
        this.titulo = c;
    }
    public void setUsuario(String c){this.usuario = c; }
    public void setRut(String c){this.rut = c; }

    public void setNombre_Itinerario(String nombre_Itinerario) {
        Nombre_Itinerario = nombre_Itinerario;
    }

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
                            agregar.setDetalle(rs.getString("comentario"));
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
                    case 7:
                        query = "SELECT DISTINCT U.tipo as tipo  FROM gratificante.lugar as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            CrearPro.AgregarSet(rs.getString("tipo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 8:
                        query = "SELECT DISTINCT U.comuna as comuna  FROM gratificante.lugar as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            CrearPro.AgregarC(rs.getString("comuna"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 9:
                        Log.e("Redirect",rut);
                        Log.e("Redirect",comuna);
                        Log.e("Redirect",actividad);
                        Log.e("redirect",nombrelugar);

                        query = "insert into gratificante.lugar(nombre,contacto,ubicacion,disponibilidad,tipo,rutpropietario,comuna,comentario) values('"+nombrelugar+"','"
                                +contacto+"','"+ubicacion+"','"+disponibilidad+"','"+actividad+"','"+rut+"','"+comuna+"','"+comentario+"');";

                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        stmt.executeUpdate(query);
                        stmt.close();
                        c.commit();
                        c.close();
                        break;

                    case 10:
                        Log.e("Redirect",titulo);
                        Log.e("Redirect",contacto);
                        Log.e("Redirect",ubicacion);
                        Log.e("redirect",nombrelugar);
                        Log.e("redirect",disponibilidad);
                        Log.e("redirect",titulo);
                        query = "UPDATE gratificante.lugar SET contacto = '"+contacto+"', ubicacion = '"+ubicacion+"', disponibilidad = '"+disponibilidad+
                                "'  where nombre = '"+ titulo+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        stmt.executeUpdate(query);
                        stmt.close();
                        c.commit();
                        c.close();
                        break;
                    case 11:
                       query = "SELECT U.temporada as temporada,U.puntaje as puntaje, U.id_itinerario as id_itinerario, U.poseedor as poseedor"
                                + "  FROM gratificante.itinerario as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        Log.e("redirect","intentare agregar en itinerario");
                        while (rs.next()) {
                            Itinerario agregar = new Itinerario(rs.getString("poseedor"),rs.getString("temporada"),rs.getString("id_itinerario"),rs.getInt("puntaje"));
                            Log.e("redirect",agregar.getNombre());
                            principal.agregarItinerario(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 12:
                        query = "SELECT U.posicion as posicion,U.nombre_actividad as nombre_actividad, U.tipo_actividad as tipo_actividad, U.lugar_actividad as lugar_actividad,U.ubicacion_actividad as ubicacion_actividad, U.comuna_actividad as comuna_actividad,U.itinerario as itinerario"
                                + "  FROM gratificante.lista as U WHERE itinerario = '"+Nombre_Itinerario +"';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query)   ;
                        Log.e("redirect","intentare agregar en lista itinerario");
                        while (rs.next()) {

                            Lista agregar = new Lista(rs.getString("nombre_actividad"),rs.getString("tipo_actividad"),rs.getString("lugar_actividad"),rs.getString("ubicacion_actividad"),rs.getString("comuna_actividad"),rs.getInt("posicion"));
                            detalleItinerario.agregarlista(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 13:
                        Log.e("redirect", "Entre caso 13");
                        query = "SELECT DISTINCT U.nombre as nombre  FROM gratificante.actividad as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            crearItinerario.AgregarSet1(rs.getString("nombre"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 14:
                        Log.e("redirect", "Entre caso 13");
                        query = "SELECT DISTINCT U.nombre_lugar as nombre_lugar,U.nombre as nombre  FROM gratificante.actividad as U WHERE nombre = '"+ Nombre_Actividad+"';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            crearItinerario.AgregarSet2(rs.getString("nombre_lugar"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 15:
                        query = "SELECT U.nombre as nombre,U.tipo as tipo, U.comuna as comuna, U.direccion as direccion,U.nombre_lugar as nombre_lugar"
                                + "  FROM gratificante.actividad as U WHERE nombre = '"+Nombre_Actividad +"' AND comuna = '" + comuna+ "' AND nombre_lugar = '"+ ubicacion+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        Log.e("redirect","intentare agregar en lista itinerario 2");
                        while (rs.next()) {
                            Lista agregar = new Lista(rs.getString("nombre_lugar"),rs.getString("tipo"),rs.getString("nombre"),rs.getString("direccion"),rs.getString("comuna"),0);
                            crearItinerario.agregarlista(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 16:
                        Log.e("redirect","ejecutare query 16");
                        query = "SELECT U.comuna as comuna FROM gratificante.actividad as U WHERE nombre = '"+Nombre_Actividad + "' AND nombre_lugar = '"+ ubicacion+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                         c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        Log.e("redirect","desplegare 1");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        Log.e("redirect","desplegare 3");
                        Log.e("redirect","intentare agregar en lista itinerario 3");
                        while (rs.next()) {
                            comuna = rs.getString("comuna");
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
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
            Detalle.setDatos(agregar.getDisponibilidad(),agregar.getcomuna(),agregar.getUbicacion(),agregar.getContacto(),agregar.getDetalle());
            break;
        case 6:
            Empresario.ordenar();
            Log.e("redirect","desplegare");
            Empresario.desplegar();
            break;
        case 7:
            CrearPro.actualizar();
            break;
        case 8:
            CrearPro.actualizarC();
            break;
        case 11:
            principal.desplegar2();
            break;
        case  12:
            detalleItinerario.desplegar();
            break;
        case 13:
            crearItinerario.actualizar1();
            break;
        case 14:
            Log.e("redirect","termine 14");
            crearItinerario.actualizar2();
            break;
        case 15:
            crearItinerario.actualiza();
            break;
        case 16:
            crearItinerario.setComuna(comuna);
            crearItinerario.ejecutarllenado();
            break;
        default:
            break;
    }
    }
    public void setTipo(int x){
        tipo = x;
    }
}




