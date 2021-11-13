package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.titular.Titular;
import java.util.Calendar;
import java.util.Collection;
public class CuentaCorriente extends CuentaBancaria{
    public CuentaCorriente(){
        setSaldo(-150);
        setLimiteMinimoCuenta(10f);
        setCantidadExtraccionesPorMes(-1);
    }
    public CuentaCorriente(Titular titular, Calendar fechaApertura){
        setSaldo(-150);
        setLimiteMinimoCuenta(10f);
        setCantidadExtraccionesPorMes(-1);
        setTitular(titular);
        setFechaApertura(fechaApertura);
    }
    public CuentaCorriente(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                            , Calendar fechaApertura){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura);
    }
    public CuentaCorriente(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                          ,Calendar fechaApertura, Collection<Transaccion> transacciones){
        super(saldo, limiteMinimoCuenta, cantidadExtraccionesPorMes, titular, fechaApertura, transacciones);
    }

    @Override
    public boolean depositar(float monto) {
        return false;
    }
    @Override
    public boolean retirar(float monto) {
        return false;
    }
}
