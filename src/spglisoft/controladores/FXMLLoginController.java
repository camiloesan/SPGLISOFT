/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import spglisoft.dao.UsersDAO;
import spglisoft.pojo.User;
import spglisoft.utils.SingletonLogin;

/**
 * FXML Controller class
 *
 * @author camilo
 */
public class FXMLLoginController {
    @FXML
    TextField tfEmail;
    @FXML
    PasswordField tfPassword;
    
    public void initialize() {
        // TODO
    }
    
    private void redirectUserToScene(User user) {
        switch (user.getTipo_usuario()) {
            case "administrador":
                try {
                MainStage.changeView("/spglisoft/vistas/FXMLGestionUsuarios.fxml", 1000, 600);
                    
            } catch (IOException e) {
                e.getMessage();
            }
            break;

            case "representante_proyecto":
                try {
                MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
            } catch (IOException e) {
                e.getMessage();
            }
            break;

            case "desarrollador":
                try {
                MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
    
    private User sessionUser() {
        UsersDAO usersDAO = new UsersDAO();
        String email = tfEmail.getText();
        User user = new User();
        
        try {
            user = usersDAO.getUserByEmail(email);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (user != null) {
            SingletonLogin singletonLogin;
            singletonLogin = SingletonLogin.getInstance();   
            singletonLogin.setUser(user);
            return user;
        }
        return null;
    }
    
    @FXML
    private void btnLogin() {
        UsersDAO usersDAO = new UsersDAO();
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        
        try {
            if (usersDAO.areCredentialsValid(email, password)) {
                System.out.println(usersDAO.areCredentialsValid(email, password));
                redirectUserToScene(sessionUser());
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
