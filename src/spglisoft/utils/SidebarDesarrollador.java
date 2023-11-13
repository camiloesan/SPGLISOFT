package spglisoft.utils;

import javafx.scene.control.Alert;
import spglisoft.controladores.MainStage;

public class SidebarDesarrollador {
    public SidebarDesarrollador() {

    }

    public static void irMenuActividades() {
        MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
    }

    public static void irMenuCambios() {

    }

    public static void irMenuDefectos() {
        MainStage.changeView("/spglisoft/vistas/FXMLDefectosDesarrollador.fxml", 1000, 600);
    }

    public static void irMenuSolicituesCambio() {
        MainStage.changeView("/spglisoft/vistas/FXMLSolicitudesCambio.fxml", 1000, 600);
    }

    public static void irMenuInformacionProyecto() {

    }

    public static void cerrarSesionDesarrollador() {
        Utilidades.mostrarAlertaSimple("Mensaje",
                "Sesion finalizada", Alert.AlertType.INFORMATION);
        SingletonLogin.cleanDetails();
        MainStage.changeView("/spglisoft/vistas/FXMLLogin.fxml", 600, 400);
    }
}
