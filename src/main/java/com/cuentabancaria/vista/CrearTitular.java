package com.cuentabancaria.vista;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class CrearTitular extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/titular/CrearTitular.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setTitle("Elegir tipos");
            stage.setScene(scene);
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args){launch();}
}
