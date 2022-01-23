package com.cuentabancaria.basedatos.controlador;
import com.cuentabancaria.basedatos.ConexionBD;
import com.cuentabancaria.basedatos.querys.Query;
import com.cuentabancaria.modelo.CambiarFecha;
import com.cuentabancaria.modelo.cuentas.Transaccion;
import com.cuentabancaria.modelo.titular.Organizacion;
import com.cuentabancaria.modelo.titular.Persona;
import java.sql.*;

public class ControladorBaseDato {
    private static Query query;
    private ConexionBD conexionBD;
    private CambiarFecha cambiarFecha;
    private static Connection conexion;
    private static PreparedStatement sentencia;
    private static ResultSet resultado;

    public ControladorBaseDato(){
        setQuery(new Query());
        setConexionBD(new ConexionBD());
        setCambiarFecha(new CambiarFecha());
        setConexion(null);
        setSentencia(null);
        setResultado(null);
    }

    public Query getQuery() { return query; }
    private static void setQuery(Query elQuery) { query = elQuery; }
    public ConexionBD getConexionBD() { return conexionBD; }
    private void setConexionBD(ConexionBD conexionBD) { this.conexionBD = conexionBD; }
    public CambiarFecha getCambiarFecha() { return cambiarFecha; }
    private void setCambiarFecha(CambiarFecha cambiarFecha) { this.cambiarFecha = cambiarFecha; }

    public static Connection getConexion() { return conexion; }
    public static void setConexion(Connection conexion) { ControladorBaseDato.conexion = conexion; }
    public static PreparedStatement getSentencia() { return sentencia; }
    public static void setSentencia(PreparedStatement sentencia) { ControladorBaseDato.sentencia = sentencia; }
    public static ResultSet getResultado() { return resultado; }
    public static void setResultado(ResultSet resultado) { ControladorBaseDato.resultado = resultado; }

    public int obtenerUltimoIDTabla(String id, String nombreTabla){
        int valorId = 0;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado;
        try{
            //Creo la conexion
            conexion = getConexionBD().getConexion();
            //Elijo la Query
            sentencia = conexion.prepareStatement(getQuery().obtenerUltimoIDTabla());
            sentencia.setString(1, id);
            sentencia.setString(2, nombreTabla);
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                try {
                    valorId = resultado.getInt(1);
                    return valorId;
                }catch (Exception excepcion){
                    System.out.println("Error al obtener el valor: " + excepcion.getMessage());
                }
            }
            return valorId;
        }catch (SQLException excepcion){ System.out.println("Error en la conexión: " + excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            if (conexion != null && sentencia != null){
                try{
                    conexion.close();
                    sentencia.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return -1;
    }
    public int obtenerUltimoID(){  //Autoincrementador para la tabla titular
        int id = 0;
        Connection conexion = null;
        PreparedStatement sentencia = null;
        ResultSet resultado;
        try{
            //Creo la conexion
            conexion = getConexionBD().getConexion();
            //Elijo la Query
            sentencia = conexion.prepareStatement(getQuery().obtenerUltimoID());
            resultado = sentencia.executeQuery();

            while (resultado.next()){
                try {
                    id = resultado.getInt(1);
                    return id;
                }catch (Exception excepcion){
                    System.out.println("Error al obtener el valor: " + excepcion.getMessage());
                }
            }
            return id;
        }catch (SQLException excepcion){ System.out.println("Error en la conexión: " + excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            if (conexion != null && sentencia != null){
                try{
                    conexion.close();
                    sentencia.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return -1;
    }
    public void insertarPersona(Persona persona){ // 1|dni, 2|nombre, 3|segundoNombre, 4|apellido, 5|fechaNacimiento, 6|numeroCuit
        Connection conexion = null;
        PreparedStatement sentencia = null;
        try{
            //Creo la conexion
            conexion = getConexionBD().getConexion();
            //Elijo la Query
            sentencia = conexion.prepareStatement(getQuery().insertarPersona());
            //Obtengo los valores de persona y los paso a la PS
            sentencia.setInt(1, persona.getDni());
            sentencia.setString(2, persona.getNombre());
            sentencia.setString(3, persona.getSegundoNombre());
            sentencia.setString(4, persona.getApellido());
            //Transformo el LocalDate a date
            java.sql.Date fecha = getCambiarFecha().localDateToDate(persona.getFechaNacimiento());
            sentencia.setDate(5, fecha);
            sentencia.setString(6, persona.getNumeroCuit());
            sentencia.executeUpdate();
        }catch (SQLException exepcion){ System.out.println("Error en la conexión: " + exepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            if (conexion != null && sentencia != null){
                try{
                    conexion.close();
                    sentencia.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    public void insertarOrganizacion(Organizacion organizacion){ // 1|nombreOrganización, 2|tipoOrganizacion, 3|fechaCreacion, 4|cuit
        Connection conexion = null;
        PreparedStatement sentencia = null;
        try{
            conexion = getConexionBD().getConexion();
            sentencia = conexion.prepareStatement(getQuery().insertarOrganizacion());
            sentencia.setString(1, organizacion.getNombreOrganizacion());
            sentencia.setString(2, organizacion.getTipoOrganizacion());
            java.sql.Date fecha = getCambiarFecha().localDateToDate(organizacion.getFechaCreacion());
            sentencia.setDate(3, fecha);
            sentencia.setString(4, organizacion.getNumeroCuit());

            sentencia.executeUpdate();
        }catch (SQLException excepcion){
            excepcion.getStackTrace();
        }finally {
            getConexionBD().cerrarConexion();
            if (conexion != null && sentencia != null){
                try{
                    conexion.close();
                    sentencia.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    public void insertarTitular(String cuitTitular) throws SQLException { //  1|id, 2|numero_cuit
        Connection conexion = null;
        PreparedStatement sentencia = null;
        try {
            //Establezco conexion con la base de datos
            conexion = getConexionBD().getConexion();
            //Elijo la Query
            sentencia = conexion.prepareStatement(getQuery().insertarTitular(), PreparedStatement.RETURN_GENERATED_KEYS);
            sentencia.setString(1, cuitTitular);
            //Ejecuto la sentencia.
            sentencia.executeUpdate();
        }catch (SQLException excepcion){ System.out.println("Error en la conexión: " + excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            if (conexion != null && sentencia != null){
                try{
                    conexion.close();
                    sentencia.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    public void insertarTransaccion(Transaccion transaccion){ // 1|idTransaccion, 2|cbuAsociado, 3|monto, 4|fechaTransaccion, 5|tipoTransaccion
        Connection conexion = null;
        PreparedStatement sentencia = null;
        try {
            conexion = getConexionBD().getConexion();
            sentencia = conexion.prepareStatement(getQuery().insertartransaccion(), Statement.RETURN_GENERATED_KEYS);

            sentencia.setString(1, transaccion.getCbuAsociado());
            sentencia.setFloat(2, transaccion.getMonto());
            java.sql.Date fecha = getCambiarFecha().localDateToDate(transaccion.getFechaTransaccion());
            sentencia.setDate(3, fecha);
            sentencia.setString(4, transaccion.getTipoTransaccion());
            /*Si no carga la transaccion, entonces no se registra en la base de datos*/
            sentencia.executeUpdate();
        }catch (SQLException excepcion){
            excepcion.getStackTrace();
        }finally {
            getConexionBD().cerrarConexion();
            if (conexion != null && sentencia != null){
                try {
                    conexion.close();
                    sentencia.close();
                }catch (SQLException excepcion){
                    excepcion.getStackTrace();
                }
            }
        }
    }

    public ResultSet obtenerPersonas(){
        setConexionBD(new ConexionBD());
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().seleccionarPersona()));
            setResultado(getSentencia().executeQuery());
            return  getResultado();
        }catch (SQLException exepcion){
            exepcion.getStackTrace();
        }finally { getConexionBD().cerrarConexion(); }
        return null;
    }
    public void cerrarConexiones() throws SQLException {
        if (!getSentencia().isClosed()){
            try {
                getSentencia().close();
            }catch (SQLException excepcion){
                System.out.println(excepcion.getMessage());
            }
        }
        if (!getResultado().isClosed()){
            try{
                getResultado().close();
            }catch (SQLException excepcion){
                System.out.println(excepcion.getMessage());
            }
        }
    }
}
