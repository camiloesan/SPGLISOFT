package spglisoft.utils;

import spglisoft.controladores.FXMLRPActividadesController;
import spglisoft.controladores.MainStage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SidebarRepresentante {
    public static void irMenuActividades() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPActividades.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void irMenuCambios() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPCambios.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void irMenuDefectos() {

    }

    public static void irMenuDesarrolladores() {

    }

    public static void irMenuInformacionProyeto() {

    }

    public static void irMenuProyectos() {
        try {
            MainStage.changeView("/spglisoft/vistas/FXMLRPMenuPrincipal.fxml", 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(FXMLRPActividadesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
