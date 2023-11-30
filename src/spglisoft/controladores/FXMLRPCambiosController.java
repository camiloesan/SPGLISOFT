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
import javafx.scene.input.MouseEvent;
import spglisoft.modelo.pojo.Cambio;

public class FXMLRPCambiosController implements Initializable, ISidebarRPButtons {

    @FXML
    private ComboBox<String> cbFiltro;
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

    private final static ObservableList<String> observableListCbFiltroCambios =
            FXCollections.observableArrayList("Interfaz" ,"Codigo", "Base de datos");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaCambios();
        cbFiltro.getItems().addAll(observableListCbFiltroCambios);
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
    }

    @Override
    public void btnDefectos() {
    }

    @Override
    public void btnDesarrolladores() {
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

    @FXML
    private void irSolicitudesCambio(MouseEvent event) {
        spglisoft.utils.SidebarRepresentante.irConsultarSolicitudesCambio();
    }
}
