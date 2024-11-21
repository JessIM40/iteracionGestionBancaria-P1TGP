package org.example;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private Map<String, Customer> customers;
    private Map<String, BankAccount> accounts;

    // Contadores
    private int savinggsAccountCounter = 0;
    private int checkingAccountCounter = 0;

    // Constructor Banco
    public Bank() {
        this.customers = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    // Registrar cliente
    public void registerCustomer(String firstName, String lastName, String dni, String email) {
        // Verificar si en la coleccion clientes existe el numero de dni
        if (customers.containsKey(dni)) {
            throw new IllegalArgumentException("DNI registrado anteriormente");
        }
        Customer customer = new Customer(firstName, lastName, dni, email);
        customers.put(dni, customer);
    }

    // Verifica si usuario esta registrado
    public boolean registeredCustomer(String dni) {
        return customers.containsKey(dni);
    }

    // Abrir cuenta bancaria
    public void openAccount(String dni, TypeAccount typeAccount) {
        Customer customer = customers.get(dni);
        if (customer == null) {
            throw new IllegalArgumentException("No está registrado");
        }

        // Numero de cuenta segun tipo de cuenta
        String accountNumber = generateAccountNumber(typeAccount);
        BankAccount account = new BankAccount(accountNumber, typeAccount);
        customer.addAccount(account);
        accounts.put(accountNumber, account);
    }

    // Generar numero de cuenta unico
    private String generateAccountNumber(TypeAccount typeAccount) {
        String accountNumber;
        if (typeAccount == TypeAccount.AHORROS) {
            savinggsAccountCounter++;
            accountNumber = "AH-" + String.format("%06d", savinggsAccountCounter);
        } else if (typeAccount == TypeAccount.CORRIENTE) {
            checkingAccountCounter++;
            accountNumber = "CC-" + String.format("%06d", checkingAccountCounter);
        } else {
            throw new IllegalArgumentException("Tipo de cuenta no valido");
        }
        if (accounts.containsKey((accountNumber))) {
            throw new IllegalArgumentException("Error al generar un numero de cuenta unico");
        }
        return accountNumber;
    }

    // Consultar saldo
    public void checkBalance(String dni) {
        Customer customer = customers.get(dni);
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        customer.getBankAccounts(). forEach(cuenta ->
                System.out.println("Cuenta: " + cuenta.getAccountNumber() + " | Saldo: " + cuenta.getBalance())
        );
    }

    // Retirar de cuenta
    public void makeWithdrawal(String dni, String accountNumber, Double amount) {

        Customer customer = customers.get(dni);
        // Verificar si el objeto Map cliente existe
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        BankAccount account = accounts.get(accountNumber);
        // Verificar si el objeto Map cuenta existe
        if (account == null) {
            throw new IllegalArgumentException("Cuenta no existe");
        }

        account.withdraw(amount);
    }

    // Depositar en cuenta
    public void makeDeposit(String dni, String accountNumber, Double amount) {
        Customer customer = customers.get(dni);
        // Verificar si el objeto Map cliente existe
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        BankAccount account = accounts.get(accountNumber);
        // Verificar si el objeto Map cuenta existe
        if (account == null) {
            throw new IllegalArgumentException("Cuenta no existe");
        }

        account.deposit(amount);
    }

}
