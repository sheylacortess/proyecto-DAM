package Metodos;

import Clases.CuentaBancaria;
import Clases.Usuario;
import Clases.Cryptos;

import java.util.Scanner;


public class Herramientas {

    /**
     * Almacena el valor de la opción (tipo String) seleccionada por el usuario,
     * empleando un mensaje para pedir al usuario la introducción de datos.
     *
     * @param mensaje Mensaje que se muestra al usuario para pedir la introducción
     *                de la opción que desea ejecutar.
     * @return opción seleccionada por el usuario.
     */
    public static String leerString(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static double leerDouble(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextDouble();
    }

    public static double leerInt(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextInt();
    }

    // Usuario y cuenta accesibles desde todo Herramientas
    private static Usuario usuarioActual;
    private static CuentaBancaria cuentaPrincipal;

    public static void crearUsuario() {
        String nombre = Herramientas.leerString("Introduzca su usuario: ");
        String dni = Herramientas.leerString("Introduzca su DNI: ");
        String email = Herramientas.leerString("Introduzca su email: ");

        usuarioActual = new Usuario(nombre, dni, email);
        cuentaPrincipal = new CuentaBancaria(usuarioActual); // Aqui se crea la cuenta

        System.out.println("Usuario creado: " + usuarioActual);
    }

    /**
     * Devuelve el menú a mostrar en el programa principal.
     *
     * @throws InterruptedException
     */

    public static void menu() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        String opcion = "";
        System.out.println("\nHola, bienvenido a La Banca de DAM. Que desea hacer hoy?");
        do {
            System.out.println("\n0 - Salir.");
            Thread.sleep(50);
            System.out.println("1 - Ver saldo de la cuenta.");
            Thread.sleep(50);
            System.out.println("2 - Transferir dinero. (No disponible)");
            Thread.sleep(50);
            System.out.println("3 - Retirar dinero.");
            Thread.sleep(50);
            System.out.println("4 - Hacer un depósito.");
            Thread.sleep(50);
            System.out.println("5 - Invertir.");
            Thread.sleep(50);
            System.out.println("6 - Ver historial de movimientos.");

            opcion = leerString("\nElija una opción: ");

            switch (opcion) {
                case "0":
                    System.out.println("Gracias! Saliendo...");
                    continuar = false;
                    break;
                case "1":
                    System.out.println("Saldo actual: " + cuentaPrincipal.getSaldo() + "€");
                    break;
                case "2":
                    System.out.println("Esta acción aún está en desarrollo. Gracias.");
                    break;
                case "3":
                    System.out.print("Introduce la cantidad a retirar: ");
                    double cantidadRetiro;

                    // Validación con hasNextDouble()
                    while (!sc.hasNextDouble()) {
                        String basura = sc.next();
                        System.out.println("No es válido. Intentelo de nuevo.");
                        System.out.print("Introduce la cantidad a retirar: ");
                    }
                    cantidadRetiro = sc.nextDouble();
                    if (cuentaPrincipal.retirar(cantidadRetiro)) {
                        System.out.println("Retiro realizado. Nuevo saldo: " + cuentaPrincipal.getSaldo() + "€");
                    } else {
                        System.out.println("No tienes saldo suficiente para retirar.");
                    }
                    break;
                case "4":
                    System.out.print("Introduce la cantidad a depositar: ");
                    double cantidadDeposito;

                    // Validacion de double
                    while (!sc.hasNextDouble()) {
                        String basura = sc.next();
                        System.out.println("No es válido. Inténtelo de nuevo.");
                        System.out.print("Introduce la cantidad a depositar: ");
                    }
                    cantidadDeposito = sc.nextDouble();
                    if (cuentaPrincipal.depositar(cantidadDeposito)) {
                        System.out.println("Depósito realizado. Saldo actual: " + cuentaPrincipal.getSaldo() + "€");
                    } else {
                        System.out.println("No se ha podido hacer el deposito, cantidad no válida.");
                    }
                    break;
                case "5":
                    Cryptos.mostrarCryptos();
                    break;
                case "6":
                    CuentaBancaria.mostrarUltimosMovimientos();
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, pruebe de nuevo.");
                    break;

            }
        } while (continuar);
    }
}
