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
public class FXMLLoginController {
    @FXML
    TextField txtUsername;
    @FXML
    PasswordField txtPassword;
    
    public void initialize() {
        // TODO
    }
    
    @FXML
    public void validateLogin() {
        String usertype = txtUsername.getText();
        switch (usertype) {
            case "admin":
                try {
                MainStage.changeView("/spglisoft/vistas/FXMLUserManagement.fxml", 1000, 600);
            } catch (IOException e) {
                e.getMessage();
            }
                break;

            case "rp":
                try {
                MainStage.changeView("/spglisoft/vistas/FXMLRPMainMenu.fxml", 1000, 600);
            } catch (IOException e) {
                e.getMessage();
            }
                break;

            case "desarrollador":
                try {
                MainStage.changeView("/spglisoft/vistas/FXMLDeveloperLogs.fxml", 1000, 600);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
