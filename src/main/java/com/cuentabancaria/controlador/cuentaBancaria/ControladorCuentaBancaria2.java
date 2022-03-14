package com.cuentabancaria.controlador.cuentaBancaria;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.Validador;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ControladorCuentaBancaria2 implements Initializable {
    @FXML private Button botonRealizarOperacion;
    @FXML private Button botonDatosCuenta;
    @FXML private Button botonCuentasBancarias;
    @FXML private Button botonCrearCuentaBancaria;
    @FXML private ComboBox<String> comboBoxRealizarOperacion;
    @FXML private Button botonHistorialTransacciones;

    @FXML private Label etiquetaNombreUsuario;
    @FXML private Label etiquetaNumeroCbu;
    @FXML private Label etiquetaExtraccionesDisponibles;
    @FXML private Label etiquetaSaldoDisponible;

    private ControladorBaseDato baseDato;
    private Titular titular;
    private CuentaBancaria cuentaBancaria;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getComboBoxRealizarOperacion().getItems().addAll("Depósito", "Extracción");
        getComboBoxRealizarOperacion().setFocusTraversable(false);
        getBotonRealizarOperacion().setFocusTraversable(false);
        getBotonDatosCuenta().setFocusTraversable(false);
        getBotonCuentasBancarias().setFocusTraversable(false);
        getBotonCrearCuentaBancaria().setFocusTraversable(false);
        getBotonHistorialTransacciones().setFocusTraversable(false);
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
    public Button getBotonHistorialTransacciones() { return botonHistorialTransacciones ;}
    public void setBotonHistorialTransacciones(Button botonHistorialTransacciones) { this.botonHistorialTransacciones = botonHistorialTransacciones; }
    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }
    public ComboBox<String> getComboBoxRealizarOperacion() { return comboBoxRealizarOperacion; }
    public void setComboBoxRealizarOperacion(ComboBox<String> comboBoxRealizarOperacion) { this.comboBoxRealizarOperacion = comboBoxRealizarOperacion; }
    public Titular getTitular() { return titular; }
    public void setTitular(Titular titular) { this.titular = titular; }
    public CuentaBancaria getCuentaBancaria() { return cuentaBancaria; }
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) { this.cuentaBancaria = cuentaBancaria; }
    public Button getBotonRealizarOperacion() { return botonRealizarOperacion; }
    public void setBotonRealizarOperacion(Button botonRealizarOperacion) { this.botonRealizarOperacion = botonRealizarOperacion; }
    public Button getBotonDatosCuenta() { return botonDatosCuenta; }
    public void setBotonDatosCuenta(Button botonDatosCuenta) { this.botonDatosCuenta = botonDatosCuenta; }
    public Button getBotonCuentasBancarias() { return botonCuentasBancarias; }
    public void setBotonCuentasBancarias(Button botonCuentasBancarias) { this.botonCuentasBancarias = botonCuentasBancarias; }
    public Button getBotonCrearCuentaBancaria() { return botonCrearCuentaBancaria; }
    public void setBotonCrearCuentaBancaria(Button botonCrearCuentaBancaria) { this.botonCrearCuentaBancaria = botonCrearCuentaBancaria; }

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
    public void accionBotonCuentasBancarias() throws IOException, SQLException{
        if(Validador.usuarioSeleccionado(getTitular())){
            try{
                abrirVentanaCuentasBancariasDisponibles();
            }catch (IOException excepcion){ System.out.println(excepcion.getMessage()); }
        }else { Validador.alertaNombreUsuarioNoSeleccionado(); }
    }
    @FXML
    public void accionBotonCrearCuentaBancaria() throws SQLException, IOException{
        if(Validador.usuarioSeleccionado(getTitular())){
            try{
                abrirVentanaSeleccionarCuentaBancaria();
                getCuentaBancaria().setTitular(getTitular());
                if(getBaseDato().insertarCuentaBancaria(getCuentaBancaria())){ Validador.alertaCuentaBancariaCreadoExitosamente(); }
                else{ Validador.alertaCuentaBancariaCreadoFallido(); }
            }catch (IOException | SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        }else{ Validador.alertaNombreUsuarioNoSeleccionado(); }
    }

    private void abrirVentanaOperacion(String operacion) throws IOException{
        switch (operacion){
            case "Depósito":
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/"));
        }
    }
    private void abrirVentanaSeleccionarCuentaBancaria() throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/cuentaBancaria/SeleccionarCuentaBancaria.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage ventana = new Stage();
            ControladorSeleccionarCuentaBancaria controlador = loader.getController();
            controlador.setControlador(this);
            controlador.setVentana(ventana);
            ventana.setTitle("Seleccionar Cuenta Bancaria");
            ventana.setResizable(false);
            ventana.setScene(scene);
            ventana.showAndWait();
        }catch (IOException excepcion){ System.out.println(excepcion.getMessage()); }
    }
    private void abrirVentanaCuentasBancariasDisponibles() throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/cuentabancaria/vista/cuentaBancaria/CuentasBancariasDisponibles.fxml"));
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            Stage ventana = new Stage();
            ControladorCuentasBancariasDisponibles controlador = loader.getController();
            controlador.setTitular(getTitular());
            ventana.setScene(scene);
            ventana.setTitle("Cuentas Bancarias");
            ventana.setResizable(false);
            ventana.showAndWait();
        }catch (IOException excepcion){ System.out.println(excepcion.getMessage()); }
    }
}
