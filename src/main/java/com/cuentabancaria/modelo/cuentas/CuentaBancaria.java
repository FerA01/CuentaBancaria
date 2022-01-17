package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.modelo.CambiarFecha;
import com.cuentabancaria.modelo.titular.Titular;
import java.time.LocalDate;
import java.util.Collection;
public abstract class CuentaBancaria implements Accion{
    private float saldo;
    private float limiteMinimoCuenta;
    private int cantidadExtraccionesPorMes;
    private Titular titular;
    private LocalDate fechaApertura;
    private Collection<Transaccion> transacciones;

    public CuentaBancaria(){
        setSaldo(0f);
        setLimiteMinimoCuenta(0);
        setCantidadExtraccionesPorMes(0);
    }
    public CuentaBancaria(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                         ,LocalDate fechaApertura){
        setSaldo(saldo);
        setLimiteMinimoCuenta(limiteMinimoCuenta);
        setCantidadExtraccionesPorMes(cantidadExtraccionesPorMes);
        setTitular(titular);
        setFechaApertura(fechaApertura);
    }
    public CuentaBancaria(float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
            ,LocalDate fechaApertura, Collection<Transaccion> transacciones){
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
    public LocalDate getFechaApertura() { return fechaApertura; }
    public void setFechaApertura(LocalDate fechaApertura) { this.fechaApertura = fechaApertura; }
    public Collection<Transaccion> getTransacciones() { return transacciones; }
    public void setTransacciones(Collection<Transaccion> transacciones) { this.transacciones = transacciones; }

    public boolean agregarTransaccion(Transaccion transaccion){ return getTransacciones().add(transaccion); }
    public boolean eliminarTransaccion(Transaccion transaccion){ return getTransacciones().removeIf(trans -> trans.equals(transaccion)); }

    public String obtenerFechaApertura(){
        CambiarFecha cambiarFecha = new CambiarFecha(getFechaApertura());
        return cambiarFecha.cambiar();
    }
    public int cantidadTransacciones(){ return getTransacciones().size(); }
    protected boolean puedeRetirar(float monto){ return (getSaldo() - monto) > getLimiteMinimoCuenta(); }
    @Override
    public abstract String toString();
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
