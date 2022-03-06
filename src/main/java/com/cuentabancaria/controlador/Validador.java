package com.cuentabancaria.controlador;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
public class Validador {
    public static boolean cuentaBancariaSeleccionada(CuentaBancaria cuentaBancaria){ return cuentaBancaria != null; }
    public static boolean usuarioSeleccionado(Titular titular){ return titular != null; }

    public static boolean tipoUsuarioCuentaBancariaSeleccionada(ComboBox<Titular> titular, ComboBox<CuentaBancaria> cuentaBancaria){
        return titular.getValue() != null && cuentaBancaria.getValue() != null;
    }
    public static void alertaNombreUsuarioNoSeleccionado(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, seleccione un usuario!");
        alert.showAndWait();
    }
    public static void alertaTitularCreadoExitosamente(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Titular creado exitosamente");
        alert.showAndWait();
    }
    public static void alertaDatosTitularIncompletos(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los datos obligatorios!");
        alert.showAndWait();
    }
    public static void alertaIngresoMontoLimite(float monto){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Ingresa un monto mayor a : $" + monto);
        alert.showAndWait();
    }
    public static void alertaSaldoInsuficiente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Saldo insuficiente. \nPor favor ingrese un monto valido.");
        alert.showAndWait();
    }
    public static void alertaSuperoCantidadExtraccionesPorMes(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡ERROR! \nSupero la cantidad de extracciones por mes. \nVuelva el mes siguiente.");
        alert.showAndWait();
    }
    public static void alertaOperacionRealizadaConExito(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Operación realizada con exito.");
        alert.showAndWait();
    }
    public static void alertaOperacionFallida(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("Operación realizada fallida.\nVuelva a intentar más tarde.");
        alert.showAndWait();
    }
    public static void alertaNombreUsuarioCuentaBancariaNoSeleccionado(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, rellene los datos!");
        alert.showAndWait();
    }
    public void alertaMontoNoEsNumero(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, seleccione un monto valido!");
        alert.showAndWait();
    }
    public static void alertaDniIncorrecto(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, ingrese un dni valido!");
        alert.showAndWait();
    }
    public static void alertaDniVacio(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor!, ingrese un dni.");
        alert.showAndWait();
    }
    public static void alertaNumeroCuitInvalido(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("¡Por favor, seleccione un número de cuit valido!");
        alert.showAndWait();
    }
    public static void alertaNumeroCuitExistente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El número de cuit ya existe.");
        alert.showAndWait();
    }
    public static void alertaNumeroDniExistente(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle(null);
        alert.setContentText("El número de dni ya existe.");
        alert.showAndWait();
    }
    public static boolean validarNumeroCuit(String numeroCuit){ return (numeroCuit.length()) == 11; }
    public static boolean validarDni(String dni){
        if(InputValidator.esNumero(dni)){
            int nuevoDni = Integer.parseInt(dni);
            return nuevoDni > 10000000 && nuevoDni < 60000000;
        }
        return false;
    }
}
