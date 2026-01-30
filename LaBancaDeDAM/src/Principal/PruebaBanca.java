package Principal;

import Clases.*;
import Metodos.Herramientas;

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {
        String opcion;

        // Pedimos el nombre de usuario
        String usuario = Herramientas.opcion("Introduzca su usuario ");

        // Creamos la cuenta AL INICIO, con el nombre del usuario
        CuentaBancaria cuentaBancaria = new CuentaBancaria(usuario);

        Herramientas.menu1();
        do {
            opcion = Herramientas.opcion("");

            switch (opcion) {
                case "0":
                    System.out.println("Gracias por visitar La Banca De DAM, esperamos que vuelva pronto");
                    break;
                case "1":
                    // Creamos la cuenta con el nombre que escribió el usuario
                    System.out.println(cuentaBancaria);
                    break;
                case "2":
                    System.out.println("Transferimos dienro");
                    break;
                case "3":
                    System.out.println("¿Cuánto dinero quieres sacar?");
                    break;
                case "4":
                    new Inversion(cuentaBancaria);  // Pasa la cuenta para verificar saldo
                    break;
            }
        } while (!opcion.equals("0"));
    }
}
