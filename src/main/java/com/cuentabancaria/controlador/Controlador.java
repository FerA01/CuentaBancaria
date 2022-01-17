package com.cuentabancaria.controlador;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class Controlador{
    @FXML private Button botonActualizar;
    public CuentaBancaria cuentaBancaria;
    public Titular titular;
    @FXML private Button botonCrearCuenta;
    @FXML private Button botonBuscarCuenta;
    @FXML private Button botonEliminarCuenta;
    @FXML private Label labelSaldo = new Label();
    @FXML private Label labelNumeroCuit = new Label();

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
    public Titular getTitular() {
        return titular;
    }
    public void setTitular(Titular titular) {
        this.titular = titular;
    }
    public Button getBotonCrearCuenta() { return this.botonCrearCuenta; }
    public void setBotonCrearCuenta(Button botonCrearCuenta) { this.botonCrearCuenta = botonCrearCuenta; }
    public Button getBotonBuscarCuenta() { return this.botonBuscarCuenta; }
    public void setBotonBuscarCuenta(Button botonBuscarCuenta) { this.botonBuscarCuenta = botonBuscarCuenta; }
    public Button getBotonEliminarCuenta() { return botonEliminarCuenta; }
    public void setBotonEliminarCuenta(Button botonEliminarCuenta) { this.botonEliminarCuenta = botonEliminarCuenta; }
    public Label getLabelSaldo() {
        return labelSaldo;
    }
    public void setLabelSaldo(Label labelSaldo) {
        this.labelSaldo = labelSaldo;
    }
    public Label getLabelNumeroCuit() {
        return labelNumeroCuit;
    }
    public void setLabelNumeroCuit(Label labelNumeroCuit) {
        this.labelNumeroCuit = labelNumeroCuit;
    }

    /**
     *  Carga una nueva ventana emergente en donde se hace el registro de una cuenta bancaria con su titular
     * **/
    @FXML
    public void accionBotonCrearCuenta(){
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/titular/CrearTitular.fxml"));
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
    public void accionBotonDepositar(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/DepositarRetirar.fxml"));
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
    public void accionBotonRetirar(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/DepositarRetirar.fxml"));
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