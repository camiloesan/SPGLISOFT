/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author conta
 */
public class FXMLDesarrolladoresSinProyectoController implements Initializable {

    @FXML
    private TableView<?> tvActividades;
    @FXML
    private TableColumn<?, ?> colTitulo;
    @FXML
    private TableColumn<?, ?> colFechaInicio;
    @FXML
    private TableColumn<?, ?> colFechaFin;
    @FXML
    private TableColumn<?, ?> colEstado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void btnAnadirDesarrollador(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        MainStage.changeView("/spglisoft/vistas/FXMLRPDesarrolladores.fxml", 1000, 600);
    }
    
}
