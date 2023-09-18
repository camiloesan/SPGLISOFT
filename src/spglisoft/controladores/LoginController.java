/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author camilo
 */
public class LoginController {
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    
    public void initialize() {
        // TODO
    }
    
    @FXML
    public void validateLogin() {
        if (txtUsername.getText().equals("admin") && txtPassword.getText().equals("admin")) {
            try {
            MainStage.changeView("/spglisoft/vistas/FXMLUserManagement.fxml", 1000, 600);
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("usuario incorrecto");
        }
    }
}
