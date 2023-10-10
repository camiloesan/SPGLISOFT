/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import spglisoft.dao.UsersDAO;
import spglisoft.pojo.User;

/**
 * FXML Controller class
 *
 * @author camilo
 */
public class FXMLAnadirUsuarioController implements Initializable {

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfEmail;
    @FXML
    private ComboBox<String> cbUserType;
    @FXML
    private PasswordField tfPassword;
    @FXML
    private TextField tfLastname;
    @FXML
    private GridPane gridPaneDeveloper;
    @FXML
    private TextField tfStudentId;
    @FXML
    private Button btnContinue;
    private final static ObservableList<String> observableListComboItemsUserType =
            FXCollections.observableArrayList("Desarrollador",
                    "Representante de Proyecto");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboBox();
    }
    
    private void fillComboBox() {
        cbUserType.setItems(observableListComboItemsUserType);
    }
    
    @FXML
    public void btnReturnToMainMenu() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLGestionUsuarios.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void cbUserType(ActionEvent event) {
        btnContinue.setVisible(true);
        
        if (cbUserType.getValue().equals("Desarrollador")) {
            gridPaneDeveloper.setVisible(true);
        } else {
            gridPaneDeveloper.setVisible(false);
        }
    }
    
    private void saveUser() {
        UsersDAO usersDAO = new UsersDAO();
        User user = new User();
        
        String name = tfName.getText().trim();
        String lastname = tfLastname.getText().trim();
        String email = tfEmail.getText().trim();
        String password = tfPassword.getText().trim();
        String studentId = tfStudentId.getText().trim();
        String usertype;
        
        if (cbUserType.getValue().equals("Desarrollador")) {
            usertype = "desarrollador";
        } else {
            usertype = "representante_proyecto";
            studentId = null;
        }
        
        user.setNombre(name);
        user.setApellidos(lastname);
        user.setEmail(email);
        user.setContrasena(password);
        user.setTipo_usuario(usertype);
        user.setMatricula(studentId);
        
        try {
            usersDAO.addUser(user);
        } catch (SQLException ex) {
            Logger.getLogger(FXMLAnadirUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnContinue(ActionEvent event) {
        saveUser();
    }
}
