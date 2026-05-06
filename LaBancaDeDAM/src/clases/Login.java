package clases;

import metodos.Herramientas;

import java.io.*;

public class Login {
    public static void main(String[] args) {
        System.out.println("Bienvenido.");
        String usuario = Herramientas.leerOpcion("Usuario: ");
        String password = Herramientas.leerOpcion("Password: ");
        String email = Herramientas.leerOpcion("Email: ");
        String dni = Herramientas.leerOpcion("DNI: ");

        registrar(usuario, password, email, dni);

        System.out.println("Bienvenido de nuevo.");
        usuario = Herramientas.leerOpcion("Introduzca su usuario para iniciar sesión: ");
        password = Herramientas.leerOpcion("Password: ");

        if (login(usuario, password)) {
            System.out.println("Login correcto");
        } else {
            System.out.println("Usuario / contraseña incorrectos");
        }
    }

    public static void registrar(String usuario, String password, String email, String dni) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("LaBancaDeDAM/src/ficheros/usuarios.txt", true))) {
            bw.write(usuario);
            bw.write(";");
            bw.write(password);
            bw.write(";");
            bw.write(dni);
            bw.write(";");
            bw.write(email);
            bw.newLine();
        } catch (IOException e)  {
            System.out.println("Error" + e.getMessage());
        }
    }

    public static boolean login(String usuario, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("LaBancaDeDAM/src/ficheros/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (usuario.equalsIgnoreCase(datos[0]) && password.equals(datos[1])) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

    // Devuelve {nombre, password, dni, email} del usuario, o null si no existe
    public static String[] obtenerDatosUsuario(String usuario) {
        try (BufferedReader br = new BufferedReader(new FileReader("LaBancaDeDAM/src/ficheros/usuarios.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (usuario.equalsIgnoreCase(datos[0])) {
                    return datos;
                }
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }
}
