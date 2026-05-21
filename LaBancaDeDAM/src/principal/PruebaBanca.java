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
        // Intentar cargar cuenta existente
//        CuentaBancaria cuenta = GestorFicheros.cargarCuenta();
//
//        if (cuenta == null) {
//            // No existe el fichero, crear nuevo usuario
//            Herramientas.crearUsuario();
//        } else {
//            // Cuenta cargada correctamente
//            System.out.println("Bienvenido de nuevo.");
//        }

        Herramientas.crearUsuario();
        // Mostrar menú
        Herramientas.iniciar();

//        // Al salir, guardar
//        GestorFicheros.guardarCuenta(Herramientas.getCuentaPrincipal());
    }
}
