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
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Actividad;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;
import sun.applet.Main;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author camilo
 */
public class FXMLAsignarActividadController implements Initializable {
    @FXML
    private TableView<Usuario> tvDesarrolladores;

    @FXML
    private TableColumn<Usuario, String> colApellidoPaterno;

    @FXML
    private TableColumn<Usuario, String> colNombre;

    @FXML
    private TableColumn<Usuario, String> colApellidoMaterno;

    @FXML
    private TableColumn<Usuario, String> colMatricula;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTabla();
    }

    private void formatearTabla() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }

    private void llenarTabla() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        tvDesarrolladores.getItems().clear();
        List<Usuario> listaDesarrolladores = new ArrayList<>();
        try {
            listaDesarrolladores = usuarioDAO
                    .obtenerDesarrolladoresPorProyecto(SingletonLogin.getInstance().getNombreProyectoActual());
        } catch (SQLException e) {
            Alertas.mostrarAlertaErrorConexionBD();
            e.printStackTrace();
        }
        tvDesarrolladores.getItems().addAll(listaDesarrolladores);
    }

    @FXML
    private void btnAsignarActividad() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Actividad actividad = (Actividad)MainStage.getUserData();
        if (tvDesarrolladores.getSelectionModel().getSelectedItem() != null) {
            Usuario usuario = tvDesarrolladores.getSelectionModel().getSelectedItem();
            try {
                usuarioDAO.asignarActividadADesarrollador(actividad.getIdActividad(), usuario.getUserId());
                Alertas.mostrarAlertaExito();
                MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
            } catch (SQLException e) {
                Alertas.mostrarAlertaErrorConexionBD();
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnCancelar() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
    }
}
