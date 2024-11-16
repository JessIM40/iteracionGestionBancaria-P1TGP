package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Cliente {
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private List<CuentaBancaria> cuentasBancarias;

    // Constructor Cliente
    public Cliente(String nombre, String apellido, String dni, String email) {
        // Valida campos obligatorios
        if (isNullOrEmpty(nombre) || isNullOrEmpty(apellido) || isNullOrEmpty(dni) || isNullOrEmpty(email)) {
            throw new IllegalArgumentException("Todos los campos son obligatorios");
        }

        // Valida formato DNI
        if (!isalidDNI(dni)) {
            throw new IllegalArgumentException("DNI debe contener exactamente 8 digitos numéricos");
        }

        // Valida el formato del email
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email no válido");
        }

        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.email = email;
        this.cuentasBancarias = new ArrayList<>();
    }

    // Metodo para validar cadenas vacias o nulas
    private boolean isNullOrEmpty(String str) {
        return str == null || str.isBlank();
    }

    // Metodo para validar formato DNI
    private boolean isalidDNI(String dni) {
        String dniRegex = "^[0-9]{8}$";
        return Pattern.matches(dniRegex, dni.trim());
    }

    // Metodo para validar formato de email
    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(regex, email);
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public List<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }


    // Agregar cuenta bancaria
    public void agregarCuenta(CuentaBancaria cuenta) {
        cuentasBancarias.add(cuenta);
    }
}
