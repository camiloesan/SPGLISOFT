package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FXMLDetalleCambioController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void btnRegresar() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
