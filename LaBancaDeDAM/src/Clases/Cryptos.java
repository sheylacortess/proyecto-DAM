package Clases;

import Metodos.Herramientas;

import java.util.Scanner;

public class Cryptos {

    private double moneda;
    private String tipoMoneda;
    private double cantidad;
    private double precioPorUnidad;
    private double costeTotal;

    private static final double BTC = 56000.0;
    private static final double ETH = 1600.0;
    private static final double SOL = 68.4;

    // Constructor por defecto
    public Cryptos() {
        this.moneda = 0;
        this.tipoMoneda = "";
        this.cantidad = 0;
        this.precioPorUnidad = 0;
        this.costeTotal = 0;
    }

    public Cryptos(double moneda, double cantidad, double precioPorUnidad, double costeTotal) {
        this.moneda = moneda;
        this.tipoMoneda = "";
        this.cantidad = cantidad;
        this.precioPorUnidad = precioPorUnidad;
        this.costeTotal = costeTotal;
    }

    // SETTERS
    private void setMoneda(double moneda) {
        this.moneda = moneda;
    }

    private void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    private void setCosteTotal(double costeTotal) {
        this.costeTotal = costeTotal;
    }

    private void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    // GETTERS
    public double getMoneda() {
        return moneda;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getCosteTotal() {
        return costeTotal;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    /**
     * Metodo para mostrar las criptomonedas al usuario,
     * pedirle cual y cuanto desea
     * y hacer el calculo correspondiente al valor de la criptomoneda
     */
    public static void mostrarCryptos() {
        Scanner sc = new Scanner(System.in);
        Cryptos monedaUsuario = new Cryptos();
        System.out.println("0 -- Salir");
        System.out.println("1 -- Ethereum (ETH) - - - 1600.0€");
        System.out.println("2 -- Bitcoin (BTC) - - - 56000€");
        System.out.println("3 -- Solana (SOL) - - - 68.4€");
        boolean opcionValida = false;
        do {
            String cryptoOpcion = Herramientas.leerString("Selecciona: 1 - 3: ");
            switch (cryptoOpcion) {
                case "0":
                    System.out.println("Gracias! Saliendo...");
                    opcionValida = true;
                    break;
                case "1":
                    monedaUsuario.setMoneda(ETH);
                    monedaUsuario.setTipoMoneda("ETH");
                    opcionValida = true;
                    break;
                case "2":
                    monedaUsuario.setMoneda(BTC);
                    monedaUsuario.setTipoMoneda("BTC");
                    opcionValida = true;
                    break;
                case "3":
                    monedaUsuario.setMoneda(SOL);
                    monedaUsuario.setTipoMoneda("SOL");
                    opcionValida = true;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, pruebe de nuevo. ");
                    break;
            }
        } while (!opcionValida);
        double cantidadUsuario;
        System.out.println("Introduce en € la cantidad deseada: ");
        while (!sc.hasNextDouble()) {
            String basura = sc.next();
            System.out.println("Opción no válida. Por favor, pruebe de nuevo. ");
            System.out.println("Introduzca en € la cantidad deseada: ");
        }
        cantidadUsuario = sc.nextDouble();
        monedaUsuario.setCantidad(cantidadUsuario);
        monedaUsuario.setCosteTotal(cantidadUsuario * monedaUsuario.getMoneda());
        System.out.println("Usted ahora tiene un valor de: " +  monedaUsuario.getCosteTotal() + " en " +  monedaUsuario.getTipoMoneda());
    }
}

