/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package spglisoft.controladores;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import spglisoft.modelo.pojo.Defecto;
import spglisoft.modelo.pojo.Desarrollador;
import spglisoft.modelo.pojo.TipoDefecto;
import spglisoft.utils.Alertas;
import spglisoft.utils.SingletonLogin;
import spglisoft.utils.Utilidades;

/**
 * FXML Controller class
 *
 * @author lecap
 */
public class FXMLRegistrarDefectoController implements Initializable {
    
    private Desarrollador desarrollador;
    private ObservableList<TipoDefecto> tiposDefectos = FXCollections.observableArrayList();

    @FXML
    private TextField tfNombreDefecto;
    @FXML
    private TextArea taDescripcion;
    @FXML
    private ComboBox<TipoDefecto> cbTipoDefecto;
    @FXML
    private TextField tfEsfuerzoEstimado;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cargarInformacionTipoDefecto();
        if (!tiposDefectos.isEmpty()) {
            cbTipoDefecto.setValue(tiposDefectos.get(0));
        }
        tfEsfuerzoEstimado.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                tfEsfuerzoEstimado.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }    

    @FXML
    private void btRegistrarDefecto(ActionEvent event) {
        registro();
    }

    @FXML
    private void btVolver(ActionEvent event) {
        Utilidades.mostrarAlertaSimple("Registrar defecto", "Registro cancelado",
                Alert.AlertType.INFORMATION);
        cerrarStage();
    }
    
    private void registro() {
        if (validarEsfuerzo()) {
            TipoDefecto tipoDefecto = (TipoDefecto) cbTipoDefecto.getSelectionModel().getSelectedItem();
            String nombreDefecto = tfNombreDefecto.getText().trim();
            String descripcion = taDescripcion.getText().trim();
            int idTipoDefecto = tipoDefecto.getIdTipoDefecto();
            String esfuerzoEstimado = tfEsfuerzoEstimado.getText().trim();
            int esfuerzoEstimadoInteger = Integer.parseInt(esfuerzoEstimado);
        
            Defecto nuevoDefecto = new Defecto();
            nuevoDefecto.setIdProyecto(desarrollador.getIdProyecto());
            nuevoDefecto.setIdDesarrollador(desarrollador.getIdDesarrollador());
            nuevoDefecto.setNombreDefectoString(nombreDefecto);
            nuevoDefecto.setDescripcion(descripcion);
            nuevoDefecto.setTipoDefecto(idTipoDefecto);
            nuevoDefecto.setEsfuerzoEstimado(esfuerzoEstimadoInteger);
            nuevoDefecto.setEstadoDefecto(2);
        
            registrarNuevoDefecto(nuevoDefecto);
        } else {
            Alertas.mostrarAlertaCamposFaltantes();
        }
    }
    
    private boolean validarEsfuerzo() {
        String esfuerzo = tfEsfuerzoEstimado.getText().trim();
        return !esfuerzo.isEmpty();
    }
    private void registrarNuevoDefecto(Defecto defecto) {
        if (!camposFaltantes()) {
            ResultadoOperacion resultado = new ResultadoOperacion();
            try {
                resultado = DefectoDAO.registrarNuevoDefecto(defecto);
                if (!resultado.isError()) {
                    Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                            Alert.AlertType.INFORMATION);
                    cerrarStage();
                }
            } catch (SQLException e) {
                Utilidades.mostrarAlertaSimple("Registro", resultado.getMensaje(),
                        Alert.AlertType.ERROR);
                e.printStackTrace();
            }
        } else {
            Alertas.mostrarAlertaCamposFaltantes();
        }
    }
    
    public void iniciarDatos() {
        this.desarrollador = SingletonLogin.getInstance().getDesarrollador();
    }
    
    private void cerrarStage() {
            Stage escenario = (Stage) tfNombreDefecto.getScene().getWindow();
            escenario.close();
        }
    
    private void cargarInformacionTipoDefecto() {
        try {
            List<TipoDefecto> lista = DefectoDAO.obtenerTiposDefecto();
            tiposDefectos.addAll(lista);
            cbTipoDefecto.setItems(tiposDefectos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean camposFaltantes() {
        return tfNombreDefecto.getText().trim().isEmpty() || taDescripcion.getText()
                .trim().isEmpty();
    }
}
