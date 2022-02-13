package com.cuentabancaria.controlador.buscar;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.Controlador;
import com.cuentabancaria.modelo.CambiarFecha;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.titular.Persona;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
public class ControladorBuscarPersona implements Initializable {
    @FXML private Button botonBuscar;
    @FXML private TextField textFieldBuscarApellido;
    @FXML private TableView<Persona> tabla;
    @FXML private TableColumn<Persona, String> columnaNumeroCuit;
    @FXML private TableColumn<Persona, String> columnaNombre;
    @FXML private TableColumn<Persona, String> columnaSegundoNombre;
    @FXML private TableColumn<Persona, String> columnaApellido;
    @FXML private TableColumn<Persona, LocalDate> columnaFechaNacimiento;
    @FXML private TableColumn<Persona, Integer> columnaDni;
    private ControladorBaseDato baseDato;
    private Controlador controladorPrincipal;
    private HashSet<String> posiblesApellidos;
    public Titular titular;
    public  CuentaBancaria cuentaBancaria;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getColumnaDni().setCellValueFactory(new PropertyValueFactory<Persona, Integer>("dni"));
        getColumnaNombre().setCellValueFactory(new PropertyValueFactory<Persona, String>("nombre"));
        getColumnaSegundoNombre().setCellValueFactory(new PropertyValueFactory<Persona, String>("segundoNombre"));
        getColumnaApellido().setCellValueFactory(new PropertyValueFactory<Persona, String>("apellido"));
        getColumnaFechaNacimiento().setCellValueFactory(new PropertyValueFactory<Persona, LocalDate>("fechaNacimiento"));
        getColumnaNumeroCuit().setCellValueFactory(new PropertyValueFactory<Persona, String>("numeroCuit"));

        setBaseDato(new ControladorBaseDato());
        try {
            ResultSet resultado = getBaseDato().obtenerPersonas();
            ArrayList<Persona> personas = obtenerPersonas(resultado);
            getBaseDato().cerrarConexiones();
            ObservableList<Persona> ol = FXCollections.observableArrayList(personas);
            getTabla().setItems(ol);
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }
    }

    public Button getBotonBuscar() { return botonBuscar; }
    public void setBotonBuscar(Button botonBuscar) { this.botonBuscar = botonBuscar; }
    public TableView<Persona> getTabla() { return tabla; }
    public void setTabla(TableView<Persona> tabla) { this.tabla = tabla; }
    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }
    public TableColumn<Persona, String> getColumnaNumeroCuit() { return columnaNumeroCuit; }
    public void setColumnaNumeroCuit(TableColumn<Persona, String> columnaNumeroCuit) { this.columnaNumeroCuit = columnaNumeroCuit; }
    public TableColumn<Persona, String> getColumnaNombre() { return columnaNombre; }
    public void setColumnaNombre(TableColumn<Persona, String> columnaNombre) { this.columnaNombre = columnaNombre; }
    public TableColumn<Persona, String> getColumnaSegundoNombre() { return columnaSegundoNombre; }
    public void setColumnaSegundoNombre(TableColumn<Persona, String> columnaSegundoNombre) { this.columnaSegundoNombre = columnaSegundoNombre; }
    public TableColumn<Persona, String> getColumnaApellido() { return columnaApellido; }
    public void setColumnaApellido(TableColumn<Persona, String> columnaApellido) { this.columnaApellido = columnaApellido; }
    public TableColumn<Persona, LocalDate> getColumnaFechaNacimiento() { return columnaFechaNacimiento; }
    public void setColumnaFechaNacimiento(TableColumn<Persona, LocalDate> columnaFechaNacimiento) { this.columnaFechaNacimiento = columnaFechaNacimiento; }
    public TableColumn<Persona, Integer> getColumnaDni() { return columnaDni; }
    public void setColumnaDni(TableColumn<Persona, Integer> columnaDni) { this.columnaDni = columnaDni; }

    public Controlador getControladorPrincipal() {
        return controladorPrincipal;
    }
    public void setControladorPrincipal(Controlador controladorPrincipal) { this.controladorPrincipal = controladorPrincipal; }

    public ArrayList<Persona> obtenerPersonas(ResultSet resultSet) throws SQLException {
        ArrayList<Persona> personas = new ArrayList<>();
        CambiarFecha cambiarFecha = new CambiarFecha();
        while (resultSet.next()){
            int dni = resultSet.getInt(1);
            String nombre = resultSet.getString(2);
            String segundoNombre = resultSet.getString(3);
            String apellido = resultSet.getString(4);
            Date fechaNacimiento = resultSet.getDate(5);
            String cuit = resultSet.getString(6);

            Persona persona = new Persona(dni, nombre, segundoNombre, apellido, cambiarFecha.dateToLocalDate(fechaNacimiento), cuit);
            personas.add(persona);
        }
        return personas;
    }
    private ArrayList<String> obtenerApellidos(){
        setBaseDato(new ControladorBaseDato());
        ArrayList<String> apellidos = new ArrayList<>();
        ResultSet resultado = null;
        try{
            resultado = getBaseDato().obtenerApellidos();
            while (resultado.next()){
                String apellido = resultado.getString(1);
                apellidos.add(apellido);
            }
            return apellidos;
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            try {
                getBaseDato().cerrarConexiones();
            } catch (SQLException excepcion) {
                System.out.println(excepcion.getMessage());
            }
        }
        return apellidos;
    }
    @FXML
    public void accionBotonBuscar() throws SQLException {
        textFieldBuscarApellido.getCharacters();
    }
    @FXML
    public void accionSeleccionarTitular() throws SQLException {
        setBaseDato(new ControladorBaseDato());
        Titular  titular = getTabla().getSelectionModel().getSelectedItem();
        CuentaBancaria cuentaBancaria = getBaseDato().obtenerCuentaBancaria(titular.getNumeroCuit());
        getControladorPrincipal().setTitular(titular);
        getControladorPrincipal().setCuentaBancaria(cuentaBancaria);
        getControladorPrincipal().obtenerSaldoCuitTitular();
    }
}
/*
    SELECT persona.nombre, persona.apellido, persona.dni, saldo, titularCuenta
    FROM persona
    INNER JOIN cuentabancaria on persona.cuit = cuentabancaria.titularCuenta
    WHERE cuentabancaria.saldo > 1000;
 */