/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.pojo.Defecto;
import spglisoft.modelo.pojo.Participantes;

/**
 *
 * @author lecap
 */
public class DefectoDAO {
    public static ResultadoOperacion registrarDefecto(Defecto defecto, Participantes participantes) throws SQLException{
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error al registrar el defecto", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO defectos(nombre_proyecto, titulo, descripcion, "
                        + "fecha_reporte, tipo) VALUES (?,?,?,CURDATE(),?)";
                PreparedStatement prepararConsulta = conexionBD.prepareCall(query);
                prepararConsulta.setString(1, participantes.getNombreProyecto());
                prepararConsulta.setString(2, defecto.getTitulo());
                prepararConsulta.setString(3, defecto.getDescripcion());
                prepararConsulta.setString(4, defecto.getTipo());
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
}
