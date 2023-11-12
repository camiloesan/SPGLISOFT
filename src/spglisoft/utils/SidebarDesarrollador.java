package spglisoft.utils;

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

    }

    public static void irMenuInformacionProyecto() {

    }

    public static void cerrarSesionDesarrollador() {
        SingletonLogin.cleanDetails();
        MainStage.changeView("/spglisoft/vistas/FXMLLogin.fxml", 600, 400);
    }
}
