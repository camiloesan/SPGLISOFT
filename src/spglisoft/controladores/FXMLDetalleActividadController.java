package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.utils.Constantes;
import spglisoft.utils.SingletonLogin;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDetalleActividadController implements Initializable {
    @FXML
    private Label lblFechaInicio;
    @FXML
    private Label lblEstado;
    @FXML
    private Label lblFechaFin;
    @FXML
    private Label lblDescripcion;
    @FXML
    private Label lblEsfuerzo;
    @FXML
    private Label lblTitulo;
    @FXML
    private Label lblDesarrollador;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializarInformacion();
    }

    private void inicializarInformacion() {
        Actividad actividad = (Actividad) MainStage.getUserData();
        lblTitulo.setText(actividad.getNombre());
        lblEstado.setText(actividad.getNombreEstado());
        lblFechaInicio.setText(actividad.getFechaInicio());
        lblFechaFin.setText(actividad.getFechaFin());
        lblEsfuerzo.setText(String.valueOf(actividad.getEsfuerzoMinutos()));
        lblDescripcion.setText(actividad.getDescripcion());
        lblDesarrollador.setText(actividad.getNombreCompletoDesarrollador());
    }

    @FXML
    private void btnRegresar() {
        if (SingletonLogin.getInstance().getTipoUsuario().equals(Constantes.USUARIO_DESARROLLADOR)) {
            MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
        } else {
            MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
        }
    }
}
