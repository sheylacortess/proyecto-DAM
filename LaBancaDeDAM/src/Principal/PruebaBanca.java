package Principal;

import Clases.*;
import Metodos.Herramientas;

/**
 * Clase main para ejecutar el programa
 *
 * @author Sheyla & Dani
 */

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {
        String opcion;

        // Crear usuario
        Herramientas.crearUsuario();

        // Mostrar menú
        Herramientas.menu();
    }
}
