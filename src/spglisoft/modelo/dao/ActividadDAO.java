package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.modelo.pojo.EstadoActividad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spglisoft.modelo.ResultadoOperacion;

public class ActividadDAO {
    public static List<Actividad> obtenerActividadesAsignadasPorIdProyecto(int idProyecto) throws SQLException {
        List<Actividad> listaActividades = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT id_actividad, id_proyecto, id_desarrollador, nombre, descripcion, " +
                "esfuerzo, fecha_inicio, fecha_fin, e.estado as estado" +
                " FROM actividad INNER JOIN estado_actividad e " +
                "ON actividad.id_estado = e.id_estado_actividad " +
                "WHERE id_proyecto = ? AND id_desarrollador IS NOT NULL";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idProyecto);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setIdProyecto(resultSet.getInt("id_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setNombre(resultSet.getString("nombre"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setNombreEstado(resultSet.getString("estado"));
            listaActividades.add(actividad);
        }
        conexionBD.close();
        return listaActividades;
    }

    public static List<Actividad> obtenerActividadesNoAsignadasPorIdProyecto(int idProyecto) throws SQLException {
        List<Actividad> listaActividades = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT id_actividad, id_proyecto, id_desarrollador, nombre, descripcion, " +
                "esfuerzo, fecha_inicio, fecha_fin, e.estado as estado" +
                " FROM actividad INNER JOIN estado_actividad e " +
                "ON actividad.id_estado = e.id_estado_actividad " +
                "WHERE id_proyecto = ? AND id_desarrollador IS NULL";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idProyecto);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setIdProyecto(resultSet.getInt("id_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setNombre(resultSet.getString("nombre"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setNombreEstado(resultSet.getString("estado"));
            listaActividades.add(actividad);
        }
        conexionBD.close();
        return listaActividades;
    }

    public static List<Actividad> obtenerActividadesAsignadasPorIdDesarrollador(int idDesarrollador) throws SQLException {
        List<Actividad> listaActividades = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT a.id_actividad, a.id_proyecto, a.id_desarrollador, a.id_estado, " +
                "a.nombre, a.descripcion, a.esfuerzo, a.fecha_inicio, a.fecha_fin, e.estado " +
                "AS estado FROM actividad a INNER JOIN estado_actividad e ON a.id_estado = e.id_estado_actividad " +
                "INNER JOIN desarrollador d ON a.id_desarrollador = d.id_desarrollador " +
                "WHERE a.id_desarrollador = (?)";

        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);

        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()) {
            Actividad actividad = new Actividad();
            actividad.setIdActividad(resultSet.getInt("id_actividad"));
            actividad.setIdProyecto(resultSet.getInt("id_proyecto"));
            actividad.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            actividad.setNombre(resultSet.getString("nombre"));
            actividad.setDescripcion(resultSet.getString("descripcion"));
            actividad.setEsfuerzoMinutos(resultSet.getInt("esfuerzo"));
            actividad.setFechaInicio(resultSet.getString("fecha_inicio"));
            actividad.setFechaFin(resultSet.getString("fecha_fin"));
            actividad.setIdEstado(resultSet.getInt("id_estado"));
            actividad.setNombreEstado(resultSet.getString("estado"));
            listaActividades.add(actividad);
        }
        conexionBD.close();
        return listaActividades;
    }

    public static List<EstadoActividad> obtenerEstadosActividad() {
        List<EstadoActividad> listaEstadosActividad = new ArrayList<>();
        try {
            Connection conexionBD = ConexionBD.obtenerConnection();
            String query = "SELECT * FROM estado_actividad";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                EstadoActividad estadoActividad = new EstadoActividad();
                estadoActividad.setIdEstado(resultSet.getInt("id_estado_actividad"));
                estadoActividad.setEstado(resultSet.getString("estado"));
                listaEstadosActividad.add(estadoActividad);
            }
            conexionBD.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaEstadosActividad;
    }
    
    public static void eliminarActividad(int idActividad) throws SQLException{
        String query = "DELETE FROM actividad " +
                       "WHERE id_actividad = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement prepareStatement = conexionBD.prepareStatement(query);
        prepareStatement.setInt(1, idActividad);
        
        prepareStatement.execute();
        conexionBD.close();
    }
    
    public static void desasignarActividad(int idActividad) throws SQLException{
        String query = "UPDATE actividad\n" +
                       "SET id_estado = 1, id_desarrollador = NULL\n" +
                       "WHERE id_actividad = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement prepareStatement = conexionBD.prepareStatement(query);
        prepareStatement.setInt(1, idActividad);
        
        prepareStatement.execute();
        conexionBD.close();
    }
    
    public static void terminarActividad(int idActividad) throws SQLException{
        String query = "UPDATE actividad " +
                       "SET id_estado = 3 " +
                       "WHERE id_actividad = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement prepareStatement = conexionBD.prepareStatement(query);
        prepareStatement.setInt(1, idActividad);
        
        prepareStatement.execute();
        conexionBD.close();
    }
    
    public static ResultadoOperacion registrarActividad(Actividad nuevaActividad) throws SQLException {
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar la actividad", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO actividad (id_proyecto, nombre, descripcion, esfuerzo, "
                        + "id_estado, fecha_inicio, fecha_fin) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conexionBD.prepareCall(query);
                preparedStatement.setInt(1, nuevaActividad.getIdProyecto());
                preparedStatement.setString(2, nuevaActividad.getNombre());
                preparedStatement.setString(3, nuevaActividad.getDescripcion());
                preparedStatement.setInt(4, nuevaActividad.getEsfuerzoMinutos());
                preparedStatement.setInt(5, nuevaActividad.getIdEstado());
                preparedStatement.setString(6, nuevaActividad.getFechaInicio());
                preparedStatement.setString(7, nuevaActividad.getFechaFin());
                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado.setError(false);
                    resultado.setMensaje("Actividad registrada");
                    resultado.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return resultado;
    }
}
