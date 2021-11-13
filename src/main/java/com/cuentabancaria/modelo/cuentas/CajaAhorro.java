package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.titular.Titular;
import java.util.Calendar;
import java.util.Collection;
public class CajaAhorro extends CuentaBancaria{
    public CajaAhorro(){
        setSaldo(0);
        setLimiteMinimoCuenta(10f);
        setCantidadExtraccionesPorMes(5);
    }
    public CajaAhorro(Titular titular, Calendar fechaApertura){
        setSaldo(0);
        setLimiteMinimoCuenta(10f);
        setCantidadExtraccionesPorMes(5);
        setTitular(titular);
        setFechaApertura(fechaApertura);
    }
    public CajaAhorro(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                      , Calendar fechaApertura){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura);
    }
    public CajaAhorro(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
            ,Calendar fechaApertura, Collection<Transaccion> transacciones){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura, transacciones);
    }
    @Override
    public boolean depositar(float monto) {
        if(monto > 0) {
            super.ingresarMonto(monto);
            return true;
        }
        return false;
    }
    @Override
    public boolean retirar(float monto){
        if (monto > 0){
            if(puedeRetirar(monto)) {
                super.sacarMonto(monto);
                return true;
            }
            return false;
        }
        return false;
    }
    private boolean puedeRetirar(float monto){ return (getSaldo() - monto) > getLimiteMinimoCuenta(); }
}
