package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.pojo.ImpactoSolicitud;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.Proyecto;
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
                        + "descripcion, fecha_solicitud, accion_propuesta, razon_cambio, id_impacto_cambio, id_estado_solicitud) "
                        + "VALUES (?,?,?,?,CURDATE(),?,?,?,?)";
                PreparedStatement consulta = conexionBD.prepareStatement(query);
                consulta.setInt(1, solicitud.getIdProyecto());
                consulta.setInt(2, solicitud.getIdDesarrollador());
                consulta.setString(3, solicitud.getNombreSolicitud());
                consulta.setString(4, solicitud.getDescripcion());
                consulta.setString(5, solicitud.getAccionPropuesta());
                consulta.setString(6, solicitud.getRazonCambio());
                consulta.setInt(7, solicitud.getIdImpacto());
                consulta.setInt(8, solicitud.getIdEstado());
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
                String consulta = "SELECT * FROM solicitud_cambio WHERE id_proyecto = ?";
                PreparedStatement obtenerSolicitudes = conexionBD.prepareStatement(consulta);
                obtenerSolicitudes.setInt(1, idProyecto);
                ResultSet resultadoConsulta = obtenerSolicitudes.executeQuery();
                solicitudes = new ArrayList<>();
                while (resultadoConsulta.next()) {
                    SolicitudCambio solicitud = new SolicitudCambio();
                    solicitud.setIdSolicitud(resultadoConsulta.getInt("id_solicitud"));
                    solicitud.setIdProyecto(resultadoConsulta.getInt("id_proyecto"));
                    solicitud.setIdDesarrollador(resultadoConsulta.getInt("id_desarrollador"));
                    solicitud.setNombreSolicitud(resultadoConsulta.getString("nombre_solicitud"));
                    solicitud.setDescripcion(resultadoConsulta.getString("descripcion"));
                    solicitud.setFechaSolicitud(resultadoConsulta.getDate("fecha_solicitud"));
                    solicitud.setAccionPropuesta(resultadoConsulta.getString("accion_propuesta"));
                    solicitud.setIdImpacto(resultadoConsulta.getInt("id_impacto_cambio"));
                    solicitud.setRazonCambio(resultadoConsulta.getString("razon_cambio"));
                    solicitudes.add(solicitud);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                conexionBD.close();
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
    
    public static List<ImpactoSolicitud> obtenerImpactoSolicitud() throws SQLException{
        List<ImpactoSolicitud> impactos = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "SELECT * FROM impacto_cambio";
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(query);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    ImpactoSolicitud impacto = new ImpactoSolicitud();
                    impacto.setIdImpacto(resultado.getInt("id_impacto_cambio"));
                    impacto.setImpacto(resultado.getString("impacto_cambio"));
                    impactos.add(impacto);
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        return impactos;
    }
}
