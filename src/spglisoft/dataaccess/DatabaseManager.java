/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.dataaccess;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author camilo
 */
public class DatabaseManager {
    private Connection connection;
    private final String DATABASE_NAME;
    private final String DATABASE_USER;
    private final String DATABASE_PASSWORD;
    
    public DatabaseManager() {
        String configFilePath = "src/config.properties";
        FileInputStream propertiesInput;
        try {
            propertiesInput = new FileInputStream(configFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Properties properties = new Properties();
        try {
            properties.load(propertiesInput);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DATABASE_NAME = (String) properties.get("DATABASE_NAME");
        DATABASE_USER = (String) properties.get("DATABASE_USER");
        DATABASE_PASSWORD = (String) properties.get("DATABASE_PASSWORD");
    }
    
    private void connect() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_NAME, DATABASE_USER, DATABASE_PASSWORD);
    }

    public Connection getConnection() throws SQLException {
        connect();
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException exception) {
                exception.getMessage();
            }
        }
    }
}
