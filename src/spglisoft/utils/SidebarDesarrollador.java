package spglisoft.utils;

import spglisoft.controladores.FXMLRPActividadesController;
import spglisoft.controladores.FXMLRPMenuPrincipalController;
import spglisoft.controladores.MainStage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SidebarDesarrollador {
    public SidebarDesarrollador() {

    }

    public static void irMenuActividades() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLActividadesDesarrollador.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void irMenuCambios() {

    }

    public static void irMenuDefectos() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLDefectosDesarrollador.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void irMenuInformacionProyecto() {

    }

    public static void cerrarSesionDesarrollador() {
        SingletonLogin.cleanDetails();
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLLogin.fxml", 600, 400);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
