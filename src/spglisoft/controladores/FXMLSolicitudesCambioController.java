/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.controladores;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spglisoft.utils.Utilidades;

/**
 *
 * @author lecap
 */
public class FXMLSolicitudesCambioController implements Initializable, ISidebarDesarrollador{

    @FXML
    private TableView<?> tvSolicitudesCambio;
    @FXML
    private TableColumn<?, ?> tcProyecto;
    @FXML
    private TableColumn<?, ?> tcTitulo;
    @FXML
    private TableColumn<?, ?> tcFechaSolicitud;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @Override
    public void btnActividades() {
        spglisoft.utils.SidebarDesarrollador.irMenuActividades();
    }

    @Override
    @FXML
    public void btnCambios() {
        
    }

    @Override
    @FXML
    public void btnDefectos() {
        spglisoft.utils.SidebarDesarrollador.irMenuDefectos();
    }

    @Override
    @FXML
    public void btnSolicitudesCambio() {
        
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
    private void btRegistrarSolicitud(ActionEvent event) {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/spglisoft/vistas/FXMLRegistrarSolicitudCambio.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLRegistrarSolicitudCambioController controlador = loader.getController();
            controlador.inicializarDatos();
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Registrar solicitud de cambio");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la ventana",
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}
