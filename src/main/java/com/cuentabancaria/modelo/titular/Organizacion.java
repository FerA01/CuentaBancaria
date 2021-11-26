package com.cuentabancaria.modelo.titular;
import javafx.scene.layout.AnchorPane;

import java.util.Calendar;
public class Organizacion extends Titular{
    private String nombreOrganizacion;
    private String tipoOrganizacion;
    private Calendar fechaCreacion;

    public Organizacion(){
        setNombreOrganizacion("");
        setTipoOrganizacion("");
        setFechaCreacion(Calendar.getInstance());
    }
    public Organizacion(String numeroCuit,String nombreOrganizacion, String tipoOrganizacion, Calendar fechaCreacion){
        setNombreOrganizacion(nombreOrganizacion);
        setTipoOrganizacion(tipoOrganizacion);
        setFechaCreacion(fechaCreacion);
        setNumeroCuit(numeroCuit);
    }
    public String getNombreOrganizacion() { return nombreOrganizacion; }
    public void setNombreOrganizacion(String nombreOrganizacion) { this.nombreOrganizacion = nombreOrganizacion; }
    public String getTipoOrganizacion() { return tipoOrganizacion; }
    public void setTipoOrganizacion(String tipoOrganizacion) { this.tipoOrganizacion = tipoOrganizacion; }
    public Calendar getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Calendar fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public int obtenerDiaFechaCreacion(){ return getFechaCreacion().get(Calendar.DAY_OF_MONTH);}
    public int obtenerMesFechaCreacion(){ return getFechaCreacion().get(Calendar.MONTH) + 1; }
    public int obtenerAnioFechaCreacion(){ return getFechaCreacion().get(Calendar.YEAR); }
    public String obtenerFechaCreacion(){
        return obtenerDiaFechaCreacion() + "/" + obtenerMesFechaCreacion() + "/" + obtenerAnioFechaCreacion();
    }
    @Override
    public String tipoTitular() { return "Organización"; }
    @Override
    public String toString(){
        return "Nombre de la organización: " + getNombreOrganizacion() + ", tipo de organización: "
                + getTipoOrganizacion() + ", fecha de creación: " + obtenerFechaCreacion()
                + ", número de cuit: " + getNumeroCuit();
    }

    @Override
    public AnchorPane panelTitular() {
        return null;
    }
}
