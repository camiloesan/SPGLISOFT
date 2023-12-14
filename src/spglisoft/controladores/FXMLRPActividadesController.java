/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.ActividadDAO;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.modelo.pojo.EstadoActividad;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import spglisoft.utils.Utilidades;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Recupera las actividades propias del desarrollador con la
 * sesion actual.
 */
public class FXMLRPActividadesController implements Initializable, ISidebarRPButtons {
    @FXML
    private TableView<Actividad> tvActividades;
    @FXML
    private TableColumn<Actividad, String> colFechaInicio;
    @FXML
    private TableColumn<Actividad, String> colEstado;
    @FXML
    private TableColumn<Actividad, String> colTitulo;
    @FXML
    private TableColumn<Actividad, String> colFechaFin;
    @FXML
    private Button btnAsignarActividad;
    @FXML
    private ComboBox<EstadoActividad> cbFiltroActividades;
    @FXML
    private Button btnEliminarActividad;
    @FXML
    private Button btnDesasignarActividad;
    private ObservableList<EstadoActividad> estadosActividad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        formatearComboFiltro();
        llenarTablaActividades();
    }

    public void formatearComboFiltro() {
        estadosActividad = FXCollections.observableArrayList();
        List<EstadoActividad> estados = ActividadDAO.obtenerEstadosActividad();
        estadosActividad.addAll(estados);
        cbFiltroActividades.setItems(estadosActividad);
        cbFiltroActividades.getSelectionModel().select(1);
    }

    private void formatearTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("nombreEstado"));
    }

    @FXML
    private void llenarTablaActividades() {
        tvActividades.getItems().clear();
        List<Actividad> listaActividades = new ArrayList<>();
        try {
            listaActividades = ActividadDAO
                    .obtenerActividadesPorIdProyecto(SingletonLogin.getInstance().getIdProyectoActual(),
                            cbFiltroActividades.getSelectionModel().getSelectedItem().getIdEstado());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvActividades.getItems().addAll(listaActividades);
    }

    @Override
    public void btnActividades() {
    }

    @Override
    public void btnDefectos() {
    }

    @Override
    @FXML
    public void btnDesarrolladores() {
        spglisoft.utils.SidebarRepresentante.irMenuDesarrolladores();
    }

    @Override
    @FXML
    public void btnSolicitudesCambio() {
        spglisoft.utils.SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @Override
    @FXML
    public void btnInformacionProyecto() {
        spglisoft.utils.SidebarRepresentante.irMenuInformacionProyeto();
    }

    @Override
    @FXML
    public void btnRegresar() {
        spglisoft.utils.SidebarRepresentante.irMenuProyectos();
    }

    @FXML
    private void btnVerDetalleActividad() {
        if (esElementoSeleccionado()) {
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            MainStage.changeView("/spglisoft/vistas/FXMLDetalleActividad.fxml", 1000, 600, actividad);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnAsignarActividad() {
        if (esElementoSeleccionado()) {
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            MainStage.changeView("/spglisoft/vistas/FXMLAsignarActividad.fxml", 1000, 600, actividad);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    private boolean esElementoSeleccionado() {
        return tvActividades.getSelectionModel().getSelectedItem() != null;
    }

    private void irSolicitudesCambio() {
        spglisoft.utils.SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @FXML
    private void btnEliminarActividad(ActionEvent event) {
        if(esElementoSeleccionado()){
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            try{
                ActividadDAO.eliminarActividad(actividad.getIdActividad());
                Alertas.mostrarAlertaExito();
                formatearTabla();
                llenarTablaActividades();
            } catch (SQLException e){
                Alertas.mostrarAlertaElementoNoSeleccionado();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnDesasignarActividad(ActionEvent event) {
        if(esElementoSeleccionado()){
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            try{
                ActividadDAO.desasignarActividad(actividad.getIdActividad());
                Alertas.mostrarAlertaExito();
                formatearTabla();
                llenarTablaActividades();
            } catch (SQLException e){
                Alertas.mostrarAlertaElementoNoSeleccionado();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnRegistrarActividad(ActionEvent event) {
        irRegistrarActividad();
    }
    
    private void irRegistrarActividad() {
        try {
            FXMLLoader loader = Utilidades.cargarVista("/spglisoft/vistas/FXMLRegistrarActividad.fxml");
            Parent vista = loader.load();
            Scene escena = new Scene(vista);
            
            Stage escenario = new Stage();
            escenario.setScene(escena);
            escenario.setTitle("Registrar actividad");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException e) {
            Utilidades.mostrarAlertaSimple("Error",
                    "No se puede mostrar la ventana", Alert.AlertType.ERROR);
        }
    }
}
