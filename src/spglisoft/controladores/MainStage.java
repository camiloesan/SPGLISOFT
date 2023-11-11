/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spglisoft.controladores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 * @author camilo
 */
public class MainStage extends Application {
    private static Scene scene;
    
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainStage.class.getResource(fxml));
        return fxmlLoader.load();
    }
    
    public static void changeView(String url, int width, int height) {
        Stage currentStage = (Stage) scene.getWindow();
        configureStage(currentStage, width, height);
        try {
            MainStage.setRoot(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    private static void configureStage(Stage stage, int width, int height) {
        stage.setWidth(width);
        stage.setHeight(height);
        stage.centerOnScreen();
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("/spglisoft/vistas/FXMLLogin.fxml"), 600, 400);
        stage.setTitle("SPGLISOFT");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch();
    }
}
