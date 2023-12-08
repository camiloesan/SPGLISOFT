/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.ActividadDAO;
import spglisoft.modelo.pojo.Cambio;
import spglisoft.modelo.pojo.EstadoActividad;
import spglisoft.utils.SidebarRepresentante;

public class FXMLRPCambiosController implements Initializable, ISidebarRPButtons {

    @FXML
    private ComboBox<EstadoActividad> cbFiltro;
    @FXML
    private TableView<Cambio> tvCambios;
    @FXML
    private TableColumn<Cambio, String> colTitulo;
    @FXML
    private TableColumn<Cambio, String> colFechaInicio;
    @FXML
    private TableColumn<Cambio, String> colFechaFin;
    @FXML
    private TableColumn<Cambio, String> colEstado;
    private ObservableList<EstadoActividad> estadosActividad;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaCambios();
        estadosActividad = FXCollections.observableArrayList();
        List<EstadoActividad> estados = ActividadDAO.obtenerEstadosActividad();
        estadosActividad.addAll(estados);
        cbFiltro.setItems(estadosActividad);
        cbFiltro.getSelectionModel().select(1);
    }

    private void formatearTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("fechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void llenarTablaCambios() {
        tvCambios.getItems().clear();
        List<Cambio> listaCambios = new ArrayList<>();
        /*
        try {
            //todo dao function
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvCambios.getItems().addAll(listaCambios);
        *
         */
    }

    @Override
    @FXML
    public void btnActividades() {
        spglisoft.utils.SidebarRepresentante.irMenuActividades();
    }

    @Override
    public void btnCambios() {
        //vista actual
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
        SidebarRepresentante.irConsultarSolicitudesCambio();
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
    private void btnVerDetalleCambio() {
        MainStage.changeView("/spglisoft/vistas/FXMLDetalleCambio.fxml", 1000, 600);
    }
}
