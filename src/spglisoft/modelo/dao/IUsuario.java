/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spglisoft.modelo.dao;

import spglisoft.modelo.pojo.Usuario;

import java.sql.SQLException;

/**
 *
 * @author camilo
 */
public interface IUsuario {
    boolean sonCredencialesValidas(String email, String password) throws SQLException;
    Usuario obtenerUsuarioPorEmail(String email) throws SQLException;
}
