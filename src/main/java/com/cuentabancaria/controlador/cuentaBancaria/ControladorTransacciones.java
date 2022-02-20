package com.cuentabancaria.controlador.cuentaBancaria;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.controlador.Controlador;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.cuentas.Transaccion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
public class ControladorTransacciones implements Initializable {
    @FXML private TableView<Transaccion> tablaHistorialTransaccion;
    @FXML private TableColumn<Transaccion, Integer> columnaIdTransaccion;
    @FXML private TableColumn<Transaccion, LocalDate> columnaFechaTransaccion;
    @FXML private TableColumn<Transaccion, Float> columnaMonto;
    @FXML private TableColumn<Transaccion, String> columnaTipoTransaccion;
    private ControladorBaseDato baseDato;
    private Controlador controladorPrincipal;
    public CuentaBancaria cuentaBancaria;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBaseDato(new ControladorBaseDato());
        getColumnaIdTransaccion().setCellValueFactory(new PropertyValueFactory<>("idTransaccion"));
        getColumnaFechaTransaccion().setCellValueFactory(new PropertyValueFactory<>("fechaTransaccion"));
        getColumnaMonto().setCellValueFactory(new PropertyValueFactory<>("monto"));
        getColumnaTipoTransaccion().setCellValueFactory(new PropertyValueFactory<>("tipoTransaccion"));

    }

    public TableView<Transaccion> getTablaHistorialTransaccion() {return tablaHistorialTransaccion;}
    public void setTablaHistorialTransaccion(TableView<Transaccion> tablaHistorialTransaccion) {this.tablaHistorialTransaccion = tablaHistorialTransaccion;}
    public TableColumn<Transaccion, Integer> getColumnaIdTransaccion() {return columnaIdTransaccion;}
    public void setColumnaIdTransaccion(TableColumn<Transaccion, Integer> columnaIdTransaccion) {this.columnaIdTransaccion = columnaIdTransaccion;}
    public TableColumn<Transaccion, LocalDate> getColumnaFechaTransaccion() {return columnaFechaTransaccion;}
    public void setColumnaFechaTransaccion(TableColumn<Transaccion, LocalDate> columnaFechaTransaccion) {this.columnaFechaTransaccion = columnaFechaTransaccion;}
    public TableColumn<Transaccion, Float> getColumnaMonto() {return columnaMonto;}
    public void setColumnaMonto(TableColumn<Transaccion, Float> columnaMonto) {this.columnaMonto = columnaMonto;}
    public TableColumn<Transaccion, String> getColumnaTipoTransaccion() {return columnaTipoTransaccion;}
    public void setColumnaTipoTransaccion(TableColumn<Transaccion, String> columnaTipoTransaccion) {this.columnaTipoTransaccion = columnaTipoTransaccion;}
    public ControladorBaseDato getBaseDato() {return baseDato;}
    public void setBaseDato(ControladorBaseDato baseDato) {this.baseDato = baseDato;}
    public Controlador getControladorPrincipal() { return controladorPrincipal; }
    public void setControladorPrincipal(Controlador controladorPrincipal) { this.controladorPrincipal = controladorPrincipal; }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public void mostrarDatos() throws SQLException {
        setCuentaBancaria(getControladorPrincipal().getCuentaBancaria());
        try{
            ResultSet resultado = getBaseDato().obtenerTransaccionesNumeroCbu(cuentaBancaria.getCbu());
            List<Transaccion> transacciones =  getBaseDato().obtenerTransaccionesNumeroCuit(resultado);
            ObservableList<Transaccion> mostrarTransacciones = FXCollections.observableArrayList(transacciones);
            getTablaHistorialTransaccion().setItems(mostrarTransacciones);
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            getBaseDato().cerrarConexiones();
        }
    }
}
