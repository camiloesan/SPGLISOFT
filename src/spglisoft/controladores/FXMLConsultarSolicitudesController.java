package spglisoft.controladores;

import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.modelo.pojo.Representante;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.utils.*;

/**
 * Creador: Martin Emmanuel Cruz Carmona.
 * Fecha de creacion: Sep 27, 2023.
 * Descripcion: Caso de uso-Consultar solicitudes de cambio, permite al representate
 * del proyecto consultar las solicitudes de cambio.
 */

public class FXMLConsultarSolicitudesController implements Initializable, ISidebarRPButtons {
    
        private ObservableList<SolicitudCambio> listaSolicitudes;
    ObservableList<String> opciones = FXCollections.observableArrayList(
            "Pendiente",
            "En proceso",
            "Concluida");
    private FilteredList<SolicitudCambio> filteredList;
    
    @FXML
    private ComboBox<String> cbFiltro;
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
        cbFiltro.setItems(opciones);
        cbFiltro.setValue("Filtrar");
        
         filteredList = new FilteredList<>(listaSolicitudes, p -> true);
         
        cbFiltro.valueProperty().addListener((observable, oldValue, newValue) ->
                filteredList.setPredicate(solicitud -> {
                    if (newValue == null || newValue.isEmpty() || newValue.equals("Estado de la solicitud")) {
                        return true; 
                    }
                    int estadoSeleccionado = obtenerCodigoEstado(newValue);
                    return solicitud.getIdEstadoSolicitud()== estadoSeleccionado;
                })
        );
        
        SortedList<SolicitudCambio> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(tvSolicitudesCambio.comparatorProperty());
        tvSolicitudesCambio.setItems(sortedList);
    }
    
    private int obtenerCodigoEstado(String nombreEstado) {
        switch (nombreEstado) {
            case "Pendiente":
                return 1;
            case "En proceso":
                return 2;
            case "Concluida":
                return 3;
            default:
                return 0; 
        }
    }

    @FXML
    private void cbSeleccionFiltro(ActionEvent event) {
        
    }
    
    public void iniciarDatos(Representante representante){
        try {
            cargarDatosLista();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cargarDatosLista(){
        try {
            listaSolicitudes = FXCollections.observableArrayList();
            ArrayList<SolicitudCambio> solicitudesBD = SolicitudCambioDAO.obtenerSolicitudes(SingletonLogin
                    .getInstance().getIdProyectoActual());
            listaSolicitudes.addAll(solicitudesBD);
            tvSolicitudesCambio.setItems(listaSolicitudes);
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
    }
    
    private void configurarTabla() {
    tcSolicitudes.setCellValueFactory(new PropertyValueFactory<>("nombreSolicitud"));

    tcDetalles.setCellFactory(new Callback<TableColumn<SolicitudCambio, Button>, TableCell<SolicitudCambio, Button>>() {
        @Override
        public TableCell<SolicitudCambio, Button> call(TableColumn<SolicitudCambio, Button> param) {
            return new TableCell<SolicitudCambio, Button>() {
                private final Button button = new Button("Detalles");

                {
                    button.setOnAction(event -> {
                        SolicitudCambio solicitud = getTableView().getItems().get(getIndex());
                        irConsultarDetalles(solicitud);
                    });
                }

                @Override
                protected void updateItem(Button item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(button);
                    }
                }
            };
        }
    });
    }
    
    private void irConsultarDetalles(SolicitudCambio solicitud) {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/spglisoft/vistas/FXMLDetallesSolicitud.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            FXMLDetallesSolicitudController controlador = loader.getController();
            controlador.iniciarInformacion(solicitud);
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Detalles");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", " No se pueden mostrar los detalles",
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    @FXML
    private void btnModificarEstadoSolicitud() {
        if (tvSolicitudesCambio.getSelectionModel().getSelectedItem() != null) {
            SolicitudCambio solicitud = tvSolicitudesCambio.getSelectionModel().getSelectedItem();
            MainStage.changeView("/spglisoft/vistas/FXMLModificarEstadoSolicitud.fxml", 1000, 600, solicitud);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnCambios() {
        SolicitudCambio solicitudCambio = tvSolicitudesCambio.getSelectionModel().getSelectedItem();
        if (solicitudCambio != null) {
            MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600, solicitudCambio);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @Override
    public void btnActividades() {
        SidebarRepresentante.irMenuActividades();
    }

    @Override
    public void btnDefectos() {

    }

    @Override
    public void btnDesarrolladores() {
        SidebarRepresentante.irMenuDesarrolladores();
    }

    @Override
    public void btnSolicitudesCambio() {

    }

    @Override
    public void btnInformacionProyecto() {
        SidebarRepresentante.irMenuInformacionProyeto();
    }

    @Override
    public void btnRegresar() {
        SidebarRepresentante.irMenuProyectos();
    }
}
