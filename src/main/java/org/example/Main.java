package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crear instacia del banco
        Bank bank = new Bank();

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

            int option = consoleInput.nextInt();
            consoleInput.nextLine();


            switch (option) {
                case 1: {
                    // Registrar cliente
                    System.out.println("\n** Registrar Cliente **");
                    System.out.println("Ingrese su nombre: ");
                    String firstName = consoleInput.nextLine().trim(); // trim() para eliminar espacios adicionales
                    System.out.println("Ingrese su apellido: ");
                    String lastName = consoleInput.nextLine().trim();
                    System.out.println("Ingrese su DNI: ");
                    String dni = consoleInput.nextLine().trim();
                    System.out.println("Ingrese su email: ");
                    String email = consoleInput.nextLine().trim();

                    try {
                        bank.registerCustomer(firstName, lastName, dni, email);
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
                    String dniAccount = consoleInput.nextLine().trim();

                    if(!bank.registeredCustomer(dniAccount)) {
                        System.out.println("Error: El cliente con DNI " + dniAccount + " no está registrado");
                        break;
                    }
                    // Si dni es valido muestra opciones de cuenta
                    System.out.println("Elija tipo de cuenta:");
                    System.out.println("1 - AHORRO");
                    System.out.println("2 - CORRIENTE");
                    int typeAccountOption = consoleInput.nextInt();
                    consoleInput.nextLine().trim();

                    TypeAccount typeAccount = null;
                    if (typeAccountOption == 1) {
                        typeAccount = TypeAccount.AHORROS;
                    } else if (typeAccountOption == 2) {
                        typeAccount = TypeAccount.CORRIENTE;
                    } else  {
                        System.out.println("Opción de tipo de cuenta no válida");
                        break;
                    }

                    try {
                        bank.openAccount(dniAccount, typeAccount);
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
                    String dniBalance = consoleInput.nextLine().trim();

                    try {
                        bank.checkBalance(dniBalance);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    // Realizar retiro
                    System.out.println("\n** Realizar Retiro **");
                    System.out.println("Ingrese su DNI: ");
                    String dniWithdrawal = consoleInput.nextLine().trim();
                    System.out.println("Ingrese número de cuenta: ");
                    String numberAccount = consoleInput.nextLine().trim();
                    System.out.println("Ingrese monto a retirar: ");
                    double withdrawalAmount = consoleInput.nextDouble();
                    consoleInput.nextLine();

                    try {
                        bank.makeWithdrawal(dniWithdrawal, numberAccount, withdrawalAmount);
                        System.out.println("Retiro realizado exitosamente");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    // Realizar depósito
                    System.out.println("\n** Realizar Depósito **");
                    System.out.println("Ingrese su DNI: ");
                    String dniDeposit = consoleInput.nextLine().trim();
                    System.out.println("Ingrese número de cuenta: ");
                    String numberAccountDeposit = consoleInput.nextLine().trim();
                    System.out.println("Ingrese monto a depositar: ");
                    double depositAmount = consoleInput.nextDouble();
                    consoleInput.nextLine();

                    try {
                        bank.makeDeposit(dniDeposit, numberAccountDeposit, depositAmount);
                        System.out.println("Deposito realizado exitosamente");
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