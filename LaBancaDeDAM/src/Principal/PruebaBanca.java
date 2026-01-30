package Principal;

import Metodos.Herramientas;

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {
        String opcion;
        opcion = Herramientas.opcion("Introduzca su usuario ");
        do {
            Herramientas.menu1();
            switch (opcion) {
                case "0":
                    System.out.println("Gracias por visitar La Banca De DAM, esperamos que vuelva pronto");
                    break;
                case "1":
                    System.out.println("¿Cuanto dinero quieres sacar?");
            }
        } while (!opcion.equals("0"));
    }
}
