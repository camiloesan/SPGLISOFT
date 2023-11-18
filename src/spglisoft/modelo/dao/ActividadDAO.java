package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.pojo.Actividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActividadDAO {
    public static List<Actividad> obtenerActividadesAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException {
        String query = "SELECT * FROM actividades " +
                "WHERE nombre_proyecto = ? AND id_desarrollador IS NOT NULL";
        Connection connection = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nombreProyecto);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Actividad> listaActividades = new ArrayList<>();
        while(resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setNombreProyecto(resultSet.getString("nombre_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setTitulo(resultSet.getString("titulo"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setEstado(resultSet.getString("estado"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo_minutos"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            listaActividades.add(actividad);
        }

        return listaActividades;
    }

    public static List<Actividad> obtenerActividadesNoAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException {
        String query = "SELECT * FROM actividades " +
                "WHERE nombre_proyecto = ? AND id_desarrollador IS NULL";
        Connection connection = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nombreProyecto);
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Actividad> listaActividades = new ArrayList<>();
        while(resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setNombreProyecto(resultSet.getString("nombre_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setTitulo(resultSet.getString("titulo"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setEstado(resultSet.getString("estado"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo_minutos"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            listaActividades.add(actividad);
        }
        return listaActividades;
    }

    public static List<Actividad> obtenerActividadesAsignadasPorDesarrollador(int idDesarrollador) throws SQLException {
        String query = "SELECT * FROM actividades " +
                "WHERE id_desarrollador = ?";
        Connection connection = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);

        ResultSet resultSet = preparedStatement.executeQuery();
        List<Actividad> listaActividades = new ArrayList<>();
        while(resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setNombreProyecto(resultSet.getString("nombre_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setTitulo(resultSet.getString("titulo"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setEstado(resultSet.getString("estado"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo_minutos"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            listaActividades.add(actividad);
        }
        return listaActividades;
    }
}