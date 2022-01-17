package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class TitularPersona extends Application {
    @Override
    public void start(Stage stage){
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/titular/TitularPersona.fxml"));
        try {
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Cuenta bancaria");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) { launch(); }
}
