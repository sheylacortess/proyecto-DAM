package Principal;

import Clases.*;
import Metodos.Herramientas;

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {

        String opcion;
        // Creamos el usuario pidiendo sus datos
//        String nombre = Herramientas.leerOpcion("Introduzca su usuario: ");
//        String dni = Herramientas.leerOpcion("Introduzca su DNI: ");
//        String email = Herramientas.leerOpcion("Introduzca su email: ");
//        Usuario usuario = new Usuario(nombre, dni, email);
//        System.out.println("Usuario creado: " + usuario);

        // CREAR USUARIO MODULARIZADO
        Herramientas.crearUsuario();

        // MENÚ MODULARIZADO
        Herramientas.menu();



        // ANTERIOR
//        // Creamos la cuenta AL INICIO
//        ProductoBancario cuentaBancaria = new CuentaBancaria();
//        // Segunda cuenta para transferencias (mismo usuario)
//        CuentaBancaria cuenta2 = new CuentaBancaria(usuario);
    }
}
