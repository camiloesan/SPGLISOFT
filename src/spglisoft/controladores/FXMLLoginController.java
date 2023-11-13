/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.SingletonLogin;

import java.sql.SQLException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import spglisoft.utils.Utilidades;

public class FXMLLoginController {
    @FXML
    TextField tfEmail;
    @FXML
    PasswordField tfPassword;
    
    public void initialize() {
        tfEmail.setText("camilo@gmail.com");
        tfPassword.setText("camilo");
    }
    
    private void redirigirAEscena(Usuario usuario) {
        switch (usuario.getTipoUsuario()) {
            case "administrador":
                MainStage.changeView("/spglisoft/vistas/FXMLGestionUsuarios.fxml", 1000, 600);
            break;

            case "representante_proyecto":
                MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
            break;

            case "desarrollador":
                MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
        }
    }
    
    private Usuario sessionUser() {
        UsuarioDAO usersDAO = new UsuarioDAO();
        String email = tfEmail.getText();
        Usuario user = new Usuario();
        
        try {
            user = usersDAO.obtenerUsuarioPorEmail(email);
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
        UsuarioDAO usersDAO = new UsuarioDAO();
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        
        try {
            if (usersDAO.sonCredencialesValidas(email, password)) {
                Utilidades.mostrarAlertaSimple("Autenticacion", "Bienvenido a SPGLISOFT",
                        Alert.AlertType.INFORMATION);
                redirigirAEscena(Objects.requireNonNull(sessionUser()));
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Alerta");
                alert.setContentText("Correo electrónico o contraseña incorrectos");
                alert.showAndWait();
            }
        } catch (SQLException ex) {
            Logger.getLogger(FXMLLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
