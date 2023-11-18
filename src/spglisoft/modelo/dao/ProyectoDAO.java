/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.pojo.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author camilo
 */
public class ProyectoDAO {
    public static List<Proyecto> obtenerProyectosPorIDUsuario(int idUsuarioResponsable) throws SQLException {
        List<Proyecto> proyectosList = new ArrayList<>();
        String query = "SELECT nombre_proyecto, descripcion, fecha_inicio,"
                + "fecha_fin, estado FROM proyectos WHERE id_usuario_responsable = ?";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.
                prepareStatement(query);
        preparedStatement.setInt(1, idUsuarioResponsable);
        ResultSet resultSet = preparedStatement.executeQuery();

        proyectosList = new ArrayList<>();
        while (resultSet.next()) {
            Proyecto proyecto = new Proyecto();
            proyecto.setNombreProyecto(resultSet.
                    getString("nombre_proyecto"));
            proyecto.setEstado(resultSet.getString("estado"));

            proyectosList.add(proyecto);
        }
        conexionBD.close();
        return proyectosList;
    }
}
