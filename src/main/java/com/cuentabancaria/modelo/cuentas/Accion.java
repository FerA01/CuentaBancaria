package com.cuentabancaria.modelo.cuentas;

public interface Accion {
    boolean depositar(float monto);
    boolean retirar(float monto);
}
