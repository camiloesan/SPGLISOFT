package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import spglisoft.modelo.pojo.Cambio;
import spglisoft.modelo.pojo.SolicitudCambio;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDetalleCambioController implements Initializable {
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblTipoCambio;
    @FXML
    private Label lblDesarrollador;
    @FXML
    private Label lblDescripcion;

    private Cambio cambioActual;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        cambioActual = (Cambio) MainStage.getUserData();
        lblNombre.setText("Detalles del cambio [" + cambioActual.getNombre() + "]");
        lblTipoCambio.setText(cambioActual.getTipoCambio());
        lblDesarrollador.setText(cambioActual.getNombreCompletoDesarrollador());
        lblDescripcion.setText(cambioActual.getDescripcion());
    }

    @FXML
    private void btnRegresar() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600, FXMLRPCambiosController.SolicitudSeleccionada);
    }
}
