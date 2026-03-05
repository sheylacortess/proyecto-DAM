package Clases;

import Metodos.Herramientas;

public class Cryptos {

    // Precios en euros de cada criptomoneda
    static final double ETH = 1600.0;
    static final double BTC = 56000.0;
    static final double SOL = 68.4;

    public static void iniciarCryptos() {
        int opcion = -1;
        do {
            mostrarMenu();
            opcion = Herramientas.leerInt("Elige una opción: ");

            switch (opcion) {
                case 0:
                    System.out.println("Gracias. Saliendo...");
                    break;
                case 1:
                    convertirCrypto("Ethereum (ETH)", ETH);
                    break;
                case 2:
                    convertirCrypto("Bitcoin (BTC)", BTC);
                    break;
                case 3:
                    convertirCrypto("Sol (SOL)", SOL);
                    break;
                default:
                    System.out.println("Opción no válida, inténtelo de nuevo.");
            }
        } while (opcion != 0);
    }

    static void mostrarMenu() {
        System.out.println("0 -- Salir");
        System.out.println("1 -- Ethereum (ETH) - - - " + ETH + "€");
        System.out.println("2 -- Bitcoin (BTC) - - - " + BTC + "€");
        System.out.println("3 -- Solana (SOL) - - - " + SOL + "€");
    }

    static void convertirCrypto(String nombre, double precioUnidad) {
        double cantidad = Herramientas.leerDouble("Ingresa la cantidad en €: ");
        double resultado = cantidad / precioUnidad;
        System.out.printf("%.2f€   ->   %.4f %s%n", cantidad, resultado, nombre);
    }
}

