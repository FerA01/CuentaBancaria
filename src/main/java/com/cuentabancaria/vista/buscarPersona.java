package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class buscarPersona extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/buscar/buscarPersona.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Buscar persona");
            stage.setScene(scene);
            stage.show();
        }catch (IOException excepcion){
            excepcion.getStackTrace();
        }
    }
    public static void main(String[] args) { launch(); }
}
