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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.Usuario;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLRegistrarDefectoController implements Initializable {
    
    private Participantes Participantes;

    @FXML
    private TextField tfNombreDefecto;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<?> cbTipoDefecto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btRegistrarDefecto(ActionEvent event) {
    }

    @FXML
    private void btVolver(ActionEvent event) {
    }
    
    public void inicializarDatos(Participantes participantes){
        
    }    
}
