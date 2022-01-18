package com.cuentabancaria.modelo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class CambiarFecha {
    private LocalDate fecha;

    public CambiarFecha(){ setFecha(LocalDate.now()); }
    public CambiarFecha(LocalDate fecha){ setFecha(fecha); }

    public LocalDate getFecha() { return this.fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String cambiar(){
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatterLocalDate.format(getFecha());
    }
}
