package test;
import com.cuentabancaria.basedatos.ConexionBD;
import com.cuentabancaria.basedatos.querys.Query;
import com.cuentabancaria.modelo.titular.Persona;
import java.sql.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

public class ConexionBDTest {
    public static ResultSet metodoBD(ConexionBD conexionBD) throws SQLException{
        Connection conexion = null;
        PreparedStatement sentencia;

        try{
            Query query = new Query();
            conexion = conexionBD.getConexion();
            sentencia = conexion.prepareStatement(query.seleccionarPersona());
            return sentencia.executeQuery();
        }catch (Exception excepcion){
            System.out.println(excepcion.getMessage());
        }
        finally {
            conexionBD.cerrarConexion();
        }
        if (conexion!=null){
            try {
                conexion.close();
            }catch (SQLException excepcion){
                System.out.println(excepcion.getMessage());
            }
        }
        return null;
    }
    public static ArrayList<Persona> resultado(ResultSet resultSet, ArrayList<Persona> personas) throws SQLException {
        if (resultSet != null){
            while (resultSet.next()){
                int dni = resultSet.getInt("dni");
                String nombre = resultSet.getString("nombre");
                String segundoNombre = resultSet.getString("segundoNombre");
                String apellido = resultSet.getString("apellido");
                Date fechaNacimiento = resultSet.getDate("fechaNacimiento");
                LocalDate localDate = Instant.ofEpochMilli(fechaNacimiento.getTime())
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                String numeroCuit = resultSet.getString("cuit");

                Persona persona = new Persona(dni, nombre, segundoNombre, apellido,  localDate, numeroCuit);
                personas.add(persona);
            }
            return personas;
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        ConexionBD conexionBD = new ConexionBD();
        ArrayList<Persona> personas = new ArrayList<>();
        ResultSet resultSet = metodoBD(conexionBD);
        personas = resultado(resultSet, personas);
        boolean a = personas == null;
        System.out.println( "Array vacio: " + a);
        assert personas != null;
        for (Persona persona : personas){
            System.out.println(persona.tipoTitular());
        }
        conexionBD.cerrarConexion();
        System.out.println("¿Conexión cerrada? " + conexionBD.isCerrada());
    }
}
