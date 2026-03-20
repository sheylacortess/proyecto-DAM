package metodos;

import clases.Cryptos;
import clases.CuentaBancaria;
import clases.Usuario;

import java.util.InputMismatchException;
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

    // Usuario y cuenta accesibles desde toda la clase Herramientas
    private static Usuario usuarioActual;
    private static CuentaBancaria cuentaPrincipal;

    public static void crearUsuario() {
        String dni = null;
        String email = null;
        String nombre = null;
        // Validación nombre
        while (nombre == null) {
            try {
                nombre = Herramientas.leerOpcion("Introduzca su usuario: ");
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("Error. Debe ingresar un nombre.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                nombre = null;
            }
        }

        // Validación DNI
        while (dni == null) {
            try {
                dni = Herramientas.leerOpcion("Introduzca su DNI: ");
                if (!Herramientas.validaFormatoDNI(dni)) {
                    throw new IllegalArgumentException("Error. Debe ingresar un DNI válido.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                dni = null;
            }
        }

        // Validación del email
        while (email == null) {
            try {
                email = Herramientas.leerOpcion("Introduzca su email: ");
                if (!clases.EmailUtils.validarEmail(email)) {
                    throw new IllegalArgumentException("Error. Debe ingresar un email válido.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                email = null;
            }
        }

        usuarioActual = new Usuario(nombre, dni, email);
        cuentaPrincipal = new CuentaBancaria(usuarioActual);
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
     * @throws InterruptedException Mejora en el metodo iniciar, hemos implementado conntrol de errores con try catch
     */
    public static void iniciar() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        String opcion = "";
        System.out.println("\nHola, bienvenido a La Banca de DAM. Que desea hacer hoy?");
        do {
            System.out.println("0 - Salir.");
            System.out.println("1 - Ver saldo de la cuenta.");
            System.out.println("2 - Transferir dinero. (NO)");
            System.out.println("3 - Retirar dinero.");
            System.out.println("4 - Hacer un depósito.");
            System.out.println("5 - Invertir.");
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
                    try {
                        System.out.print("Introduce la cantidad a retirar: ");
                        double cantidadRetiro = sc.nextDouble();
                        if (cantidadRetiro <= 0) {
                            throw new IllegalArgumentException("La cantidad a retirar debe ser mayor a 0.");
                        }
                        if (cuentaPrincipal.retirar(cantidadRetiro)) {
                            System.out.println("Retiro realizado. Nuevo saldo: " + cuentaPrincipal.getSaldo() + "€");
                        } else {
                            System.out.println("No tienes saldo suficiente para retirar.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error en el retiro: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debes introducir un número válido.");
                        sc.nextLine(); // Limpiar buffer
                    }
                    break;

                case "4":
                    try {
                        System.out.print("Introduce la cantidad a depositar: ");
                        double cantidadDeposito = sc.nextDouble();
                        if (cantidadDeposito <= 0) {
                            throw new IllegalArgumentException("La cantidad a depositar debe ser mayor a 0.");
                        }
                        if (cuentaPrincipal.depositar(cantidadDeposito)) {
                            System.out.println("Depósito realizado. Saldo actual: " + cuentaPrincipal.getSaldo() + "€");
                        } else {
                            System.out.println("No se ha podido hacer el deposito, cantidad no válida.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error en el deposito: " + e.getMessage());
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debe introducir un número válido.");
                        sc.nextLine(); // Limpiar
                    }
                    break;

                case "5":
                    try {
                        Cryptos inversionCrypto = new Cryptos();
                        inversionCrypto.mostrarCryptos(cuentaPrincipal);
                    } catch (NullPointerException e) {
                        System.out.println("Error: no se ha podido cargar el módulo de inversión.");
                    }
                    break;

                case "6":
                    cuentaPrincipal.mostrarUltimosMovimientos();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, elija una opción del menú.");
                    break;
            }
        } while (continuar);
    }
}
