/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLActividadesDesarrolladorController implements Initializable, ISidebarDesarrollador {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void btnActividades() {

    }

    @Override
    public void btnCambios() {

    }

    @Override
    public void btnDefectos() {
        spglisoft.utils.SidebarDesarrollador.irMenuDefectos();
    }

    @Override
    public void btnInformacionProyecto() {

    }

    @Override
    public void btnCerrarSesion() {
        spglisoft.utils.SidebarDesarrollador.cerrarSesionDesarrollador();
    }

    @FXML
    private void btnVerDetalleActividad() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLDetalleActividad.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
