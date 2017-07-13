package com.ii.is.aplicacion.aplicacionmovil;

import android.os.AsyncTask;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;


public class ControlBase extends AsyncTask<Void, Void, Void> {
    private Connection c;
    private Statement stmt;
    private String  nombrecito,lugar,nombre,direccion,contraseña,query,rut,nombrelugar,contacto,ubicacion,disponibilidad,actividad,comuna,comentario,Nombre_Itinerario,Nombre_Actividad,temporada,id,poseedor,nombre_actividad,tipo_actividad,lugar_actividad,ubicacion_actividad,comuna_actividad,itinerario;
    private Login login;
    private int tipo,posicion,nota;
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
    private  ModificarDetalle modificardetalle;
    ResultSet rs;
    private Vector <Lista> mylista;
    private Vector <String> tipos = new Vector<String>();
    private  Vector notitas = new Vector();
    private Servicios servicio;
    private Itinerarios itinerariosFragment;
    private ServiciosFragment Sf;
    private MapaComuna mapaComuna;
    private MapaItinerarios mapaItinerarios;
    private Mapa mapa;
    public String getNombrecito() {
        return nombrecito;
    }

    public void setNombrecito(String nombrecito) {
        this.nombrecito = nombrecito;
    }

    public ModificarDetalle getModificardetalle() {
        return modificardetalle;
    }

    public void setModificardetalle(ModificarDetalle modificardetalle) {
        this.modificardetalle = modificardetalle;
    }

    public Vector<String> getTipos() {
        return tipos;
    }

    public void setTipos(Vector<String> tipos) {
        this.tipos = tipos;
    }

    public Vector getNotitas() {
        return notitas;
    }

    public void setNotitas(Vector notitas) {
        this.notitas = notitas;
    }

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    public Itinerarios getItinerariosFragment() {
        return itinerariosFragment;
    }

    public void setItinerariosFragment(Itinerarios itinerariosFragment) {
        this.itinerariosFragment = itinerariosFragment;
    }

    public MapaComuna getMapaComuna() {
        return mapaComuna;
    }

    public void setMapaComuna(MapaComuna mapaComuna) {
        this.mapaComuna = mapaComuna;
    }

    public MapaItinerarios getMapaItinerarios() {
        return mapaItinerarios;
    }

    public void setMapaItinerarios(MapaItinerarios mapaItinerarios) {
        this.mapaItinerarios = mapaItinerarios;
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void setMapa(Mapa mapa) {
        this.mapa = mapa;
    }

    public ServiciosFragment getSf() {
        return Sf;
    }

    public void setSf(ServiciosFragment sf) {
        Sf = sf;
    }

    public ControlBase(ServiciosFragment detalle){
        c = null;
        stmt = null;
        Sf  = detalle;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(MapaComuna detalle){
        c = null;
        stmt = null;
        mapaComuna  = detalle;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(Itinerarios detalle){
        c = null;
        stmt = null;
        itinerariosFragment  = detalle;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(Servicios detalle){
        c = null;
        stmt = null;
        servicio  = detalle;
        estado = -1;
        ejemplo = "";
    }
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
    public ControlBase(ModificarDetalle md){
        c = null;
        stmt = null;
        this.modificardetalle  = md;
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
    public ControlBase(Mapa log){
        c = null;
        stmt = null;
        this.mapa  = log;
        estado = -1;
        ejemplo = "";
    }
    public ControlBase(MapaItinerarios log){
        c = null;
        stmt = null;
        this.mapaItinerarios  = log;
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNota() {
        return nota;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
    public  void addNotita(int t){notitas.add(t);}
    public void addTipos(String s){
        tipos.add(s);
    }
    public void setTemporada(String c){temporada = c;}
    public  void setId(String c){id = c;}
    public void setPoseedor(String c){poseedor = c;}
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

    public Vector<Lista> getMylista() {
        return mylista;
    }

    public void setMylista(Vector<Lista> mylista) {
        this.mylista = mylista;
    }
    public Connection getC() {
        return c;
    }

    public Statement getStmt() {
        return stmt;
    }

    public String getActividad() {
        return actividad;
    }

    public String getComentario() {
        return comentario;
    }

    public String getComuna() {
        return comuna;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public String getNombre_Actividad() {
        return Nombre_Actividad;
    }

    public String getNombrelugar() {
        return nombrelugar;
    }

    public String getQuery() {
        return query;
    }

    public String getRut() {
        return rut;
    }

    public void setC(Connection c) {
        this.c = c;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getId() {
        return id;
    }

    public String getNombre_Itinerario() {
        return Nombre_Itinerario;
    }

    public String getTemporada() {
        return temporada;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }

    public boolean isConectado() {
        return conectado;
    }

    public boolean isEntro() {
        return entro;
    }

    public CrearItinerario getCrearItinerario() {
        return crearItinerario;
    }

    public CrearPropiedad getCrearPro() {
        return CrearPro;
    }

    public String getPoseedor() {
        return poseedor;
    }

    public DetalleActivity getDetalle() {
        return Detalle;
    }

    public DetalleItinerario getDetalleItinerario() {
        return detalleItinerario;
    }

    public Empresario_vista getEmpresario() {
        return Empresario;
    }

    public int getEstado() {
        return estado;
    }

    public int getPosicion() {
        return posicion;
    }

    public int getTipo() {
        return tipo;
    }

    public Login getLogin() {
        return login;
    }

    public void setCrearItinerario(CrearItinerario crearItinerario) {
        this.crearItinerario = crearItinerario;
    }

    public Lugar getAgregar() {
        return agregar;
    }

    public String getTipo_actividad() {
        return tipo_actividad;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public ResultSet getRs() {
        return rs;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getComuna_actividad() {
        return comuna_actividad;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public String getItinerario() {
        return itinerario;
    }

    public String getLugar_actividad() {
        return lugar_actividad;
    }

    public void setLugar_actividad(String lugar_actividad) {
        this.lugar_actividad = lugar_actividad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public String getUbicacion_actividad() {
        return ubicacion_actividad;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setAgregar(Lugar agregar) {
        this.agregar = agregar;
    }

    public void setComuna_actividad(String comuna_actividad) {
        this.comuna_actividad = comuna_actividad;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
    }

    public void setCrearPro(CrearPropiedad crearPro) {
        CrearPro = crearPro;
    }

    public void setDetalle(DetalleActivity detalle) {
        Detalle = detalle;
    }

    public void setDetalleItinerario(DetalleItinerario detalleItinerario) {
        this.detalleItinerario = detalleItinerario;
    }

    public void setEjemplo(String ejemplo) {
        this.ejemplo = ejemplo;
    }

    public void setEmpresario(Empresario_vista empresario) {
        Empresario = empresario;
    }

    public void setEntro(boolean entro) {
        this.entro = entro;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public void setItinerario(String itinerario) {
        this.itinerario = itinerario;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public void setTipo_actividad(String tipo_actividad) {
        this.tipo_actividad = tipo_actividad;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    public void setUbicacion_actividad(String ubicacion_actividad) {
        this.ubicacion_actividad = ubicacion_actividad;
    }
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
                         query = "SELECT U.usuario as usuario,U.contraseña as contraseña FROM gratificante2.turista as U"
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
                        query = "SELECT U.nombre as nombre,U.contraseña as contraseña,U.rut as rut FROM gratificante2.empresario as U"
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
                        query = "SELECT DISTINCT U.tipo as tipo  FROM gratificante2.lugar as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            servicio.AgregarSet(rs.getString("tipo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 4:
                        Log.e("redirect",nombrecito);
                        Log.e("redirect",comuna);
                        Log.e("redirect",tipo_actividad);
                        Log.e("redirect",temporada);
                        query = "SELECT  DISTINCT l1.nombre, l1.contacto, l1.ubicacion, l1.disponibilidad, l1.tipo, l1.rutpropietario, l1.comuna, l1.promedio_lugar, l1.comentario,l1.imagen_frontal,l1.imagen_extra,l1.imagen_interior  " +
                                "FROM gratificante2.lugar as l1 , gratificante2.servicio as s1 " +
                                "WHERE l1.tipo Like('"+categoria+"') and l1.nombre Like('%"+nombrecito+"%') and l1.comuna Like('"+comuna+"')  and l1.disponibilidad Like('"+temporada+"')" +
                                "and s1.lugar = l1.nombre and  s1.tipo Like('"+tipo_actividad+"')";
                        if(nota > 0) query += " AND l1.promedio_lugar >= "+ nota;
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        Log.e("redirect",query);
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
                            agregar.setImagen1(rs.getString("imagen_frontal"));
                            if(rs.wasNull()) agregar.setImagen1("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            agregar.setImagen2(rs.getString("imagen_interior"));
                            if(rs.wasNull()) agregar.setImagen2("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            agregar.setImagen3(rs.getString("imagen_extra"));
                            if(rs.wasNull()) agregar.setImagen3("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            servicio.agregarServicio(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 5:
                        Log.e("redirect","consultare?????");
                        query = "SELECT U.tipo as tipo,U.nombre as nombre, U.ubicacion as ubicacion, U.contacto as contacto,U.comuna as comuna,U.disponibilidad as disponibilidad,U.rutpropietario as rutpropietario,U.promedio_lugar as promedio_lugar,U.comentario as comentario,U.imagen_frontal as imagen_frontal, U.imagen_interior as imagen_interior, U.imagen_extra as imagen_extra"
                                + "  FROM gratificante2.lugar as U"
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
                            if(comentario != null)Log.e("redirect",rs.getString("comentario"));
                            else comentario = "";
                            agregar.setUbicacion(rs.getString("ubicacion"));
                            agregar.setDisponibilidad(rs.getString("disponibilidad"));
                            agregar.setTipo(rs.getString("tipo"));
                            agregar.setrutpropietario(rs.getString("rutpropietario"));
                            agregar.setcomuna(rs.getString("comuna"));
                            agregar.setpromediolugar(rs.getInt("promedio_lugar"));
                            agregar.setDetalle(rs.getString("comentario"));
                            agregar.setImagen1(rs.getString("imagen_frontal"));
                            if(rs.wasNull()) agregar.setImagen1("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            agregar.setImagen2(rs.getString("imagen_interior"));
                            if(rs.wasNull()) agregar.setImagen2("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            agregar.setImagen3(rs.getString("imagen_extra"));
                            if(rs.wasNull()) agregar.setImagen3("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                        }
                        Log.e("redirect","termine");
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 6:
                        query = "SELECT U.tipo as tipo,U.nombre as nombre, U.ubicacion as ubicacion, U.contacto as contacto,U.comuna as comuna,U.disponibilidad as disponibilidad,U.rutpropietario as rutpropietario,U.promedio_lugar as promedio_lugar,U.imagen_frontal as imagen_frontal, U.imagen_interior as imagen_interior, U.imagen_extra as imagen_extra"
                                + "  FROM gratificante2.lugar as U"
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
                            agregar.setImagen1(rs.getString("imagen_frontal"));
                            if(rs.wasNull()) agregar.setImagen1("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            agregar.setImagen2(rs.getString("imagen_interior"));
                            if(rs.wasNull()) agregar.setImagen2("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            agregar.setImagen3(rs.getString("imagen_extra"));
                            if(rs.wasNull()) agregar.setImagen3("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ2sxSYX-IIg2IIh3Ag4_W72fg4c9LHy612d5GqRvH6zqMpu84bYQ");
                            Sf.agregarServicio(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        Log.e("redirect","termine");
                        break;
                    case 7:
                        query = "SELECT DISTINCT U.tipo as tipo  FROM gratificante2.lugar as U;";
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
                        query = "SELECT DISTINCT U.comuna as comuna  FROM gratificante2.lugar as U;";
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

                        query = "insert into gratificante2.lugar(nombre,contacto,ubicacion,disponibilidad,tipo,rutpropietario,comuna,comentario) values('"+nombrelugar+"','"
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
                        query = "UPDATE gratificante2.lugar SET contacto = '"+contacto+"', ubicacion = '"+ubicacion+"', disponibilidad = '"+disponibilidad+
                                "', nombre = '" +nombrelugar +"', comentario = '"+comentario+"'  where nombre = '"+ titulo+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        stmt.executeUpdate(query);
                        Log.e("redirect",titulo);
                        stmt.close();
                        c.commit();
                        c.close();
                        break;
                    case 11:
                       query = "SELECT U.temporada as temporada,U.puntaje as puntaje, U.id_itinerario as id_itinerario, U.poseedor as poseedor"
                                + "  FROM gratificante2.itinerario as U;";
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
                            itinerariosFragment.agregarItinerario(agregar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 12:
                        query = "SELECT U.posicion as posicion,U.nombre_actividad as nombre_actividad, U.tipo_actividad as tipo_actividad, U.lugar_actividad as lugar_actividad,U.ubicacion_actividad as ubicacion_actividad, U.comuna_actividad as comuna_actividad,U.itinerario as itinerario"
                                + "  FROM gratificante2.lista as U WHERE itinerario = '"+Nombre_Itinerario +"';";
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
                        query = "SELECT DISTINCT U.nombre as nombre  FROM gratificante2.actividad as U;";
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
                        query = "SELECT DISTINCT U.nombre_lugar as nombre_lugar,U.nombre as nombre  FROM gratificante2.actividad as U WHERE nombre = '"+ Nombre_Actividad+"';";
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
                                + "  FROM gratificante2.actividad as U WHERE nombre = '"+Nombre_Actividad +"' AND comuna = '" + comuna+ "' AND nombre_lugar = '"+ ubicacion+ "';";
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
                        query = "SELECT U.comuna as comuna FROM gratificante2.actividad as U WHERE nombre = '"+Nombre_Actividad + "' AND nombre_lugar = '"+ ubicacion+ "';";
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
                    case 17:
                        query = "insert into gratificante2.itinerario(temporada,puntaje,id_itinerario,poseedor) values('"+temporada+"','"
                                +0+"','"+id+"','"+poseedor+"');";

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
                    case 18:
                        Log.e("redirect","ejecutare query 18");
                        for(int i = 0; i < mylista.size(); i++) {
                        query = "insert into gratificante2.lista(posicion,nombre_actividad,tipo_actividad,lugar_actividad,ubicacion_actividad,comuna_actividad,itinerario) values('" + mylista.elementAt(i).getPos() + "','"
                                + mylista.elementAt(i).getLugar_actividad() + "','" + mylista.elementAt(i).getTipo_actividad() + "','" + mylista.elementAt(i).getNombre_actividad() + "','" + mylista.elementAt(i).getUbicacion_actividad() + "','" + mylista.elementAt(i).getComuna_actividad() + "','" + mylista.elementAt(i).getItinerario() + "');";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                            Log.e("redirect","tratare de insertar");
                        stmt.executeUpdate(query);
                            Log.e("redirect","inserte");
                        stmt.close();
                        c.commit();
                        c.close();
                    }
                        Log.e("redirect","terminer ejecutar query 18");
                        break;
                    case 19:
                        Log.e("redirect","ejecutare query 19");
                        Log.e("redirect",titulo);
                        query = "SELECT U.tipo as tipo FROM gratificante2.servicio as U WHERE lugar = '"+titulo+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Log.e("redirect",rs.getString("tipo"));
                            Detalle.agregarSN(rs.getString("tipo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 20:
                        Log.e("redirect","ejecutare query 20");
                        query = "SELECT *  FROM gratificante2.evaluacion as U WHERE U.lugar = '"+titulo+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            evaluacion eval = new evaluacion((double)rs.getInt("nota"),rs.getString("comentario"),rs.getString("tipo_servicio"),rs.getString("lugar"),rs.getString("comuna"),rs.getString("usuario"),rs.getString("direccion"));
                            Detalle.agregarEval(eval);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 21:
                        Log.e("redirect","ejecutare query 21");
                        query = "SELECT *  FROM gratificante2.opinion as U WHERE U.lugar = '"+titulo+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Opinion eval = new Opinion((float)rs.getInt("nota"),rs.getString("comentario"),rs.getString("usuario"),rs.getString("lugar"),rs.getString("comuna"),rs.getString("direccion"));
                            Detalle.agregaOpinion(eval);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 22:
                        query = "insert into gratificante2.opinion(nota,comentario,usuario,lugar,comuna,direccion) values('" + nota+ "','"
                                + comentario+ "','" + usuario + "','" + lugar_actividad+ "','" + comuna + "','" + direccion+ "');";
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
                    case 23:
                        Log.e("redirect","consultare?????");
                        query = "SELECT U.tipo as tipo,U.nombre as nombre, U.ubicacion as ubicacion, U.contacto as contacto,U.comuna as comuna,U.disponibilidad as disponibilidad,U.rutpropietario as rutpropietario,U.promedio_lugar as promedio_lugar,U.comentario as comentario"
                                + "  FROM gratificante2.lugar as U"
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
                            if(comentario != null)Log.e("redirect",rs.getString("comentario"));
                            else comentario = "";
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
                    case 24:
                        Log.e("Redirect",titulo);
                        Log.e("Redirect",contacto);
                        Log.e("Redirect",ubicacion);
                        Log.e("redirect",nombrelugar);
                        Log.e("redirect",disponibilidad);
                        Log.e("redirect",titulo);
                        query = "UPDATE gratificante2.lugar SET contacto = '"+contacto+"', ubicacion = '"+ubicacion+"', disponibilidad = '"+disponibilidad+
                                "', nombre = '" +nombrelugar +"', comentario = '"+comentario+"'  where nombre = '"+ titulo+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        stmt.executeUpdate(query);
                        Log.e("redirect",titulo);
                        stmt.close();
                        c.commit();
                        c.close();
                        break;
                    case 25:
                        for(int i = 0; i < notitas.size();i++) {
                            int agregarputanota= (int) notitas.elementAt(i);
                            query = "insert into gratificante2.evaluacion(nota,comentario,usuario,lugar,comuna,direccion,tipo_servicio) values('" + agregarputanota + "','"
                                    + comentario + "','" + usuario + "','" + lugar_actividad + "','" + comuna + "','" + direccion + "','" + tipos.elementAt(i) + "');";
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
                        }
                        break;
                    case 26:
                        query = "SELECT DISTINCT U.tipo as tipo  FROM gratificante2.servicio as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            CrearPro.agregarS(rs.getString("tipo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 27:
                        query = "insert into gratificante2.servicio(tipo,acceso,lugar,direccion,comuna) values('"+tipo_actividad+"','Privado','"+lugar+"','"+direccion + "','"+comuna+"');";

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
                    case 28:
                        Log.e("redirect", "Entre caso 28");
                        query = "SELECT DISTINCT U.tipo as tipo  FROM gratificante2.servicio as U;";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            servicio.AgregarSet2(rs.getString("tipo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 29:
                        Log.e("redirect","ejecutare query 29");
                        query = "SELECT DISTINCT u.nombre as nombre,u.latitud as latitud, u.longitud as longitud, u.comuna as comuna  FROM gratificante2.lugar as U WHERE U.comuna = '"+comuna+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Lugar lugar = new Lugar();
                            lugar.setNombre(rs.getString("nombre"));
                            lugar.setLat(rs.getDouble("latitud"));
                            lugar.setLng(rs.getDouble("longitud"));
                            mapaComuna.add(lugar);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 30:
                        Log.e("redirect","ejecutare query 30");
                        query = "SELECT DISTINCT u.nombre as nombre,u.latitud as latitud, u.longitud as longitud, u.comuna as comuna  FROM gratificante2.lugar as U WHERE U.nombre = '"+comuna+ "';";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Log.e("redirect","Pille el  lugar");
                            Log.e("redirect","Su latitud : "+rs.getDouble("latitud"));
                            mapa.setLat(rs.getDouble("latitud"));
                            mapa.setLng(rs.getDouble("longitud"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 31:
                        Log.e("redirect","ejecutare query 31");
                        Log.e("redirect",comuna);
                        query = "Select id_itinerario, nombre, latitud, longitud, posicion " +
                                "from gratificante2.lugar, gratificante2.lista, gratificante2.itinerario " +
                                "where lista.itinerario = id_itinerario and id_itinerario Like('%"+comuna+"%') and lugar_actividad = lugar.nombre " +
                                "order by id_itinerario, posicion";
                        c = DriverManager
                                .getConnection("jdbc:postgresql://plop.inf.udec.cl/BDIc",
                                        "UbdIc", "udb2016c");
                        c.setAutoCommit(false);
                        System.out.println("Opened database successfully");
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            mapaItinerarios.agregarLatitud(rs.getDouble("latitud"));
                            mapaItinerarios.agregarLng(rs.getDouble("longitud"));
                            mapaItinerarios.agregarNombre(rs.getString("nombre"));
                   //         Log.e("redirect",rs.getString("nombre"));
             //               Log.e("redirect","Su latitud : "+rs.getDouble("latitud"));
               //             mapa.setLat(rs.getDouble("latitud"));
                 //           mapa.setLng(rs.getDouble("longitud"));
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
            servicio.actualizar();
            break;
        case 4:
            servicio.ordenar();
            servicio.desplegar();
            break;
        case 5:
            Detalle.setDatos(agregar.getDisponibilidad(),agregar.getcomuna(),agregar.getUbicacion(),agregar.getContacto(),agregar.getDetalle(),agregar.getpromediolugar(),agregar.getImagen1(),agregar.getImagen2(),agregar.getImagen3());
            break;
        case 6:
            Sf.ordenar();
            Log.e("redirect","desplegare");
            Sf.desplegar();
            break;
        case 7:
            CrearPro.actualizar();
            break;
        case 8:
            CrearPro.actualizarC();
            CrearPro.actualizarT();
            break;
        case 11:
            itinerariosFragment.desplegar2();
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
        case 17:
            crearItinerario.creaactividades();
            break;
        case 18:
            Log.e("redirect","ejecutare confirmar");
            crearItinerario.confirmar();
            break;
        case 19:
            Log.e("redirect","ejecutare evaluaciones");
            Detalle.evaluaciones();
            break;
        case 20:
            Detalle.crearServicio_Nota();
             break;
        case 21:
            Detalle.generarOpiniones();
            break;
        case 22:
            Detalle.crearEvaluacion();
            break;
        case 23:
            modificardetalle.setDatos(agregar.getDisponibilidad(),agregar.getcomuna(),agregar.getUbicacion(),agregar.getContacto(),agregar.getDetalle(),agregar.getpromediolugar());
            modificardetalle.actualizarT();
            modificardetalle.actualizarC();
            break;
        case 24:
            modificardetalle.confirmar();
            break;
        case 25:
            Detalle.iniciarComentarios();
            break;
        case 26:
            CrearPro.actualizarS();
            break;
        case 27:
            CrearPro.confirmar();
            break;
        case 29:
            mapaComuna.agregarpuntos();
            break;
        case 30:
            mapa.ejecutar();
            break;
        case 31:
            mapaItinerarios.comenzar();
            break;
        default:
            break;
    }
    }
    public void setTipo(int x){
        tipo = x;
    }
}




