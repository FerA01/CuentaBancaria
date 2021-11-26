package com.cuentabancaria.modelo.titular;
import javafx.scene.layout.AnchorPane;

import java.util.Calendar;
public class Persona extends Titular{
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private int dni;
    private Calendar fechaNacimiento;

    public Persona(){}
    public Persona(String nombre, String apellido, int dni, String numeroCuit, Calendar fechaNacimiento){
        setNombre(nombre);
        setSegundoNombre("");
        setApellido(apellido);
        setDni(dni);
        setNumeroCuit(numeroCuit);
        setFechaNacimiento(fechaNacimiento);
    }
    public Persona(String nombre, String segundoNombre, String apellido, int dni, String numeroCuit
                  , Calendar fechaNacimiento){
        setNombre(nombre);
        setSegundoNombre(segundoNombre);
        setApellido(apellido);
        setDni(dni);
        setNumeroCuit(numeroCuit);
        setFechaNacimiento(fechaNacimiento);
    }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getSegundoNombre() { return segundoNombre; }
    public void setSegundoNombre(String segundoNombre) { this.segundoNombre = segundoNombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public int getDni() { return dni; }
    public void setDni(int dni) { this.dni = dni; }
    public Calendar getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(Calendar fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public int obtenerDiaFechaNacimiento(){ return getFechaNacimiento().get(Calendar.DAY_OF_MONTH);}
    public int obtenerMesFechaNacimiento(){ return getFechaNacimiento().get(Calendar.MONTH) + 1; }
    public int obtenerAnioFechaNacimiento(){ return getFechaNacimiento().get(Calendar.YEAR); }
    public String obtenerFechaCreacion(){ return obtenerDiaFechaNacimiento()
                                          + "/" + obtenerMesFechaNacimiento()
                                          + "/" + obtenerAnioFechaNacimiento();
    }
    @Override
    public String tipoTitular() { return "Personal"; }
    @Override
    public String toString() {
        return "Nombre: " + getNombre() + " " + getSegundoNombre() + " " + getApellido() + ", dni: " + getDni()
                + ", número de cuit: " + getNumeroCuit() + ", fecha de nacimiento: " + obtenerFechaCreacion();
    }

    @Override
    public AnchorPane panelTitular() {

        AnchorPane panel = new AnchorPane();
        panel.setPrefHeight(230);
        panel.setPrefWidth(570);
        return panel;
    }
}
