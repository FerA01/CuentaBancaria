package com.cuentabancaria.modelo.cuentas;

import java.sql.SQLException;

public interface Accion {
    boolean depositar(float monto) throws SQLException;
    boolean retirar(float monto) throws SQLException;
}
