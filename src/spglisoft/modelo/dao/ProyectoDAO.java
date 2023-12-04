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
import spglisoft.modelo.pojo.Representante;
import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author camilo
 */
public class ProyectoDAO {
    public static List<Proyecto> obtenerProyectosPorIdRepresentante(int idUsuarioResponsable) throws SQLException {
        List<Proyecto> proyectosList = new ArrayList<>();
        String query = "SELECT id_proyecto, nombre_proyecto, e.estado_proyecto as estado " +
                "FROM proyecto INNER JOIN estado_proyecto e " +
                "on proyecto.estado_proyecto = e.id_estado_proyecto" +
                " where id_representante = (?);";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idUsuarioResponsable);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Proyecto proyecto = new Proyecto();
            proyecto.setIdProyecto(resultSet.getInt("id_proyecto"));
            proyecto.setNombreProyecto(resultSet.getString("nombre_proyecto"));
            proyecto.setNombreEstado(resultSet.getString("estado"));
            proyectosList.add(proyecto);
        }
        conexionBD.close();
        return proyectosList;
    }
    
    public static Proyecto obtenerProyecto(Representante representante) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        Proyecto proyecto = new Proyecto();
        if (conexionBD != null) {
            try {
                String query = "SELECT * FROM proyecto WHERE id_representante = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
                preparedStatement.setInt(1, representante.getIdRepresentante());
                ResultSet resultado = preparedStatement.executeQuery();
                if (resultado.next()) {
                    proyecto.setIdProyecto(resultado.getInt("id_proyecto"));
                    proyecto.setNombreProyecto(resultado.getString("nombre_proyecto"));
                    proyecto.setDescripcion(resultado.getString("descripcion"));
                    proyecto.setFechaInicio(resultado.getString("fecha_inicio"));
                    proyecto.setFechaFin(resultado.getString("fecha_fin"));
                    proyecto.setIdEstado(resultado.getInt("estado_proyecto"));
                    proyecto.setUsuarioResponsable(resultado.getInt("id_representante"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        }
        return proyecto;
    }
}
