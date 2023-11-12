package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spglisoft.modelo.dao.ParticipantesDAO;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.Utilidades;


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
        try {
            FXMLLoader loader = Utilidades.cargarVista("/spglisoft/vistas/FXMLRegistrarDefecto.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarDefectoController controlador = loader.getController();
            controlador.inicializarDatos();
            
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
