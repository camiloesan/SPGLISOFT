/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FXMLAsignarActividadController implements Initializable {
    @FXML
    private TableView<Desarrollador> tvDesarrolladores;
    @FXML
    private TableColumn<Desarrollador, String> colApellidoPaterno;
    @FXML
    private TableColumn<Desarrollador, String> colNombre;
    @FXML
    private TableColumn<Desarrollador, String> colApellidoMaterno;
    @FXML
    private TableColumn<Desarrollador, String> colMatricula;
    @FXML
    private Label lblTitulo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarInformacion();
        formatearTabla();
        llenarTabla();
    }

    private void inicializarInformacion() {
        Actividad actividad = (Actividad) MainStage.getUserData();
        lblTitulo.setText("Asignar Desarrollador Actividad [" + actividad.getNombre() + "]");
    }

    private void formatearTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }

    private void llenarTabla() {
        tvDesarrolladores.getItems().clear();
        List<Desarrollador> listaDesarrolladores = new ArrayList<>();
        try {
            listaDesarrolladores = UsuarioDAO
                    .obtenerDesarrolladoresPorIdProyecto(SingletonLogin.getInstance().getIdProyectoActual());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvDesarrolladores.getItems().addAll(listaDesarrolladores);
    }

    @FXML
    private void btnAsignarActividad() {
        Actividad actividad = (Actividad) MainStage.getUserData();
        if (esElementoSeleccionado()) {
            Desarrollador desarrollador = tvDesarrolladores.getSelectionModel().getSelectedItem();
            try {
                UsuarioDAO.asignarActividadADesarrollador(actividad.getIdActividad(),
                        desarrollador.getIdDesarrollador());
                Alertas.mostrarAlertaExito();
                MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
            } catch (SQLException e) {
                Alertas.mostrarAlertaErrorConexionBD();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    private boolean esElementoSeleccionado() {
        return tvDesarrolladores.getSelectionModel().getSelectedItem() != null;
    }

    @FXML
    private void btnCancelar() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
    }
}
