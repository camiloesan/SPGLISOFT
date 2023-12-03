/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.utils.Alertas;
import spglisoft.utils.Constantes;
import spglisoft.utils.SingletonLogin;
import java.sql.SQLException;
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
        tfEmail.setText("zs21013861");
        tfPassword.setText("groyper");
    }
    
    @FXML
    private void btnLogin() {
        iniciarSesion();
    }
    
    private void iniciarSesion(){
        if (!camposVacios()) {
            String usuario = tfEmail.getText().trim();
            String contrasena = tfPassword.getText().trim();
            if (esMatricula()) {
                try {
                    Desarrollador desarrollador = UsuarioDAO.iniciarSesionDesarrollador(usuario, contrasena);
                    if (desarrollador != null) {
                        SingletonLogin singletonLogin = SingletonLogin.getInstance();
                        singletonLogin.setDesarrollador(desarrollador);
                        singletonLogin.setTipoUsuario(Constantes.USUARIO_DESARROLLADOR);
                        MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
                    } else {
                        Alertas.mostrarAlertaLoginFallido();
                    }
                } catch (SQLException e) {
                    Alertas.mostrarAlertaErrorConexionBD();
                }
            } else if (usuario.matches("\\d+")) {
                try {
                    Representante representante = UsuarioDAO.iniciarSesionRepresentante(usuario, contrasena);
                    if (representante != null) {
                        SingletonLogin singletonLogin = SingletonLogin.getInstance();
                        singletonLogin.setRepresentante(representante);
                        singletonLogin.setTipoUsuario(Constantes.USUARIO_REPRESENTANTE);
                        MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
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
