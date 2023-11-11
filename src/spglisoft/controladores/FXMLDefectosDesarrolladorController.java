package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDefectosDesarrolladorController implements Initializable, ISidebarDesarrollador {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void btnVerDetalleDefecto() {
        MainStage.changeView("/spglisoft/vistas/FXMLDetalleDefecto.fxml", 1000, 600);
    }

    @Override
    public void btnActividades() {
        spglisoft.utils.SidebarDesarrollador.irMenuActividades();
    }

    @Override
    public void btnCambios() {

    }

    @Override
    public void btnDefectos() {

    }

    @Override
    public void btnInformacionProyecto() {

    }

    @Override
    public void btnCerrarSesion() {
        spglisoft.utils.SidebarDesarrollador.cerrarSesionDesarrollador();
    }
}
