package spglisoft.controladores;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spglisoft.utils.Utilidades;

public class FXMLDefectosDesarrolladorController implements Initializable, ISidebarDesarrollador {
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    public void btnVerDetalleDefecto() {
        MainStage.changeView("/spglisoft/vistas/FXMLDetalleDefecto.fxml", 1000, 600);
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
    public void btnSolicitudesCambio() {
        spglisoft.utils.SidebarDesarrollador.irMenuSolicituesCambio();
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
        irRegistrarDefecto();
    }
    
    private void irRegistrarDefecto() {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/spglisoft/vistas/FXMLRegistrarDefecto.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarDefectoController controlador = loader.getController();
            controlador.iniciarDatos();
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Registrar defecto");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error",
                    "No se puede mostrar la ventana", Alert.AlertType.ERROR);
        }
    }
}
