package com.cuentabancaria.controlador;
import com.cuentabancaria.modelo.cuentas.CajaAhorro;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.cuentas.CuentaCorriente;
import com.cuentabancaria.modelo.titular.Organizacion;
import com.cuentabancaria.modelo.titular.Persona;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private ComboBox<Titular> comboBoxTipoTitular = new ComboBox<>();
    @FXML
    private ComboBox<CuentaBancaria> comboBoxTipoCuenta = new ComboBox<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getComboBoxTipoCuenta().setPromptText("Elige una cuenta");
        getComboBoxTipoCuenta().setItems(FXCollections.observableArrayList(new CajaAhorro(), new CuentaCorriente()));
        getComboBoxTipoTitular().setPromptText("Elige el titular");
        getComboBoxTipoTitular().setItems(FXCollections.observableArrayList(new Persona(), new Organizacion()));

    }

    public Button getBotonCrearTitular() {return botonCrearTitular;}
    public void setBotonCrearTitular(Button botonCrearTitular){this.botonCrearTitular = botonCrearTitular;}
    public ComboBox<Titular> getComboBoxTipoTitular() {return comboBoxTipoTitular;}
    public void setComboBoxTipoTitular(ComboBox<Titular> comboBoxTipoTitular) {this.comboBoxTipoTitular = comboBoxTipoTitular;}
    public ComboBox<CuentaBancaria> getComboBoxTipoCuenta() {return comboBoxTipoCuenta;}
    public void setComboBoxTipoCuenta(ComboBox<CuentaBancaria> comboBoxTipoCuenta) {this.comboBoxTipoCuenta = comboBoxTipoCuenta;}
    public AnchorPane getPanelTitular() {return panelTitular;}
    public void setPanelTitular(AnchorPane panelTitular) {this.panelTitular = panelTitular;}

    private void tipoTitular(String tipo) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/"+tipo));
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
    }
    private String obtenerFxmlTitular(String titular){ return "/com/cuentabancaria/vista/" + titular; }
    @FXML
    public void accionBoton(){
        CuentaBancaria cuentaBancaria = getComboBoxTipoCuenta().getValue();
        Titular tipoTitular = getComboBoxTipoTitular().getValue();
        if (tipoTitular instanceof Persona){
            tipoTitular = crearTitularPersona();
        }else {
            tipoTitular = crearTitularOrganizacion();
        }
        cuentaBancaria.setTitular(tipoTitular);
    }
    private Persona crearTitularPersona(){
        TextField nombre = (TextField) getPanelTitular().getChildren().get(6);
        TextField segundoNombre = (TextField) getPanelTitular().getChildren().get(7);
        TextField apellido = (TextField) getPanelTitular().getChildren().get(8);
        TextField dni = (TextField) getPanelTitular().getChildren().get(9);
        TextField cuit = (TextField) getPanelTitular().getChildren().get(10);
        DatePicker fechaNacimiento = (DatePicker) getPanelTitular().getChildren().get(11);
        Persona nuevoTitular = new Persona(nombre.getText()
                                            , segundoNombre.getText()
                                            , apellido.getText()
                                            , Integer.parseInt(dni.getText())
                                            , cuit.getText()
                                            , fechaNacimiento.getValue());
        System.out.println(nuevoTitular.tipoTitular());
        return nuevoTitular;
    }
    private Organizacion crearTitularOrganizacion(){
        TextField nombre = (TextField) getPanelTitular().getChildren().get(4);
        TextField tipoOrganizacion = (TextField) getPanelTitular().getChildren().get(5);
        TextField numeroCuit = (TextField) getPanelTitular().getChildren().get(6);
        DatePicker fechaCreacion = (DatePicker) getPanelTitular().getChildren().get(7);
        Organizacion nuevoTitular = new Organizacion(numeroCuit.getText()
                                                    , nombre.getText()
                                                    , tipoOrganizacion.getText()
                                                    , fechaCreacion.getValue());
        System.out.println(nuevoTitular.tipoTitular());
        return nuevoTitular;
    }
    @FXML
    public void combo(){
        Titular titular = getComboBoxTipoTitular().getValue();
        CuentaBancaria cuentaBancaria = getComboBoxTipoCuenta().getValue();
        cuentaBancaria.setTitular(titular);
        try {
            AnchorPane panel = FXMLLoader.load(getClass().getResource(obtenerFxmlTitular(titular.obtenerFxml())));
            getPanelTitular().getChildren().clear();
            getPanelTitular().getChildren().setAll(panel.getChildren());
        }catch (IOException exception){

        }
        System.out.println(cuentaBancaria);
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