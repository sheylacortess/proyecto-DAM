package Clases;

import Metodos.Herramientas;

import java.util.Scanner;
import java.util.Random;

public class Cryptos /** extends ProductoBancario */
{

    private String moneda;
    private double cantidad;
    private double precioPorUnidad;
    private double costeTotal;

    private static final double BTC = 56000.0;
    private static final double ETH = 1600.0;
    private static final double SOL = 68.4;

    // NUEVO CONSTRUCTOR POR DEFECTO
    public Cryptos() {
        this.moneda = "";
        this.cantidad = 0;
        this.precioPorUnidad = 0;
        this.costeTotal = 0;
    }

    public Cryptos(String moneda, double cantidad, double precioPorUnidad, double costeTotal) {
        this.moneda = moneda;
        this.cantidad = cantidad;
        this.precioPorUnidad = precioPorUnidad;
        this.costeTotal = costeTotal;
    }

    // SETTERS
    private void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    private void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    // GETTERS
    public String getMoneda() {
        return moneda;
    }

    public double getCantidad() {
        return cantidad;
    }

    public double getCosteTotal() {
        return costeTotal;
    }


    /**
     * ANTERIOR CONSTRUCTOR CON HERENCIA
     * public Cryptos(ProductoBancario producto) {
     * super(producto.getTitular());
     * <p>
     * Random random = new Random();
     * this.id = 1000 + random.nextInt(9000);
     * <p>
     * Scanner sc = new Scanner(System.in);
     * CuentaBancaria cuenta = (CuentaBancaria) producto;
     * <p>
     * }
     *
     * @Override public String resumen() {
     * return "Inversión ID " + id + ": " + moneda + " (" + costeTotal + "€)";
     * }
     */

    // Metodo mostrar todas las cryptos
    public static void mostrarCryptos() {
        Cryptos monedaUsuario = new Cryptos();
        System.out.println("1 -- Ethereum (ETH) - - - 1600.0€");
        System.out.println("2 -- Bitcoin (BTC) - - - 56000€");
        System.out.println("3 -- Solana (SOL) - - - 68.4€");
        String crypto = Herramientas.leerString("Selecciona: 1 - 3");
        if (crypto.equals("1")) {
            monedaUsuario.setMoneda("ETH");
        } else if (crypto.equals("2")) {
            monedaUsuario.setMoneda("BTC");
        } else if (crypto.equals("3")) {
            monedaUsuario.setMoneda("SOL");
        }
        double cantidadUsuario = Herramientas.leerDouble("Introduce la cantidad deseada: ");
        monedaUsuario.setCantidad(cantidadUsuario);
    }


}

