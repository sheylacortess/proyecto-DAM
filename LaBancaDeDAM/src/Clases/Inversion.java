package Clases;

import java.util.Scanner;

public class Inversion {

    private String moneda;
    private double cantidad;
    private double precioPorUnidad; // € por unidad de la cripto
    private double costeTotal;

    // Precios aproximados al 30 enero 2026 (datos reales) [web:32][web:22][web:31]
    private static final double BTC = 70582.0;  // 1 BTC ≈ 70,582€
    private static final double ETH = 2730.0;   // 1 ETH ≈ 2,730€

    public Inversion(CuentaBancaria cuenta) {
        Scanner sc = new Scanner(System.in);

        // Mostrar opciones
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
            return;
        }

        System.out.print("Cantidad de " + moneda + " a comprar: ");
        this.cantidad = sc.nextDouble();

        this.costeTotal = cantidad * precioPorUnidad;

        System.out.println("\n--- RESUMEN DE INVERSIÓN ---");
        System.out.printf("Moneda: %s%n", moneda);
        System.out.printf("Cantidad: %.6f%n", cantidad);
        System.out.printf("Precio unidad: %.2f€%n", precioPorUnidad);
        System.out.printf("COSTE TOTAL: %.2f€%n", costeTotal);

        // Verificar si la cuenta tiene saldo suficiente
        if (cuenta.getSaldo() >= costeTotal) {
            System.out.println("¡Saldo suficiente! Puedes proceder a invertir.");
            // Aquí podrías llamar a cuenta.retirar(costeTotal) si quieres ejecutar la inversión
        } else {
            System.out.println("¡Saldo insuficiente! Necesitas " + costeTotal + "€");
        }
    }

    // Getters si los necesitas
    public String getMoneda() { return moneda; }
    public double getCantidad() { return cantidad; }
    public double getCosteTotal() { return costeTotal; }
}
