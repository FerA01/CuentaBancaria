package com.cuentabancaria.basedatos.controlador;
import com.cuentabancaria.basedatos.ConexionBD;
import com.cuentabancaria.basedatos.querys.Query;
import com.cuentabancaria.controlador.InputValidator;
import com.cuentabancaria.modelo.CambiarFecha;
import com.cuentabancaria.modelo.cuentas.CajaAhorro;
import com.cuentabancaria.modelo.cuentas.CuentaBancaria;
import com.cuentabancaria.modelo.cuentas.CuentaCorriente;
import com.cuentabancaria.modelo.cuentas.Transaccion;
import com.cuentabancaria.modelo.titular.Organizacion;
import com.cuentabancaria.modelo.titular.Persona;
import com.cuentabancaria.modelo.titular.Titular;
import com.cuentabancaria.modelo.usuario.Usuario;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class ControladorBaseDato {
    private static Query query;
    private ConexionBD conexionBD;
    private CambiarFecha cambiarFecha;
    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultado;

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
    public Connection getConexion() { return conexion; }
    public void setConexion(Connection conexion) { this.conexion = conexion; }
    public PreparedStatement getSentencia() { return sentencia; }
    public void setSentencia(PreparedStatement sentencia) { this.sentencia = sentencia; }
    public ResultSet getResultado() { return resultado; }
    public void setResultado(ResultSet resultado) { this.resultado = resultado; }

    // Cerrar conexiones.
    public int obtenerCantidadColumnasTabla(String nombreTabla) throws SQLException{
        setConexionBD(new ConexionBD());
        int cantidadColumnas = 0;
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerCantidadColumnasTabla()));
            getSentencia().setString(1, nombreTabla);
            setResultado(getSentencia().executeQuery());
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            if (getResultado().next()){
                cantidadColumnas = getResultado().getInt(1);
            }
            getConexionBD().cerrarConexion();
            cerrarConexiones();
        }
        return cantidadColumnas;
    }
    public void cerrarConexionesInsertar() throws SQLException {
        getConexionBD().cerrarConexion();
        if (getSentencia() != null){
            try {
                getSentencia().close();
            }catch (SQLException excepcion){
                System.out.println("Error al cerrar la conexión: " + excepcion.getMessage());
            }
        }
        if (getConexion() != null){
            try {
                getConexion().close();
            }catch (SQLException excepcion){
                System.out.println("Error al cerrar la conexión: " + excepcion.getMessage());
            }
        }
    }
    public void cerrarConexiones() throws SQLException {
        getConexionBD().cerrarConexion();
        if (getSentencia() != null){
            try {
                getSentencia().close();
            }catch (SQLException excepcion){
                System.out.println(excepcion.getMessage());
            }
        }
        if (getResultado() != null){
            try{
                getResultado().close();
            }catch (SQLException excepcion){
                System.out.println(excepcion.getMessage());
            }
        }
        if (getConexion() != null){
            try {
                getConexion().close();
            }catch (SQLException excepcion){
                System.out.println(excepcion.getMessage());
            }
        }
    }
    public void insertarPersona(Persona persona) throws SQLException{ // 1|dni, 2|nombre, 3|segundoNombre, 4|apellido, 5|fechaNacimiento, 6|numeroCuit, 7|tipoTitular
        try{
            //Creo la conexion
            setConexion(getConexionBD().getConexion());
            //Elijo la Query
            setSentencia(getConexion().prepareStatement(getQuery().insertarPersona()));
            //Obtengo los valores de persona y los paso a la PS
            getSentencia().setInt(1, persona.getDni());
            getSentencia().setString(2, persona.getNombre());
            getSentencia().setString(3, persona.getSegundoNombre());
            getSentencia().setString(4, persona.getApellido());
            //Transformo el LocalDate a date
            java.sql.Date fecha = getCambiarFecha().localDateToDate(persona.getFechaNacimiento());
            getSentencia().setDate(5, fecha);
            getSentencia().setString(6, persona.getNumeroCuit());
            getSentencia().setString(7, persona.toString());
            getSentencia().executeUpdate();
        }catch (SQLException exepcion){ System.out.println("Error en la conexión: " + exepcion.getMessage());
        }finally { cerrarConexionesInsertar(); }
    }
    public void insertarOrganizacion(Organizacion organizacion) throws SQLException{ // 1|nombreOrganización, 2|tipoOrganizacion, 3|fechaCreacion, 4|cuit, 5|tipoTitular
        Connection conexion = null;
        PreparedStatement sentencia = null;
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().insertarOrganizacion()));
            getSentencia().setString(1, organizacion.getNombreOrganizacion());
            getSentencia().setString(2, organizacion.getTipoOrganizacion());
            java.sql.Date fecha = getCambiarFecha().localDateToDate(organizacion.getFechaCreacion());
            getSentencia().setDate(3, fecha);
            getSentencia().setString(4, organizacion.getNumeroCuit());
            getSentencia().setString(5, organizacion.toString());
            getSentencia().executeUpdate();
        }catch (SQLException excepcion){ excepcion.getStackTrace();
        }finally { cerrarConexionesInsertar(); }
    }
    public void insertarTitular(String cuitTitular, String tipoTitular) throws SQLException { //  1|id, 2|numero_cuit, 3|tipoTitular
        try {
            //Establezco conexion con la base de datos
            setConexion(getConexionBD().getConexion());
            //Elijo la Query
            setSentencia(getConexion().prepareStatement(getQuery().insertarTitular(), PreparedStatement.RETURN_GENERATED_KEYS));
            getSentencia().setString(1, cuitTitular);
            getSentencia().setString(2, tipoTitular);
            //Ejecuto la sentencia.
            getSentencia().executeUpdate();
        }catch (SQLException excepcion){ System.out.println("Error en la conexión: " + excepcion.getMessage());
        }finally { cerrarConexionesInsertar(); }
    }
    public void insertarTransaccion(Transaccion transaccion) throws SQLException { // 1|idTransaccion, 2|cbuAsociado, 3|monto, 4|fechaTransaccion, 5|tipoTransaccion
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().insertartransaccion(), Statement.RETURN_GENERATED_KEYS));

            getSentencia().setString(1, transaccion.getCbuAsociado());
            getSentencia().setFloat(2, transaccion.getMonto());
            //java.sql.Date fecha = getCambiarFecha().localDateToDate(transaccion.getFechaTransaccion());
            //getSentencia().setDate(3, fecha);
            getSentencia().setString(3, transaccion.getTipoTransaccion());
            /*Si no carga la transaccion, entonces no se registra en la base de datos*/
            getSentencia().executeUpdate();
        }catch (SQLException excepcion){ excepcion.getStackTrace();
        }finally { cerrarConexionesInsertar(); }
    }
    public boolean insertarCuentaBancaria(CuentaBancaria cuentaBancaria) throws SQLException{
        try {  // 1|cbu  2|saldo 3|limiteMinimoCuenta 4|cantidadExtraccionesMes 5|fechaApertura 6|titularCuenta 7|tipoCuenta
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().insertarCuentaBancaria()));
            getSentencia().setString(1, InputValidator.generarCbu());
            getSentencia().setFloat(2, cuentaBancaria.getSaldo());
            getSentencia().setFloat(3, cuentaBancaria.getLimiteMinimoCuenta());
            getSentencia().setInt(4, cuentaBancaria.getCantidadExtraccionesPorMes());
            getSentencia().setString(5, cuentaBancaria.numeroCuitTitular());
            getSentencia().setString(6, cuentaBancaria.tipoCuentaBancaria());
            getSentencia().executeUpdate();
            return true;
        }catch (SQLException excepcion){ System.out.println("Error: " + excepcion.getMessage());
        }finally { cerrarConexionesInsertar(); }
        return false;
    }
    public void insertarUsuario(Usuario usuario) throws SQLException{
        setConexionBD(new ConexionBD());
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().insertarUsuario(), Statement.RETURN_GENERATED_KEYS));
            getSentencia().setString(1, usuario.getNombreUsuario());
            getSentencia().setString(2, usuario.getContrasena());
            getSentencia().setString(3, usuario.getCuitTitular());
            Date fecha = getCambiarFecha().localDateToDate(usuario.getFechaCreacion());

            getSentencia().executeUpdate();
        }catch (SQLException excepcion){
            System.out.println("Error: " + excepcion.getMessage());
        }finally { cerrarConexionesInsertar(); }
    }
    public boolean loginUsuario(Usuario usuario){
        setConexionBD(new ConexionBD());
        return false;
    }

    //Obtener datos
    public ResultSet obtenerApellidos(){
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerApellidosPersonas()));
            setResultado(getSentencia().executeQuery());
            return  getResultado();
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        finally { getConexionBD().cerrarConexion(); }
        return null;
    }
    public ResultSet obtenerPersonas() throws SQLException {
        setConexionBD(new ConexionBD());
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().seleccionarPersona()));
            setResultado(getSentencia().executeQuery());
            return  getResultado();
        }catch (SQLException exepcion){ System.out.println(exepcion.getMessage()); }
        finally { getConexionBD().cerrarConexion(); }
        return null;
    }
    public Persona obtenerPersonaPorCuit(String cuit) throws SQLException{
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().seleccionarPersonaPorCuit()));
            getSentencia().setString(1, cuit);
            setResultado(getSentencia().executeQuery());
            if(getResultado().next()){
                int dni = getResultado().getInt(1);
                String nombre = getResultado().getString(2);
                String segundoNombre = getResultado().getString(3);
                String apellido = getResultado().getString(4);
                Date fechaNacimiento = getResultado().getDate(5);
                LocalDate fecha = getCambiarFecha().dateToLocalDate(fechaNacimiento);
                return new Persona(dni, nombre, segundoNombre, apellido, fecha, cuit);
            }
            return null;
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        finally {
            getConexionBD().cerrarConexion();
            cerrarConexiones();
        }
        return null;
    }
    public Organizacion obtenerOrganizacionPorCuit(String cuit) throws SQLException{
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().seleccionarOrganizacionPorCuit()));
            getSentencia().setString(1, cuit);
            setResultado(getSentencia().executeQuery());
            if(getResultado().next()){
                String nombreOrganizacion = getResultado().getString(1);
                String tipoOrganizacion = getResultado().getString(2);
                Date fecha = getResultado().getDate(3);
                LocalDate fechaCreacion = getCambiarFecha().dateToLocalDate(fecha);
                return new Organizacion(cuit, nombreOrganizacion, tipoOrganizacion, fechaCreacion);
            }
            return null;
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            cerrarConexiones();
        }
        return null;
    }
    public boolean existeNumeroCuit(String cuit) throws SQLException{
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerCuitTitular()));
            getSentencia().setString(1, cuit);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()) { //Si hay resultado entro en el if
                return  (getResultado().getString(1)).equals(cuit);
            }
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        finally { getConexionBD().cerrarConexion(); }
        return false;
    }
    public boolean existeNumeroDni(String cuit, String dni) throws SQLException{
        setConexionBD(new ConexionBD());
        try{
            int dni1;
            int dni2;
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerDniPersona()));
            getSentencia().setString(1, cuit);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()){
                dni1 = (getResultado().getInt(1));
                dni2 = (int) Integer.parseInt(dni);
                return (dni1 == dni2) ? true : false;
            }else{ return false; }
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        finally { cerrarConexiones(); }
        return false;
    }
    public boolean existeNumeroDni(String dni) throws SQLException{
        setConexionBD(new ConexionBD());
        try{
            int dni1 = Integer.parseInt(dni);
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerDni()));
            getSentencia().setInt(1, dni1);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()){
                int dni2 = (getResultado().getInt(1));
                return dni1 == dni2;
            }else{ return false; }
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        finally { cerrarConexiones(); }
        return false;
    }
    public boolean existeNombreUsuario(String nombreUsuario) throws SQLException{
        setConexionBD(new ConexionBD());
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerNombreUsuario()));
            getSentencia().setString(1, nombreUsuario);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()){
                return (getResultado().getString(1)).equals(nombreUsuario);
            }
        }catch (SQLException exception){
            System.out.println(exception.getMessage());
        }finally {
            cerrarConexiones();
        }
        return false;
    }

    public String obtenerContrasena(String nombreUsuario) throws SQLException{
        setConexionBD(new ConexionBD());
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerContrasena()));
            getSentencia().setString(1, nombreUsuario);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()){
                return getResultado().getString(1);
            }
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            cerrarConexiones();
        }
        return null;
    }

    public Titular abc(Titular titular) throws SQLException{
        setConexionBD(new ConexionBD());
        Titular titular1 = null;
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().abc()));
            getSentencia().setString(1, titular.nombreTabla());
            getSentencia().setString(2, titular.getNumeroCuit());
            setResultado(getSentencia().executeQuery());
            if ((titular.nombreTabla()).equals("persona")){
                titular1 = obtenerPersona(getResultado());
            }
            if ((titular.nombreTabla()).equals("organizacion")){
                titular1 = obtenerOrganizacion(getResultado());
            }
            getConexionBD().cerrarConexion();
            cerrarConexiones();
            return titular1;
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            cerrarConexiones();
        }
        return null;
    }
    public Titular abc2(Titular titular) throws SQLException{
        setConexionBD(new ConexionBD());
        Titular titular1 = null;
        try{
            setConexion(getConexionBD().getConexion());
            if (titular instanceof Persona){
                setSentencia(getConexion().prepareStatement(getQuery().seleccionarPersona()));
            }
            if (titular instanceof Organizacion){
                setSentencia(getConexion().prepareStatement(getQuery().seleccionarOrganizacion()));
            }
            setResultado(getSentencia().executeQuery());
            if ((titular.nombreTabla()).equals("persona")){
                titular1 = obtenerPersona(getResultado());
            }else{
                titular1 = obtenerOrganizacion(getResultado());
            }
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            cerrarConexiones();
        }
        return titular1;
    }
    private Persona obtenerPersona(ResultSet resultado) throws SQLException {
        if (resultado.next()){
            int dni = resultado.getInt(1);
            String nombre = resultado.getString(2);
            String segundoNombre = resultado.getString(3);
            String apellido = resultado.getString(4);
            Date fecha = resultado.getDate(5);
            String cuit = resultado.getString(6);
            LocalDate fechaNacimiento = getCambiarFecha().dateToLocalDate(fecha);
            return new Persona(dni, nombre, segundoNombre, apellido, fechaNacimiento, cuit);
        }
        return null;
    }
    private Organizacion obtenerOrganizacion(ResultSet resultado) throws SQLException{
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().seleccionarOrganizacionPorCuit()));
            setResultado(getSentencia().executeQuery());
            if(getResultado().next()){
                String nombreOrganizacion = resultado.getString(1);
                String tipoOrganizacion = resultado.getString(2);
                Date fecha = resultado.getDate(3);
                LocalDate fechaCreacion = getCambiarFecha().dateToLocalDate(fecha);
                String cuit = resultado.getString(4);
                getConexionBD().cerrarConexion();
                cerrarConexiones();
                return new Organizacion(cuit, nombreOrganizacion, tipoOrganizacion, fechaCreacion);
            }
            return null;
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            getConexionBD().cerrarConexion();
            cerrarConexiones();
        }
        return null;
    }
    public CuentaBancaria obtenerCuentaBancaria(String titularCuenta) throws SQLException{
        setConexionBD(new ConexionBD());
        CambiarFecha cambiarFecha = new CambiarFecha();
        CuentaBancaria cuentaBancaria = null;
        try {  // 1|cbu  2|saldo 3|limiteMinimoCuenta 4|cantidadExtraccionesMes 5|fechaApertura 6|titularCuenta 7|tipoCuenta
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerCuentaBancaria()));
            getSentencia().setString(1, titularCuenta);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()){
                String cbu = getResultado().getString(1);
                float saldo = getResultado().getFloat(2);
                float limiteMinimoCuenta = getResultado().getFloat(3);
                int extraccionesMes = getResultado().getInt(4);
                Date fechaApertura = getResultado().getDate(5);
                LocalDate fecha = cambiarFecha.dateToLocalDate(fechaApertura);
                String cuitTitular = getResultado().getString(6);
                String tipoCuenta = getResultado().getString(7);
                cuentaBancaria = tipoCuentaBancaria(tipoCuenta);
                if (cuentaBancaria != null){
                    cuentaBancaria.setCbu(cbu);
                    cuentaBancaria.setSaldo(saldo);
                    cuentaBancaria.setLimiteMinimoCuenta(limiteMinimoCuenta);
                    cuentaBancaria.setCantidadExtraccionesPorMes(extraccionesMes);
                    cuentaBancaria.setFechaApertura(fecha);
                    cuentaBancaria.setNumeroCuit(cuitTitular);
                    return cuentaBancaria;
                }
                else{ System.out.println("¡La cuenta bancaria es nula!"); }
            }
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage());
        }finally { cerrarConexiones(); }
        return cuentaBancaria;
    }
    public ResultSet obtenerTransaccionesNumeroCbu(String cbu) throws SQLException{
        setConexionBD(new ConexionBD());
        ResultSet resultado = null;
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerTransaccionesNumeroCbu()));
            getSentencia().setString(1, cbu);
            setResultado(getSentencia().executeQuery());

        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }
        resultado = getResultado();
        return resultado;
    }
    public List<Transaccion> obtenerTransaccionesNumeroCuit(ResultSet resultado) throws SQLException {
        List<Transaccion> transacciones = new ArrayList<>(); //idTransaccion, cbuAsociado, monto, fechaTransaccion, tipoTransaccion
        while (resultado.next()){
            int id = resultado.getInt(1);
            String cbu = resultado.getString(2);
            float monto = resultado.getFloat(3);
            Date fecha = resultado.getDate(4);
            String tipoTransaccion = resultado.getString(5);
            CambiarFecha cambiarFecha = new CambiarFecha();
            Transaccion transaccion = new Transaccion(id, cbu, monto, cambiarFecha.dateToLocalDate(fecha), tipoTransaccion);
            transacciones.add(transaccion);
        }
        return transacciones;
    }
    private CuentaBancaria tipoCuentaBancaria(String tipoCuenta){
        if (tipoCuenta.equals("Caja de ahorro")){ return new CajaAhorro(); }
        if (tipoCuenta.equals("Cuenta Corriente")){ return new CuentaCorriente(); }
        return null;
    }
    public void actualizarDatosCuentaBancaria(float saldo, int cantidadTransaccionesMes, String cbu) throws SQLException{
        setConexionBD(new ConexionBD());
        try{
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().actualizarDatosCuentaBancaria()));
            getSentencia().setFloat(1, saldo);
            getSentencia().setInt(2, cantidadTransaccionesMes);
            getSentencia().setString(3, cbu);
            getSentencia().executeUpdate();
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        finally { cerrarConexionesInsertar(); }
    }
    public Usuario obtenerUsuario(String nombreUsuario) throws SQLException{
        setConexionBD(new ConexionBD());
        try{ // 1| nombreUsuario, 2| contrasena, 3| cuitTitular, 4| fechaCreacion
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerUsuario()));
            getSentencia().setString(1, nombreUsuario);
            setResultado(getSentencia().executeQuery());
            if (getResultado().next()){
                String nombre = getResultado().getString(1);
                String contrasena = getResultado().getString(2);
                String cuit = getResultado().getString(3);
                LocalDate fecha = getCambiarFecha().dateToLocalDate(getResultado().getDate(4));

                return new Usuario(nombre, contrasena, comprobarCuitNulo(cuit), fecha);
            }
        }catch (SQLException excepcion){
            System.out.println(excepcion.getMessage());
        }finally {
            cerrarConexiones();
        }
        return null;
    }
    private String comprobarCuitNulo(String cuit){
        if (cuit == null){ return ""; }
        return cuit;
    }

    public Collection<CuentaBancaria> obtenerCuentasBancariasPorCuit(String numeroCuit, ArrayList<CuentaBancaria> cuentasBancarias) throws SQLException{
        setConexionBD(new ConexionBD());
        try {
            setConexion(getConexionBD().getConexion());
            setSentencia(getConexion().prepareStatement(getQuery().obtenerCuentaBancaria()));
            getSentencia().setString(1, numeroCuit);
            setResultado(getSentencia().executeQuery());
            while (getResultado().next()){
                String cbu = getResultado().getString(1);
                float saldo = getResultado().getFloat(2);
                float limiteMinimoCuenta = getResultado().getFloat(3);
                int extraccionesMes = getResultado().getInt(4);
                Date fechaApertura = getResultado().getDate(5);
                LocalDate fecha = cambiarFecha.dateToLocalDate(fechaApertura);
                String cuitTitular = getResultado().getString(6);
                String tipoCuenta = getResultado().getString(7);
                CuentaBancaria cuentaBancaria = tipoCuentaBancaria(tipoCuenta);
                if (cuentaBancaria != null){
                    cuentaBancaria.setCbu(cbu);
                    cuentaBancaria.setSaldo(saldo);
                    cuentaBancaria.setLimiteMinimoCuenta(limiteMinimoCuenta);
                    cuentaBancaria.setCantidadExtraccionesPorMes(extraccionesMes);
                    cuentaBancaria.setFechaApertura(fecha);
                    cuentaBancaria.setNumeroCuit(cuitTitular);
                    cuentasBancarias.add(cuentaBancaria);
                }
            }
        }catch (SQLException excepcion){ System.out.println(excepcion.getMessage()); }
        return cuentasBancarias;
    }
}
