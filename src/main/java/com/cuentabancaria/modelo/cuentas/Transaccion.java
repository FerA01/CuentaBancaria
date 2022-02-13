package com.cuentabancaria.modelo.cuentas;
import java.time.LocalDate;
public class Transaccion {
    private int idTransaccion;
    private float monto;
    private LocalDate fechaTransaccion;
    private String tipoTransaccion;
    private String cbuAsociado;

    public Transaccion(){ setMonto(0); setFechaTransaccion(null); setTipoTransaccion("");}
    // 1|idTransaccion, 2|cbuAsociado, 3|monto, 4|fechaTransaccion, 5|tipoTransaccion
    public Transaccion(float monto, LocalDate fechaTransaccion, String tipoTransaccion){
        setMonto(monto);
        setFechaTransaccion(fechaTransaccion);
        setTipoTransaccion(tipoTransaccion);
    }
    public Transaccion(String cbuAsociado, float monto, LocalDate fechaTransaccion, String tipoTransaccion){
        setCbuAsociado(cbuAsociado);
        setMonto(monto);
        setFechaTransaccion(fechaTransaccion);
        setTipoTransaccion(tipoTransaccion);
    }
    public Transaccion(int idTransaccion, String cbuAsociado, float monto, LocalDate fechaTransaccion, String tipoTransaccion){
        setIdTransaccion(idTransaccion);
        setCbuAsociado(cbuAsociado);
        setMonto(monto);
        setFechaTransaccion(fechaTransaccion);
        setTipoTransaccion(tipoTransaccion);
    }
    public float getMonto() { return monto; }
    public void setMonto(float monto) { this.monto = monto; }
    public LocalDate getFechaTransaccion() { return fechaTransaccion; }
    public void setFechaTransaccion(LocalDate fechaTransaccion) { this.fechaTransaccion = fechaTransaccion; }
    public String getTipoTransaccion() { return tipoTransaccion; }
    public void setTipoTransaccion(String tipoTransaccion) { this.tipoTransaccion = tipoTransaccion; }
    public String getCbuAsociado() { return cbuAsociado; }
    public void setCbuAsociado(String cbuAsociado) { this.cbuAsociado = cbuAsociado; }
    public int getIdTransaccion() { return idTransaccion; }
    public void setIdTransaccion(int idTransaccion) { this.idTransaccion = idTransaccion; }

    /*
            public int obtenerDiaFechaTransaccion(){ return getFechaTransaccion().get(Calendar.DAY_OF_MONTH); }
            public int obtenerMesFechaTransaccion(){ return getFechaTransaccion().get(Calendar.MONTH) + 1; }
            public int obtenerAnioFechaTransaccion(){ return getFechaTransaccion().get(Calendar.YEAR); }
            public String obtenerFechaTransaccion(){ return obtenerDiaFechaTransaccion() + "/" + obtenerMesFechaTransaccion()
                                                     + "/" + obtenerAnioFechaTransaccion(); }
            */
    @Override
    public String toString(){
        return "Tipo de transacción: " + getTipoTransaccion() + ", monto: $" + getMonto() + ", fecha transacción: " + getFechaTransaccion().toString();
    }
}
