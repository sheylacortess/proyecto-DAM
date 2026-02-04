package Clases;

import java.util.Scanner;
import java.util.Random;

public class Inversion extends ProductoBancario {  // ← HERENCIA

    private String moneda;
    private double cantidad;
    private double precioPorUnidad;
    private double costeTotal;

    // Precios aproximados (Feb 2026)
    private static final double BTC = 70582.0;
    private static final double ETH = 2730.0;

    public Inversion(ProductoBancario producto) {
        super(producto.getTitular());  // ✅ Usuario correcto

        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);  // ✅ ID

        Scanner sc = new Scanner(System.in);
        CuentaBancaria cuenta = (CuentaBancaria) producto;

        System.out.println("\n=== INVERSIÓN EN CRIPTOMONEDAS ===");
        System.out.println("1. Bitcoin (BTC) - " + BTC + "€");
        System.out.println("2. Ethereum (ETH) - " + ETH + "€");
        System.out.print("Elige la moneda (1 o 2): ");
        int opcion = sc.nextInt();

        if (opcion == 1) {
            this.moneda = "Bitcoin (BTC)";
            this.precioPorUnidad = BTC;
        } else if (opcion == 2) {
            this.moneda = "Ethereum (ETH)";
            this.precioPorUnidad = ETH;
        } else {
            System.out.println("Opción inválida. Inversión cancelada.");
            this.costeTotal = 0;
            return;
        }

        System.out.print("Cantidad de " + moneda + " a comprar: ");
        this.cantidad = sc.nextDouble();
        this.costeTotal = cantidad * precioPorUnidad;

        System.out.println("\n--- RESUMEN DE INVERSIÓN ---");
        System.out.printf("ID: %d%n", id);
        System.out.printf("Moneda: %s%n", moneda);
        System.out.printf("Cantidad: %.6f%n", cantidad);
        System.out.printf("Precio unidad: %.2f€%n", precioPorUnidad);
        System.out.printf("COSTE TOTAL: %.2f€%n", costeTotal);

        if (cuenta.getSaldo() >= costeTotal) {
            System.out.println("Saldo suficiente!");
        } else {
            System.out.println("Saldo insuficiente!");
        }
    }

    @Override
    public String resumen() {
        return "Inversión ID " + id + ": " + moneda + " (" + costeTotal + "€)";
    }

    public String getMoneda() { return moneda; }
    public double getCantidad() { return cantidad; }
    public double getCosteTotal() { return costeTotal; }
}
