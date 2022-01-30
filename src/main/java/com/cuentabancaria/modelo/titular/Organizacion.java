package com.cuentabancaria.modelo.titular;
import com.cuentabancaria.modelo.CambiarFecha;
import java.time.LocalDate;
public class Organizacion extends Titular{
    private String nombreOrganizacion;
    private String tipoOrganizacion;
    private LocalDate fechaCreacion;

    public Organizacion(){
        setNombreOrganizacion("");
        setTipoOrganizacion("");
        setFechaCreacion(LocalDate.now());
    }
    public Organizacion(String numeroCuit,String nombreOrganizacion, String tipoOrganizacion, LocalDate fechaCreacion){
        setNombreOrganizacion(nombreOrganizacion);
        setTipoOrganizacion(tipoOrganizacion);
        setFechaCreacion(fechaCreacion);
        setNumeroCuit(numeroCuit);
    }
    public String getNombreOrganizacion() { return nombreOrganizacion; }
    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }
    public String getTipoOrganizacion() { return tipoOrganizacion; }
    public void setTipoOrganizacion(String tipoOrganizacion) { this.tipoOrganizacion = tipoOrganizacion; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    @Override
    public String nombreTabla() { return "organizacion"; }
    @Override
    public String toString() { return "Organización"; }
    @Override
    public String tipoTitular(){
        return "Nombre de la organización: " + getNombreOrganizacion() + ", tipo de organización: "
                + getTipoOrganizacion() + ", fecha de creación: " + obtenerFechaCreacion()
                + ", número de cuit: " + getNumeroCuit();
    }
    @Override
    public String obtenerFxml(){ return "titular/TitularOrganizacion.fxml"; }
    public String obtenerFechaCreacion(){
        CambiarFecha cambiarFecha = new CambiarFecha(getFechaCreacion());
        return cambiarFecha.cambiar();
    }
}
