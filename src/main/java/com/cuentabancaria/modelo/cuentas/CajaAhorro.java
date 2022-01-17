package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.titular.Titular;

import java.time.LocalDate;
import java.util.Calendar;
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
    public CajaAhorro(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                      , LocalDate fechaApertura){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura);
    }
    public CajaAhorro(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
            ,LocalDate fechaApertura, Collection<Transaccion> transacciones){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura, transacciones);
    }

    @Override
    public String toString() { return "Caja de ahorro"; }

    @Override
    public boolean depositar(float monto) {
        if(monto > 0) {
            super.ingresarMonto(monto);
            agregarTransaccion(new Transaccion(monto, Calendar.getInstance(), "Deposito"));
            return true;
        }
        return false;
    }
    @Override
    public boolean retirar(float monto){
        if (monto > 0 && getCantidadExtraccionesPorMes() > 0){
            if(puedeRetirar(monto)) {
                super.sacarMonto(monto);
                agregarTransaccion(new Transaccion(monto, Calendar.getInstance(), "Extracción"));
                return true;
            }
            return false;
        }
        return false;
    }
}
