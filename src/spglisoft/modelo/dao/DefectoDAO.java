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
        ResultadoOperacion resultado = new ResultadoOperacion(true, "Error en la consulta", -1);
        Connection conexionBD = ConexionBD.obtenerConnection();
        if (conexionBD != null) {
            try {
                String query = "INSERT INTO defectos(nombre_proyecto, titulo, descripcion, estado, "
                        + "esfuerzo_estimado_minutos, fecha_reporte, tipo) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement prepararConsulta = conexionBD.prepareCall(query);
                prepararConsulta.setString(1, participantes.getNombreProyecto());
                prepararConsulta.setString(2, defecto.getTitulo());
                prepararConsulta.setString(3, defecto.getDescripcion());
                prepararConsulta.setString(4, defecto.getEstado());
                prepararConsulta.setInt(5, defecto.getEsfuerzoEstimado());
                prepararConsulta.setDate(6, defecto.getFechaReporte());
                prepararConsulta.setString(7, defecto.getTipo());
                int filasAfectadas = prepararConsulta.executeUpdate();
                if (filasAfectadas > 1) {
                    resultado.setError(false);
                    resultado.setMensaje("Defecto registrado");
                    resultado.setFilasAfectadas(filasAfectadas);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return resultado;
    }
}
