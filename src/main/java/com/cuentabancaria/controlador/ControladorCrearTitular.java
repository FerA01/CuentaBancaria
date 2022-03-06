package com.cuentabancaria.controlador;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.titular.ControladorTitular;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
public class ControladorCrearTitular implements Initializable {
    private static ControladorBaseDato baseDato;
    private Titular titular;
    private CuentaBancaria cuentaBancaria;
    private Stage ventana;
    @FXML private AnchorPane panelTitular = new AnchorPane();
    @FXML private Button botonCrearTitular = new Button();
    @FXML private ComboBox<Titular> comboBoxTipoTitular = new ComboBox<>();
    @FXML private ComboBox<CuentaBancaria> comboBoxTipoCuenta = new ComboBox<>();
    private ControladorTitular controladorTitular;
    /**
     * Inicializa los comboBox
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getComboBoxTipoCuenta().setPromptText("Elige una cuenta");
        getComboBoxTipoCuenta().setItems(FXCollections.observableArrayList(new CajaAhorro(), new CuentaCorriente()));
        getComboBoxTipoTitular().setPromptText("Elige el titular");
        getComboBoxTipoTitular().setItems(FXCollections.observableArrayList(new Persona(), new Organizacion()));
        getBotonCrearTitular().setDisable(true);
        setBaseDato(null);
    }

    public Titular getTitular() { return titular; }
    public void setTitular(Titular titular) { this.titular = titular; }
    public CuentaBancaria getCuentaBancaria() { return cuentaBancaria; }
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) { this.cuentaBancaria = cuentaBancaria; }
    public Button getBotonCrearTitular() {return botonCrearTitular;}
    public void setBotonCrearTitular(Button botonCrearTitular){this.botonCrearTitular = botonCrearTitular;}
    public ComboBox<Titular> getComboBoxTipoTitular() {return comboBoxTipoTitular;}
    public void setComboBoxTipoTitular(ComboBox<Titular> comboBoxTipoTitular) {this.comboBoxTipoTitular = comboBoxTipoTitular;}
    public ComboBox<CuentaBancaria> getComboBoxTipoCuenta() {return comboBoxTipoCuenta;}
    public void setComboBoxTipoCuenta(ComboBox<CuentaBancaria> comboBoxTipoCuenta) {this.comboBoxTipoCuenta = comboBoxTipoCuenta;}
    public AnchorPane getPanelTitular() {return panelTitular;}
    public void setPanelTitular(AnchorPane panelTitular) {this.panelTitular = panelTitular;}
    public Stage getVentana(){ return ventana; }
    public void setVentana(Stage ventana) { this.ventana = ventana; }
    public static ControladorBaseDato getBaseDato() { return baseDato; }
    private static void setBaseDato(ControladorBaseDato baseDato) { ControladorCrearTitular.baseDato = baseDato; }
    public ControladorTitular getControladorTitular() { return controladorTitular; }
    public void setControladorTitular(ControladorTitular controladorTitular) { this.controladorTitular = controladorTitular; }

    private boolean comprobarDatosSeleccionados(){ return Validador.tipoUsuarioCuentaBancariaSeleccionada(getComboBoxTipoTitular(), getComboBoxTipoCuenta()); }
    private String generarCbu(){
        Random numero = new Random();
        int generado =  (100 + numero.nextInt((1000+1)-100));
        return String.valueOf(generado);
    }

    /**
     * Metodo que carga un panel de registro según el tipo de titular seleccionado en el comboBox
     * (Persona u Organización).
     * @param tipo
     */
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
    private boolean insertarDatos(ControladorBaseDato baseDato ,CuentaBancaria cuentaBancaria, Titular titular, String cbu) throws SQLException {
        cuentaBancaria.setTitular(titular);
        cuentaBancaria.setCbu(cbu);
        cuentaBancaria.setFechaApertura(LocalDate.now());
        return baseDato.insertarCuentaBancaria(cuentaBancaria);
    }
    private boolean comprobarDatosOrganizacionVacios(){
        TextField nombre = (TextField) getPanelTitular().getChildren().get(4);
        TextField tipoOrganizacion = (TextField) getPanelTitular().getChildren().get(5);
        TextField numeroCuit = (TextField) getPanelTitular().getChildren().get(6);
        DatePicker fechaCreacion = (DatePicker) getPanelTitular().getChildren().get(7);
        return nombre.getText().isEmpty() || tipoOrganizacion.getText().isEmpty() || numeroCuit.getText().isEmpty() || fechaCreacion.getValue() == null;
    }
    private boolean datosOrganizacionNulos(){
        TextField nombre = (TextField) getPanelTitular().getChildren().get(4);
        TextField tipoOrganizacion = (TextField) getPanelTitular().getChildren().get(5);
        TextField numeroCuit = (TextField) getPanelTitular().getChildren().get(6);
        DatePicker fechaCreacion = (DatePicker) getPanelTitular().getChildren().get(7);
        return nombre.getText() == null && tipoOrganizacion.getText() == null && numeroCuit.getText() == null && fechaCreacion.getValue() == null;
    }
    private boolean datosPersonaVacios(){
        TextField nombre = getControladorTitular().getTextFieldNombre();
        TextField apellido = getControladorTitular().getTextFieldApellido();
        TextField dni = getControladorTitular().getTextFieldDni();
        TextField cuit = getControladorTitular().getTextFieldCuit();
        DatePicker fechaNacimiento = getControladorTitular().getFechaNacimiento();
        return nombre.getText().isEmpty() || apellido.getText().isEmpty() || dni.getText().isEmpty() || cuit.getText().isEmpty() || fechaNacimiento.getValue() == null;
    }
    private boolean datosPersonaNulos(){
        TextField nombre = getControladorTitular().getTextFieldNombre();
        TextField apellido = getControladorTitular().getTextFieldApellido();
        TextField dni = getControladorTitular().getTextFieldDni();
        TextField cuit = getControladorTitular().getTextFieldCuit();
        DatePicker fechaNacimiento = getControladorTitular().getFechaNacimiento();
        return nombre.getText() == null || apellido.getText() == null || dni.getText() == null || cuit.getText() == null || fechaNacimiento.getValue() == null;
    }
    /**
     * Obtengo los datos ingresados de la organización y lo devuelvo.
     * @return Organización
     */
    private Organizacion crearTitularOrganizacion(){
        TextField nombre = getControladorTitular().getTextFieldNombreOrganizacion();
        TextField tipoOrganizacion = getControladorTitular().getTextFieldTipoOrganizacion();
        TextField numeroCuit = getControladorTitular().getTextFieldNumeroCuitOrganizacion();
        DatePicker fechaCreacion = getControladorTitular().getFechaCreacion();
        Organizacion nuevoTitular = new Organizacion(numeroCuit.getText()
                                                    , InputValidator.capitalizarDatos(nombre.getText())
                                                    , InputValidator.capitalizarTodoLosdatos(tipoOrganizacion.getText())
                                                    , fechaCreacion.getValue());
        System.out.println(nuevoTitular.tipoTitular());
        return nuevoTitular;
    }
    /**
     * Obtengo los datos ingresados de la persona y lo devuelvo.
     * @return Persona
     */
    private Persona crearTitularPersona(){
        Persona nuevoTitular = null;
        TextField nombre = (TextField) getPanelTitular().getChildren().get(6);
        TextField segundoNombre = (TextField) getPanelTitular().getChildren().get(7);
        TextField apellido = (TextField) getPanelTitular().getChildren().get(8);
        TextField dni = (TextField) getPanelTitular().getChildren().get(9);
        TextField cuit = (TextField) getPanelTitular().getChildren().get(10);
        DatePicker fechaNacimiento = (DatePicker) getPanelTitular().getChildren().get(11);
        String segNombre= segundoNombre.getText();
        if (segNombre.isEmpty()){
            nuevoTitular = new Persona(InputValidator.capitalizarDatos(nombre.getText())
                    , InputValidator.capitalizarDatos(apellido.getText())
                    , Integer.parseInt(dni.getText())
                    , cuit.getText()
                    , fechaNacimiento.getValue());
        }else{
            nuevoTitular = new Persona(InputValidator.capitalizarDatos(nombre.getText())
                    , InputValidator.capitalizarDatos(segundoNombre.getText())
                    , InputValidator.capitalizarDatos(apellido.getText())
                    , Integer.parseInt(dni.getText())
                    , cuit.getText()
                    , fechaNacimiento.getValue());
        }

        System.out.println(nuevoTitular.tipoTitular());
        return nuevoTitular;
    }
    //ACCIONES
    /**
     * Metodo que selecciona los elementos de los comboBox tipo de titular y tipo de cuenta.
     */
    @FXML
    public void accionObtenerCuentaBancariaTitular(){
        if (comprobarDatosSeleccionados()) {
            getBotonCrearTitular().setDisable(false);
            Titular titular = getComboBoxTipoTitular().getValue();
            CuentaBancaria cuentaBancaria = getComboBoxTipoCuenta().getValue();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(obtenerFxmlTitular(titular)));
                Parent root = fxmlLoader.load();
                AnchorPane panel = (AnchorPane) root;
                setControladorTitular(fxmlLoader.getController());
                getPanelTitular().getChildren().clear();
                getPanelTitular().getChildren().setAll(panel.getChildren());
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
            setTitular(titular);
        }
    }
    /**
     * Obtengo el archivo fxml según el tipo de Titular
     * @param titular
     * @return String
     */
    private String obtenerFxmlTitular(Titular titular){ return "/com/cuentabancaria/vista/" + titular.obtenerFxml(); }
    @FXML
    private void accionObtenerTitularSeleccionado(){
        if (getComboBoxTipoTitular().getValue() instanceof Persona){
            Persona persona = crearTitularPersona();
            setTitular(persona);
        }else {
            Organizacion organizacion = crearTitularOrganizacion();
            setTitular(organizacion);
        }
        System.out.println(getTitular().tipoTitular());
    }
    @FXML
    private void accionBotonCrearTitular() throws SQLException {
        //Esta parte lo agrega a la base de datos
        setCuentaBancaria(getComboBoxTipoCuenta().getValue());
        setBaseDato(new ControladorBaseDato());
        if (getComboBoxTipoTitular().getValue() instanceof Persona){
            String cuitPersona = getControladorTitular().getTextFieldCuit().getText();
            String dni = getControladorTitular().getTextFieldDni().getText();
            if (datosPersonaVacios() || datosPersonaNulos()){
                Validador.alertaDatosTitularIncompletos();
            }else{
                if (Validador.validarNumeroCuit(cuitPersona) && InputValidator.esNumero(cuitPersona)){
                    if (getBaseDato().existeNumeroCuit(cuitPersona)){
                        Validador.alertaNumeroCuitExistente();
                    }else{
                        if (Validador.validarDni(dni) && InputValidator.esNumero(dni)){
                            if (getBaseDato().existeNumeroDni(dni)){
                                Validador.alertaNumeroDniExistente();
                            }else{
                                setTitular(crearTitularPersona());
                                getBaseDato().insertarTitular(getTitular().getNumeroCuit(), getTitular().toString());
                                getBaseDato().insertarPersona((Persona) getTitular());
                                if(insertarDatos(getBaseDato(), getCuentaBancaria(), getTitular(), generarCbu())) {
                                    Validador.alertaTitularCreadoExitosamente();
                                    getVentana().close();
                                }
                            }
                        }else{ Validador.alertaDniIncorrecto(); }
                    }
                }else{
                    Validador.alertaNumeroCuitInvalido();
                }
            }
        }else {
            String cuitOrganizacion = getControladorTitular().getTextFieldNumeroCuitOrganizacion().getText();
            if (comprobarDatosOrganizacionVacios() || datosOrganizacionNulos()){
                Validador.alertaDatosTitularIncompletos();
            }else{
                if(Validador.validarNumeroCuit(cuitOrganizacion) && InputValidator.esNumero(cuitOrganizacion)){
                    if(getBaseDato().existeNumeroCuit(cuitOrganizacion)){
                        Validador.alertaNumeroCuitExistente();
                    }else{
                        setTitular(crearTitularOrganizacion());
                        getCuentaBancaria().setTitular(getTitular());
                        getBaseDato().insertarTitular(getTitular().getNumeroCuit(), getTitular().toString());
                        getBaseDato().insertarOrganizacion((Organizacion) getTitular());
                        if(insertarDatos(getBaseDato(), getCuentaBancaria(), getTitular(), generarCbu())) {
                            Validador.alertaTitularCreadoExitosamente();
                            getVentana().close();
                        }
                    }
                }else{
                    Validador.alertaNumeroCuitInvalido();
                }
            }
        }
    }
}