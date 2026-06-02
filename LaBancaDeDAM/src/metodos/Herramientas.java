package metodos;

import clases.CryptoBank;
import clases.CuentaBancaria;
import clases.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static void crearUsuario() {
        String dni = null;
        String email = null;
        String nombre = null;
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

    public static double pedirCantidadGUI(String titulo) {

        // Creamos la ventana emergente
        JDialog dialog = new JDialog();
        dialog.setSize(300, 150); // tamaño de la ventana en píxeles
        dialog.setModal(true); // pausa el programa hasta que el usuario cierre la ventana
        dialog.setLayout(new FlowLayout()); // coloca los componentes en fila

        // Campo donde el usuario escribe la cantidad
        JTextField campo = new JTextField(15);
        // Botón para confirmar
        JButton boton = new JButton("Aceptar");
        // Usamos array porque dentro del evento no se puede modificar una variable normal
        double[] resultado = {-1}; // -1 significa que el usuario no ha introducido nada válido

        // Evento que se ejecuta cuando el usuario pulsa Aceptar
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = campo.getText().trim(); // leemos lo que escribió el usuario
                try {
                    double cantidad = Double.parseDouble(input); // convertimos el texto a número
                    if (cantidad <= 0) {
                        JOptionPane.showMessageDialog(null, "La cantidad debe ser mayor que 0.");
                    } else {
                        resultado[0] = cantidad; // guardamos el resultado
                        dialog.dispose(); // cerramos la ventana
                    }
                } catch (NumberFormatException ex) {
                    // si el texto no es un número mostramos un aviso
                    JOptionPane.showMessageDialog(null, "Introduce un número válido.");
                }
            }
        });

        // Añadimos los componentes a la ventana
        dialog.add(new JLabel("Introduce la cantidad (€):"));
        dialog.add(campo);
        dialog.add(boton);
        dialog.setVisible(true); // mostramos la ventana y pausamos aquí hasta que se cierre

        // Devolvemos la cantidad introducida (-1 si no introdujo nada válido)
        return resultado[0];
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
