package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.titular.Titular;
import java.util.Calendar;
import java.util.Collection;
public class CuentaCorriente extends CuentaBancaria{
    public CuentaCorriente(){
        setSaldo(0);
        setLimiteMinimoCuenta(-150f);
        setCantidadExtraccionesPorMes(-1);
    }
    public CuentaCorriente(Titular titular, Calendar fechaApertura){
        setSaldo(0);
        setLimiteMinimoCuenta(-150f);
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
        if(monto > 0) {
            super.ingresarMonto(monto);
            agregarTransaccion(new Transaccion(monto, Calendar.getInstance(), "Deposito"));
            return true;
        }
        return false;
    }
    @Override
    public boolean retirar(float monto) {
        if (monto > 0 && puedeRetirar(monto)){
            super.sacarMonto(monto);
            agregarTransaccion(new Transaccion(monto, Calendar.getInstance(), "Extracción"));
            setCantidadExtraccionesPorMes(-1);
            return true;
        }
        return false;
    }
}
