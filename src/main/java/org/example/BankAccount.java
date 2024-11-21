package org.example;

public class BankAccount {
    private String accountNumber;
    private double balance;
    private TypeAccount typeAccount;

    // Constructor Cuenta Bancaria
    public BankAccount(String accountNumber, TypeAccount typeAccount) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("El número de cuenta no puede ser nulo o vació");
        }
        if (typeAccount == null) {
            throw new IllegalArgumentException("El tipo de cuenta no puede ser nulo");
        }
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.typeAccount = typeAccount;
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public TypeAccount getTypeAccount() {
        return typeAccount;
    }

    // Metodo para realizar un deposito
    public void deposit(Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("El monto a depositar debe ser mayor a 0");
        }
        balance += amount;
    }

    // Metodo para realizar un retiro
    public void withdraw(Double amount) {
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("El monto a retirar debe ser mayor a 0");
        }

        if (typeAccount == TypeAccount.AHORROS && balance - amount < 0) {
            throw new IllegalArgumentException("El monto excede al saldo disponible");
        }

        if (typeAccount == TypeAccount.CORRIENTE && balance - amount < -500.00) {
            throw new IllegalArgumentException("Sobregiro excedido");
        }

        balance -= amount;
    }
}
