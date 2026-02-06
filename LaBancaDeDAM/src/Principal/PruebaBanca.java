package Principal;

import Clases.*;
import Metodos.Herramientas;

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {

        String opcion;

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
