package com.cuentabancaria.basedatos;
import java.sql.*;
public class ConexionBD {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Configuracion configuracion;
    private static Connection conexion;

    public ConexionBD() { setConexion(null); }

    public static void setConexion(Connection conexion) { ConexionBD.conexion = conexion; }
    public boolean isCerrada() throws SQLException { return conexion.isClosed(); }
    public Connection getConexion() throws SQLException {
        this.configuracion = new Configuracion();
        try {
            Class.forName(DRIVER);
            conexion = configuracion.conectar();
        } catch (SQLException | ClassNotFoundException excepcion) {
            System.out.println("Error en la conexión con la base de datos: " + excepcion.getMessage());
        }
        return conexion;
    }
    public void cerrarConexion() {
        if (conexion != null) {
            try {
                getConexion().close();
                System.out.println("Conexión de la base de datos cerrada");
            } catch (SQLException excepcion) {
                System.out.println("Error al cerrar la conexión de la base da datos: " + excepcion.getMessage());
            }
        }
    }
}