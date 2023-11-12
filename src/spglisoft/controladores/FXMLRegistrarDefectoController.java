/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spglisoft.modelo.ResultadoOperacion;
import spglisoft.modelo.dao.DefectoDAO;
import spglisoft.modelo.dao.ParticipantesDAO;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Defecto;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLRegistrarDefectoController implements Initializable {
    
    private Participantes participantes;
    private Usuario sesion;
    ObservableList<String> opciones = FXCollections.observableArrayList(
            "Javascript",
            "Interfaz");

    @FXML
    private TextField tfNombreDefecto;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<String> cbTipoDefecto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbTipoDefecto.setItems(opciones);
        cbTipoDefecto.setValue("JavaScript");
    }    

    @FXML
    private void btRegistrarDefecto(ActionEvent event) {
        String titulo = tfNombreDefecto.getText();
        String descripcion = taDescripcion.getText();
        String tipoDefecto = cbTipoDefecto.getValue();
        Defecto nuevoDefecto = new Defecto();
        nuevoDefecto.setTitulo(titulo);
        nuevoDefecto.setDescripcion(descripcion);
        nuevoDefecto.setTipo(tipoDefecto);
        registrarDefecto(nuevoDefecto, participantes);
    }

    @FXML
    private void btVolver(ActionEvent event) {
        Utilidades.mostrarAlertaSimple("Registrar defecto", "Registro cancelado",
                Alert.AlertType.INFORMATION);
        cerrarStage();
    }
    
    public void inicializarDatos(){
        try {
            this.sesion = UsuarioDAO.getSesion();
            this.participantes = ParticipantesDAO.obtenerProyecto(sesion);
        } catch (SQLException e) {
            System.out.println("Error");
        } 
    }
    
    private void registrarDefecto(Defecto defecto, Participantes participantes){
        if (tfNombreDefecto.getText().trim().isEmpty() || taDescripcion.getText().trim().isEmpty()) {
            Utilidades.mostrarAlertaSimple("Formulario", 
                    "Campos faltantes", Alert.AlertType.INFORMATION);
        }else{
            try {
                ResultadoOperacion resultadoRegistro = DefectoDAO.registrarDefecto(defecto, participantes);
                if (!resultadoRegistro.isError()) {
                    Utilidades.mostrarAlertaSimple("Registro", resultadoRegistro.getMensaje(),
                            Alert.AlertType.INFORMATION);
                    cerrarStage();
                }
            } catch (SQLException e) {
                
            }
        }
    }
    
    private void cerrarStage(){
            Stage escenario = (Stage) tfNombreDefecto.getScene().getWindow();
            escenario.close();
        }
}
