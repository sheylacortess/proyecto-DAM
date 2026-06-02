package metodos;

import bases.ConexionDB;
import bases.UsuarioDAO;
import clases.CryptoBank;
import clases.CuentaBancaria;
import clases.Usuario;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public static int leerInt(String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.print(mensaje);
        return sc.nextInt();
    }

    // Declaración de un Scanner para usarlo de forma centralizada
    private static Scanner sc = new Scanner(System.in);

    // Usuario y cuenta accesibles desde toda la clase Herramientas
    private static Usuario usuarioActual;
    private static CuentaBancaria cuentaPrincipal;

    // Getter para poder trabajar con la cuenta desde otras clases
    public static CuentaBancaria getCuentaPrincipal() {
        return cuentaPrincipal;
    }

    public static boolean crearUsuario() {
        String dni = null;
        String email = null;
        String nombre = null;
        String password = null;
        // Validación nombre
        while (nombre == null) {
            try {
                nombre = Herramientas.leerOpcion("Introduzca su usuario: ");
                if (nombre.equals("D")) {
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

        // Pedir password
        while (password == null) {
            try {
                password = Herramientas.leerOpcion("Introduce una contraseña: ");
                if (password.isEmpty()) {
                    throw new IllegalArgumentException("Error: introduzca una contraseña.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                password = null;
            }
        }

        usuarioActual = new Usuario(nombre, dni, email);
        cuentaPrincipal = new CuentaBancaria(usuarioActual);

        boolean guardado = UsuarioDAO.registrar(nombre, dni, email, password);
        if (guardado) {
            System.out.println("Usuario creado y guardado en la base de datos: " + usuarioActual);
            return true;
        } else {
            System.out.println("Error al guardar el usuario.");
        }
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
     * Metodo para iniciar sesion con metodo login() de UsuarioDAO
     */
    public static boolean iniciarSesion() {
        String dni = null;
        String password = null;

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

        // Pedir password
        password = Herramientas.leerOpcion("Introduzca su contraseña: ");

        // Llamar al login
        boolean exito = UsuarioDAO.login(dni, password);
        if (exito) {
            System.out.println("Login correcto. Bienvenido!");
            usuarioActual = obtenerUsuarioPorDni(dni);
            if (usuarioActual != null) {
                cuentaPrincipal = new CuentaBancaria(usuarioActual);
                System.out.println("Bienvenido, " + usuarioActual.getNombre());
                return true;
            }
        } else {
            System.out.println("DNI o contraseña incorrectos.");
        }
        return false;
    }

    /**
     * Metodo para buscar en la base de datos por DNI
     */
    public static Usuario obtenerUsuarioPorDni(String dni) {
        String sql = "SELECT * FROM usuarios WHERE dni = ?";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                return new Usuario(nombre, dni, email);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario: " + e.getMessage());
        }
        return null;
    }

    public static double pedirCantidadGUI(String titulo) {
        String input = JOptionPane.showInputDialog(null, "Introduce la cantidad (€):", titulo, JOptionPane.PLAIN_MESSAGE);

        if (input == null) return -1; // usuario canceló

        try {
            double cantidad = Double.parseDouble(input.trim());
            if (cantidad <= 0) {
                JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0.");
                return -1;
            }
            return cantidad;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Introduce un número válido.");
            return -1;
        }
    }

    public static void menuInicio() {
        boolean continuar = true;
        do {
            try {
                System.out.println("1. Registrarse");
                System.out.println("2. Iniciar sesión");
                System.out.println("0. Salir");
                int opcion = Integer.parseInt(leerOpcion("Elige una opción: "));
                if (opcion < 0 || opcion > 2) {
                    throw new IllegalArgumentException("Introduce una opción válida.");
                }

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo...");
                        continuar = false;
                        break;
                    case 1:
                        if (crearUsuario()) {
                            iniciar();
                        }
                        break;
                    case 2:
                        if (iniciarSesion()) {
                            iniciar();
                        }
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (continuar);
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
            System.out.println("\n0 - Salir.");
            System.out.println("1 - Ver saldo de la cuenta.");
            System.out.println("2 - Transferir dinero. (NO)");
            System.out.println("3 - Retirar dinero.");
            System.out.println("4 - Hacer un depósito.");
            System.out.println("5 - Criptomonedas.");
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
                    double cantidadRetiro = pedirCantidadGUI("Retirar dinero");
                    if (cantidadRetiro == -1) break;
                    if (cuentaPrincipal.retirar(cantidadRetiro)) {
                        JOptionPane.showMessageDialog(null, "Retiro realizado. Saldo: " + cuentaPrincipal.getSaldo() + "€");
                    } else {
                        JOptionPane.showMessageDialog(null, "No tienes saldo suficiente.");
                    }
                    break;
                case "4":
                    double cantidadDeposito = pedirCantidadGUI("Hacer un depósito");
                    if (cantidadDeposito == -1) break; // canceló o dato inválido
                    if (cuentaPrincipal.depositar(cantidadDeposito)) {
                        JOptionPane.showMessageDialog(null, "Depósito realizado. Saldo: " + cuentaPrincipal.getSaldo() + "€");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo hacer el depósito.");
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
