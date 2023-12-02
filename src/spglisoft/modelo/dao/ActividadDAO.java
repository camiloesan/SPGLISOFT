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
        List<Actividad> listaActividades = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT * FROM actividades " +
                "WHERE nombre_proyecto = ? AND id_desarrollador IS NOT NULL";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, nombreProyecto);

        ResultSet resultSet = preparedStatement.executeQuery();
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
        conexionBD.close();
        return listaActividades;
    }

    public static List<Actividad> obtenerActividadesNoAsignadasPorNombreProyecto(String nombreProyecto) throws SQLException {
        List<Actividad> listaActividades = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT * FROM actividad " +
                "WHERE id_proyecto = ? AND id_desarrollador IS NULL";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, nombreProyecto);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setNombreProyecto(resultSet.getString("id_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setTitulo(resultSet.getString("nombre"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setEstado(resultSet.getString("id_estado"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            listaActividades.add(actividad);
        }
        conexionBD.close();
        return listaActividades;
    }

    public static List<Actividad> obtenerActividadesAsignadasPorDesarrollador(int idDesarrollador) throws SQLException {
        List<Actividad> listaActividades = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT * FROM actividad " +
                "WHERE id_desarrollador = ?";

        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setNombreProyecto(resultSet.getString("id_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setTitulo(resultSet.getString("nombre"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setEstado(resultSet.getString("id_estado"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            listaActividades.add(actividad);
        }
        conexionBD.close();
        return listaActividades;
    }
}
