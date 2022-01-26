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
        return "INSERT INTO cuentabancaria (cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesMes, fechaApertura) "
                + "VALUES(?, ?, ?, ?, ?)";
    }

    //Obtener datos.
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
    //Eliminar datos.
    public final String eliminarPersona(){ return "DELETE FROM persona WHERE cuit = ?"; }
    public final String eliminarOrganizacion(){ return "DELETE FROM organizacion WHERE cuit = ?"; }
    public final String eliminarTitular(){ return "DELETE FROM titular WHERE numero_cuit = ?"; }
    public final String eliminarTransaccion(){ return "DELETE FROM transacciones WHERE cbuAsociado = ?"; }

    //Modificar datos.
    public final String actualizarPersona(){ return "UPDATE persona SET dni = ?, nombre = ?, segundoNombre = ?, apellido = ?, fechaNacimiento = ?"
                                             + " WHERE cuit = ?"; }
    public final String actualizarOrganizacion(){ return "UPDATE organizacion SET nombreOrganizacion = ?, tipoOrganizacion = ?, fechaCreacion = ?"
                                                 + " WHERE cuit = ?"; }
    public final String actualizarCuentaBancaria(){ return "UPDATE cuentaBancaria SET saldo = ?, limiteMinimoCuenta = ?, CantidadExtraccionesMes = ?, fechaApertura = ?"
                                                    + " WHERE cbu = ?"; }

}
