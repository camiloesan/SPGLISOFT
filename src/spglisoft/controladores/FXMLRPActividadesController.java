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

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaActividadesNoAsignadas();
    }

    private void formatearTabla() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colFechaInicio.setCellValueFactory(new PropertyValueFactory<>("FechaInicio"));
        colFechaFin.setCellValueFactory(new PropertyValueFactory<>("fechaFin"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    private void llenarTablaActividadesNoAsignadas() {
        ActividadDAO actividadDAO = new ActividadDAO();
        tvActividades.getItems().clear();
        System.out.println(SingletonLogin.getInstance().getNombreProyectoActual());
        List<Actividad> listaActividades;
        try {
            listaActividades = actividadDAO.obtenerActividadesNoAsignadasPorNombreProyecto(SingletonLogin.getInstance().getNombreProyectoActual());
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
        spglisoft.utils.SidebarRepresentante.irMenuCambios();
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
    private void btnVerDetalleActividad() {

    }

    @FXML
    private void btnAsignarActividad() {
        MainStage.changeView("/spglisoft/vistas/FXMLAsignarActividad.fxml", 1000, 600);
    }
}
