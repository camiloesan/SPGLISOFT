package spglisoft.controladores;

import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.SingletonLogin;
import spglisoft.utils.Utilidades;

public class FXMLConsultarSolicitudesController implements Initializable {
    
    private Proyecto proyecto;
    private ObservableList<SolicitudCambio> listaSolicitudes;
    
    @FXML
    private ComboBox<?> cbFiltro;
    @FXML
    private TableView<SolicitudCambio> tvSolicitudesCambio;
    @FXML
    private TableColumn tcSolicitudes;
    @FXML
    private TableColumn tcDetalles;
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        configurarTabla();
        cargarDatosLista();
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
    
    public void inicializar(Usuario responsable) {
        try {
            //Usuario responsable = UsuarioDAO.getSesion();
            this.proyecto = ProyectoDAO.obtenerProyectoResponsable(responsable);
            cargarDatosLista();
            System.out.println("EN EL INIZIALIZAR");
        } catch (Exception e) {
            System.out.println("Error inicializar");
        }
    }
    
    private void cargarDatosLista() {
        try {
            listaSolicitudes = FXCollections.observableArrayList();
            ArrayList<SolicitudCambio> solicitudesBD = SolicitudCambioDAO
                    .obtenerSolicitudes(SingletonLogin.getInstance().getNombreProyectoActual());
            listaSolicitudes.addAll(solicitudesBD);
            tvSolicitudesCambio.setItems(listaSolicitudes);
        } catch (Exception e) {
            System.out.println("Error cargarDatosLista");
            e.printStackTrace();
        }
    }
    
    private void configurarTabla(){
        this.tcSolicitudes.setCellValueFactory(new PropertyValueFactory("titulo"));
        //this.tcDetalles.setCellFactory(new PropertyValueFactory("titulo"));
    }
}
