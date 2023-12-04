package spglisoft.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import spglisoft.controladores.FXMLConsultarSolicitudesController;
import spglisoft.controladores.MainStage;
import spglisoft.modelo.dao.ProyectoDAO;
import spglisoft.modelo.dao.UsuarioDAO;
import spglisoft.modelo.pojo.Proyecto;
import spglisoft.modelo.pojo.Representante;
import spglisoft.modelo.pojo.Usuario;

public class SidebarRepresentante {
    public static void irMenuActividades() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
    }

    public static void irMenuCambios() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600);
    }

    public static void irMenuDefectos() {

    }

    public static void irMenuDesarrolladores() {

    }

    public static void irMenuInformacionProyeto() {

    }

    public static void irMenuProyectos() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
    }
    
    public static void irConsultarActividades() {
        MainStage.changeView("/spglisoft/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
    
    public static void irConsultarSolicitudesCambio(){
        try {
            Representante representante = SingletonLogin.getInstance().getRepresentante();
            if (representante != null) {
                FXMLLoader loader = Utilidades.cargarVista("/spglisoft/vistas/FXMLConsultarSolicitudes.fxml");
                Parent vista = loader.load();   
                FXMLConsultarSolicitudesController controlador = loader.getController();
                controlador.iniciarDatos(representante);
            } 
            MainStage.changeView("/spglisoft/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
        } catch (Exception e) {
            Utilidades.mostrarAlertaSimple("Error", "No se puede mostrar la ventana",
                    Alert.AlertType.ERROR);
        }
    }
}
