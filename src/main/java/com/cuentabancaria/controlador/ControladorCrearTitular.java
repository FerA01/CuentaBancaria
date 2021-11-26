package com.cuentabancaria.controlador;
import com.cuentabancaria.modelo.titular.Organizacion;
import com.cuentabancaria.modelo.titular.Persona;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ControladorCrearTitular implements Initializable {
    @FXML
    public AnchorPane panelTitular;
    @FXML
    private Button botonCrearTitular;
    @FXML
    private ComboBox<String> comboBoxTipoTitular;
    @FXML
    private ComboBox<String> comboBoxTipoCuenta;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getComboBoxTipoCuenta().setPromptText("Elige una cuenta");
        getComboBoxTipoCuenta().setItems(FXCollections.observableArrayList("Caja de ahorro", "Cuenta corriente"));
        getComboBoxTipoTitular().setPromptText("Elige el titular");
        getComboBoxTipoTitular().setItems(FXCollections.observableArrayList("Personal", "Organización"));
    }

    public Button getBotonCrearTitular() {return botonCrearTitular;}
    public void setBotonCrearTitular(Button botonCrearTitular){this.botonCrearTitular = botonCrearTitular;}
    public ComboBox<String> getComboBoxTipoTitular() {return comboBoxTipoTitular;}
    public void setComboBoxTipoTitular(ComboBox<String> comboBoxTipoTitular) {this.comboBoxTipoTitular = comboBoxTipoTitular;}
    public ComboBox<String> getComboBoxTipoCuenta() {return comboBoxTipoCuenta;}
    public void setComboBoxTipoCuenta(ComboBox<String> comboBoxTipoCuenta) {this.comboBoxTipoCuenta = comboBoxTipoCuenta;}
    public AnchorPane getPanelTitular() {return panelTitular;}
    public void setPanelTitular(AnchorPane panelTitular) {this.panelTitular = panelTitular;}

    private Titular obtenerTitular(Persona persona){
        boolean isPersona = (getComboBoxTipoTitular().getValue()).equals(persona.tipoTitular());
        if (!isPersona){
            return null;
        }
        return new Persona();
    }
    private Titular obtenerTitular(Organizacion organizacion){
        boolean isOrganizacion = (getComboBoxTipoTitular().getValue()).equals(organizacion.tipoTitular());
        if (!isOrganizacion){
            return null;
        }
        return new Organizacion();
    }
    private void tipoTitular(String tipo) {
        Persona persona = new Persona();
        Organizacion organizacion = new Organizacion();
        if (tipo.equals("Personal")) {
            System.out.println("Tipo personal");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TitularPersona.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error perro");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } if (tipo.equals("Organización")){
            System.out.println("Tipo organizacion");
            try{
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TitularOrganizacion.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            }catch (IOException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
    }
    @FXML
    public void accionBoton(){
        String titular = getComboBoxTipoTitular().getValue();
        System.out.println("Tipo personal");
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/TitularPersona.fxml"));
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
    public void accionCrearCuenta(){
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
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
/*
    try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/TitularPersona.fxml"));
                Parent parent = fxmlLoader.load();
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(scene);
                stage.showAndWait();
            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error perro");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
 */