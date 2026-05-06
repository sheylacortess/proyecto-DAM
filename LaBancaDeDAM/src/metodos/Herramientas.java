package metodos;

import clases.CryptoBank;
import clases.CuentaBancaria;
import clases.Login;
import clases.Usuario;

import java.util.HashMap;
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
    // Scanner centralizado para toda la clase
    private static final Scanner sc = new Scanner(System.in);

    public static String leerOpcion(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        double valor = sc.nextDouble();
        sc.nextLine(); // consume el salto de línea que deja nextDouble
        return valor;
    }

    public static int leerInt(String mensaje) {
        System.out.print(mensaje);
        int valor = sc.nextInt();
        sc.nextLine(); // consume el salto de línea que deja nextInt
        return valor;
    }

    // Usuario y cuenta accesibles desde toda la clase Herramientas
    private static Usuario usuarioActual;
    private static CuentaBancaria cuentaPrincipal;

    // Mapa de usuarios registrados: DNI → Usuario
    private static HashMap<String, Usuario> usuarios = new HashMap<>();

    public static void iniciarSesion() {
        boolean continuar = true;
        do {
            System.out.println("\n-- LA BANCA DE DAM --");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Salir");
            switch (leerOpcion("Elija una opción: ")) {
                case "1" -> { crearUsuario(); iniciar(); continuar = false; }
                case "2" -> { if (loginUsuario()) { iniciar(); continuar = false; } }
                case "0" -> { System.out.println("Hasta luego."); continuar = false; }
                default  -> System.out.println("Opción no válida.");
            }
        } while (continuar);
    }

    // Getter para poder trabajar con la cuenta desde otras clases
    public static CuentaBancaria getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public static void mostrarUsuariosRegistrados() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
            return;
        }
        for (Usuario u : usuarios.values()) {
            System.out.println(u);
        }
    }

    public static Usuario buscarUsuarioPorDni(String dni) {
        return usuarios.get(dni.trim().toUpperCase());
    }

    public static void crearUsuario() {
        String nombre = null;
        String password = null;
        String dni = null;
        String email = null;

        // Validación nombre
        while (nombre == null) {
            try {
                nombre = Herramientas.leerOpcion("Introduzca su usuario: ");
                if (nombre.equals("D")) {
                    password = "d";
                    dni = "00000000U";
                    email = "d@gmail.com";
                }
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("Error. Debe ingresar un nombre.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                nombre = null;
            }
        }

        // Validación contraseña
        while (password == null) {
            try {
                password = Herramientas.leerOpcion("Introduzca su contraseña: ");
                if (password.isEmpty()) {
                    throw new IllegalArgumentException("Error. Debe ingresar una contraseña.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                password = null;
            }
        }

        // Validación DNI
        while (dni == null) {
            try {
                dni = Herramientas.leerOpcion("Introduzca su DNI: ");
                if (!Herramientas.validaFormatoDNI(dni)) {
                    throw new IllegalArgumentException("Error. Debe ingresar un DNI válido.");
                }
                if (usuarios.containsKey(dni.trim().toUpperCase())) {
                    throw new IllegalArgumentException("Error. Ya existe un usuario con ese DNI.");
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
        usuarios.put(dni.trim().toUpperCase(), usuarioActual);
        cuentaPrincipal = new CuentaBancaria(usuarioActual);
        Login.registrar(nombre, password, email, dni);
        System.out.println("Usuario registrado: " + usuarioActual);
    }

    public static boolean loginUsuario() {
        String usuario = leerOpcion("Usuario: ");
        String password = leerOpcion("Contraseña: ");
        if (Login.login(usuario, password)) {
            String[] datos = Login.obtenerDatosUsuario(usuario);
            if (datos != null) {
                // formato fichero: nombre;password;dni;email → datos[0..3]
                usuarioActual = new Usuario(datos[0], datos[2], datos[3]);
                usuarios.put(datos[2].trim().toUpperCase(), usuarioActual);
                cuentaPrincipal = new CuentaBancaria(usuarioActual);
                System.out.println("Sesión iniciada: " + usuarioActual);
                return true;
            }
        }
        System.out.println("Usuario o contraseña incorrectos.");
        return false;
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
        boolean continuar = true;
        System.out.println("\nHola, bienvenido a La Banca de DAM. Que desea hacer hoy?");
        do {
            System.out.println("\n0 - Salir.");
            System.out.println("1 - Ver saldo de la cuenta.");
            System.out.println("2 - Transferir dinero. (NO)");
            System.out.println("3 - Retirar dinero.");
            System.out.println("4 - Hacer un depósito.");
            System.out.println("5 - Criptomonedas.");
            System.out.println("6 - Ver historial de movimientos.");

            String opcion = leerOpcion("\nElija una opción: ");

            switch (opcion) {
                case "0":
                    System.out.println("Gracias! Saliendo...");
                    continuar = false;
                    break;
                case "1":
                    System.out.println("Saldo actual: " + cuentaPrincipal.getSaldo() + "€");
                    break;
                case "2":
                    System.out.println("Esta opción aun está pendiente de desarrollo.");
                    break;
                case "3":
                    try {
                        double cantidadRetiro = leerDouble("Introduce la cantidad a retirar: ");
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
                        sc.nextLine();
                    }
                    break;
                case "4":
                    try {
                        double cantidadDeposito = leerDouble("Introduce la cantidad a depositar: ");
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
                        sc.nextLine();
                    }
                    break;
                case "5":
                    CryptoBank.iniciar();
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
