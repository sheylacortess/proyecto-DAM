package Clases;

import java.util.Random;

public class CuentaBancaria extends ProductoBancario {

    private double saldo;
    private String mov1, mov2, mov3;

    public CuentaBancaria(Usuario titular) {
        super(titular);
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.saldo = Math.round(random.nextDouble() * 5000 * 100) / 100;
        String mensaje = String.format("Cuenta creada con saldo inicial: %.2f€", saldo);
        mov1 = mensaje;  // Inicial solo mov1
    }

    @Override
    public String resumen() {
        return "Cuenta ID " + id + ", Saldo " + saldo + "€";
    }

    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            actualizarMovimiento(String.format("Depósito: +%.2f€. Saldo: %.2f€", cantidad, saldo));
            return true;
        }
        return false;
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            actualizarMovimiento(String.format("Retiro: -%.2f€. Saldo: %.2f€", cantidad, saldo));
            return true;
        }
        return false;
    }

    private void actualizarMovimiento(String nuevoMov) {
        mov3 = mov2;  // Desplaza
        mov2 = mov1;
        mov1 = nuevoMov;
    }

    public double getSaldo() { return saldo; }

    public void mostrarUltimosMovimientos() {
        System.out.println("Historial reciente:");
        if (mov1 != null) System.out.println("  " + mov1);
        if (mov2 != null) System.out.println("  " + mov2);
        if (mov3 != null) System.out.println("  " + mov3);
    }
}
