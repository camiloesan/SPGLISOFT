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
import spglisoft.modelo.pojo.User;

/**
 *
 * @author camilo
 */
public class UsersDAO implements IUsers {
    @Override
    public void addUser(User user) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        
        if (conexionBD != null) {
            String query = "INSERT INTO usuarios(nombre, apellidos, email, contrasena, matricula, tipo_usuario) VALUES (?,?,?,SHA2(?, 256),?,?)";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            preparedStatement.setString(1, user.getNombre());
            preparedStatement.setString(2, user.getApellidos());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getContrasena());
            preparedStatement.setString(5, user.getMatricula());
            preparedStatement.setString(6, user.getTipo_usuario());
            preparedStatement.executeUpdate();

            conexionBD.close();
        }
    }
  
    @Override
    public void deleteUserByEmail(String email) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        
        if (conexionBD != null) {
            String query = "DELETE FROM usuarios WHERE email=(?)";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.executeUpdate();

            conexionBD.close();
        }
    }

    @Override
    public boolean areCredentialsValid(String email, String password) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        boolean isValid = false;
        
        if (conexionBD != null) {
            String query = "SELECT 1 FROM usuarios WHERE email=(?) AND contrasena=(SHA2(?, 256))";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            isValid = resultSet.next();

            conexionBD.close();
        }
        
        return isValid;
    }    

    @Override
    public User getUserByEmail(String email) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        User user = null;
        
        if (conexionBD != null) {
            String query = "SELECT * FROM usuarios WHERE email = (?)";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            user = new User();
            while (resultSet.next()) {
                user.setUserId(resultSet.getInt("id_usuario"));
                user.setNombre(resultSet.getString("nombre"));
                user.setApellidos(resultSet.getString("apellidos"));
                user.setEmail(resultSet.getString("email"));
                user.setMatricula(resultSet.getString("matricula"));
                user.setTipo_usuario(resultSet.getString("tipo_usuario"));
            }

            conexionBD.close();
        }
        
        return user;
    }

    @Override
    public List getUsersList() throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        List<User> userList = null;
        
        if (conexionBD != null) {
            String query = "SELECT id_usuario, nombre, apellidos, email, matricula, tipo_usuario FROM usuarios";

            PreparedStatement preparedStatement = conexionBD.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            userList = new ArrayList<>();
            while(resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("id_usuario"));
                user.setNombre(resultSet.getString("nombre"));
                user.setApellidos(resultSet.getString("apellidos"));
                user.setEmail(resultSet.getString("email"));
                user.setMatricula(resultSet.getString("matricula"));
                user.setTipo_usuario(resultSet.getString("tipo_usuario"));

                userList.add(user);
            }

            conexionBD.close();
        }
        
        return userList;
    }
}
