package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.controlador.Validador;
import com.cuentabancaria.modelo.titular.Titular;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collection;
public class CuentaCorriente extends CuentaBancaria{
    public CuentaCorriente(){
        setSaldo(0);
        setLimiteMinimoCuenta(-150f);
        setCantidadExtraccionesPorMes(-1);
    }
    public CuentaCorriente(Titular titular, LocalDate fechaApertura){
        setSaldo(0);
        setLimiteMinimoCuenta(-150f);
        setCantidadExtraccionesPorMes(-1);
        setTitular(titular);
        setFechaApertura(fechaApertura);
    }
    public CuentaCorriente(String cbu, float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                            , LocalDate fechaApertura){
        super(cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura);
    }
    public CuentaCorriente(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                          ,LocalDate fechaApertura, Collection<Transaccion> transacciones){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura, transacciones);
    }

    @Override
    public String tipoCuentaBancaria() { return "Cuenta Corriente"; }

    @Override
    public String toString() { return "Cuenta corriente"; }

    @Override
    public boolean depositar(float monto) throws SQLException {
        if(monto > 0) {
            super.ingresarMonto(monto);
            insertarTransaccion(new Transaccion(getCbu(), monto, LocalDate.now(), "Deposito"));
            actualizarDatosCuentaBancaria(getSaldo(), getCantidadExtraccionesPorMes(), getCbu());
            return true;
        }
        return false;
    }
    @Override
    public boolean retirar(float monto) throws SQLException{
        if (monto > getSaldo() || !puedeRetirar()){
            Validador.alertaSaldoInsuficiente();
        }
        if (monto > 0 && puedeRetirar(monto) && puedeRetirar()){
            super.sacarMonto(monto);
            insertarTransaccion(new Transaccion(getCbu(), monto, LocalDate.now(), "Extracción"));
            setCantidadExtraccionesPorMes(-1);
            actualizarDatosCuentaBancaria(getSaldo(), getCantidadExtraccionesPorMes(), getCbu());
            return true;
        }
        return false;
    }

    @Override
    protected boolean puedeRetirar() { return true; }
}
