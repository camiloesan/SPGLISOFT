package spglisoft.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.utils.Alertas;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLModificarEstadoSolicitudController implements Initializable {
    @FXML
    ComboBox<String> cbEstadoSolicitud;

    private final static ObservableList<String> observableListCbEstadosSolicitud =
            FXCollections.observableArrayList("Aceptado" ,"Rechazado", "Sin asignar");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearCbEstadoSolicitud();

        //todo get data
    }

    private void formatearCbEstadoSolicitud() {
        cbEstadoSolicitud.getItems().addAll(observableListCbEstadosSolicitud);
        cbEstadoSolicitud.getSelectionModel().select(2);
    }

    @FXML
    private void btnGuardar() {
        SolicitudCambioDAO solicitudCambioDAO = new SolicitudCambioDAO();
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

        Object todoObj = MainStage.getUserData();//REMINDER TO GET THE OBJECT SOLICITUDCAMBIO
        try {
            solicitudCambioDAO.actualizarEstadoSolicitud(estado, 1); //TEMPORAL FIXXXXXXXXX
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
