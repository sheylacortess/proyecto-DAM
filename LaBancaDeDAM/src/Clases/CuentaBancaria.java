package Clases;

import java.util.ArrayList;
import java.util.Random;

public class CuentaBancaria extends ProductoBancario {  // ← HERENCIA

    private double saldo;
    private ArrayList<String> movimientos;

    public CuentaBancaria(Usuario titular) {
        super(titular);  // ← Llama constructor de ProductoBancario
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.saldo = random.nextDouble() * 5000;
        this.movimientos = new ArrayList<>();
        this.movimientos.add("Cuenta creada con saldo inicial: " + saldo + "€");
    }

    @Override
    public String resumen() {  // ← Método abstracto implementado
        return "Cuenta ID " + id + ", Saldo " + saldo + "€";
    }

    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            movimientos.add("Depósito: +" + cantidad + "€. Saldo: " + saldo + "€");
            return true;
        }
        return false;
    }

    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            movimientos.add("Retiro: -" + cantidad + "€. Saldo: " + saldo + "€");
            return true;
        }
        return false;
    }

    public double getSaldo() { return saldo; }

    public void mostrarUltimosMovimientos() {
        System.out.println("Historial reciente:");
        int fin = Math.min(3, movimientos.size());
        for (int i = movimientos.size() - fin; i < movimientos.size(); i++) {
            System.out.println("  " + movimientos.get(i));
        }
    }
}
