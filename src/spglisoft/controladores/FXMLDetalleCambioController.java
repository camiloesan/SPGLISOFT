package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDetalleCambioController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnRegresar() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600);
    }
}
