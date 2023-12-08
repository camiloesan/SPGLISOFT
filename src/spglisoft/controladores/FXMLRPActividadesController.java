/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

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
import javafx.scene.input.MouseEvent;

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
        llenarTablaActividadesNoAsignadas();
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

    private void llenarTablaActividadesNoAsignadas() {
        tvActividades.getItems().clear();
        List<Actividad> listaActividades = new ArrayList<>();
        try {
            listaActividades = ActividadDAO
                    .obtenerActividadesNoAsignadasPorIdProyecto(SingletonLogin.getInstance().getIdProyectoActual());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvActividades.getItems().addAll(listaActividades);
    }

    private void llenarTablaActividadesAsignadas() {
        tvActividades.getItems().clear();
        List<Actividad> listaActividades = new ArrayList<>();
        try {
            listaActividades = ActividadDAO
                    .obtenerActividadesAsignadasPorIdProyecto(SingletonLogin.getInstance().getIdProyectoActual());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvActividades.getItems().addAll(listaActividades);
    }

    @FXML
    private void cbSeleccionFiltro() {
        EstadoActividad filtro = cbFiltroActividades.getSelectionModel().getSelectedItem();
        switch (filtro.toString()) {
            case "Asignada":
                llenarTablaActividadesAsignadas();
                btnAsignarActividad.setDisable(true);
                btnDesasignarActividad.setDisable(false);
                btnEliminarActividad.setDisable(true);
                break;
            case "No asignada":
                llenarTablaActividadesNoAsignadas();
                btnAsignarActividad.setDisable(false);
                btnDesasignarActividad.setDisable(true);
                btnEliminarActividad.setDisable(false);
                break;
        }
    }

    @Override
    public void btnActividades() {
    }

    @Override
    @FXML
    public void btnCambios() {
        spglisoft.utils.SidebarRepresentante.irMenuCambios();
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
    public void btnSolicitudesCambio() {
        spglisoft.utils.SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @Override
    public void btnInformacionProyecto() {
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

    @FXML
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
                llenarTablaActividadesNoAsignadas();
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
                llenarTablaActividadesAsignadas();
            } catch (SQLException e){
                Alertas.mostrarAlertaElementoNoSeleccionado();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
}
