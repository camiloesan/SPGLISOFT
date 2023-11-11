/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.ActividadDAO;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.utils.SingletonLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLActividadesDesarrolladorController implements Initializable, ISidebarDesarrollador {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaActividades();
    }

    private void formatearTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("FechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void llenarTablaActividades() {
        ActividadDAO actividadDAO = new ActividadDAO();
        tvActividades.getItems().clear();
        List<Actividad> listaActividades;
        try {
            listaActividades = actividadDAO
                    .obtenerActividadesAsignadasPorDesarrollador(SingletonLogin.getInstance().getUser().getUserId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        tvActividades.getItems().addAll(listaActividades);
    }

    @Override
    public void btnActividades() {

    }

    @Override
    public void btnCambios() {

    }

    @Override
    public void btnDefectos() {
        spglisoft.utils.SidebarDesarrollador.irMenuDefectos();
    }

    @Override
    public void btnSolicitudesCambio() {

    }

    @Override
    public void btnInformacionProyecto() {

    }

    @Override
    public void btnCerrarSesion() {
        spglisoft.utils.SidebarDesarrollador.cerrarSesionDesarrollador();
    }

    @FXML
    private void btnVerDetalleActividad() {
        if (tvActividades.getSelectionModel().getSelectedItem() != null) {
            Actividad actividad = tvActividades.getSelectionModel().getSelectedItem();
            MainStage.changeView("/spglisoft/vistas/FXMLDetalleActividad.fxml", 1000, 600, actividad);
        }
    }
}
