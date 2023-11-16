package spglisoft.controladores;

import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.Utilidades;

public class FXMLConsultarSolicitudesController implements Initializable {
    
    private Proyecto proyecto;
    private ObservableList<SolicitudCambio> listaSolicitudes;

    @FXML
    private ComboBox<?> cbFiltro;
    @FXML
    private TableView<?> tvSolicitudesCambio;
    @FXML
    private TableColumn<?, ?> tcSolicitudes;
    @FXML
    private TableColumn<?, ?> tcDetalles;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }

    @FXML
    private void btnCambios(MouseEvent event) {
    }

    @FXML
    private void btnRegresar(MouseEvent event) {
    }

    @FXML
    private void cbSeleccionFiltro(ActionEvent event) {
    }
    
    public void inicializarInformacion(){
        try {
            Usuario responsable = UsuarioDAO.getSesion();
            this.proyecto = ProyectoDAO.obtenerProyectoResponsable(responsable);
        } catch (SQLException e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede cargar la tabla",
                    Alert.AlertType.ERROR);
        }
    }
}
