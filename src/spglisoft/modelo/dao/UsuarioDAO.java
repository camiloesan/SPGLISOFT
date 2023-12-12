/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo.dao;

import spglisoft.modelo.ConexionBD;
import spglisoft.modelo.pojo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.modelo.pojo.Representante;

/**
 *
 * @author camilo
 */
public class UsuarioDAO {
    public static Usuario sesion;
    private static Desarrollador sesionDesarrollador;
    private static Representante sesionRepresentante;
    
    public static Desarrollador iniciarSesionDesarrollador(String matricula, String contrasena) throws SQLException {
        Desarrollador desarrollador = null;
        try {
            Connection conexionBD = ConexionBD.obtenerConnection();
            String query = "SELECT * FROM desarrollador WHERE matricula = ? AND contrasena = ?";
            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            preparedStatement.setString(1, matricula);
            preparedStatement.setString(2, contrasena);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                desarrollador = new Desarrollador();
                desarrollador.setIdDesarrollador(resultado.getInt("id_desarrollador"));
                desarrollador.setNombre(resultado.getString("nombre"));
                desarrollador.setApellidoPaterno(resultado.getString("apellido_paterno"));
                desarrollador.setApellidoMaterno(resultado.getString("apellido_materno"));
                desarrollador.setMatricula(resultado.getString("matricula"));
                desarrollador.setIdProyecto(resultado.getInt("id_proyecto"));
                desarrollador.setSemestre(resultado.getInt("semestre"));
            }
            conexionBD.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sesionDesarrollador = desarrollador;
        return desarrollador;
    }
    
    public static Desarrollador getSesionDesarrollador(){
        return sesionDesarrollador;
    }
    
    public static Representante iniciarSesionRepresentante(String numeroPersonal, String contrasena) throws SQLException{
        Representante representante = null;
        try {
            Connection conexionBD = ConexionBD.obtenerConnection();
            String query = "SELECT * FROM representante_proyecto WHERE numero_personal = ? AND contrasena = ?";
            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            preparedStatement.setString(1, numeroPersonal);
            preparedStatement.setString(2, contrasena);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()) {
                representante = new Representante();
                representante.setIdRepresentante(resultado.getInt("id_representante"));
                representante.setNombre(resultado.getString("nombre"));
                representante.setApellidoPaterno(resultado.getString("apellido_paterno"));
                representante.setApellidoMaterno(resultado.getString("apellido_materno"));
                representante.setNumeroPersonal(resultado.getString("numero_personal"));
            }
            conexionBD.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sesionRepresentante = representante;
        return representante;
    }
    
    public static Representante getSesionRepresentante(){
        return sesionRepresentante;
    }

    public static Usuario getSesion(){
        return sesion;
    }

    public static List<Desarrollador> obtenerDesarrolladoresPorIdProyecto(int idProyecto) throws SQLException {
        List<Desarrollador> listaUsuarios = new ArrayList<>();
        String query = "SELECT id_desarrollador, nombre, apellido_paterno, apellido_materno, matricula, semestre " +
                "FROM desarrollador WHERE id_proyecto = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idProyecto);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Desarrollador desarrollador = new Desarrollador();
            desarrollador.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            desarrollador.setNombre(resultSet.getString("nombre"));
            desarrollador.setApellidoPaterno(resultSet.getString("apellido_paterno"));
            desarrollador.setApellidoMaterno(resultSet.getString("apellido_materno"));
            desarrollador.setMatricula(resultSet.getString("matricula"));
            listaUsuarios.add(desarrollador);
        }
        conexionBD.close();
        return listaUsuarios;
    }
    
    public static List<Desarrollador> obtenerDesarrolladoresSinProyecto() throws SQLException{
        List<Desarrollador> listaUsuarios = new ArrayList<>();
        String query = "SELECT id_desarrollador, nombre, apellido_paterno, apellido_materno, matricula,semestre " +
                "FROM desarrollador WHERE id_proyecto IS NULL ";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Desarrollador desarrollador = new Desarrollador();
            desarrollador.setIdDesarrollador(resultSet.getInt("id_desarrollador"));
            desarrollador.setNombre(resultSet.getString("nombre"));
            desarrollador.setApellidoPaterno(resultSet.getString("apellido_paterno"));
            desarrollador.setApellidoMaterno(resultSet.getString("apellido_materno"));
            desarrollador.setMatricula(resultSet.getString("matricula"));
            listaUsuarios.add(desarrollador);
        }
        conexionBD.close();
        return listaUsuarios;
    }
    
    public static void eliminarDesarrolladorDelProyecto(int idDesarrollador) throws SQLException{
        String query = "UPDATE desarrollador " +
                       "SET id_proyecto = NULL " +
                       "WHERE id_desarrollador = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);
        
        preparedStatement.execute();
        conexionBD.close();
        
        query = "UPDATE actividad\n" +
                "SET id_estado = 2, id_desarrollador = NULL\n" +
                "WHERE id_desarrollador = (?);";
        conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement prepareStatement = conexionBD.prepareStatement(query);
        prepareStatement.setInt(1, idDesarrollador);
        
        prepareStatement.execute();
        conexionBD.close();
    }
    
    public static void anadirDesarrolladorProyecto(int idDesarrollador, int idProyecto) throws SQLException{
        String query = "UPDATE desarrollador " +
                       "SET id_proyecto = (?) " +
                       "WHERE id_desarrollador = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idProyecto);
        preparedStatement.setInt(2, idDesarrollador);
        
        preparedStatement.execute();
        conexionBD.close();
    }

    public static void asignarActividadADesarrollador(int idActividad, int idDesarrollador) throws SQLException {
        String query = "UPDATE actividad " +
                "SET id_desarrollador = (?), id_estado = 1 " +
                "WHERE id_actividad = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idDesarrollador);
        preparedStatement.setInt(2, idActividad);

        preparedStatement.execute();
        conexionBD.close();
    }
}
