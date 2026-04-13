package clases;

import excepciones.*;
import metodos.Herramientas;

import java.util.InputMismatchException;

public class CryptoBank {

    static CuentaBancaria cuenta = Herramientas.getCuentaPrincipal();
    static Wallet wallet = new Wallet();

    public static void iniciar() {
        boolean continuar = true;

        do {
            System.out.println("\n1. Comprar");
            System.out.println("2. Vender");
            System.out.println("3. Opciones Wallet");
            System.out.println("0. Salir");

            try {
                // Leer la opción del usuario
                String opcion = Herramientas.leerOpcion("Elija una opción: ");

                switch (opcion) {
                    case "1" -> comprar();
                    case "2" -> vender();
                    case "3" -> opcionesWallet();
                    case "0" -> {
                        System.out.println("Saliendo.");
                        continuar = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce una opción válida.");
            }
        } while (continuar);
    }

    // MÉTODOS
    static void comprar() {
        boolean continuar = true;
        do {
            // mostrar cryptos y salida
            System.out.println();
            for (Crypto c : wallet.getCryptos()) {
                System.out.println(c.getNombre() + " --- " + c.getPrecio());
            }
            System.out.println("0. Salir");

            try {
                String eleccion = Herramientas.leerOpcion("¿Qué criptomoneda desea comprar? (BTC, ETH, SOL): ");
                if (eleccion.equals("0")) {
                    continuar = false;
                }
                if (!eleccion.equals("BTC") && !eleccion.equals("ETH") && !eleccion.equals("SOL")) {
                    throw new EleccionCompraNoValida("Por favor, seleccione una opción válida (BTC, ETH, SOL)");
                }
                double cantidadEurosCompra = Herramientas.leerDouble("Introduce la cantidad en €: ");
                if (cantidadEurosCompra > wallet.getSaldoEuros()) {
                    throw new CompraCryptoNoValida("Saldo insuficiente.");
                }
                if (cantidadEurosCompra <= 0) {
                    throw new CompraCryptoNoValida("La cantidad no puede ser 0.");
                }
                switch (eleccion) {
                    case "BTC" -> {
                        double btc = wallet.getCryptos().get(0).getPrecio();
                        double cantidadARecibir = cantidadEurosCompra / btc;
                        
                        try {
                            // Hay que crear la variable mensaje porque no se puede usar directamente en Herramientes.leerOpcion
                            String mensaje = String.format("Vas a comprar %.6f BTC por %.2f€, ¿desea continuar? (s/n): ", cantidadARecibir, cantidadEurosCompra);
                            String confirmacion = Herramientas.leerOpcion(mensaje);
                            if (confirmacion.equalsIgnoreCase("S")) {
                                wallet.setSaldoEuros(wallet.getSaldoEuros() - cantidadEurosCompra);
                                // Obtener la crypto BTC (posición 0 del ArrayList), y se le suma la cantidad comprada a la que ya tenía
                                wallet.getCryptos().get(0).setCantidad(wallet.getCryptos().get(0).getCantidad() + cantidadARecibir);
                                System.out.println("¡Compra aceptada! - Saldo " + wallet.getCryptos().get(0).getNombre() + " = " + wallet.getCryptos().get(0).getCantidad());
                                continuar = false;
                            } else if (confirmacion.equalsIgnoreCase("N")) {
                                continuar = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Introduce (s/n), porfavor.");
                        }
                    }
                    case "ETH" -> {
                        double eth = wallet.getCryptos().get(1).getPrecio();
                        double cantidadARecibir = cantidadEurosCompra / eth;

                        try {
                            // Hay que crear la variable mensaje porque no se puede usar directamente en Herramientes.leerOpcion
                            String mensaje = String.format("Vas a comprar %.6f ETH por %.2f€, ¿desea continuar? (s/n): ", cantidadARecibir, cantidadEurosCompra);
                            String confirmacion = Herramientas.leerOpcion(mensaje);
                            if (confirmacion.equalsIgnoreCase("S")) {
                                wallet.setSaldoEuros(wallet.getSaldoEuros() - cantidadEurosCompra);
                                // Obtener la crypto ETH (posición 1 del ArrayList), y se le suma la cantidad comprada a la que ya tenía
                                wallet.getCryptos().get(1).setCantidad(wallet.getCryptos().get(1).getCantidad() + cantidadARecibir);
                                System.out.println("¡Compra aceptada! - Saldo " + wallet.getCryptos().get(1).getNombre() + " = " + wallet.getCryptos().get(1).getCantidad());
                                continuar = false;
                            } else if (confirmacion.equalsIgnoreCase("N")) {
                                continuar = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Introduce (s/n), porfavor.");
                        }
                    }
                    case "SOL" -> {
                        double sol = wallet.getCryptos().get(2).getPrecio();
                        double cantidadARecibir = cantidadEurosCompra / sol;

                        try {
                            // Hay que crear la variable mensaje porque no se puede usar directamente en Herramientes.leerOpcion
                            String mensaje = String.format("Vas a comprar %.6f SOL por %.2f€, ¿desea continuar? (s/n): ", cantidadARecibir, cantidadEurosCompra);
                            String confirmacion = Herramientas.leerOpcion(mensaje);
                            if (confirmacion.equalsIgnoreCase("S")) {
                                wallet.setSaldoEuros(wallet.getSaldoEuros() - cantidadEurosCompra);
                                // Obtener la crypto SOL (posición 2 del ArrayList), y se le suma la cantidad comprada a la que ya tenía
                                wallet.getCryptos().get(2).setCantidad(wallet.getCryptos().get(2).getCantidad() + cantidadARecibir);
                                System.out.println("¡Compra aceptada! - Saldo " + wallet.getCryptos().get(2).getNombre() + " = " + wallet.getCryptos().get(2).getCantidad());
                                continuar = false;
                            } else if (confirmacion.equalsIgnoreCase("N")) {
                                continuar = false;
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Introduce (s/n), porfavor.");
                        }
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduzca un valor válido, porfavor.");
            } catch (EleccionCompraNoValida e) {
                System.out.println(e.getMessage());
            } catch (CompraCryptoNoValida e) {
                System.out.println(e.getMessage());
            }
        } while (continuar);
    }

    /**
     * # Flujo del metodo:
     * - Mostrar las criptomonedas que tiene el usuario
     * - Pedir la crypto que desea vender
     * - Validar que la elección sea válida
     * - El usuario introduce cuanta crypto quiere vender
     *      - Validar (cantidad > 0 || suficiente cantidad de crypto en Wallet)
     * - Calcular cuántos € recibe (cantidad crypto * precio de la crypto)
     * - Confirmar operación como en comprar()
     * - Restar crypto de Wallet
     * - Sumar los € a Wallet
     */
    static void vender() {
        boolean continuarVender = true;
        do {
            System.out.println("Tus criptomonedas en Wallet: ");
            for (Crypto c : wallet.getCryptos()) {
                System.out.println(c.getNombre() + "  =  " + c.getCantidad());
            }

            try {
                String eleccionVender = Herramientas.leerOpcion("Seleccione la criptomoneda que desea vender (BTC, ETH, SOL): ");
                System.out.println("0. Salir");
                if (!eleccionVender.equalsIgnoreCase("BTC") &&  !eleccionVender.equalsIgnoreCase("ETH") && !eleccionVender.equalsIgnoreCase("SOL")) {
                    throw new EleccionVentaNoValida("Por favor, seleccione una opción válida (BTC, ETH, SOL)");
                }
                if (eleccionVender.equals("0")) {
                    continuarVender = false;
                }

            } catch (InputMismatchException e) {
                System.out.println("Introduzca un valor válido, porfavor.");
            } catch (EleccionVentaNoValida e) {
                System.out.println(e.getMessage());
            }
        } while (continuarVender);
    }

    static void opcionesWallet() {
        boolean continuar = true;
        do {
            System.out.println("\n-- OPCIONES WALLET --");
            System.out.println("1. Ingresar dinero en Wallet");
            System.out.println("2. Retirar dinero de Wallet");
            System.out.println("3. Estado Wallet");
            System.out.println("0. Salir");

            try {
                String opcion = Herramientas.leerOpcion("Elija una opcion: ");

                switch (opcion) {
                    case "1" -> {
                        boolean ingresarContinuar = true;
                        do {
                            try {
                                double cantidadIngresar = Herramientas.leerDouble("Introduzca la cantidad que desea ingresar a su Wallet: ");
                                if (cantidadIngresar > cuenta.getSaldo()) {
                                    throw new CantidadAIngresarNoValida("Saldo Insuficiente.");
                                }
                                cuenta.retirar(cantidadIngresar); // Ya que tenemos el metodo retirar lo usamos y genera el movimiento también
                                wallet.setSaldoEuros(wallet.getSaldoEuros() + cantidadIngresar);
                                ingresarContinuar = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Introduzca un valor válido, porfavor.");
                            } catch (CantidadAIngresarNoValida e) {
                                System.out.println(e.getMessage());
                            }
                        } while (ingresarContinuar);
                    }
                    case "2" -> {
                        boolean retirarContinuar = true;
                        do {
                            try {
                                double cantidadRetirar = Herramientas.leerDouble("Introduzca la cantidad a retirar: ");
                                if (cantidadRetirar > wallet.getSaldoEuros()) {
                                    throw new CantidadARetirarNoValida("La cantidad que desea retirar es mayor al saldo de su Wallet.");
                                }
                                if (cantidadRetirar <= 0) {
                                    throw new CantidadARetirarNoValida("La cantidad a retirar debe de ser mayor que 0.");
                                }
                                wallet.setSaldoEuros(wallet.getSaldoEuros() - cantidadRetirar);
                                cuenta.depositar(cantidadRetirar); // Función depositar aprovechada
                                retirarContinuar = false;
                            } catch (InputMismatchException e) {
                                System.out.println("Introduzca un valor válido, porfavor.");
                            } catch (CantidadARetirarNoValida e) {
                                System.out.println(e.getMessage());
                            }
                        } while (retirarContinuar);
                    }
                    case "3" -> {
                        /**
                         * %.2f = %f(double), .2(2 decimales)
                         * €%n = %n(salto de línea)
                         * %s = String(BTC, ETH, SOL)
                         * %.6f = double con 6 decimales
                         * (%.2f €) = texto literal
                         */
                        System.out.printf("\nSaldo actual del Wallet: %.2f €%n", wallet.getSaldoEuros());
                        System.out.println("CRIPTOMONEDAS:");
                        // for-each para mostrar las cryptos
                        for (Crypto c : wallet.getCryptos()) {
                            System.out.printf("%s: %.6f (%.2f €)%n", c.getNombre(), c.getCantidad(), c.getCantidad() * c.getPrecio());
                        }
                    }
                    case "0"  -> {
                        System.out.println("Saliendo.");
                        continuar = false;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Introduce una opción válida.");
            }
        } while (continuar);
    }
}
