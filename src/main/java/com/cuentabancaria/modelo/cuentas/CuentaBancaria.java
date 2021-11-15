package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.titular.Titular;
import java.util.Calendar;
import java.util.Collection;
public abstract class CuentaBancaria implements Accion{
    private float saldo;
    private float limiteMinimoCuenta;
    private int cantidadExtraccionesPorMes;
    private Titular titular;
    private Calendar fechaApertura;
    private Collection<Transaccion> transacciones;

    public CuentaBancaria(){
        setSaldo(0f);
        setLimiteMinimoCuenta(0);
        setCantidadExtraccionesPorMes(0);
    }
    public CuentaBancaria(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                         ,Calendar fechaApertura){
        setSaldo(saldo);
        setLimiteMinimoCuenta(limiteMinimoCuenta);
        setCantidadExtraccionesPorMes(cantidadExtraccionesPorMes);
        setTitular(titular);
        setFechaApertura(fechaApertura);
    }
    public CuentaBancaria(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
            ,Calendar fechaApertura, Collection<Transaccion> transacciones){
        setSaldo(saldo);
        setLimiteMinimoCuenta(limiteMinimoCuenta);
        setCantidadExtraccionesPorMes(cantidadExtraccionesPorMes);
        setTitular(titular);
        setFechaApertura(fechaApertura);
        setTransacciones(transacciones);
    }
    public float getSaldo() { return saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }
    public float getLimiteMinimoCuenta() { return limiteMinimoCuenta; }
    public void setLimiteMinimoCuenta(float limiteMinimoCuenta) { this.limiteMinimoCuenta = limiteMinimoCuenta; }
    public int getCantidadExtraccionesPorMes() { return cantidadExtraccionesPorMes; }
    public void setCantidadExtraccionesPorMes(int cantidadExtraccionesPorMes) { this.cantidadExtraccionesPorMes = cantidadExtraccionesPorMes; }
    public Titular getTitular() { return titular; }
    public void setTitular(Titular titular) { this.titular = titular; }
    public Calendar getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(Calendar fechaApertura) { this.fechaApertura = fechaApertura; }
    public Collection<Transaccion> getTransacciones() { return transacciones; }
    public void setTransacciones(Collection<Transaccion> transacciones) { this.transacciones = transacciones; }

    public boolean agregarTransaccion(Transaccion transaccion){ return getTransacciones().add(transaccion); }
    public boolean eliminarTransaccion(Transaccion transaccion){ return getTransacciones().removeIf(trans -> trans.equals(transaccion)); }
    public int obtenerDiaFechaApertura(){ return getFechaApertura().get(Calendar.DAY_OF_MONTH); }
    public int obtenerMesFechaApertura(){ return getFechaApertura().get(Calendar.MONTH) + 1; }
    public int obtenerAnioFechaApertura(){ return getFechaApertura().get(Calendar.YEAR); }
    public String obtenerFechaApertura(){ return obtenerDiaFechaApertura() + "/" + obtenerMesFechaApertura() + "/" + obtenerAnioFechaApertura(); }
    public int cantidadTransacciones(){ return getTransacciones().size(); }
    protected boolean puedeRetirar(float monto){ return (getSaldo() - monto) > getLimiteMinimoCuenta(); }
    @Override
    public abstract boolean depositar(float monto);
    @Override
    public abstract boolean retirar(float monto);
    protected void ingresarMonto(float monto){ setSaldo(getSaldo() + monto); }
    protected void sacarMonto(float monto){
        setSaldo(getSaldo() - monto);
        setCantidadExtraccionesPorMes(getCantidadExtraccionesPorMes() - 1);
    }
}
