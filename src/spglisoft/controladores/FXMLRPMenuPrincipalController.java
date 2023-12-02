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
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import spglisoft.modelo.dao.UsuarioDAO;

public class FXMLRPMenuPrincipalController implements Initializable {

    @FXML
    private TableView<Proyecto> tablaProyectos;
    @FXML
    private TableColumn<Proyecto, String> columnaNombre;
    @FXML
    private TableColumn<Proyecto, String> columnaEstado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaProyectos();
    }    
    
    private void formatearTabla() {
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreProyecto"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
    
    private void llenarTablaProyectos() {
        tablaProyectos.getItems().clear();
        List<Proyecto> listaProyectos = new ArrayList<>();
        //int userID = SingletonLogin.getInstance().getUser().getUserId();
        int id_representante = UsuarioDAO.getSesionRepresentante().getIdRepresentante();
        try {
            listaProyectos = ProyectoDAO.obtenerProyectosPorIDUsuario(id_representante);
        } catch (SQLException ex) {
            Alertas.mostrarAlertaErrorConexionBD();
            ex.printStackTrace();
        }
        tablaProyectos.getItems().addAll(listaProyectos);
    }

    @FXML
    private void btnDetails() {
        if (tablaProyectos.getSelectionModel().getSelectedItem() != null) {
            SingletonLogin.getInstance().setNombreProyectoActual(tablaProyectos.getSelectionModel().getSelectedItem().getNombreProyecto());
            MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
    
    @FXML
    private void btnLogOut() {
        SingletonLogin.cleanDetails();
        MainStage.changeView("/spglisoft/vistas/FXMLLogin.fxml", 600, 400);
    }
}
