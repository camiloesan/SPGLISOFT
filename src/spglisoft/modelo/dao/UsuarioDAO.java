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
import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author camilo
 */
public class UsuarioDAO implements IUsuario {
    
    private static Usuario sesion;
    
    @Override
    public boolean sonCredencialesValidas(String email, String password) throws SQLException {
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
    public Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
        Connection conexionBD = ConexionBD.obtenerConnection();
        Usuario user = null;
        
        if (conexionBD != null) {
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
        }
        sesion = user;
        return user;
    }
    
    public static Usuario getSesion(){
        return sesion;
    }
}
