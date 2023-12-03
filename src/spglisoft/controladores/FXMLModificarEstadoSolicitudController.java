package spglisoft.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.utils.Alertas;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLModificarEstadoSolicitudController implements Initializable {
    @FXML
    ComboBox<String> cbEstadoSolicitud;
    SolicitudCambio solicitudCambio;

    private final static ObservableList<String> observableListCbEstadosSolicitud =
            FXCollections.observableArrayList("Aceptado" ,"Rechazado", "Sin asignar");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearCbEstadoSolicitud();
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        solicitudCambio = (SolicitudCambio) MainStage.getUserData();
        cbEstadoSolicitud.getSelectionModel().select(solicitudCambio.getIdEstado());
    }

    private void formatearCbEstadoSolicitud() {
        cbEstadoSolicitud.getItems().addAll(observableListCbEstadosSolicitud);
        cbEstadoSolicitud.getSelectionModel().select(2);
    }

    @FXML
    private void btnGuardar() {
        String estado = "no_asignado";
        switch (cbEstadoSolicitud.getSelectionModel().getSelectedItem()) {
            case "Aceptado":
                estado = "aceptado";
                break;
            case "Rechazado":
                estado = "rechazado";
                break;
            case "Sin asignar":
                estado = "no_asignado";
                break;
        }

        try {
            SolicitudCambioDAO.actualizarEstadoSolicitud(estado, solicitudCambio.getIdSolicitud());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
            e.printStackTrace();
        }
    }

    @FXML
    private void btnCancelar() {
        MainStage.changeView("/vistas/FXMLDetalleSolicitud.fxml", 1000, 600);
    }
}
