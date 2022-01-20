package com.cuentabancaria.modelo;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

public class CambiarFecha{
    private LocalDate fecha;

    public CambiarFecha(){ setFecha(LocalDate.now()); }
    public CambiarFecha(LocalDate fecha){ setFecha(fecha); }

    public LocalDate getFecha() { return this.fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public java.sql.Date localDateToDate(LocalDate localDate){
        return java.sql.Date.valueOf( localDate );
    }
    public LocalDate dateToLocalDate(java.sql.Date date){
        return date.toLocalDate();
    }
    public String cambiar(){
        DateTimeFormatter formatterLocalDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return formatterLocalDate.format(getFecha());
    }
}
