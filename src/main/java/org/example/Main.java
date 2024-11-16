package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear instacia del banco
        Banco banco = new Banco();

        // Recoger datos de consola
        Scanner consoleInput = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n** Bienvenida al sistema del Banco **");
            System.out.println("Elija la operación que desea realizar:");
            System.out.println("1 - Registrar cliente");
            System.out.println("2 - Abrir cuenta bancaria");
            System.out.println("3 - Consultar saldo");
            System.out.println("4 - Realizar retiro");
            System.out.println("5 - Realizar depósito");
            System.out.println("6 - Salir");
            System.out.println("Opcion: ");

            int opcion = consoleInput.nextInt();
            consoleInput.nextLine();


            switch (opcion) {
                case 1: {
                    // Registrar cliente
                    System.out.println("\n** Registrar Cliente **");
                    System.out.println("Ingrese su nombre: ");
                    // trim() para eliminar espacios adicionales
                    String nombre = consoleInput.nextLine().trim();
                    System.out.println("Ingrese su apellido: ");
                    String apellido = consoleInput.nextLine().trim();
                    System.out.println("Ingrese su DNI: ");
                    String dni = consoleInput.nextLine().trim();
                    System.out.println("Ingrese su email: ");
                    String email = consoleInput.nextLine().trim();

                    try {
                        banco.registrarCliente(nombre, apellido, dni, email);
                        System.out.println("Cliente registrado exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case 2: {
                    // Abrir cuenta bancaria
                    System.out.println("\n** Abrir Cuenta Bancaria **");
                    System.out.println("Ingrese su DNI: ");
                    String dniCuenta = consoleInput.nextLine().trim();

                    if(!banco.clienteRegistrado(dniCuenta)) {
                        System.out.println("Error: El cliente con DNI " + dniCuenta + " no está registrado");
                        break;
                    }
                    // Si dni es valido muestra opciones de cuenta
                    System.out.println("Elija tipo de cuenta:");
                    System.out.println("1 - AHORRO");
                    System.out.println("2 - CORRIENTE");
                    int tipoCuentaOpcion = consoleInput.nextInt();
                    consoleInput.nextLine().trim();

                    TipoCuenta tipoCuenta = null;
                    if (tipoCuentaOpcion == 1) {
                        tipoCuenta = TipoCuenta.AHORROS;
                    } else if (tipoCuentaOpcion == 2) {
                        tipoCuenta = TipoCuenta.CORRIENTE;
                    } else  {
                        System.out.println("Opción de tipo de cuenta no válida");
                        break;
                    }

                    try {
                        banco.abrirCuenta(dniCuenta, tipoCuenta);
                        System.out.println("Cuenta bancaria abierta exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case 3:
                    // Consultar saldo
                    System.out.println("\n** Consultar Saldo **");
                    System.out.println("Ingrese su DNI: ");
                    String dniSaldo = consoleInput.nextLine().trim();

                    try {
                        banco.consultarSaldo((dniSaldo));
                        System.out.println("Cuenta bancaria abierta exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Realizar retiro
                    System.out.println("\n** Realizar Retiro **");
                    System.out.println("Ingrese su DNI: ");
                    String dniRetiro = consoleInput.nextLine().trim();
                    System.out.println("Ingrese número de cuenta: ");
                    String numeroCuenta = consoleInput.nextLine().trim();
                    System.out.println("Ingrese monto a retirar: ");
                    double montoRetiro = consoleInput.nextDouble();
                    consoleInput.nextLine().trim();

                    try {
                        banco.realizarRetiro(dniRetiro, numeroCuenta, montoRetiro);
                        System.out.println("Retiro realizado exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Realizar depósito
                    System.out.println("\n** Realizar Depósito **");
                    System.out.println("Ingrese su DNI: ");
                    String dniDeposito = consoleInput.nextLine();
                    System.out.println("Ingrese número de cuenta: ");
                    String numeroCuentaDeposito = consoleInput.nextLine();
                    System.out.println("Ingrese monto a depositar: ");
                    double montoDeposito = consoleInput.nextDouble();
                    consoleInput.nextLine();

                    try {
                        banco.realizarDeposito(dniDeposito, numeroCuentaDeposito, montoDeposito);
                        System.out.println("Retiro realizado exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    // Salir
                    exit = true;
                    System.out.println("Gracias por utilizar el sistema del banco");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, intentelo otra vez");
            }
        }
        consoleInput.close();;
    }
}