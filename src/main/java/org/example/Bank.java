package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
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
        /*if (customers.containsKey(dni)) {
            throw new IllegalArgumentException("DNI registrado anteriormente");
        }
        Customer customer = new Customer(firstName, lastName, dni, email);
        customers.put(dni, customer);*/

        // PARA SQL
        String sql = "INSERT INTO customers (dni, first_name, last_name, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.setString(2, firstName);
            stmt.setString(3, lastName);
            stmt.setString(4, email);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Error al registrar el cliente: " + e.getMessage(), e);
        }

    }

    // Verifica si usuario esta registrado
    public boolean registeredCustomer(String dni) {
        return customers.containsKey(dni);
    }

    // Abrir cuenta bancaria
    public void openAccount(String dni, TypeAccount typeAccount) {
        /*Customer customer = customers.get(dni);
        if (customer == null) {
            throw new IllegalArgumentException("No está registrado");
        }

        // Numero de cuenta segun tipo de cuenta
        String accountNumber = generateAccountNumber(typeAccount);
        BankAccount account = new BankAccount(accountNumber, typeAccount);
        customer.addAccount(account);
        accounts.put(accountNumber, account);*/

        // PARA SQL
        String accountNumber = generateAccountNumber(typeAccount);
        String sql = "INSERT INTO bank_accounts (account_number, balance, type_account, customer_dni) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            stmt.setDouble(2, 0.0);
            stmt.setString(3, typeAccount.name());
            stmt.setString(4, dni);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al abrir la cuenta bancaaria: " + e.getMessage(), e);
        }
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
        /*Customer customer = customers.get(dni);
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        customer.getBankAccounts(). forEach(cuenta ->
                System.out.println("Cuenta: " + cuenta.getAccountNumber() + " | Saldo: " + cuenta.getBalance())
        );*/

        // PARA SQL
        String sql = "SELECT account_number, balance FROM bank_accounts WHERE customer_dni = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String accountNumber = rs.getString("account_number");
                double balance = rs.getDouble("balance");
                System.out.println("Cuenta: " + accountNumber + " | Saldo: " + balance);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar saldo: " + e.getMessage(), e);
        }
    }

    // Retirar de cuenta
    public void makeWithdrawal(String dni, String accountNumber, Double amount) {
       /* Customer customer = customers.get(dni);
        // Verificar si el objeto Map cliente existe
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        BankAccount account = accounts.get(accountNumber);
        // Verificar si el objeto Map cuenta existe
        if (account == null) {
            throw new IllegalArgumentException("Cuenta no existe");
        }

        account.withdraw(amount);*/

        // PARA SQL
        String sql = "UPDATE bank_accounts SET balance = balance - ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, amount);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException ("Error al realizar el retiro: " + e.getMessage(), e);
        }
    }

    // Depositar en cuenta
    public void makeDeposit(String dni, String accountNumber, Double amount) {
        /*Customer customer = customers.get(dni);
        // Verificar si el objeto Map cliente existe
        if (customer == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        BankAccount account = accounts.get(accountNumber);
        // Verificar si el objeto Map cuenta existe
        if (account == null) {
            throw new IllegalArgumentException("Cuenta no existe");
        }

        account.deposit(amount);*/

        // PARA SQL
        String sql = "UPDATE bank_accounts SET balance = balance + ? WHERE account_number = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setDouble(1, amount);
            stmt.setString(2, accountNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException ("Error al realizar el deposito: " + e.getMessage(), e);
        }
    }

}
