package com.ii.is.aplicacion.aplicacionmovil;

import android.os.AsyncTask;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by diego on 22-11-2017.
 */

public class Controlador extends AsyncTask<Void, Void, Void> {
    private Login inicio;
    private Connection c;
    private Statement stmt;
    private boolean conectado;
    private int tipo,rbd;
    private String query,universidad,carrera,PsuMat,PsuLen,PsuHis,PsuCie;
    private MapaEstablecimientos mapaEstablecimientos;
    private DetalleEstablecimiento DetalleEs;
    private DetalleCursos DetalleCu;
    private DetalleProfesores DetallePro;
    private DetalleIntergracion DetalleInt;
    private ListaUniversidades ListaUni;
    private ListaCarrera ListaCa;
    private DetalleCarrera DetalleCa;
    private DetallePostulacion DetallePos;
    private  ResultSet rs;
    private int total,total2;
    public Controlador(Login inicio){
        c = null;
        stmt = null;
        this.inicio = inicio;
    }
    public Controlador(MapaEstablecimientos mapa){
        c = null;
        stmt = null;
        mapaEstablecimientos = mapa;
    }
    public Controlador(DetalleEstablecimiento detalleEstablecimiento){
        c = null;
        stmt = null;
        DetalleEs = detalleEstablecimiento;
    }
    public Controlador(DetalleCursos detalleEstablecimiento){
        c = null;
        stmt = null;
        DetalleCu = detalleEstablecimiento;
    }
    public Controlador(DetalleProfesores detalleEstablecimiento){
        c = null;
        stmt = null;
        DetallePro = detalleEstablecimiento;
    }
    public Controlador(DetalleIntergracion detalleEstablecimiento){
        c = null;
        stmt = null;
        DetalleInt = detalleEstablecimiento;
    }
    public Controlador(ListaUniversidades detalleEstablecimiento){
        c = null;
        stmt = null;
        ListaUni = detalleEstablecimiento;
    }
    public Controlador(ListaCarrera detalleEstablecimiento){
        c = null;
        stmt = null;
        ListaCa = detalleEstablecimiento;
    }
    public Controlador(DetalleCarrera detalleEstablecimiento){
        c = null;
        stmt = null;
        DetalleCa = detalleEstablecimiento;
    }
    public Controlador(DetallePostulacion detalleEstablecimiento){
        c = null;
        stmt = null;
        DetallePos = detalleEstablecimiento;
    }
    public boolean isConectado() {
        return conectado;
    }
    public void setUniversidad(String s){
        universidad = s;
    }
    public void setCarrera(String s){
        carrera = s;
    }
    public void ConectarBaseDeDatos() {
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017","grupo4_2017", "grupo4_17");
            c.setAutoCommit(false);
            conectado = true;
            c.close();
        } catch (ClassNotFoundException | SQLException e) {
            Log.e("redirect", e.getClass().getName() + ": " + e.getMessage());
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            conectado = false;
        }
    }
    public void ejecutar() {
        Log.e("redirect","execute");
        execute();
    }
    public void setTipo(int edit){
        tipo = edit;
    }
    public void setRbd(int edit){
        rbd = edit;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
        this.ConectarBaseDeDatos();
        if (conectado) {
            Log.e("redirect", "Entre");
                switch (tipo) {
                    case 2:
                        Log.e("redirect", "Entre caso 2");
                       query = "SELECT DISTINCT U.rbd as rbd,U.nombreestablecimiento as nombreestablecimiento,U.estado as estado,U.latitud as latitud,U.longitud as longitud  FROM respuestas.coordenada_corregida_establecimiento as U;";
                      //  query = "SELECT DISTINCT U.rbd as rbd FROM respuestas.informacion_establecimiento as U;";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Establecimiento aux = new Establecimiento();
                            aux.setRbd(rs.getInt("rbd"));
                            aux.setEstado(rs.getString("estado"));
                            aux.setNombre(rs.getString("nombreestablecimiento"));
                            String lng = rs.getString("longitud");
                            String lat = rs.getString("latitud");
                            String lat2 = "",lng2 = "";
                            boolean encontrado = false;
                            for(int i = 0; i < lat.length();i++){
                                if(lat.charAt(i) == '.' && !encontrado){
                                    encontrado = true;
                                    lat2+= lat.charAt(i);
                                }
                                else if (lat.charAt(i) == '.' && encontrado){

                                }
                                else {
                                    lat2+= lat.charAt(i);
                                }
                            }
                            encontrado = false;
                            for(int i = 0; i < lng.length();i++){
                                if(lng.charAt(i) == '.' && !encontrado){
                                    encontrado = true;
                                    lng2+= lng.charAt(i);
                                }
                                else if (lng.charAt(i) == '.' && encontrado){

                                }
                                else {
                                    lng2+= lng.charAt(i);
                                }
                            }

                            Double lng3 = Double.parseDouble(lng2);
                            Double lat3 = Double.parseDouble(lat2);
                            if(lng3 != 0.0 && lng3 < 10.0)lng3*=10.0;
                            if(lat3 == 0);
                            aux.setLng(lng3);
                            aux.setLat(lat3);
                            mapaEstablecimientos.add(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 3:
                        Log.e("redirect", "Entre caso 3");
                        query = "SELECT DISTINCT U.codigo as codigo FROM respuestas.tipo_ensenanza_establecimiento as U WHERE U.rbd = '" + rbd +"';";
                        //  query = "SELECT DISTINCT U.rbd as rbd FROM respuestas.informacion_establecimiento as U;";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            DetalleEs.add(rs.getString("codigo"));
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 4:
                        Log.e("redirect", "Entre caso 4");
                        query = "SELECT DISTINCT U.nombreestablecimiento as nombreestablecimiento,U.grado as grado,U.letra as letra,U.cantidad_alumnos as cantidad_alumnos,U.promedio as promedio,U.asistencia as asistencia " +
                                "FROM respuestas.promedio_asistencia_gradoletra as U " +
                                "WHERE U.rbd = '" + rbd +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Cursos aux = new Cursos();
                            aux.setGrado(rs.getString("grado"));
                            aux.setLetra(rs.getString("letra"));
                            aux.setAlumnos(rs.getInt("cantidad_alumnos"));
                            aux.setPromedio(rs.getDouble("promedio"));
                            aux.setAsistencia(rs.getDouble("asistencia"));
                            DetalleCu.add(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 5:
                        Log.e("redirect", "Entre caso 5");
                        query = "SELECT DISTINCT U.clasificacion as clasificacion,U.run as run,U.nivel as nivel,U.asignatura as asignatura,U.notafinal as notafinal " +
                                "FROM respuestas.evaluacion_de_docentes as U " +
                                "WHERE U.rbd = '" + rbd +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            EvaluacionDocentes aux = new EvaluacionDocentes();
                            aux.setRut(rs.getInt("run"));
                            aux.setNotafinal(rs.getDouble("notafinal"));
                            aux.setClasificacion(rs.getString("clasificacion"));
                            aux.setNivel(rs.getString("nivel"));
                            aux.setAsignatura(rs.getString("asignatura"));
                            DetallePro.addEval(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 6:
                        Log.e("redirect", "Entre caso 6");
                        query = "SELECT DISTINCT U.funcion_principal as funcion_principal,U.cantidad as cantidad,U.nivel as nivel,U.asignatura as asignatura " +
                                "FROM respuestas.docentes_por_area as U " +
                                "WHERE U.rbd = '" + rbd +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            DocentesArea aux = new DocentesArea();
                            aux.setFuncion(rs.getString("funcion_principal"));
                            aux.setCantidad(rs.getInt("cantidad"));
                            aux.setNivel(rs.getString("nivel"));
                            aux.setAsignatura(rs.getString("asignatura"));
                            DetallePro.addArea(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 7:
                        Log.e("redirect", "Entre caso 7");
                        query = "SELECT DISTINCT U.tipo_integracion as tipo_integracion,U.cantidad as cantidad " +
                                "FROM respuestas.cantidad_integracion as U " +
                                "WHERE U.rbd = '" + rbd +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Intergracion aux = new Intergracion();
                            aux.setTipo(rs.getString("tipo_integracion"));
                            aux.setCantidad(rs.getInt("cantidad"));
                            DetalleInt.add(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 8:
                        Log.e("redirect", "Entre caso 8");
                        query = "SELECT distinct U.universidad as universidad " +
                                "FROM respuestas.rbd_por_carrera_y_universidad as U";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            ListaUni.add(rs.getString("universidad"));;
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 9:
                        Log.e("redirect", "Entre caso 9");
                        query = "SELECT distinct U.carrera as carrera " +
                                "FROM respuestas.rbd_por_carrera_y_universidad as U " +
                                "WHERE U.universidad = '"+universidad+"'";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            ListaCa.add(rs.getString("carrera"));;
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 10:
                        Log.e("redirect", "Entre caso 10");
                        query = "SELECT distinct U.nombreestablecimiento as nombreestablecimiento, U.via as via, U.cantidaddealumnos as cantidaddealumnos, U.puntajemin as puntajemin " +
                                "FROM respuestas.rbd_por_carrera_y_universidad as U " +
                                "WHERE U.universidad = '"+universidad+"' AND U.carrera = '"+carrera+"'";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Carrera aux = new Carrera();
                            aux.setNombre(rs.getString("nombreestablecimiento"));
                            aux.setPuntaje(rs.getDouble("puntajemin"));
                            aux.setCantida(rs.getInt("cantidaddealumnos"));
                            aux.setVia(rs.getString("via"));
                            DetalleCa.add(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 11:
                        Log.e("redirect", "Entre caso 12");
                        query = "SELECT distinct U.carrera as carrera, U.via as via, U.cantidaddealumnos as cantidaddealumnos, U.puntajemin as puntajemin " +
                                "FROM respuestas.rbd_por_carrera_y_universidad as U " +
                                "WHERE U.nombreestablecimiento = '"+carrera+"'";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            Carrera aux = new Carrera();
                            aux.setNombre(rs.getString("carrera"));
                            aux.setPuntaje(rs.getDouble("puntajemin"));
                            aux.setCantida(rs.getInt("cantidaddealumnos"));
                            aux.setVia(rs.getString("via"));
                            DetallePos.add(aux);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 12:
                        Log.e("redirect", "Entre caso 12");
                        query = "SELECT U.cantidad as cantidad " +
                                "FROM respuestas.docentes_por_establecimiento as U " +
                                "WHERE U.rbd = '" + rbd +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        total = 0;
                        while (rs.next()) {
                            total +=rs.getInt("cantidad");
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 13:
                        Log.e("redirect", "Entre caso 13");
                        query = "SELECT U.cantidad as cantidad " +
                                "FROM respuestas.asistentes_por_establecimiento as U " +
                                "WHERE U.rbd = '" + rbd +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        total2 = 0;
                        while (rs.next()) {
                            total2 +=rs.getInt("cantidad");
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 14:
                        Log.e("redirect", "Entre caso 14");
                        query = "SELECT U.promedio as promedio " +
                                "FROM respuestas.promedios_psu_proceso_actual as U " +
                                "WHERE U.prueba = 'Matemática' AND U.nombre_establecimiento = '" + carrera +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            PsuMat=String.valueOf(rs.getDouble("promedio"));
                            Log.e("redirect", PsuMat);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 15:
                        Log.e("redirect", "Entre caso 15");
                        query = "SELECT U.promedio as promedio " +
                                "FROM respuestas.promedios_psu_proceso_actual as U " +
                                "WHERE U.prueba = 'Lenguaje' AND U.nombre_establecimiento = '" + carrera +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            PsuLen = (String.valueOf(rs.getDouble("promedio")));
                            Log.e("redirect", PsuLen);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 16:
                        Log.e("redirect", "Entre caso 16");
                        query = "SELECT U.promedio as promedio " +
                                "FROM respuestas.promedios_psu_proceso_actual as U " +
                                "WHERE U.prueba = 'Historia y geografía' AND U.nombre_establecimiento = '" + carrera +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            PsuHis = (String.valueOf(rs.getDouble("promedio")));
                            Log.e("redirect", PsuHis);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    case 17:
                        Log.e("redirect", "Entre caso 17");
                        query = "SELECT U.promedio as promedio " +
                                "FROM respuestas.promedios_psu_proceso_actual as U " +
                                "WHERE U.prueba = 'Ciencias' AND U.nombre_establecimiento = '" + carrera +"';";
                        c = DriverManager.getConnection("jdbc:postgresql://plop.inf.udec.cl/grupo4_2017", "grupo4_2017", "grupo4_17");
                        c.setAutoCommit(false);
                        stmt = c.createStatement();
                        rs = stmt.executeQuery(query);
                        while (rs.next()) {
                            PsuCie = (String.valueOf(rs.getDouble("promedio")));
                            Log.e("redirect", PsuCie);
                        }
                        stmt.close();
                        rs.close();
                        c.commit();
                        c.close();
                        break;
                    default:
                        break;
                }
            }
        } catch(SQLException e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        switch (tipo){
            case 1:
                inicio.iniciar();
                break;
            case 2:
                mapaEstablecimientos.agregarpuntos();
                break;
            case 3:
                DetalleEs.rellenar();
                break;
            case 4:
                DetalleCu.actualizar();
                break;
            case 5:
                DetallePro.Ejecutar6();
                break;
            case 6:
                DetallePro.actualizar();
                break;
            case 7:
                DetalleInt.actualizar();
                break;
            case 8:
                ListaUni.actualiza();
                break;
            case 9:
                ListaCa.actualiza();
                break;
            case 10:
                DetalleCa.actualizar();
                break;
            case 11:
                DetallePos.actualizar();
                break;
            case 12:
                DetallePro.setProfes(String.valueOf(total));
                DetallePro.ejecutaAsis();
                break;
            case 13:
                DetallePro.setAsistentes(String.valueOf(total2));
                break;
            case 14:
                DetallePos.setPsuMate(PsuMat);
                DetallePos.leng();
                break;
            case 15:
                DetallePos.setPsuLeng(PsuLen);
                DetallePos.hist();
                break;
            case 16:
                DetallePos.setPsuHist(PsuHis);
                DetallePos.cien();
                break;
            case 17:
                DetallePos.setPsuCien(PsuCie);
                break;
            default:
                break;
        }
    }
}
