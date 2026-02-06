package Principal;

import Clases.*;
import Metodos.Herramientas;

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {

        String opcion;
        // Creamos el usuario pidiendo sus datos
        String nombre = Herramientas.opcion("Introduzca su usuario: ");
        String dni = Herramientas.opcion("Introduzca su DNI: ");
        String email = Herramientas.opcion("Introduzca su email: ");

        Usuario usuario = new Usuario(nombre, dni, email);
        System.out.println("Usuario creado: " + usuario);

        // Creamos la cuenta AL INICIO
        ProductoBancario cuentaBancaria = new CuentaBancaria(usuario);
        // Segunda cuenta para transferencias (mismo usuario)
        CuentaBancaria cuenta2 = new CuentaBancaria(usuario);

        Herramientas.menu1();
        do {
            opcion = Herramientas.opcion("Elige una opción: ");

            switch (opcion) {
                case "0":
                    System.out.println("Gracias por visitar La Banca De DAM, esperamos que vuelva pronto");
                    break;
                case "1":
                    System.out.println("Datos de tu cuenta:");
                    System.out.println(cuentaBancaria.resumen());
                    ((CuentaBancaria) cuentaBancaria).mostrarUltimosMovimientos();
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":
                    // Crear inversión con POLIMORFISMO
                    Inversion inversion = new Inversion((CuentaBancaria) cuentaBancaria);

                    if (inversion.getCosteTotal() > 0) {
                        ProductoBancario[] productos = {cuentaBancaria, inversion};
                        System.out.println("=== TUS PRODUCTOS BANCARIOS ===");
                        for (ProductoBancario p : productos) {
                            System.out.println("• " + p.resumen());
                        }
                    }
                    break;
            }
        } while (!opcion.equals("0"));
    }
}
