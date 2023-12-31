/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLRPCambiosController implements Initializable, ISidebarRPButtons {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @Override
    public void btnActividades() {
        spglisoft.utils.SidebarRepresentante.irMenuActividades();
    }

    @Override
    public void btnCambios() {
    }

    @Override
    public void btnDefectos() {
    }

    @Override
    public void btnDesarrolladores() {
    }

    @Override
    public void btnInformacionProyecto() {
    }

    @Override
    public void btnRegresar() {
        spglisoft.utils.SidebarRepresentante.irMenuProyectos();
    }

    @FXML
    private void btnVerDetalleCambio() {
        MainStage.changeView("/spglisoft/vistas/FXMLDetalleCambio.fxml", 1000, 600);
    }
}
