/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author lecap
 */
public class ParticipantesDAO {
    public static Participantes obtenerProyecto(Usuario usuario) throws SQLException{
        Connection conexionBD = ConexionBD.obtenerConnection();
        Participantes participantes = null;
        if (conexionBD != null) {
            try {
                String consulta = "SELECT id_participante, id_usuario, nombre_proyecto"
                        + "FROM participantes WHERE id_usuario = ?";
                PreparedStatement prepararConsulta = conexionBD.prepareStatement(consulta);
                prepararConsulta.setInt(1, usuario.getUserId());
                ResultSet resultadoConsulta = prepararConsulta.executeQuery();
                if (resultadoConsulta.next()) {
                    participantes.setIdParticipante(resultadoConsulta.getInt("id_participante"));
                    participantes.setIdUsuario(resultadoConsulta.getInt("id_usuario"));
                    participantes.setNombreProyecto(resultadoConsulta.getString("nombre_proyecto"));
                }else{
                    participantes.setIdParticipante(-1);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return participantes;
    }
}
