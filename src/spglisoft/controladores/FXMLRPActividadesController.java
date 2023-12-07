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
    private ComboBox<String> cbFiltroActividades;

    private final static ObservableList<String> observableListCbFiltroActividades =
            FXCollections.observableArrayList("Asignadas", "No Asignadas");
    @FXML
    private Button btnEliminarActividad;
    @FXML
    private Button btnDesasignarActividad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        formatearComboFiltro();
        llenarTablaActividadesNoAsignadas();
    }

    public void formatearComboFiltro() {
        cbFiltroActividades.getItems().addAll(observableListCbFiltroActividades);
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
        String filtro = cbFiltroActividades.getSelectionModel().getSelectedItem();
        switch (filtro) {
            case "Asignadas":
                llenarTablaActividadesAsignadas();
                btnAsignarActividad.setVisible(false);
                btnDesasignarActividad.setVisible(true);
                btnEliminarActividad.setVisible(false);
                break;
            case "No Asignadas":
                llenarTablaActividadesNoAsignadas();
                btnAsignarActividad.setVisible(true);
                btnDesasignarActividad.setVisible(false);
                btnEliminarActividad.setVisible(true);
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
    private void testConsultarSolicitudes(MouseEvent event) {
        //spglisoft.utils.SidebarRepresentante.irConsultarSolicitudesCambio();
    }

    @FXML
    private void irSolicitudesCambio(MouseEvent event) {
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
