package com.cuentabancaria.modelo.usuario;
public class Usuario {
    private String nombreUsuario;
    private String contrasena;

    public Usuario(){}
    public Usuario(String nombreUsuario, String contrasena){
        setNombreUsuario(nombreUsuario);
        setContrasena(contrasena);
    }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    @Override
    public String toString(){ return "Nombre de usuario: " + getNombreUsuario() + " Contraseña: " + getContrasena(); }
}
