package Principal;

import Clases.CuentaBancaria;
import Metodos.Herramientas;

public class PruebaBanca {
    public static void main(String[] args) throws InterruptedException {
        String opcion;
        opcion = Herramientas.opcion("Introduzca su usuario ");
        Herramientas.menu1();
        do {
            String opMenu;
            opcion = Herramientas.opcion("");
            switch (opcion) {
                case "0":
                    System.out.println("Gracias por visitar La Banca De DAM, esperamos que vuelva pronto");
                    break;
                case "1":
                    System.out.println("¿Cuánto dinero quieres sacar?");
                    break;
                case "2":
                    CuentaBancaria cuentaBancaria = new CuentaBancaria(1,"Dani", 1000);
                    System.out.println(cuentaBancaria);
                    break;
            }
        } while (!opcion.equals("0"));
    }
}
