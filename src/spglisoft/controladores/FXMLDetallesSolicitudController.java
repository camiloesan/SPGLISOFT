/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import spglisoft.modelo.pojo.SolicitudCambio;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLDetallesSolicitudController implements Initializable {
    
    private SolicitudCambio solicitud;
    private String nombreSolicitante;

    @FXML
    private Label lbTituloSolicitud;
    @FXML
    private Label lbImpactoCambio;
    @FXML
    private Label lbNombreSolicitante;
    @FXML
    private Label lbFechaSolicitud;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private TextArea taRazonCambio;
    @FXML
    private TextArea taAccionPropuesta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        taDescripcion.setEditable(false);
        taRazonCambio.setEditable(false);
        taAccionPropuesta.setEditable(false);
    }    

    @FXML
    private void btVolver(ActionEvent event) {
        cerrarVentana();
    }
    
    public void iniciarInformacion(SolicitudCambio solicitud) {
        try {
            this.solicitud = solicitud;
            //this.nombreSolicitante = SolicitudCambioDAO.obtenerSolicitante(solicitud.getIdProponente());
            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
            String fechaString = formato.format(solicitud.getFechaSolicitud());
            //lbTituloSolicitud.setText(solicitud.getTitulo());
            //lbImpactoCambio.setText("Impacto del cambio: " + solicitud.getImpacto());
            lbNombreSolicitante.setText("Nombre del solicitante: " + nombreSolicitante);
            lbFechaSolicitud.setText("Fecha de registro de la solicitud: " + fechaString);
            taDescripcion.setText(solicitud.getDescripcion());
            taRazonCambio.setText(solicitud.getRazonCambio());
            taAccionPropuesta.setText(solicitud.getAccionPropuesta());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void cerrarVentana() {
        Stage escena = (Stage) lbFechaSolicitud.getScene().getWindow();
        escena.close();
    }
}
