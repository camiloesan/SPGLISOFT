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
import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author camilo
 */
public class ProyectoDAO implements IProyecto {

    @Override
    public List<Proyecto> obtenerProyectosPorIDUsuario(int idUsuarioResponsable) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        List<Proyecto> proyectosList = null;
        
        if (conexionBD != null) {
            String query = "SELECT nombre_proyecto, descripcion, fecha_inicio,"
                    + "fecha_fin, estado FROM proyectos WHERE id_usuario_responsable = ?";

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
        }
        
        return proyectosList;
    }
    
    public static Proyecto obtenerProyectoResponsable(Usuario responsable) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        Proyecto proyecto = new Proyecto();
        if (conexionBD != null) {
            try {
                String consulta = "SELECT * FROM proyectos WHERE id_usuario_responsable = ?";
                PreparedStatement obtenerProyecto = conexionBD.prepareStatement(consulta);
                obtenerProyecto.setInt(1, responsable.getUserId());
                ResultSet resultadoConsulta = obtenerProyecto.executeQuery();
                if (resultadoConsulta.next()) {
                    proyecto.setNombreProyecto(resultadoConsulta.getString("nombre_proyecto"));
                    proyecto.setDescripcion(resultadoConsulta.getString("descripcion"));
                    proyecto.setFechaInicio(resultadoConsulta.getString("fecha_inicio"));
                    proyecto.setFechaFin(resultadoConsulta.getString("fecha_fin"));
                    proyecto.setEstado(resultadoConsulta.getString("estado"));
                    proyecto.setUsuarioResponsable(resultadoConsulta.getInt("id_usuario_responsable"));
                }
            } catch (SQLException e) {
                throw e;
            } finally { 
                conexionBD.close();
            }
        }
        return proyecto;
    }
}
