package com.cuentabancaria.modelo.titular;
import com.cuentabancaria.modelo.CambiarFecha;
import java.time.LocalDate;
public class Persona extends Titular{
    private String nombre;
    private String segundoNombre;
    private String apellido;
    private int dni;
    private LocalDate fechaNacimiento;
    private String numeroCuit;

    public Persona(){
        setNombre("");
        setSegundoNombre("");
        setApellido("");
        setDni(0);
        setNumeroCuit("");
        setFechaNacimiento(null);
    }
    public Persona(String nombre, String apellido, int dni, String numeroCuit, LocalDate fechaNacimiento){
        setNombre(nombre);
        setSegundoNombre("");
        setApellido(apellido);
        setDni(dni);
        setNumeroCuit(numeroCuit);
        setFechaNacimiento(fechaNacimiento);
    }
    public Persona(String nombre, String segundoNombre, String apellido, int dni, String numeroCuit
                  , LocalDate fechaNacimiento){
        setNombre(nombre);
        setSegundoNombre(segundoNombre);
        setApellido(apellido);
        setDni(dni);
        setNumeroCuit(numeroCuit);
        setFechaNacimiento(fechaNacimiento);
    }
    public Persona(int dni, String nombre, String segundoNombre, String apellido, LocalDate fechaNacimiento, String numeroCuit){
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
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
    @Override
    public String toString() { return "Personal"; }
    @Override
    public String tipoTitular() {
        if (getSegundoNombre().equals("")){
            return "Nombre: " + getNombre() + " " + getApellido() + ", dni: " + getDni() + ", número de cuit: " + getNumeroCuit() + ", fecha de nacimiento: " + obtenerFechaNacimiento();
        }else {
            return "Nombre: " + getNombre() + " " + getSegundoNombre() + " " + getApellido() + ", dni: " + getDni()
                    + ", número de cuit: " + getNumeroCuit() + ", fecha de nacimiento: " + obtenerFechaNacimiento();
        }
    }
    @Override
    public String obtenerFxml(){ return "titular/TitularPersona.fxml"; }
    public String obtenerFechaNacimiento(){
        CambiarFecha cambiarFecha = new CambiarFecha(getFechaNacimiento());
        return cambiarFecha.cambiar();
    }
}
