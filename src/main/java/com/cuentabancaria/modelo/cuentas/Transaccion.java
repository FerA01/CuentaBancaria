package com.cuentabancaria.modelo.cuentas;
import java.util.Calendar;
public class Transaccion {
    private float monto;
    private Calendar fechaTransaccion;
    private String tipoTransaccion;

    public Transaccion(){ setMonto(0); setFechaTransaccion(null); setTipoTransaccion(""); }
    public Transaccion(float monto, Calendar fechaTransaccion, String tipoTransaccion){
        setMonto(monto);
        setFechaTransaccion(fechaTransaccion);
        setTipoTransaccion(tipoTransaccion);
    }
    public float getMonto() { return monto; }
    public void setMonto(float monto) { this.monto = monto; }
    public Calendar getFechaTransaccion() { return fechaTransaccion; }
    public void setFechaTransaccion(Calendar fechaTransaccion) { this.fechaTransaccion = fechaTransaccion; }
    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
    public int obtenerDiaFechaTransaccion(){ return getFechaTransaccion().get(Calendar.DAY_OF_MONTH); }
    public int obtenerMesFechaTransaccion(){ return getFechaTransaccion().get(Calendar.MONTH); }
    public int obtenerAnioFechaTransaccion(){ return getFechaTransaccion().get(Calendar.YEAR); }
    public String obtenerFechaTransaccion(){ return obtenerDiaFechaTransaccion() + "/" + obtenerMesFechaTransaccion()
                                             + "/" + obtenerAnioFechaTransaccion(); }
    @Override
    public String toString(){
        return "Tipo de transacción: " + getTipoTransaccion() + ", monto: $" + getMonto() + ", fecha transacción: " + getFechaTransaccion().toString();
    }
}
