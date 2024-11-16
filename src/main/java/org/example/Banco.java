package org.example;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<String, Cliente> clientes;
    private Map<String, CuentaBancaria> cuentas;

    // Constructor Banco
    public Banco() {
        this.clientes = new HashMap<>();
        this.cuentas = new HashMap<>();
    }

    // Registrar cliente
    public void registrarCliente(String nombre, String apellido, String dni, String email) {
        // Verificar si en la coleccion clientes existe el numero de dni
        if (clientes.containsKey(dni)) {
            throw new IllegalArgumentException("DNI registrado anteriormente");
        }
        Cliente cliente = new Cliente(nombre, apellido, dni, email);
        clientes.put(dni, cliente);
    }

    // Abrir cuenta bancaria
    public void abrirCuenta(String dni, TipoCuenta tipoCuenta) {
        Cliente cliente = clientes.get(dni);
        if (cliente == null) {
            throw new IllegalArgumentException("No está registrado");
        }

        String numeroCuenta = generarNumeroCuenta();
        CuentaBancaria cuenta = new CuentaBancaria(numeroCuenta, tipoCuenta);
        cliente.agregarCuenta(cuenta);
        cuentas.put(numeroCuenta, cuenta);
    }

    // Generar numero de cuenta unico
    private String generarNumeroCuenta() {
        return "CUENTA-" + (cuentas.size() + 1);
    }

    // Consultar saldo
    public void consultarSaldo(String dni) {
        Cliente cliente = clientes.get(dni);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        cliente.getCuentasBancarias(). forEach(cuenta ->
                System.out.println("Cuenta: " + cuenta.getNumeroCuenta() + " | Saldo: " + cuenta.getSaldo())
        );
    }

    // Retirar de cuenta
    public void realizarRetiro(String dni, String numeroCuenta, Double monto) {

        Cliente cliente = clientes.get(dni);
        // Verificar si el objeto Map cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        CuentaBancaria cuenta = cuentas.get(numeroCuenta);
        // Verificar si el objeto Map cuenta existe
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no existe");
        }

        cuenta.retirar(monto);
    }

    // Depositar en cuenta
    public void realizarDeposito(String dni, String numeroCuenta, Double monto) {
        Cliente cliente = clientes.get(dni);
        // Verificar si el objeto Map cliente existe
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no registrado");
        }

        CuentaBancaria cuenta = cuentas.get(numeroCuenta);
        // Verificar si el objeto Map cuenta existe
        if (cuenta == null) {
            throw new IllegalArgumentException("Cuenta no existe");
        }

        cuenta.depositar(monto);
    }

}
