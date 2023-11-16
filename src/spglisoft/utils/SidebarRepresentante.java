package spglisoft.utils;

import spglisoft.controladores.MainStage;

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
}
