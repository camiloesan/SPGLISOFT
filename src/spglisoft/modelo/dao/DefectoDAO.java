/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.pojo.Defecto;
import spglisoft.modelo.pojo.TipoDefecto;

/**
 *
 * @author lecap
 */
public class DefectoDAO {
    
    public static ResultadoOperacion registrarNuevoDefecto(Defecto defecto) throws SQLException {
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar el defecto", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO defecto (id_proyecto, id_desarrollador, nombre_defecto, descripcion, "
                        + "fecha_reporte, tipo_defecto) VALUES (?,?,?,?,CURDATE(),?)";
                PreparedStatement prepararConsulta = conexionBD.prepareCall(query);
                prepararConsulta.setInt(1, defecto.getIdProyecto());
                prepararConsulta.setInt(2, defecto.getIdDesarrollador());
                prepararConsulta.setString(3, defecto.getNombreDefectoString());
                prepararConsulta.setString(4, defecto.getDescripcion());
                prepararConsulta.setInt(5, defecto.getTipoDefecto());
                int filasAfectadas = prepararConsulta.executeUpdate();
                if (filasAfectadas > 0) {
                    resultado.setError(false);
                    resultado.setMensaje("Defecto registrado");
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
    
    public static List<TipoDefecto> obtenerTiposDefecto() throws SQLException {
        List<TipoDefecto> tiposDefecto = new ArrayList<>();
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "SELECT * FROM tipo_defecto";
                PreparedStatement prepararSentencia =conexionBD.prepareStatement(query);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    TipoDefecto tipoDefecto = new TipoDefecto();
                    tipoDefecto.setIdTipoDefecto(resultado.getInt("id_tipo_defecto"));
                    tipoDefecto.setTipoDefecto(resultado.getString("tipo_defecto"));
                    tiposDefecto.add(tipoDefecto);
                }
            } catch (SQLException e) {
                throw e;
            }
        }
        return tiposDefecto;
    }
}
