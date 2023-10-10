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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.dao.UsersDAO;
import spglisoft.pojo.User;
import spglisoft.utils.SingletonLogin;

/**
 * FXML Controller class
 *
 * @author camilo
 */
public class FXMLGestionUsuariosController implements Initializable {
    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private TableColumn<User, Integer> columnId;
    @FXML
    private TableColumn<User, String> columnName;
    @FXML
    private TableColumn<User, String> columnLastName;
    @FXML
    private TableColumn<User, String> columnUserType;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        columnId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        columnUserType.setCellValueFactory(new PropertyValueFactory<>("tipo_usuario"));
        fillUsersTable();
    }    
    
    private void fillUsersTable() {
        UsersDAO usersDAO = new UsersDAO();
        tableViewUsers.getItems().clear();
        try {
            tableViewUsers.getItems().addAll(usersDAO.getUsersList());
        } catch (SQLException ex) {
            Logger.getLogger(FXMLGestionUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void btnLogOut() {
        SingletonLogin.cleanDetails();
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLLogin.fxml", 600, 400);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnAddUser() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLAnadirUsuario.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnDeleteUser() {
        UsersDAO usersDAO = new UsersDAO();
        String email = tableViewUsers.getSelectionModel().getSelectedItem().getEmail();
        
        if (email != null) {
            try {
              usersDAO.deleteUserByEmail(email);
            } catch (SQLException ex) {
                Logger.getLogger(FXMLGestionUsuariosController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        fillUsersTable();
    }
}
