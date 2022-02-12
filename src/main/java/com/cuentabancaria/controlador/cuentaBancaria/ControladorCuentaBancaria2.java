package com.cuentabancaria.controlador.cuentaBancaria;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class ControladorCuentaBancaria2 {
    @FXML private Label etiquetaNombreUsuario;
    @FXML private Label etiquetaNumeroCbu;
    @FXML private Label etiquetaExtraccionesDisponibles;
    @FXML private Label etiquetaSaldoDisponible;
    @FXML private Button botonRealizarOperacion;
    @FXML private Button botonHistorialTransacciones;
    @FXML private Button botonVerDatosCuenta;

    public Label getEtiquetaNombreUsuario() { return etiquetaNombreUsuario; }
    public void setEtiquetaNombreUsuario(Label etiquetaNombreUsuario) { this.etiquetaNombreUsuario = etiquetaNombreUsuario; }
    public Label getEtiquetaNumeroCbu() { return etiquetaNumeroCbu;}
    public void setEtiquetaNumeroCbu(Label etiquetaNumeroCbu) { this.etiquetaNumeroCbu = etiquetaNumeroCbu; }
    public Label getEtiquetaExtraccionesDisponibles() { return etiquetaExtraccionesDisponibles; }
    public void setEtiquetaExtraccionesDisponibles(Label etiquetaExtraccionesDisponibles) { this.etiquetaExtraccionesDisponibles = etiquetaExtraccionesDisponibles; }
    public Label getEtiquetaSaldoDisponible() { return etiquetaSaldoDisponible; }
    public void setEtiquetaSaldoDisponible(Label etiquetaSaldoDisponible) { this.etiquetaSaldoDisponible = etiquetaSaldoDisponible; }
    public Button getBotonRealizarOperacion() { return botonRealizarOperacion; }
    public void setBotonRealizarOperacion(Button botonRealizarOperacion) { this.botonRealizarOperacion = botonRealizarOperacion; }
    public Button getBotonHistorialTransacciones() { return botonHistorialTransacciones ;}
    public void setBotonHistorialTransacciones(Button botonHistorialTransacciones) { this.botonHistorialTransacciones = botonHistorialTransacciones; }
    public Button getBotonVerDatosCuenta() { return botonVerDatosCuenta; }
    public void setBotonVerDatosCuenta(Button botonVerDatosCuenta) { this.botonVerDatosCuenta = botonVerDatosCuenta; }

    private void cambiarTextoEtiqueta(Label etiqueta, String texto){ etiqueta.setText(texto); }

    @FXML
    public void accionRealizarOperacion(){}
    @FXML
    public void accionHistorialTransacciones(){}
    @FXML
    public void accionVerDatosCuenta(){}
}
