package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private List<BankAccount> bankAccounts;

    // Constructor Cliente
    public Customer(String firstName, String lastName, String dni, String email) {
        // Valida campos obligatorios
        if (isNullOrEmpty(firstName) || isNullOrEmpty(lastName) || isNullOrEmpty(dni) || isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }
        // Valida formato DNI
        if (!isValidDNI(dni)) {
            throw new IllegalArgumentException("DNI debe contener exactamente 8 digitos numéricos");
        }
        // Valida el formato del email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email no válido");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.bankAccounts = new ArrayList<>();
    }

    // Metodo para validar cadenas vacias o nulas
    private boolean isNullOrEmpty(String str) {
        return str == null || str.isBlank();
    }

    // Metodo para validar formato DNI
    private boolean isValidDNI(String dni) {
        String dniRegex = "^[0-9]{8}$";
        return Pattern.matches(dniRegex, dni.trim());
    }

    // Metodo para validar formato de email
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);
    }

    // Getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }


    // Agregar cuenta bancaria
    public void addAccount(BankAccount account) {
        bankAccounts.add(account);
    }
}
