package com.cuentabancaria.controlador;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.buscar.ControladorBuscarPersona;
import com.cuentabancaria.controlador.cuentaBancaria.ControladorTransacciones;
import com.cuentabancaria.modelo.cuentas.CajaAhorro;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.cuentas.Transaccion;
import com.cuentabancaria.modelo.titular.Persona;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
public class Controlador implements Initializable {
    private static ControladorBaseDato baseDato;
    @FXML private Button botonRetirar;
    @FXML private Button botonDepositar;
    @FXML private Button botonHistorialTransacciones;
    private CuentaBancaria cuentaBancaria;
    private Titular titular;
    @FXML private Button botonCrearCuenta;
    @FXML private Button botonBuscarCuenta;
    @FXML private Button botonEliminarCuenta;
    @FXML private Label labelSaldo;
    @FXML private Label labelNumeroCuit;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBaseDato(null);
    }

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
    public static ControladorBaseDato getBaseDato() { return baseDato; }
    public static void setBaseDato(ControladorBaseDato baseDato) { Controlador.baseDato = baseDato; }
    public Button getBotonRetirar() { return botonRetirar; }
    public void setBotonRetirar(Button botonRetirar) { this.botonRetirar = botonRetirar; }
    public Button getBotonDepositar() { return botonDepositar; }
    public void setBotonDepositar(Button botonDepositar) { this.botonDepositar = botonDepositar; }
    public Button getBotonHistorialTransacciones() { return botonHistorialTransacciones; }

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
        getCuentaBancaria().setTipoTransaccion("deposito");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/DepositarRetirar.fxml"));
            Parent parent = fxmlLoader.load();
            ControladorCuentaBancaria cuentaBancaria = fxmlLoader.getController();
            cuentaBancaria.setControladorPrincipal(this);
            cuentaBancaria.cambiarTextoSaldoDisponible(getCuentaBancaria().getSaldo());
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
        getCuentaBancaria().setTipoTransaccion("extraccion");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/DepositarRetirar.fxml"));
            Parent parent = fxmlLoader.load();
            ControladorCuentaBancaria cuentaBancaria = fxmlLoader.getController();
            cuentaBancaria.setControladorPrincipal(this);
            cuentaBancaria.cambiarTextoSaldoDisponible(getCuentaBancaria().getSaldo());
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
    public void accionHistorialTransacciones() throws IOException, SQLException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/HistorialTransacciones.fxml"));
            Parent parent = loader.load();
            ControladorTransacciones controladorTransacciones =  (ControladorTransacciones) loader.getController();
            controladorTransacciones.setControladorPrincipal(this);
            controladorTransacciones.mostrarDatos();
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
    }

    @FXML
    public void accionBotonBuscar(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/buscar/buscarPersona.fxml"));
            Parent root = loader.load();
            ControladorBuscarPersona buscarPersona = loader.getController();
            buscarPersona.setControladorPrincipal(this);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Buscar");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException exception){

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText(exception.getMessage());
            alert.showAndWait();
        }
    }
    //Obtener saldo y cuit
    public void obtenerSaldoCuitTitular(){
        getLabelSaldo().setText(String.valueOf(getCuentaBancaria().getSaldo()));
        getLabelNumeroCuit().setText(mascaraNumeroCuit(getTitular().getNumeroCuit()));
        System.out.println((getLabelNumeroCuit().getText()).replaceFirst("(\\d{2})(\\d{8})(\\d+)", "$1-$2-$3"));
        Persona persona = (Persona) getTitular();
        System.out.println((String.valueOf(persona.getDni())).replaceFirst("(\\d{2})(\\d{3})(\\d+)", "$1.$2.$3") );
    }
    //Obtener persona
    private Persona obtenerPersona(){
        setBaseDato(new ControladorBaseDato());
        Persona persona= null;
        return persona;
    }
    private String mascaraNumeroCuit(String numeroCuit){ return (numeroCuit).replaceFirst("(\\d{2})(\\d{8})(\\d+)", "$1-$2-$3"); }
    private String mascaraDni(int dni){ return (String.valueOf(dni)).replaceFirst("(\\d{2})(\\d{3})(\\d+)", "$1.$2.$3") ; }
}