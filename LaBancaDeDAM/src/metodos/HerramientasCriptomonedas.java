package metodos;

import clases.Crypto;
import clases.CuentaBancaria;
import clases.Wallet;
import enums.TipoMovimiento;
import excepciones.VenderCryptoExcepciones;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HerramientasCriptomonedas {

    public static void mostrarCriptomonedasVender(Wallet wallet) {
        System.out.println("\nTus criptomonedas en Wallet: ");
        for (Crypto c : wallet.getCryptos().values()) {
            System.out.printf("%s = %-16.6f ------   %10.2f€%n",
                    c.getNombre(),
                    c.getCantidad(),
                    c.getPrecio() * c.getCantidad()
            );
        }
        System.out.println("0. Salir");
    }

    public static void mostrarCryptos(Wallet wallet) {
        for (Crypto c : wallet.getCryptos().values()) {
            System.out.println(c);
        }
    }

    /**
     * Metodo para preguntar al usuario por el formato en el que quiere vender sus criptomonedas,
     * el metodo devuelve TRUE si es en € y false si es en CRYPTO
     *
     * @param wallet // El wallet actual
     * @param crypto // Criptomoneda correspondiente: BTC, ETH, SOL
     * @return
     */
    public static boolean funcionElegirEuroOCrypto(Wallet wallet, String crypto) {
        boolean seguirEligiendo = true;
        String eleccion = "";
        do {
            try {
                eleccion = Herramientas.leerOpcion("¿En qué formato desea vender " + wallet.getCryptos().get(crypto).getNombre() + "? (€ o " + wallet.getCryptos().get(crypto).getNombre() + "): ");
                if (!eleccion.equals("€") && !eleccion.equalsIgnoreCase(wallet.getCryptos().get(crypto).getNombre())) {
                    throw new VenderCryptoExcepciones.CriptomonedaSeleccionadaException("Seleccione una opción válida: (€ o " + wallet.getCryptos().get(crypto).getNombre() + ")");
                }
                System.out.println("Elección: " + eleccion);
                seguirEligiendo = false;
            } catch (VenderCryptoExcepciones.CriptomonedaSeleccionadaException e) {
                System.out.println(e.getMessage());
            }
        } while (seguirEligiendo);
        if (eleccion.equalsIgnoreCase("€")) {
            return true;
        } else  {
            return false;
        }
    }

    /**
     * Metodo que pregunta la cantidad de euros que desea vender el usuario
     * en el metodo vender(). Valida que la cantidad sea válida y la devuelve
     *
     * @param wallet
     * @param crypto // criptomoneda correspondiente
     * @return cantidad en Euros válida a vender
     */
    public static double preguntarCantidadAVenderEuros(Wallet wallet, String crypto) {
        double cantidad;
        double maxEuros = wallet.getCryptos().get(crypto).getCantidad() * wallet.getCryptos().get(crypto).getPrecio(); // Guardar el calculo en una variable para que sea más comodo

        while (true) {
            try {
                cantidad = Herramientas.leerDouble("Introduce la cantidad en € que desea vender: ");

                if (cantidad <= 0) {
                    throw new VenderCryptoExcepciones.CantidadEurosNoValida("La cantidad a vender debe de ser mayor que 0");
                }

                if (cantidad > maxEuros) {
                    throw new VenderCryptoExcepciones.CantidadEurosNoValida("Cantidad insuficiente");
                }

                return cantidad;
            } catch (InputMismatchException e) {
                System.out.println("Introduce una cantidad válida.");
            } catch (VenderCryptoExcepciones.CantidadEurosNoValida e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static double preguntarCantidadAVenderCriptomonedas(Wallet wallet, String crypto) {
        double cantidad;
        double maxCriptomoneda = wallet.getCryptos().get(crypto).getCantidad();

        while (true) {
            try {
                cantidad = Herramientas.leerDouble("Ingrese la cantidad en " + wallet.getCryptos().get(crypto).getNombre() + " que desea vender (los decimales separados con ','): ");

                if (cantidad <= 0) {
                    throw new VenderCryptoExcepciones.CriptomonedaSeleccionadaException("La cantidad no puede ser 0 o menos.");
                }

                if (cantidad > maxCriptomoneda) {
                    throw new VenderCryptoExcepciones.CriptomonedaSeleccionadaException("No dispone de la cantidad suficiente de " + wallet.getCryptos().get(crypto).getNombre());
                }

                return cantidad;
            } catch (InputMismatchException e) {
                System.out.println("Introduzca una cantidad válida.");
            } catch (VenderCryptoExcepciones.CriptomonedaSeleccionadaException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Metodo que vende la criptomoneda que le entre por parametro
     *
     * @param wallet
     * @param crypto
     * @throws VenderCryptoExcepciones.CriptomonedaSeleccionadaException
     */
    public static void venderCrypto(Wallet wallet, String crypto) throws VenderCryptoExcepciones.CriptomonedaSeleccionadaException {
        if (wallet.getCryptos().get(crypto).getCantidad() <= 0) {
            throw new VenderCryptoExcepciones.CriptomonedaSeleccionadaException("No dispone de" + wallet.getCryptos().get(crypto).getNombre() + "para vender.");
        } else {
            double cantidadAVenderEnEuros;
            double cantidadAVenderEnCriptomonedas;
            if (HerramientasCriptomonedas.funcionElegirEuroOCrypto(wallet, crypto)) {
                cantidadAVenderEnEuros = HerramientasCriptomonedas.preguntarCantidadAVenderEuros(wallet, crypto);
                double cryptoARestar = cantidadAVenderEnEuros / wallet.getCryptos().get(crypto).getPrecio();

                // TRANSACCIÓN:
                wallet.getCryptos().get(crypto).setCantidad(wallet.getCryptos().get(crypto).getCantidad() - cryptoARestar);
                wallet.setSaldoEuros(wallet.getSaldoEuros() + cantidadAVenderEnEuros);
                CuentaBancaria.actualizarMovimiento(TipoMovimiento.CRYPTO, cantidadAVenderEnEuros, "Venta de " + wallet.getCryptos().get(crypto).getNombre());

                System.out.printf("¡Venta realizada! -- %.6f %s por %.2f€%n",
                        cryptoARestar,
                        wallet.getCryptos().get(crypto).getNombre(),
                        cantidadAVenderEnEuros);
            } else {
                cantidadAVenderEnCriptomonedas = HerramientasCriptomonedas.preguntarCantidadAVenderCriptomonedas(wallet, crypto);
                double cryptoARestar = cantidadAVenderEnCriptomonedas;
                double euros = cantidadAVenderEnCriptomonedas *  wallet.getCryptos().get(crypto).getPrecio();

                // TRANSACCIÓN:
                wallet.getCryptos().get(crypto).setCantidad(wallet.getCryptos().get(crypto).getCantidad() - cryptoARestar);
                wallet.setSaldoEuros(wallet.getSaldoEuros() + euros);
                CuentaBancaria.actualizarMovimiento(TipoMovimiento.CRYPTO, euros, "Venta de " + wallet.getCryptos().get(crypto).getNombre());

                System.out.printf("¡Venta realizada! -- %.6f %s por %.2f€%n",
                        cryptoARestar,
                        wallet.getCryptos().get(crypto).getNombre(),
                        euros);
            }


        }
    }
}
