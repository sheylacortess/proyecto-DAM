package metodos;

import clases.Crypto;
import clases.Wallet;
import excepciones.VenderCryptoExcepciones;

public class HerramientasCriptomonedas {

    public static void mostrarCriptomonedasVender(Wallet wallet) {
        System.out.println("\nTus criptomonedas en Wallet: ");
        for (Crypto c : wallet.getCryptos()) {
            System.out.printf("%s = %-10s------   %10.2f€%n",
                    c.getNombre(),
                    c.getCantidad(),
                    c.getPrecio() * c.getCantidad()
            );
        }
        System.out.println("0. Salir");
    }

    /**
     * Metodo para preguntar al usuario por el formato en el que quiere vender sus criptomonedas,
     * el metodo devuelve TRUE si es en € y false si es en CRYPTO
     *
     * @param wallet // El wallet actual
     * @param indiceCrypto // Indice para trabajar con la crypto correspondiente (0, 1 o 2)
     * @return
     */
    public static boolean funcionElegirEuroOCrypto(Wallet wallet, int indiceCrypto) {
        boolean seguirEligiendo = true;
        String eleccion = "";
        do {
            try {
                eleccion = Herramientas.leerOpcion("¿En qué formato desea vender " + wallet.getCryptos().get(indiceCrypto).getNombre() + "? (€ o " + wallet.getCryptos().get(indiceCrypto).getNombre() + "): ");
                if (!eleccion.equals("€") && !eleccion.equalsIgnoreCase(wallet.getCryptos().get(indiceCrypto).getNombre())) {
                    throw new VenderCryptoExcepciones.CriptomonedaSeleccionadaException("Seleccione una opción válida: (€ o " + wallet.getCryptos().get(indiceCrypto).getNombre() + ")");
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
     *  do {
     *                 try {
     *                     String eleccionCryptoVender = Herramientas.leerOpcion("Escriba la crypto que desea vender (BTC, ETH, SOL): ");
     *                     eleccionCryptoVender = eleccionCryptoVender.toUpperCase(); // Convertir la eleccion a UPPER para usarla sin complicaciones de sintaxis
     *                     // Crear un indice para trabajar con el arrayList del Wallet con las cryptos
     *                     int indice = 0;
     *                     switch (eleccionCryptoVender) {
     *                         case "BTC" -> indice = 0;
     *                         case "ETH" -> indice = 1;
     *                         case "SOL" -> indice = 2;
     *                     }
     *                     if (eleccionCryptoVender.equals("0")) {
     *                         seguirVender = false;
     *                     }
     *                     if (eleccionCryptoVender.equals("BTC") || eleccionCryptoVender.equals("ETH") ||  eleccionCryptoVender.equals("SOL")) {
     *
     *                         // Preguntar por formato en el que quiere vender (€ / CRYPTO)
     *                         boolean formatoContinuar = true;
     *                         String formatoElegido = "";
     *                         do {
     *                             try {
     *                                 formatoElegido = Herramientas.leerOpcion("En qué formato desea vender (€ / " + eleccionCryptoVender + "): ");
     *                                 if (formatoElegido.equals("€") || formatoElegido.equalsIgnoreCase(eleccionCryptoVender)) {
     *                                     System.out.println("Formato seleccionado: " +  formatoElegido);
     *                                 } else {
     *                                     throw new VenderCryptoExcepciones.FormatoElegidoNoValido("Introduzca (€ / " + eleccionCryptoVender + "): ");
     *                                 }
     *                                 formatoContinuar = false;
     *                             } catch (InputMismatchException e) {
     *                                 System.out.println("Introduzca un valor válido, porfavor.");
     *                             } catch (VenderCryptoExcepciones.FormatoElegidoNoValido e) {
     *                                 System.out.println(e.getMessage());
     *                             }
     *                         } while (formatoContinuar);
     *
     *                         // Cuando ya se obtiene el formato deseado se pregunta por la cantidad depende de lo que haya elegido
     *                         boolean preguntarCantidad = true;
     *                         double cantidadVenderEnEuros;
     *                         double cantidadVenderEnCrypto;
     *                         do {
     *                             if (formatoElegido.equals("€")) {
     *                                 boolean continuarEuros = true;
     *                                 do {
     *                                     try {
     *                                         cantidadVenderEnEuros = Herramientas.leerDouble("Introduzca la cantidad en € que desea vender: ");
     *                                         if (cantidadVenderEnEuros > wallet.getCryptos().get(indice).getCantidad() * wallet.getCryptos().get(indice).getPrecio()) {
     *                                             throw new VenderCryptoExcepciones.CantidadEurosNoValida("Cantidad insuficiente.");
     *                                         }
     *                                     } catch (InputMismatchException e) {
     *                                         System.out.println("Introduzca una cantidad válida, por favor.");
     *                                     } catch (VenderCryptoExcepciones.CantidadEurosNoValida e) {
     *                                         System.out.println(e.getMessage());
     *                                     }
     *                                 } while (continuarEuros);
     *                             } else {
     *
     *                             }
     *                         } while (preguntarCantidad);
     *                         switch (eleccionCryptoVender) {
     *                             case "BTC" -> {
     *
     *                             }
     *                             case "ETH" -> {
     *
     *                             }
     *                             case "SOL" -> {
     *
     *                             }
     *                         }
     *                     }
     *
     *                 } catch (InputMismatchException e) {
     *                     System.out.println("Introduzca un valor válido, porfavor.");
     *                 }
     *             } while (seguirVender);
     *             continuarVender = false;
     */
}
