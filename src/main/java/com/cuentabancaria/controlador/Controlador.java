package com.cuentabancaria.controlador;
import com.cuentabancaria.modelo.titular.Organizacion;
import com.cuentabancaria.modelo.titular.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Controlador {
    public Button botonCrearCuenta;
    public Button botonBuscarCuenta;
    public Button botonEliminarCuenta;

    @FXML
    private Label welcomeText;

    public Button getBotonCrearCuenta() { return this.botonCrearCuenta; }
    public void setBotonCrearCuenta(Button botonCrearCuenta) { this.botonCrearCuenta = botonCrearCuenta; }
    public Button getBotonBuscarCuenta() { return this.botonBuscarCuenta; }
    public void setBotonBuscarCuenta(Button botonBuscarCuenta) { this.botonBuscarCuenta = botonBuscarCuenta; }
    public Button getBotonEliminarCuenta() { return botonEliminarCuenta; }
    public void setBotonEliminarCuenta(Button botonEliminarCuenta) { this.botonEliminarCuenta = botonEliminarCuenta; }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/Inicio.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
    @FXML
    public void accionBotonCrearCuenta(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/CrearTitular.fxml"));
            Parent parent = fxmlLoader.load();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}