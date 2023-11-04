/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spglisoft.modelo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author camilo
 */
public interface IProyecto {
    List getProyectosList() throws SQLException;
}
