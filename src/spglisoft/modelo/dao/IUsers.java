/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spglisoft.modelo.dao;

import java.sql.SQLException;
import java.util.List;
import spglisoft.modelo.pojo.User;

/**
 *
 * @author camilo
 */
public interface IUsers {
    void addUser(User user) throws SQLException;
    void deleteUserByEmail(String email) throws SQLException;
    boolean areCredentialsValid(String email, String password) throws SQLException;
    User getUserByEmail(String email) throws SQLException;
    List getUsersList() throws SQLException;
}
