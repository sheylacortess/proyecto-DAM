package Metodos;

import Clases.Cryptos;
import Clases.CuentaBancaria;
import Clases.Usuario;

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
    public static String leerOpcion(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static double leerDouble(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextDouble();
    }

    // Usuario y cuenta accesiles desde todo Herramientas
    private static Usuario usuarioActual;
    private static CuentaBancaria cuentaPrincipal;

    public static void crearUsuario() {
        String dni;
        String nombre = Herramientas.leerOpcion("Introduzca su usuario: ");
        do {
            dni = Herramientas.leerOpcion("Introduzca su DNI: ");
            if (!Herramientas.validaFormatoDNI(dni)) {
                System.out.print("Introduzca un DNI válido, porfavor.");
                System.out.println();
            }
        } while (!Herramientas.validaFormatoDNI(dni));
        String email = Herramientas.leerOpcion("Introduzca su email: ");

        usuarioActual = new Usuario(nombre, dni, email);
        cuentaPrincipal = new CuentaBancaria(usuarioActual); // Aqui se crea la cuenta

        System.out.println("Usuario creado: " + usuarioActual);
    }

    // Validador DNI
    public static boolean validaFormatoDNI(String dni) {
        // comprobar longitud
        dni = dni.trim();
        if (dni.length() != 9) {
            return false;
        }

        // comprobar que los 8 primeros sean dígitos
        for (int i = 0; i < 8; i++) {
            char c = dni.charAt(i);
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // comprobar que el último sea una letra
        char ultimo = dni.charAt(8);
        if (!Character.isLetter(ultimo)) {
            return false;
        }

        return true;
    }

    /**
     * Devuelve el menú a mostrar en el programa principal.
     *
     * @throws InterruptedException
     */

    public static void iniciar() throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        String opcion = "";
        System.out.println("\nHola, bienvenido a La Banca de DAM. Que desea hacer hoy?");
        do {
            Thread.sleep(500);
            System.out.println("0 - Salir.");
            Thread.sleep(500);
            System.out.println("1 - Ver saldo de la cuenta.");
            Thread.sleep(500);
            System.out.println("2 - Transferir dinero. (NO)");
            Thread.sleep(500);
            System.out.println("3 - Retirar dinero.");
            Thread.sleep(500);
            System.out.println("4 - Hacer un depósito.");
            Thread.sleep(500);
            System.out.println("5 - Invertir.");
            Thread.sleep(500);
            System.out.println("6 - Ver historial de movimientos.");

            opcion = leerOpcion("\nElija una opción: ");

            switch (opcion) {
                case "0":
                    System.out.println("Gracias! Saliendo...");
                    continuar = false;
                    break;
                case "1":
                    System.out.println("Saldo actual: " + cuentaPrincipal.getSaldo() + "€");
                    break;
                case "2":
                    System.out.println("Esta opción aun está pendiente de desarrollo. ");
                    break;
                case "3":
                    System.out.print("Introduce la cantidad a retirar: ");
                    double cantidadRetiro = sc.nextDouble();
                    if (cuentaPrincipal.retirar(cantidadRetiro)) {
                        System.out.println("Retiro realizado. Nuevo saldo: " + cuentaPrincipal.getSaldo() + "€");
                    } else {
                        System.out.println("No tienes saldo suficiente para retirar.");
                    }
                    break;
                case "4":
                    System.out.print("Introduce la cantidad a depositar: ");
                    double cantidadDeposito = sc.nextDouble();
                    if (cuentaPrincipal.depositar(cantidadDeposito)) {
                        System.out.println("Depósito realizado. Saldo actual: " + cuentaPrincipal.getSaldo() + "€");
                    } else {
                        System.out.println("No se ha podido hacer el deposito, cantidad no válida.");
                    }
                    break;
                case "5":
                    Cryptos InversionCrypto = null;
                    InversionCrypto.mostrarCryptos(cuentaPrincipal);
                    break;
                case "6":
                    cuentaPrincipal.mostrarUltimosMovimientos();
                    break;
                default:
                    System.out.println("Adiós.");
                    continuar = false;
                    break;
            }
        } while (continuar);
    }
}
