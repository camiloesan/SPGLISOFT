/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spglisoft.modelo.dao;

import java.sql.SQLException;
import spglisoft.modelo.pojo.Usuario;

/**
 *
 * @author camilo
 */
public interface IUsuario {
    boolean sonCredencialesValidas(String email, String password) throws SQLException;
    Usuario obtenerUsuarioPorEmail(String email) throws SQLException;
}
