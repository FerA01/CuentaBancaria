package com.cuentabancaria.controlador;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ControladorCuentaBancaria implements Initializable {
    @FXML private Label saldoDisponible;
    @FXML private TextField ingresarMonto;
    @FXML private Button botonAceptar;
    private Controlador controladorPrincipal;

    public Label getSaldoDisponible() { return saldoDisponible; }
    public void setSaldoDisponible(Label saldoDisponible) { this.saldoDisponible = saldoDisponible; }
    public TextField getIngresarMonto() { return ingresarMonto; }
    public void setIngresarMonto(TextField ingresarMonto) { this.ingresarMonto = ingresarMonto; }
    public Button getBotonAceptar() { return botonAceptar; }
    public void setBotonAceptar(Button botonAceptar) { this.botonAceptar = botonAceptar; }
    public Controlador getControladorPrincipal() { return controladorPrincipal; }
    public void setControladorPrincipal(Controlador controladorPrincipal) { this.controladorPrincipal = controladorPrincipal; }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { getBotonAceptar().setDisable(true); }

    @FXML
    public void accionIngresarMonto(){
        try {
            getBotonAceptar().setDisable(false);
            float obtenerMontoIngresado = Float.parseFloat(getIngresarMonto().getText());
            montoIngresadoIncorrecto(obtenerMontoIngresado);
            System.out.println("Monto ingresado: $" + obtenerMontoIngresado);

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }
    @FXML
    public void comprobarIngresoNumero(){
        boolean isVacio = getIngresarMonto().getText().isEmpty();
        if (!isVacio){ getBotonAceptar().setDisable(false); }
        else { getBotonAceptar().setDisable(true); }
    }

    private void montoIngresadoIncorrecto(float valor) throws SQLException {
        float limiteMinimo = getControladorPrincipal().getCuentaBancaria().getLimiteMinimoCuenta();
        CuentaBancaria cuentaBancaria = getControladorPrincipal().getCuentaBancaria();
        if (valor <= limiteMinimo){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ingresa un monto mayor a $" + limiteMinimo);
            alert.showAndWait();
        }else{
            cuentaBancaria.tipoTransaccion(cuentaBancaria.getTipoTransaccion(), valor);
            getControladorPrincipal().obtenerDatosTitularCuenta();
            cambiarTextoSaldoDisponible(cuentaBancaria.getSaldo());
        }
    }
    public void cambiarTextoSaldoDisponible(float monto){ getSaldoDisponible().setText(String.valueOf(monto)); }
}
