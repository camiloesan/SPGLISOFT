package spglisoft.utils;

import javafx.scene.control.Alert;

public class Alertas {
    static Alert alert;

    public Alertas() {

    }

    public static void mostrarAlertaErrorConexionBD() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Error de conexión");
        alert.setContentText("No se pudo establecer conexión con la base de datos, inténtelo de nuevo más tarde");
        alert.showAndWait();
    }

    public static void mostrarAlertaExito() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Operación satisfactoria");
        alert.setContentText("Se ha realizado la operación correctamente");
        alert.showAndWait();
    }
}