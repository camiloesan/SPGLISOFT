/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.utils.SingletonLogin;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLRPMenuPrincipalController implements Initializable {

    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> columnaNombre;
    @FXML
    private TableColumn<Proyecto, String> columnaEstado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaProyectos();
    }    
    
    private void formatearTabla() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
    
    private void llenarTablaProyectos() {
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        tablaProyectos.getItems().clear();
        try {
            tablaProyectos.getItems().addAll(proyectoDAO.getProyectosList());
        } catch (SQLException ex) {
        }
    }

    @FXML
    private void btnDetails(ActionEvent event) {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
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
}
