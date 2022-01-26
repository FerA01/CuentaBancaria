package com.cuentabancaria.controlador;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
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
import java.sql.SQLException;
import java.util.ResourceBundle;
public class ControladorCrearTitular implements Initializable {
    private static ControladorBaseDato baseDato;
    private Titular titular;
    private CuentaBancaria cuentaBancaria;
    @FXML private AnchorPane panelTitular = new AnchorPane();
    @FXML private Button botonCrearTitular = new Button();
    @FXML private ComboBox<Titular> comboBoxTipoTitular = new ComboBox<>();
    @FXML private ComboBox<CuentaBancaria> comboBoxTipoCuenta = new ComboBox<>();
    /**
     * Inicializa los comboBox
     * */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getComboBoxTipoCuenta().setPromptText("Elige una cuenta");
        getComboBoxTipoCuenta().setItems(FXCollections.observableArrayList(new CajaAhorro(), new CuentaCorriente()));
        getComboBoxTipoTitular().setPromptText("Elige el titular");
        getComboBoxTipoTitular().setItems(FXCollections.observableArrayList(new Persona(), new Organizacion()));
        getBotonCrearTitular().setVisible(true);
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

    public static ControladorBaseDato getBaseDato() { return baseDato; }
    private static void setBaseDato(ControladorBaseDato baseDato) { ControladorCrearTitular.baseDato = baseDato; }

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
    private void accionObtenerCuentaBancaria(){
        setCuentaBancaria(getComboBoxTipoCuenta().getValue());
        System.out.println(getCuentaBancaria().toString());
    }
    @FXML
    private void accionBotonCrearTitular() throws SQLException {
        //Esta parte lo agrega a la base de datos
        setCuentaBancaria(getComboBoxTipoCuenta().getValue());
        setBaseDato(new ControladorBaseDato());
        if (getComboBoxTipoTitular().getValue() instanceof Persona){
            setTitular(crearTitularPersona());
            getCuentaBancaria().setTitular(getTitular());

            getBaseDato().insertarTitular(getTitular().getNumeroCuit(), getTitular().toString());
            getBaseDato().insertarPersona((Persona) getTitular());
        }else {
            setTitular(crearTitularOrganizacion());
            getCuentaBancaria().setTitular(getTitular());
            getBaseDato().insertarTitular(getTitular().getNumeroCuit(), getTitular().toString());
            getBaseDato().insertarOrganizacion((Organizacion) getTitular());
        }
        setBaseDato(null);
    }
    /**
     * Obtengo los datos ingresados de la persona y lo devuelvo.
     * @return Persona
     */
    private Persona crearTitularPersona(){
        TextField nombre = (TextField) getPanelTitular().getChildren().get(6);
        TextField segundoNombre = (TextField) getPanelTitular().getChildren().get(7);
        TextField apellido = (TextField) getPanelTitular().getChildren().get(8);
        TextField dni = (TextField) getPanelTitular().getChildren().get(9);
        boolean dniEsNumero = InputValidator.textIsNumericOnly(dni.getText());
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
    /**
     * Obtengo los datos ingresados de la organización y lo devuelvo.
     * @return Organización
     */
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
    /**
     * Metodo que selecciona los elementos de los comboBox tipo de titular y tipo de cuenta.
     */
    @FXML
    public void combo(){
        Titular titular = getComboBoxTipoTitular().getValue();
        CuentaBancaria cuentaBancaria = getComboBoxTipoCuenta().getValue();
        try {
            AnchorPane panel = FXMLLoader.load(getClass().getResource(obtenerFxmlTitular(titular)));
            getPanelTitular().getChildren().clear();
            getPanelTitular().getChildren().setAll(panel.getChildren());
        }catch (IOException exception){
            System.out.println(exception.getMessage());
        }
        setTitular(titular);
        System.out.println(cuentaBancaria);
        System.out.println(titular);
    }
}