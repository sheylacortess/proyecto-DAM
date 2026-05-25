package principal;

import clases.CuentaBancaria;
import metodos.GestorFicheros;
import metodos.Herramientas;

/**
 * Clase main para ejecutar el programa
 *
 * @author Sheyla & Dani
 */

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {

        CuentaBancaria cuenta = GestorFicheros.cargarCuenta(); // Cargar cuenta

        if (cuenta == null) {
            Herramientas.crearUsuario(); // Si no ha encontrado una cuenta, se creara una nueva
        } else {
            Herramientas.setCuentaPrincipal(cuenta); // Si ha encontrado cuenta, te muestra la bienvenida
            System.out.println("Bienvenido de nuevo");
        }

        // Mostrar menú
        Herramientas.iniciar(); // Para ambos casos iniciar
    }
}
