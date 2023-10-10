/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spglisoft.dataaccess.DatabaseManager;
import spglisoft.pojo.User;

/**
 *
 * @author camilo
 */
public class UsersDAO implements IUsers {
    @Override
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO usuarios(nombre, apellidos, email, contrasena, matricula, tipo_usuario) VALUES (?,?,?,SHA2(?, 256),?,?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = new DatabaseManager().getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getNombre());
        preparedStatement.setString(2, user.getApellidos());
        preparedStatement.setString(3, user.getEmail());
        preparedStatement.setString(4, user.getContrasena());
        preparedStatement.setString(5, user.getMatricula());
        preparedStatement.setString(6, user.getTipo_usuario());
        preparedStatement.executeUpdate();
        
        databaseManager.closeConnection();
    }
  
    @Override
    public void deleteUserByEmail(String email) throws SQLException {
        String query = "DELETE FROM usuarios WHERE email=(?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.executeUpdate();

        databaseManager.closeConnection();
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean areCredentialsValid(String email, String password) throws SQLException {
        String query = "SELECT 1 FROM usuarios WHERE email=(?) AND contrasena=(SHA2(?, 256))";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        boolean isValid;
        isValid = resultSet.next();
        
        databaseManager.closeConnection();

        return isValid;
    }    

    @Override
    public User getUserByEmail(String email) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE email = (?)";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();
        
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, email);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            user.setUserId(resultSet.getInt("id_usuario"));
            user.setNombre(resultSet.getString("nombre"));
            user.setApellidos(resultSet.getString("apellidos"));
            user.setEmail(resultSet.getString("email"));
            user.setMatricula(resultSet.getString("matricula"));
            user.setTipo_usuario(resultSet.getString("tipo_usuario"));
        }
        
        databaseManager.closeConnection();
        return user;
    }

    @Override
    public List getUsersList() throws SQLException {
        String query = "SELECT id_usuario, nombre, apellidos, email, matricula, tipo_usuario FROM usuarios";
        DatabaseManager databaseManager = new DatabaseManager();
        Connection connection = databaseManager.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        
        List<User> userList = new ArrayList<>();
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
        databaseManager.closeConnection();
        
        return userList;
    }
}
