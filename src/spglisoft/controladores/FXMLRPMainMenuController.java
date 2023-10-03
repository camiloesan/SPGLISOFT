/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author camilo
 */
public class FXMLRPMainMenuController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnDetails(ActionEvent event) {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPActivities.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
