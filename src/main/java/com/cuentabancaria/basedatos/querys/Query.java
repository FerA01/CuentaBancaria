package com.cuentabancaria.basedatos.querys;
public class Query {
    public Query(){}

    public final String insertarPersona(){
        return "INSERT INTO persona (dni, nombre, segundoNombre, apellido, fechaNacimiento, numeroCuit) "
                + "VALUES(?, ?, ?, ?, ?, ?)";
    }
    public final String seleccionarPersona(){ return "SELECT * FROM persona"; }
    public final String insertarTitular(){
        return "INSERT INTO titular (id, numero_cuit) VALUES(?, ?)";
    }
    public final String insertarOrganizacion(){
        return "INSERT INTO organizacion (nombreOrganizacion, tipoOrganizacion, fechaCreacion, cuit) VALUES (?, ?, ?, ?)";
    }
    public final String insertartransaccion(){
        return "INSERT INTO transacciones (idTransaccion, cbuAsociado, monto, fechaTransaccion, tipoTransaccion) "
                + "VALUES(?, ?, ?, ?, ?)";
    }
    public final String insertarCuentaBancaria(){
        return "INSERT INTO cuentabancaria (cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesMes, fechaApertura) "
                + "VALUES(?, ?, ?, ?, ?)";
    }
}
