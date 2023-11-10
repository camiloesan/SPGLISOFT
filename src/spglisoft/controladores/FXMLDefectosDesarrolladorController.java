package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;

public class FXMLDefectosDesarrolladorController implements Initializable, ISidebarDesarrollador {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btnVerDetalleDefecto() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLDetalleDefecto.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    @FXML
    public void btnActividades() {
        spglisoft.utils.SidebarDesarrollador.irMenuActividades();
    }

    @Override
    public void btnCambios() {

    }

    @Override
    @FXML
    public void btnDefectos() {

    }

    @Override
    public void btnInformacionProyecto() {

    }

    @Override
    @FXML
    public void btnCerrarSesion() {
        spglisoft.utils.SidebarDesarrollador.cerrarSesionDesarrollador();
    }

    @FXML
    private void btRegistrarDefecto(ActionEvent event) {
        
    }
}
