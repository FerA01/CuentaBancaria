package com.cuentabancaria.modelo.cuentas;
import com.cuentabancaria.basedatos.controlador.ControladorBaseDato;
import com.cuentabancaria.modelo.CambiarFecha;
import com.cuentabancaria.modelo.titular.Titular;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
public abstract class CuentaBancaria implements Accion{
    private String cbu;
    private float saldo;
    private float limiteMinimoCuenta;
    private int cantidadExtraccionesPorMes;
    private Titular titular;
    private LocalDate fechaApertura;
    private Collection<Transaccion> transacciones;
    private String numeroCuit;
    private ControladorBaseDato baseDato;
    private String tipoTransaccion;

    public CuentaBancaria(){
        setSaldo(0f);
        setLimiteMinimoCuenta(0);
        setCantidadExtraccionesPorMes(0);
    }
    public CuentaBancaria(String cbu){
        setCbu(cbu);
        setSaldo(0f);
        setLimiteMinimoCuenta(0);
        setCantidadExtraccionesPorMes(0);
    }
    public CuentaBancaria(String cbu, float saldo, float limiteMinimoCuenta, int cantidadExtraccionesPorMes, Titular titular
                         ,LocalDate fechaApertura){
        setCbu(cbu);
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
    public String getCbu() { return this.cbu; }
    public void setCbu(String cbu) { this.cbu = cbu; }
    public String getNumeroCuit() { return numeroCuit; }
    public void setNumeroCuit(String numeroCuit) { this.numeroCuit = numeroCuit; }
    public ControladorBaseDato getBaseDato() { return baseDato; }
    public void setBaseDato(ControladorBaseDato baseDato) { this.baseDato = baseDato; }
    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }

    public boolean agregarTransaccion(Transaccion transaccion){ return getTransacciones().add(transaccion); }
    public boolean eliminarTransaccion(Transaccion transaccion){ return getTransacciones().removeIf(trans -> trans.equals(transaccion)); }

    public String obtenerFechaApertura(){
        CambiarFecha cambiarFecha = new CambiarFecha(getFechaApertura());
        return cambiarFecha.cambiar();
    }
    public int cantidadTransacciones(){ return getTransacciones().size(); }
    protected boolean puedeRetirar(float monto){ return (getSaldo() - monto) > getLimiteMinimoCuenta(); }
    public abstract String tipoCuentaBancaria();
    @Override
    public abstract String toString();
    @Override
    public abstract boolean depositar(float monto) throws SQLException;
    @Override
    public abstract boolean retirar(float monto) throws SQLException;
    protected void ingresarMonto(float monto){ setSaldo(getSaldo() + monto); }
    protected void sacarMonto(float monto){
        setSaldo(getSaldo() - monto);
        setCantidadExtraccionesPorMes(getCantidadExtraccionesPorMes() - 1);
    }
    public String numeroCuitTitular(){ return getTitular().getNumeroCuit(); }
    protected void insertarTransaccion(Transaccion transaccion) throws SQLException{
        setBaseDato(new ControladorBaseDato());
        try{
            getBaseDato().insertarTransaccion(transaccion);
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }
    }
    protected void actualizarDatosCuentaBancaria(float saldo, int cantidadTransaccionesMes, String cbu) throws SQLException{
        setBaseDato(new ControladorBaseDato());
        try {
            getBaseDato().actualizarDatosCuentaBancaria(saldo, cantidadTransaccionesMes, cbu);
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }
    }
    public boolean tipoTransaccion(String tipoTransaccion, float monto) throws SQLException {
        return switch (tipoTransaccion) {
            case "deposito" -> depositar(monto);
            case "extraccion" -> retirar(monto);
            default -> false;
        };
    }
    protected abstract boolean puedeRetirar();
}
