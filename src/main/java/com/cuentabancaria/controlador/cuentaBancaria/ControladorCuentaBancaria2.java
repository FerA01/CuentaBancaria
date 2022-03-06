package com.cuentabancaria.controlador.cuentaBancaria;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class ControladorCuentaBancaria2 implements Initializable {
    //Atributos
    @FXML private Label etiquetaNombreUsuario;
    @FXML private Label etiquetaNumeroCbu;
    @FXML private Label etiquetaExtraccionesDisponibles;
    @FXML private Label etiquetaSaldoDisponible;
    @FXML private Button botonSeleccionarCuentaBancaria;
    @FXML private Button botonHistorialTransacciones;
    @FXML private Button botonVerDatosCuenta;
    @FXML private ComboBox<String> comboBoxRealizarOperacion;
    private ControladorBaseDato baseDato;
    private Titular titular;
    private CuentaBancaria cuentaBancaria;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getComboBoxRealizarOperacion().getItems().addAll("Depósito", "Extracción");
    }

    //Getters y Setters
    public Label getEtiquetaNombreUsuario() { return etiquetaNombreUsuario; }
    public void setEtiquetaNombreUsuario(Label etiquetaNombreUsuario) { this.etiquetaNombreUsuario = etiquetaNombreUsuario; }
    public Label getEtiquetaNumeroCbu() { return etiquetaNumeroCbu;}
    public void setEtiquetaNumeroCbu(Label etiquetaNumeroCbu) { this.etiquetaNumeroCbu = etiquetaNumeroCbu; }
    public Label getEtiquetaExtraccionesDisponibles() { return etiquetaExtraccionesDisponibles; }
    public void setEtiquetaExtraccionesDisponibles(Label etiquetaExtraccionesDisponibles) { this.etiquetaExtraccionesDisponibles = etiquetaExtraccionesDisponibles; }
    public Label getEtiquetaSaldoDisponible() { return etiquetaSaldoDisponible; }
    public void setEtiquetaSaldoDisponible(Label etiquetaSaldoDisponible) { this.etiquetaSaldoDisponible = etiquetaSaldoDisponible; }
    public Button getBotonSeleccionarCuentaBancaria() { return botonSeleccionarCuentaBancaria; }
    public void setBotonSeleccionarCuentaBancaria(Button botonSeleccionarCuentaBancaria) { this.botonSeleccionarCuentaBancaria = botonSeleccionarCuentaBancaria; }
    public Button getBotonHistorialTransacciones() { return botonHistorialTransacciones ;}
    public void setBotonHistorialTransacciones(Button botonHistorialTransacciones) { this.botonHistorialTransacciones = botonHistorialTransacciones; }
    public Button getBotonVerDatosCuenta() { return botonVerDatosCuenta; }
    public void setBotonVerDatosCuenta(Button botonVerDatosCuenta) { this.botonVerDatosCuenta = botonVerDatosCuenta; }
    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }
    public ComboBox<String> getComboBoxRealizarOperacion() { return comboBoxRealizarOperacion; }
    public void setComboBoxRealizarOperacion(ComboBox<String> comboBoxRealizarOperacion) { this.comboBoxRealizarOperacion = comboBoxRealizarOperacion; }
    public Titular getTitular() { return titular; }
    public void setTitular(Titular titular) { this.titular = titular; }
    public CuentaBancaria getCuentaBancaria() { return cuentaBancaria; }
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) { this.cuentaBancaria = cuentaBancaria; }

    //Metodos
    private void cambiarTextoEtiqueta(Label etiqueta, String texto){ etiqueta.setText(texto); }

    //Eventos
    @FXML
    public void accionRealizarOperacion(){}
    @FXML
    public void accionHistorialTransacciones(){}
    @FXML
    public void accionVerDatosCuenta(){}
    @FXML
    private void accionSeleccionarOperacion(){
        String operacionSeleccionada = getComboBoxRealizarOperacion().getSelectionModel().getSelectedItem();

    }

    private void abrirVentanaOperacion(String operacion) throws IOException{
        switch (operacion){
            case "Depósito":
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/"));
        }
    }
}
