/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;

import java.sql.SQLException;
import java.util.Objects;

public class FXMLLoginController {
    @FXML
    TextField tfEmail;
    @FXML
    PasswordField tfPassword;
    
    public void initialize() {
    }
    
    private void redirigirAEscena(Usuario usuario) {
        switch (usuario.getTipoUsuario()) {
            case "representante_proyecto":
                MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
            break;

            case "desarrollador":
                MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
        }
    }
    
    private Usuario sessionUser() {
        String email = tfEmail.getText();
        Usuario usuario = new Usuario();
        
        try {
            usuario = UsuarioDAO.obtenerUsuarioPorEmail(email);
        } catch (SQLException ex) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        
        if (usuario != null) {
            SingletonLogin singletonLogin;
            singletonLogin = SingletonLogin.getInstance();   
            singletonLogin.setUser(usuario);
            return usuario;
        }
        return null;
    }
    
    @FXML
    private void btnLogin() {
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        
        try {
            if (UsuarioDAO.sonCredencialesValidas(email, password)) {
                redirigirAEscena(Objects.requireNonNull(sessionUser()));
            } else {
                Alertas.mostrarAlertaLoginFallido();
            }
        } catch (SQLException ex) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
    }
}
