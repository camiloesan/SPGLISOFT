/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.modelo;

import spglisoft.utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author camilo
 */
public class ConexionBD {
    public static final String URL_CONEXION = "jdbc:mysql://"
            + Constantes.HOSTNAME + ":"
            + Constantes.PUERTO 
            + "/"
            + Constantes.NOMBRE_BD
            + "?allowPublicKeyRetrieval=true&useSSL=false";

    public static Connection obtenerConnection() throws SQLException {
        return DriverManager.getConnection(URL_CONEXION, Constantes.USUARIO, Constantes.PASSWORD);
    }
}
