/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;
import spglisoft.utils.Utilidades;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/*
 * Creador: Camilo Espejo Sánchez.
 * Fecha de creación: Dec 14, 2023.
 * Descripción: Caso de uso: Asignar actividad, permite la asignacion de
 * una actividad a un desarrollador en particular, para que el sea cargo de esa,
 * muestra una tabla con los desarrolladores para despues seleccionar al que se
 * vaya a asignar.
 */

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
        Desarrollador desarrollador = tvDesarrolladores.getSelectionModel().getSelectedItem();
        if (desarrollador != null) {
            try {
                UsuarioDAO.asignarActividadADesarrollador(actividad.getIdActividad(),
                        desarrollador.getIdDesarrollador());
                Utilidades.mostrarAlertaSimple("Éxito",
                        "Se ha asignado esta actividad al desarrollador: "
                                + desarrollador.getNombre() + " "
                                + desarrollador.getApellidoPaterno() + " "
                                + desarrollador.getApellidoMaterno(),
                        Alert.AlertType.INFORMATION);
                MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
            } catch (SQLException e) {
                Alertas.mostrarAlertaErrorConexionBD();
            }
        } else {
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }

    @FXML
    private void btnCancelar() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
    }
}
