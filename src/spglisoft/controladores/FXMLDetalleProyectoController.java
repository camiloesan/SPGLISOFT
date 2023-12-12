package spglisoft.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.utils.SidebarDesarrollador;
import spglisoft.utils.SidebarRepresentante;
import spglisoft.utils.SingletonLogin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class FXMLDetalleProyectoController implements Initializable {

    @FXML
    private Label lblFechaInicio;

    @FXML
    private Label lblFechaFin;

    @FXML
    private Label lblDescripcion;

    @FXML
    private Label lblRepresentanteProyecto;

    @FXML
    private Label lblNombreProyecto;

    @FXML
    private Label lblEstadoProyecto;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        formatearInformacion();
    }

    private void formatearInformacion() {
        Proyecto proyecto = new Proyecto();
        try {
            if (SingletonLogin.getInstance().getDesarrollador() != null) {
                proyecto = ProyectoDAO
                        .obtenerProyectoPorIdDesarrollador(SingletonLogin.getInstance().getDesarrollador().getIdDesarrollador());
            } else {
                proyecto = SingletonLogin.getInstance().getProyectoActual();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        lblNombreProyecto.setText("Proyecto: " + proyecto.getNombreProyecto());
        lblDescripcion.setText(proyecto.getDescripcion());
        lblFechaInicio.setText(proyecto.getFechaInicio());
        lblFechaFin.setText(proyecto.getFechaFin());
        lblEstadoProyecto.setText(proyecto.getNombreEstado());
        lblRepresentanteProyecto.setText(proyecto.getNombreRepresentante()
                + " " + proyecto.getApellidoPaternoRepresentante()
                + " " + proyecto.getApellidoMaternoRepresentante());
    }

    @FXML
    private void btnRegresar() {
        if (SingletonLogin.getInstance().getDesarrollador() != null) {
            SidebarDesarrollador.irMenuActividades();
        } else {
            SidebarRepresentante.irMenuActividades();
        }
    }
}
