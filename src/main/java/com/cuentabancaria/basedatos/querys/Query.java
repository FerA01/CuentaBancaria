package com.cuentabancaria.basedatos.querys;
public class Query {
    public Query(){}

    //Inserciones.
    public final String insertarTitular(){ return "INSERT INTO titular VALUES(null, ?, ?)"; }
    public final String insertarPersona(){
        return "INSERT INTO persona (dni, nombre, segundoNombre, apellido, fechaNacimiento, cuit, tipoTitular) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
    }
    public final String insertarOrganizacion(){
        return "INSERT INTO organizacion (nombreOrganizacion, tipoOrganizacion, fechaCreacion, cuit, tipoTitular) VALUES (?, ?, ?, ?, ?)";
    }
    public final String insertartransaccion(){
        return "INSERT INTO transacciones (idTransaccion, cbuAsociado, monto, fechaTransaccion, tipoTransaccion) "
                + "VALUES(null, ?, ?, ?, ?)";
    }
    public final String insertarCuentaBancaria(){
        return "INSERT INTO cuentabancaria (cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesMes, fechaApertura, titularCuenta, tipoCuenta) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
    }

    //Obtener datos.
    public final String obtenerCantidadColumnasTabla(){ return "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = ?"; }
    public final String obtenerUltimoIDTabla(){ return "SELECT MAX(?) FROM ? "; }
    public final String obtenerUltimoID(){ return "SELECT MAX(id) FROM titular"; }
    public final String obtenerCuitTitular(){ return "SELECT numero_cuit FROM titular WHERE numero_cuit = ?"; }
    public final String seleccionarPersona(){
        return "SELECT * FROM persona";
    }
    public final String seleccionarPersonaPorCuit(){ return "SELECT * FROM persona WHERE cuit = ?"; }
    public final String obtenerNumeroCuitTitular(){ return "SELECT numero_cuit FROM titular"; }
    public final String obtenerApellidosPersonas(){ return "SELECT apellido FROM persona"; }
    public final String seleccionarOrganizacion(){ return "SELECT * FROM organizacion"; }
    public final String seleccionarOrganizacionPorCuit(){ return "SELECT * FROM organizacion WHERE cuit = ?"; }
    public final String abc(){ return "SELECT * FROM ? WHERE cuit = ? "; }
    public final String obtenerCuentaBancaria(){ return  "SELECT cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesMes, fechaApertura, titularCuenta, tipoCuenta FROM cuentabancaria WHERE titularCuenta = ?"; }
    public final String obtenerTransaccionesNumeroCbu(){ return "SELECT * FROM transacciones WHERE cbuAsociado = ?"; }
    //Eliminar datos.
    public final String eliminarPersona(){ return "DELETE FROM persona WHERE cuit = ?"; }
    public final String eliminarOrganizacion(){ return "DELETE FROM organizacion WHERE cuit = ?"; }
    public final String eliminarTitular(){ return "DELETE FROM titular WHERE numero_cuit = ?"; }
    public final String eliminarTransaccion(){ return "DELETE FROM transacciones WHERE cbuAsociado = ?"; }
    public final String eliminarCuentaBancaria(){ return "DELETE FROM cuentabancaria WHERE cbu = ?"; }

    //Modificar datos.
    public final String actualizarPersona(){ return "UPDATE persona SET dni = ?, nombre = ?, segundoNombre = ?, apellido = ?, fechaNacimiento = ?"
                                             + " WHERE cuit = ?"; }
    public final String actualizarOrganizacion(){ return "UPDATE organizacion SET nombreOrganizacion = ?, tipoOrganizacion = ?, fechaCreacion = ?"
                                                 + " WHERE cuit = ?"; }
    public final String actualizarCuentaBancaria(){ return "UPDATE cuentabancaria SET saldo = ?, limiteMinimoCuenta = ?, cantidadExtraccionesMes = ?, fechaApertura = ?"
                                                  + " WHERE cbu = ?"; }
    public final String actualizarDatosCuentaBancaria(){ return "UPDATE cuentabancaria SET saldo = ?, cantidadExtraccionesMes = ? WHERE cbu = ?"; }
}
