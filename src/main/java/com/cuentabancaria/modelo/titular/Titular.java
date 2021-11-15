package com.cuentabancaria.modelo.titular;
public abstract class Titular {
    private String numeroCuit;

    public Titular(){}
    public Titular(String numeroCuit){ setNumeroCuit(numeroCuit); }

    public String getNumeroCuit() { return numeroCuit; }
    public void setNumeroCuit(String numeroCuit) { this.numeroCuit = numeroCuit; }

    public abstract String tipoTitular();
    public abstract String toString();
}
