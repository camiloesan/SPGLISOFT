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

    public static void irMenuDefectos() {

    }

    public static void irMenuDesarrolladores() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPDesarrolladores.fxml", 1000, 600);
    }

    public static void irMenuInformacionProyeto() {
        MainStage.changeView("/spglisoft/vistas/FXMLDetalleProyecto.fxml", 1000, 600);
    }

    public static void irMenuProyectos() {
        MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
    }
    
    public static void irConsultarSolicitudesCambio(){
        MainStage.changeView("/spglisoft/vistas/FXMLConsultarSolicitudes.fxml", 1000, 600);
    }
}
