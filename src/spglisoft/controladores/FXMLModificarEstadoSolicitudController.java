package spglisoft.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.utils.Alertas;
import spglisoft.utils.Constantes;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLModificarEstadoSolicitudController implements Initializable {
    @FXML
    ComboBox<String> cbEstadoSolicitud;
    @FXML
    Label lblDescripcion;

    SolicitudCambio solicitudCambio;

    private final static ObservableList<String> observableListCbEstadosSolicitud =
            FXCollections.observableArrayList("Pendiente" ,"En Proceso", "Concluida");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearCbEstadoSolicitud();
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        solicitudCambio = (SolicitudCambio) MainStage.getUserData();
        lblDescripcion.setText("Descripcion: " + solicitudCambio.getDescripcion());
        cbEstadoSolicitud.getSelectionModel().select(solicitudCambio.getIdEstadoSolicitud());
    }

    private void formatearCbEstadoSolicitud() {
        cbEstadoSolicitud.getItems().addAll(observableListCbEstadosSolicitud);
        cbEstadoSolicitud.getSelectionModel().select(2);
    }

    @FXML
    private void btnGuardar() {
        int estado = Constantes.ESTADO_SOLICITUD_PENDIENTE;
        switch (cbEstadoSolicitud.getSelectionModel().getSelectedItem()) {
            case "Pendiente":
                estado = Constantes.ESTADO_SOLICITUD_PENDIENTE;
                break;
            case "En proceso":
                estado = Constantes.ESTADO_SOLICITUD_EN_PROCESO;
                break;
            case "Concluida":
                estado = Constantes.ESTADO_SOLICITUD_CONCLUIDA;
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
        MainStage.changeView("/spglisoft/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
}
