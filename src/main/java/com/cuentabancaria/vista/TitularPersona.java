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
        FXMLLoader loader =  new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/TitularPersona.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setTitle("Cuenta bancaria");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) { launch(); }
}
