package com.cuentabancaria.basedatos.querys;
public class Query {
    public Query(){}

    //Inserciones.
    public final String insertarTitular(){ return "INSERT INTO titular VALUES(null, ?)"; }
    public final String insertarPersona(){
        return "INSERT INTO persona (dni, nombre, segundoNombre, apellido, fechaNacimiento, cuit) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
    }
    public final String insertarOrganizacion(){
        return "INSERT INTO organizacion (nombreOrganizacion, tipoOrganizacion, fechaCreacion, cuit) VALUES (?, ?, ?, ?)";
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
    public final String seleccionarPersona(){
        return "SELECT * FROM persona";
    }
    public final String obtenerNumeroCuitTitular(){ return "SELECT numero_cuit FROM titular"; }

    //Eliminar datos.

    //Modificar datos.
}
