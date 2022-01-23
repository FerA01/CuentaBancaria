package com.cuentabancaria.basedatos;
import java.sql.*;
public class ConexionBD {
    private static final String NOMBREDB = "cuenta_bancaria";
    private static final String USUARIO = "root";
    private static final String CONTRASENA = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection conexion;

    public ConexionBD() { setConexion(null); }

    public static void setConexion(Connection conexion) { ConexionBD.conexion = conexion; }

    public boolean isCerrada() throws SQLException { return conexion.isClosed(); }
    public Connection getConexion() throws SQLException {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + NOMBREDB
                    , USUARIO
                    , CONTRASENA
            );
            if (conexion != null) {
                System.out.println("Conexión establecida.");
            }
        } catch (SQLException | ClassNotFoundException excepcion) {
            System.out.println("Error en la conexión: " + excepcion.getMessage());
        }
        return conexion;
    }

    public void cerrarConexion() {
        if (conexion != null) {
            try {
                getConexion().close();
                System.out.println("Conexión cerrada");
            } catch (SQLException excepcion) {
                System.out.println("Error al cerrar la conexión: " + excepcion.getMessage());
            }
        }
    }
}