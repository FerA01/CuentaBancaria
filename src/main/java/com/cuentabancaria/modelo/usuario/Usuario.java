package com.cuentabancaria.modelo.usuario;
import com.cuentabancaria.modelo.titular.Titular;
import java.time.LocalDate;
public class Usuario {
    private String nombreUsuario;
    private String contrasena;
    private Titular titular;
    private LocalDate fechaCreacion;
    private String cuitTitular;

    public Usuario(){}
    public Usuario(String nombreUsuario, String contrasena){
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
        setFechaCreacion(LocalDate.now());
        setCuitTitular(cuitTitular);
    }
    public Usuario(String nombreUsuario, String contrasena, String cuitTitular){
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
        setCuitTitular(cuitTitular);
        setFechaCreacion(LocalDate.now());
    }
    public Usuario(String nombreUsuario, String contrasena, String cuitTitular, LocalDate fechaCreacion){
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
        setCuitTitular(cuitTitular);
        setFechaCreacion(fechaCreacion);
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public Titular getTitular() { return titular; }
    public void setTitular(Titular titular) { this.titular = titular; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public String getCuitTitular(){ return this.cuitTitular; }
    public void setCuitTitular(String cuitTitular){ this.cuitTitular = cuitTitular; }

    @Override
    public String toString(){ return "Usuario { Nombre de usuario: " + getNombreUsuario() + " Contraseña: " + getContrasena() + " Fecha creación: " + getFechaCreacion() + " Cuit del titular: " + getCuitTitular() + " }"; }
}
