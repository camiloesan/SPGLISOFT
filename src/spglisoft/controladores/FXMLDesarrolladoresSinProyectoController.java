/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;

/**
 * FXML Controller class
 *
 * @author conta
 */
public class FXMLDesarrolladoresSinProyectoController implements Initializable {

    @FXML
    private TableView<Desarrollador> tvDesarrolladores;
    @FXML
    private TableColumn<Desarrollador, String> colNombre;
    @FXML
    private TableColumn<Desarrollador, String> colApellidoPaterno;
    @FXML
    private TableColumn<Desarrollador, String> colApellidoMaterno;
    @FXML
    private TableColumn<Desarrollador, String> colMatricula;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        formatearTabla();
        llenarTablaDesarrolladores();
    }    

    private void formatearTabla(){
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
    }
    public void llenarTablaDesarrolladores(){
        tvDesarrolladores.getItems().clear();
        List<Desarrollador> listaDesarrolladores = new ArrayList<>();
        try{
            listaDesarrolladores=UsuarioDAO.obtenerDesarrolladoresSinProyecto();
        } catch (SQLException e){
            Alertas.mostrarAlertaErrorConexionBD();
        }
        tvDesarrolladores.getItems().addAll(listaDesarrolladores);
    }


    @FXML
    private void btnCancelar(ActionEvent event) {
        spglisoft.utils.SidebarRepresentante.irMenuDesarrolladores();
    }

    @FXML
    private void btnAnadirDesarrollador(ActionEvent event) {
        if(esElementoSeleccionado()){
            Desarrollador desarrollador = tvDesarrolladores.getSelectionModel().getSelectedItem();
            try{
                UsuarioDAO.añadirDesarrolladorProyecto(desarrollador.getIdDesarrollador(), SingletonLogin.getInstance().getIdProyectoActual());
                Alertas.mostrarAlertaExito();
                formatearTabla();
                llenarTablaDesarrolladores();
                MainStage.changeView("/spglisoft/vistas/FXMLRPDesarrolladores.fxml", 1000, 600);
            } catch (SQLException e){
                Alertas.mostrarAlertaErrorConexionBD();
            }
        } else{
            Alertas.mostrarAlertaElementoNoSeleccionado();
        }
    }
    
    private boolean esElementoSeleccionado() {
        return tvDesarrolladores.getSelectionModel().getSelectedItem() != null;
    }
}
