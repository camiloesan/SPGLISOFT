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
import javafx.scene.control.Alert;
import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.modelo.pojo.Representante;
import spglisoft.utils.Utilidades;

public class FXMLLoginController {
    
    String formato = "zs[a-zA-Z0-9]+";
    
    @FXML
    TextField tfEmail;
    @FXML
    PasswordField tfPassword;
    
    public void initialize() {
    }
    
    /*
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
        Usuario usuario = null;
        
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
    */
    
    private void cambiarEscenaDesarrollador(){
        MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
    }
    
    private void cambiarEscenaRepresentante(){
        MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
    }
    
    @FXML
    private void btnLogin() {
        /*
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
        */
        
        iniciarSesion();
    }
    
    private void iniciarSesion(){
        if (!camposVacios()) {
            String usuario = tfEmail.getText().trim();
            String contrasena = tfPassword.getText().trim();
            if (esMatricula()) {
                try {
                    Desarrollador desarrollador = UsuarioDAO.iniciarSesionDesarrollador(usuario, contrasena);
                    if (!(desarrollador == null)) {
                        System.out.println("El desarrollador no es null");
                        MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
                        SingletonLogin singletonLogin;
                        singletonLogin = SingletonLogin.getInstance();   
                        singletonLogin.setDesarrollador(desarrollador);
                    } else {
                        Alertas.mostrarAlertaLoginFallido();
                        System.out.println("el desarrollador es null");
                    }
                } catch (SQLException e) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            } else if (usuario.matches("\\d+")) {
                try {
                    Representante representante = UsuarioDAO.iniciarSesionRepresentante(usuario, contrasena);
                    if (!(representante == null)) {
                        MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
                        SingletonLogin singletonLogin;
                        singletonLogin = SingletonLogin.getInstance();   
                        singletonLogin.setRepresentante(representante);
                    } else {
                        Alertas.mostrarAlertaLoginFallido();
                    }
                } catch (SQLException e) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            }
        }else {
            Utilidades.mostrarAlertaSimple("Campos faltantes", "Ingrese los campos faltantes",
                    Alert.AlertType.INFORMATION);
        }
    }
    
    private boolean camposVacios(){
        return tfEmail.getText().trim().isEmpty() || tfPassword.getText().trim().isEmpty();
    }
    
    private boolean esMatricula(){
        return tfEmail.getText().matches(formato);
    }
}
