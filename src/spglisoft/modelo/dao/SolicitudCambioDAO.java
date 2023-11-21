package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.modelo.pojo.SolicitudCambio;

public class SolicitudCambioDAO implements ISolicitudCambio {
    @Override
    public void actualizarEstadoSolicitud(String estado, int idSolicitud) throws SQLException {
        String query = "UPDATE solicitudes_cambios " +
                "SET estado = ? " +
                "WHERE id_solicitud = ?";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, estado);
        preparedStatement.setInt(2, idSolicitud);
        preparedStatement.execute();
        conexionBD.close();
    }
    
    public static ResultadoOperacion registrarSolicitud(Participantes participantes,
            SolicitudCambio solicitud) throws SQLException{
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar la solicitud", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO solicitudes_cambios(nombre_proyecto, id_proponente, "
                        + "titulo, descripcion, fechaSolicitud, accion_propuesta, impacto, razon_cambio) "
                        + "VALUES (?,?,?,?,CURDATE(),?,?,?)";
                PreparedStatement consulta = conexionBD.prepareCall(query);
                consulta.setString(1, participantes.getNombreProyecto());
                consulta.setInt(2, participantes.getIdUsuario());
                consulta.setString(3, solicitud.getTitulo());
                consulta.setString(4, solicitud.getDescripcion());
                consulta.setString(5, solicitud.getAccionPropuesta());
                consulta.setString(6, solicitud.getImpacto());
                consulta.setString(7, solicitud.getRazonCambio());
                int filasAfectadas = consulta.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado.setError(false);
                    resultado.setMensaje("Solicitud de cambio registrada");
                    resultado.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                conexionBD.close();
            }
        }
        return resultado;
    }
    
    public static ArrayList<SolicitudCambio> obtenerSolicitudes(Proyecto proyecto) throws SQLException {
        ArrayList<SolicitudCambio> solicitudes = null;
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT * FROM solicitudes_cambios WHERE nombre_proyecto = ?";
                PreparedStatement obtenerSolicitudes = conexionBD.prepareStatement(consulta);
                obtenerSolicitudes.setString(1, proyecto.getNombreProyecto());
                ResultSet resultadoConsulta = obtenerSolicitudes.executeQuery();
                solicitudes = new ArrayList<>();
                while (resultadoConsulta.next()) {
                    SolicitudCambio solicitud = new SolicitudCambio();
                    solicitud.setIdSolicitud(resultadoConsulta.getInt("id_solicitud"));
                    solicitud.setNombreProyecto(resultadoConsulta.getString("nombre_proyecto"));
                    solicitud.setIdProponente(resultadoConsulta.getInt("id_proponente"));
                    solicitud.setTitulo(resultadoConsulta.getString("titulo"));
                    solicitud.setDescripcion(resultadoConsulta.getString("descripcion"));
                    solicitud.setFechaSolicitud(resultadoConsulta.getDate("fechaSolicitud"));
                    solicitud.setAccionPropuesta(resultadoConsulta.getString("accion_propuesta"));
                    solicitud.setImpacto(resultadoConsulta.getString("impacto"));
                    solicitud.setRazonCambio(resultadoConsulta.getString("razon_cambio"));
                    solicitudes.add(solicitud);
                    System.out.println("Se anadio una solicitud en el DAO");
                }
            } catch (SQLException e) {
                throw e;
            } finally {
                conexionBD.close();
            }
        }
        return solicitudes;
    }
}
