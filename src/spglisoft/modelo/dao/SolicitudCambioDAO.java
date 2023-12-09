package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.pojo.SolicitudCambio;

public class SolicitudCambioDAO {
    public static void actualizarEstadoSolicitud(String estado, int idSolicitud) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "UPDATE solicitudes_cambios " +
                "SET estado = ? " +
                "WHERE id_solicitud = ?";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, estado);
        preparedStatement.setInt(2, idSolicitud);
        preparedStatement.execute();
        conexionBD.close();
    }
    
    public static ResultadoOperacion registrarSolicitud(SolicitudCambio solicitud) throws SQLException{
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar la solicitud", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO solicitud_cambio (id_proyecto, id_desarrollador, nombre_solicitud, "
                        + "descripcion, fecha_solicitud, accion_propuesta, razon_cambio, impacto_cambio, id_estado_solicitud) "
                        + "VALUES (?,?,?,?,CURDATE(),?,?,?,?)";
                PreparedStatement consulta = conexionBD.prepareStatement(query);
                consulta.setInt(1, solicitud.getIdProyecto());
                consulta.setInt(2, solicitud.getIdDesarrollador());
                consulta.setString(3, solicitud.getNombreSolicitud());
                consulta.setString(4, solicitud.getDescripcion());
                consulta.setString(5, solicitud.getAccionPropuesta());
                consulta.setString(6, solicitud.getRazonCambio());
                consulta.setString(7, solicitud.getImpactoCambio());
                consulta.setInt(8, solicitud.getIdEstadoSolicitud());
                int filasAfectadas = consulta.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado.setError(false);
                    resultado.setMensaje("Solicitud de cambio registrada");
                    resultado.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return resultado;
    }
    
    public static ArrayList<SolicitudCambio> obtenerSolicitudes(int idProyecto) throws SQLException {
        ArrayList<SolicitudCambio> solicitudes = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT \n" +
                        "sc.id_solicitud,\n" +
                        "sc.nombre_solicitud,\n" +
                        "sc.descripcion,\n" +
                        "sc.fecha_solicitud,\n" +
                        "sc.accion_propuesta,\n" +
                        "sc.razon_cambio,\n" +
                        "sc.impacto_cambio,\n" +
                        "es.estado_solicitud,\n" +
                        "CONCAT(d.nombre, ' ', d.apellido_paterno, ' ', d.apellido_materno) AS nombre_desarrollador,\n " +
                        "p.nombre_proyecto\n " +
                        "FROM solicitud_cambio sc\n " +
                        "INNER JOIN desarrollador d ON sc.id_desarrollador = d.id_desarrollador\n " +
                        "INNER JOIN estado_solicitud es ON sc.id_estado_solicitud = es.id_estado_solicitud\n " +
                        "INNER JOIN proyecto p ON sc.id_proyecto = p.id_proyecto WHERE sc.id_proyecto = ?";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idProyecto);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    SolicitudCambio solicitud = new SolicitudCambio();
                    solicitud.setIdSolicitud(resultado.getInt("sc.id_solicitud"));
                    solicitud.setNombreSolicitud(resultado.getString("sc.nombre_solicitud"));
                    solicitud.setDescripcion(resultado.getString("sc.descripcion"));
                    solicitud.setFechaSolicitud(resultado.getDate("sc.fecha_solicitud"));
                    solicitud.setAccionPropuesta(resultado.getString("accion_propuesta"));
                    solicitud.setRazonCambio(resultado.getString("sc.razon_cambio"));
                    solicitud.setImpactoCambio(resultado.getString("sc.impacto_cambio"));
                    solicitud.setEstadoSolicitud(resultado.getString("es.estado_solicitud"));
                    solicitud.setNombreDesarrollador(resultado.getString("nombre_desarrollador"));
                    solicitud.setNombreProyecto(resultado.getString("p.nombre_proyecto"));
                    solicitudes.add(solicitud);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                if (conexionBD != null) {
                    conexionBD.close();
                }
            }
        }
        return solicitudes;
    }
    
    public static String obtenerSolicitante(int idSolicitante) throws SQLException{
        Connection conexionBD = ConexionBD.obtenerConnection();
        String nombreSolicitante = null;
        if (conexionBD != null) {
            try {
                String consulta = "SELECT CONCAT(nombre,' ',apellido_paterno,' ',apellido_materno) AS nombre_completo "
                        + "FROM usuarios WHERE id_usuario = ?";
                PreparedStatement obtenerSolicitante = conexionBD.prepareStatement(consulta);
                obtenerSolicitante.setInt(1, idSolicitante);
                ResultSet resultado = obtenerSolicitante.executeQuery();
                if (resultado.next()) {
                    nombreSolicitante = resultado.getString("nombre_completo");
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                conexionBD.close();
            }
        }
        return nombreSolicitante;
    }
}
