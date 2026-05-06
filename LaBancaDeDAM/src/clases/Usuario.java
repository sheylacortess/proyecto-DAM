package clases;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Usuario {

    //atributos
    private static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    private String nombre;
    private String dni;
    private String email;

    /**
     * constructor
     *
     * @param nombre
     * @param dni
     * @param email
     */
    public Usuario(String nombre, String dni, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    public static void registrarUsuario() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        do {
            try {
                System.out.print("Nombre: ");
                String nombre = sc.nextLine();
                System.out.print("DNI: ");
                String dni = sc.nextLine();
                System.out.print("Email: ");
                String email = sc.nextLine();
                usuarios.add(new Usuario(nombre, dni,email));
                System.out.println("Usuario registrado exitosamente con DNI: " + dni);
                continuar = false;
            } catch (InputMismatchException e) {
                System.out.println("Error -- Ingresa un valor válido.");
            }
        } while (continuar);
    }

    public static void mostrarUsuariosRegistrados() {
        for (Usuario u : usuarios) {
            System.out.println(u);
        }
    }

    // GETTERS - NO SETTERS

    /**
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return dni del usuario
     */
    public String getDni() {
        return dni;
    }

    /**
     * @return email del usuario
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (DNI: " + dni + ", Email: " + email + ")";
    }
}
