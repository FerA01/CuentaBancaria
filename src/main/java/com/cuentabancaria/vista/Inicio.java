package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Inicio extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("Inicio.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Cuenta bancaria");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add( new Image(
                    getClass().getResourceAsStream( "/imagenes/icono.png" )));
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
