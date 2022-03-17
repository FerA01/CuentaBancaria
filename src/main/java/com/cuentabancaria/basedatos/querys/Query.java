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
                + "VALUES(null, ?, ?, now(), ?)";
    }
    public final String insertarCuentaBancaria(){
        return "INSERT INTO cuentabancaria (cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesMes, fechaApertura, titularCuenta, tipoCuenta) "
                + "VALUES(?, ?, ?, ?, now(), ?, ?)";
    }
    public final String insertarUsuario(){
        return "INSERT INTO usuario (idUsuario, nombreUsuario, contrasena, cuitTitular, fechaCreacion) VALUES (null, ?, ?, ?, now())";
    }

    //Obtener datos.
    public final String obtenerCantidadColumnasTabla(){ return "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = ?"; }
    public final String obtenerUltimoIDTabla(){ return "SELECT MAX(?) FROM ? "; }
    public final String obtenerUltimoID(){ return "SELECT MAX(id) FROM titular"; }
    public final String obtenerCuitTitular(){ return "SELECT numero_cuit FROM titular WHERE numero_cuit = ?"; }
    public final String existeCuitTitular(){ return "SELECT tipoTitular FROM titular WHERE EXISTS ()"; }
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
    public final String obtenerCuentaBancariaPorNombreUsuario(){
        return "SELECT cbu, tipoCuenta, fechaApertura, saldo FROM cuentaBancaria "
                + "INNER JOIN usuario ON cuentaBancaria.titularCuenta = usuario.cuitTitular "
                + "WHERE usuario.nombreUsuario = ?";
    }
    public final String obtenerCuentaBancariaPorCbuYNombreUsuario(){
        return "SELECT cbu, saldo, limiteMinimoCuenta, cantidadExtraccionesMes, fechaApertura, titularCuenta, tipoCuenta FROM cuentabancaria " +
                "INNER JOIN usuario ON cuentabancaria.titularCuenta = usuario.cuitTitular " +
                "WHERE cuentabancaria.cbu = ? " +
                "AND usuario.nombreUsuario = ?";
    }
    public final String obtenerTransaccionesNumeroCbu(){ return "SELECT * FROM transacciones WHERE cbuAsociado = ?"; }
    public final String obtenerNombreUsuario(){ return "SELECT nombreUsuario FROM usuario WHERE nombreUsuario = ?"; }
    public final String obtenerContrasena(){ return "SELECT contrasena FROM usuario WHERE nombreUsuario = ?"; }
    public final String obtenerUsuario(){ return "SELECT nombreUsuario, contrasena, cuitTitular, fechaCreacion FROM usuario WHERE nombreUsuario = ?"; }
    public final String obtenerDniPersona(){ return "SELECT dni FROM persona WHERE cuit = ?"; }
    public final String obtenerDni(){ return "SELECT dni FROM persona WHERE dni = ?"; }
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

    public final String actualizarNumeroCuitUsuario(){ return "UPDATE usuario SET cuitTitular = ? WHERE nombreUsuario = ?"; }
}
