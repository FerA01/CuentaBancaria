package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class HistorialTransacciones extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/cuentaBancaria/HistorialTransacciones.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Historial Transacciones");
        stage.show();
    }
    public static void main(String[] args) {launch();}
}
