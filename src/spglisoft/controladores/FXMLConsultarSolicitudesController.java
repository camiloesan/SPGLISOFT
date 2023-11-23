package spglisoft.controladores;

import java.io.IOException;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.SingletonLogin;
import spglisoft.utils.Utilidades;

public class FXMLConsultarSolicitudesController implements Initializable {
    
    private Proyecto proyecto;
    private ObservableList<SolicitudCambio> listaSolicitudes;
    ObservableList<String> opciones = FXCollections.observableArrayList(
            "Mas recientes",
            "Mas antiguas");
    
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
        cbFiltro.setValue(" ");
    }

    @FXML
    private void btnCambios(MouseEvent event) {
    }

    @FXML
    private void btnRegresar(MouseEvent event) {
    }

    @FXML
    private void cbSeleccionFiltro(ActionEvent event) {
        String opcionSeleccionada = cbFiltro.getValue();
        if ("Mas recientes".equals(opcionSeleccionada)) {
            ordenarPorMasRecientes();
        } else if ("Mas antiguas".equals(opcionSeleccionada)) {
            ordenarPorMasAntiguas();
        }
        
    }
    
    private void ordenarPorMasRecientes(){
        ObservableList<SolicitudCambio> listaActual = FXCollections.observableArrayList(tvSolicitudesCambio.getItems());
        listaActual.sort(Comparator.comparing(SolicitudCambio::getFechaSolicitud).reversed());
        tvSolicitudesCambio.setItems(listaActual);
    }
    
    private void ordenarPorMasAntiguas(){
        ObservableList<SolicitudCambio> listaActual = FXCollections.observableArrayList(tvSolicitudesCambio.getItems());
        listaActual.sort(Comparator.comparing(SolicitudCambio::getFechaSolicitud));
        tvSolicitudesCambio.setItems(listaActual);
    }
    
    public void inicializar(Usuario responsable) {
        try {
            this.proyecto = ProyectoDAO.obtenerProyectoResponsable(responsable);
            cargarDatosLista();
        } catch (Exception e) {
            e.printStackTrace();
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
    
    private void configurarTabla() {
    tcSolicitudes.setCellValueFactory(new PropertyValueFactory<>("titulo"));

    tcDetalles.setCellFactory(new Callback<TableColumn<SolicitudCambio, Button>, TableCell<SolicitudCambio, Button>>() {
        @Override
        public TableCell<SolicitudCambio, Button> call(TableColumn<SolicitudCambio, Button> param) {
            return new TableCell<SolicitudCambio, Button>() {
                private final Button button = new Button("Detalles");

                {
                    button.setOnAction(event -> {
                        SolicitudCambio solicitud = getTableView().getItems().get(getIndex());
                        System.out.println("Detalles de la solicitud: " + solicitud.getTitulo());
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
            escenario.setTitle("Consultar detalles de la solicitud de cambio");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error", " No se pueden mostrar los detalles",
                    Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
}