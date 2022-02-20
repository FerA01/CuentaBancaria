package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("login/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Iniciar Sesión.");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.getIcons().add( new Image(
                    getClass().getResourceAsStream( "/imagenes/icono.png" )));
            stage.show();
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void main(String[] args) {launch();}
}
