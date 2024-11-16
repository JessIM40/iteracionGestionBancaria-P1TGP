package org.example;

public class CuentaBancaria {
    private String numeroCuenta;
    private double saldo;
    private TipoCuenta tipoCuenta;

    // Constructor Cuenta Bancaria
    public CuentaBancaria(String numeroCuenta, TipoCuenta tipoCuenta) {
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0.0;
        this.tipoCuenta = tipoCuenta;
    }

    // Getters
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void depositar(Double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a 0");
        }
        saldo += monto;
    }

    public void retirar(Double monto) {
        if (monto <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a 0");
        }

        if (tipoCuenta == TipoCuenta.AHORROS && saldo - monto < 0) {
            throw new IllegalArgumentException("El monto excede al saldo disponible");
        }

        if (tipoCuenta == TipoCuenta.CORRIENTE && saldo - monto < -500.00) {
            throw new IllegalArgumentException("Sobregiro excedido");
        }

        saldo -= monto;
    }
}
