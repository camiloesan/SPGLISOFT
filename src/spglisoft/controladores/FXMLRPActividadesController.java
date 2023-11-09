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
public class FXMLRPActividadesController implements Initializable, ISidebarRPButtons {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @Override
    public void btnActividades() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void btnCambios() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void btnDefectos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void btnDesarrolladores() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void btnInformacionProyecto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    @FXML
    public void btnRegresar() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void btnAsignarActividad() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLAsignarActividad.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
