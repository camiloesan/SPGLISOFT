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

/**
 *
 * @author camilo
 */
public class UsuarioDAO {
    public static boolean sonCredencialesValidas(String email, String password) throws SQLException {
        boolean isValid = false;
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT 1 FROM usuarios WHERE email=(?) AND contrasena=(SHA2(?, 256))";

        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        isValid = resultSet.next();

        conexionBD.close();
        return isValid;
    }    

    public static Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
        Usuario user = null;
        Connection conexionBD = ConexionBD.obtenerConnection();
        String query = "SELECT * FROM usuarios WHERE email = (?)";
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, email);

        ResultSet resultSet = preparedStatement.executeQuery();
        user = new Usuario();
        while (resultSet.next()) {
            user.setUserId(resultSet.getInt("id_usuario"));
            user.setNombre(resultSet.getString("nombre"));
            user.setApellidoPaterno(resultSet.getString("apellido_paterno"));
            user.setApellidoMaterno(resultSet.getString("apellido_materno"));
            user.setEmail(resultSet.getString("email"));
            user.setMatricula(resultSet.getString("matricula"));
            user.setTipoUsuario(resultSet.getString("tipo_usuario"));
        }
        conexionBD.close();
        return user;
    }

    public static Usuario getSesion(){
        return sesion;
    }

    public static List<Usuario> obtenerDesarrolladoresPorProyecto(String nombreProyecto) throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String query = "SELECT p.id_usuario AS id_usuario, nombre, apellido_paterno, apellido_materno, matricula " +
                "FROM usuarios INNER JOIN participantes p " +
                "WHERE tipo_usuario = 'desarrollador' AND nombre_proyecto = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setString(1, nombreProyecto);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Usuario usuario = new Usuario();
            usuario.setUserId(resultSet.getInt("id_usuario"));
            usuario.setNombre(resultSet.getString("nombre"));
            usuario.setApellidoPaterno(resultSet.getString("apellido_paterno"));
            usuario.setApellidoMaterno(resultSet.getString("apellido_materno"));
            usuario.setMatricula(resultSet.getString("matricula"));
            listaUsuarios.add(usuario);
        }
        conexionBD.close();
        return listaUsuarios;
    }

    public static void asignarActividadADesarrollador(int idActividad, int idUsuario) throws SQLException {
        String query = "UPDATE actividades " +
                "SET id_desarrollador = (?) " +
                "WHERE id_actividad = (?)";
        Connection conexionBD = ConexionBD.obtenerConnection();
        PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
        preparedStatement.setInt(1, idUsuario);
        preparedStatement.setInt(2, idActividad);

        preparedStatement.execute();
        conexionBD.close();
    }
}
