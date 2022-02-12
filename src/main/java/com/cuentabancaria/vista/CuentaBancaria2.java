package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class CuentaBancaria2 extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/cuentaBancaria/CuentaBancaria.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Cuenta Bancaria");
            stage.show();
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
    }
    public static void main(String[] args) { launch(); }
}
