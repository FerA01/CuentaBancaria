package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
public class CajaAhorro extends CuentaBancaria{
    public CajaAhorro(){
        setSaldo(0);
        setLimiteMinimoCuenta(10f);
        setCantidadExtraccionesPorMes(5);
    }
    public CajaAhorro(Titular titular, LocalDate fechaApertura){
        setSaldo(0);
        setLimiteMinimoCuenta(10f);
        setCantidadExtraccionesPorMes(5);
        setTitular(titular);
        setFechaApertura(fechaApertura);
    }
    public CajaAhorro(String cbu, float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                      , LocalDate fechaApertura){
        super(cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura);
    }
    public CajaAhorro(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
            ,LocalDate fechaApertura, Collection<Transaccion> transacciones){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura, transacciones);
    }

    @Override
    public String tipoCuentaBancaria() { return "Caja de ahorro"; }
    @Override
    public String toString() { return "Caja de ahorro"; }
    @Override
    public boolean depositar(float monto) throws SQLException {
        if(monto > 0) {
            super.ingresarMonto(monto);
            //agregarTransaccion(new Transaccion(monto, null, "Deposito"));
            insertarTransaccion(new Transaccion(getCbu(), monto, LocalDate.now(), "Deposito"));
            actualizarDatosCuentaBancaria(getSaldo(), getCantidadExtraccionesPorMes(), getCbu());
            return true;
        }
        return false;
    }
    @Override
    public boolean retirar(float monto) throws SQLException{
        if (monto > getSaldo()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR");
            alert.setTitle("SALDO INSUFICIENTE");
            alert.setContentText("!POR FAVOR INGRESE UN MONTO VALIDO¡");
            alert.showAndWait();
        }
        if (!puedeRetirar()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("SUPERO CANTIDAD DE EXTRACCIONES POR DIA");
            alert.setTitle("ERROR");
            alert.setContentText("!POR FAVOR, VUELVA EL PROXIMO MES¡");
            alert.showAndWait();
        }
        if (monto > 0 && puedeRetirar()){
            if(puedeRetirar(monto)) {
                super.sacarMonto(monto);
                //agregarTransaccion(new Transaccion(monto, null, "Extracción"));
                insertarTransaccion(new Transaccion(getCbu(), monto, LocalDate.now(), "Extracción"));
                actualizarDatosCuentaBancaria(getSaldo(), getCantidadExtraccionesPorMes(), getCbu());
                return true;
            }
            return false;
        }
        return false;
    }
    @Override
    protected boolean puedeRetirar() { return getCantidadExtraccionesPorMes() > 0; }
}
