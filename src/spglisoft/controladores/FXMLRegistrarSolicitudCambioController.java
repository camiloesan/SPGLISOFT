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
import spglisoft.modelo.dao.ParticipantesDAO;
import spglisoft.modelo.dao.SolicitudCambioDAO;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Participantes;
import spglisoft.modelo.pojo.SolicitudCambio;
import spglisoft.modelo.pojo.Usuario;
import spglisoft.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLRegistrarSolicitudCambioController implements Initializable {
    
    private Usuario sesion;
    private Participantes participantes;
    ObservableList<String> opciones = FXCollections.observableArrayList(
            "Bajo impacto",
            "Moderado impacto",
            "Alto impacto",
            "Critico impacto",
            "No aplicable");

    @FXML
    private TextField tfTitulo;
    @FXML
    private ComboBox <String>cbImpactoCambio;
    @FXML
    private TextArea taAcccionPropuesta;
    @FXML
    private TextArea taDescripcionCambio;
    @FXML
    private TextArea taRazonCambio;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cbImpactoCambio.setItems(opciones);
        cbImpactoCambio.setValue("Bajo impacto");
    }

    @FXML
    private void btVolver(ActionEvent event) {
        Utilidades.mostrarAlertaSimple("Registrar solicitud de cambio", 
                "Registro cancelado", Alert.AlertType.INFORMATION);
        cerrarStage();
    }
    
    private void registrarSolicitud(Participantes participantes, SolicitudCambio solicitud){
        if (tfTitulo.getText().trim().isEmpty() || taAcccionPropuesta.getText().trim().isEmpty()
                || taDescripcionCambio.getText().trim().isEmpty() || taRazonCambio.getText().trim().isEmpty()){
            Utilidades.mostrarAlertaSimple("Formulario", 
                    "Campos faltantes", Alert.AlertType.INFORMATION);
        }else{
            ResultadoOperacion resultado = new ResultadoOperacion();
            try {
                resultado = SolicitudCambioDAO.registrarSolicitud(participantes, solicitud);
                if (!resultado.isError()) {
                    Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                            Alert.AlertType.INFORMATION);
                    cerrarStage();
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                        Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void btRegistrarSolicitud(ActionEvent event) {
        String titulo = tfTitulo.getText();
        String descripcion = taDescripcionCambio.getText();
        String accionPropuesta = taAcccionPropuesta.getText();
        String razonCambio = taRazonCambio.getText();
        String impacto = cbImpactoCambio.getValue();
        SolicitudCambio nuevaSolicitud = new SolicitudCambio();
        nuevaSolicitud.setAccionPropuesta(accionPropuesta);
        nuevaSolicitud.setImpacto(impacto);
        nuevaSolicitud.setRazonCambio(razonCambio);
        nuevaSolicitud.setDescripcion(descripcion);
        nuevaSolicitud.setTitulo(titulo);
        
        registrarSolicitud(participantes, nuevaSolicitud);
    }
    
    public void inicializarDatos(){
        try {
            this.sesion = UsuarioDAO.getSesion();
            this.participantes = ParticipantesDAO.obtenerProyecto(sesion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private void cerrarStage(){
        Stage escenario = (Stage) tfTitulo.getScene().getWindow();
        escenario.close();
    }
}
