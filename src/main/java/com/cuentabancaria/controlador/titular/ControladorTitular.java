package com.cuentabancaria.controlador.titular;
import com.cuentabancaria.controlador.InputValidator;
import com.cuentabancaria.controlador.Validador;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
public class ControladorTitular {
    @FXML private TextField textFieldNombre;
    @FXML private TextField textFieldSegundoNombre;
    @FXML private TextField textFieldApellido;
    @FXML private TextField textFieldDni;
    @FXML private TextField textFieldCuit;
    @FXML private DatePicker fechaNacimiento;
    @FXML private TextField textFieldNombreOrganizacion;
    @FXML private TextField textFieldTipoOrganizacion;
    @FXML private TextField textFieldNumeroCuitOrganizacion;
    @FXML private DatePicker fechaCreacion;

    public TextField getTextFieldNombre() { return textFieldNombre; }
    public void setTextFieldNombre(TextField textFieldNombre) { this.textFieldNombre = textFieldNombre; }
    public TextField getTextFieldSegundoNombre() { return textFieldSegundoNombre; }
    public void setTextFieldSegundoNombre(TextField textFieldSegundoNombre) { this.textFieldSegundoNombre = textFieldSegundoNombre; }
    public TextField getTextFieldApellido() { return textFieldApellido; }
    public void setTextFieldApellido(TextField textFieldApellido) { this.textFieldApellido = textFieldApellido; }
    public TextField getTextFieldDni() { return textFieldDni; }
    public void setTextFieldDni(TextField textFieldDni) { this.textFieldDni = textFieldDni; }
    public TextField getTextFieldCuit() { return textFieldCuit; }
    public void setTextFieldCuit(TextField textFieldCuit) { this.textFieldCuit = textFieldCuit; }
    public DatePicker getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(DatePicker fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    public TextField getTextFieldNombreOrganizacion() { return textFieldNombreOrganizacion; }
    public void setTextFieldNombreOrganizacion(TextField textFieldNombreOrganizacion) { this.textFieldNombreOrganizacion = textFieldNombreOrganizacion; }
    public TextField getTextFieldTipoOrganizacion() { return textFieldTipoOrganizacion; }
    public void setTextFieldTipoOrganizacion(TextField textFieldTipoOrganizacion) { this.textFieldTipoOrganizacion = textFieldTipoOrganizacion; }
    public TextField getTextFieldNumeroCuitOrganizacion() { return textFieldNumeroCuitOrganizacion; }
    public void setTextFieldNumeroCuitOrganizacion(TextField textFieldNumeroCuitOrganizacion) { this.textFieldNumeroCuitOrganizacion = textFieldNumeroCuitOrganizacion; }
    public DatePicker getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(DatePicker fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @FXML
    public void accionValidarDni(){
        if (!(InputValidator.esNumero(getTextFieldDni().getText()))){
            Validador.alertaDniIncorrecto();
        }
        if ((getTextFieldDni().getText().isEmpty())){
            Validador.alertaDniVacio();
        }
    }
    @FXML
    public void accionValidarCuitPersona() {
        validarCuit(getTextFieldCuit());
    }
    @FXML
    public void accionValidarCuitOrganizacion(){
        validarCuit(getTextFieldNumeroCuitOrganizacion());
    }
    private boolean validarCuit(TextField textFieldCuit){
        if (Validador.validarNumeroCuit(textFieldCuit.getText()) || InputValidator.esNumero(textFieldCuit.getText())){
            return true;
        }else{
            Validador.alertaNumeroCuitInvalido();
            return false;
        }
    }
}
