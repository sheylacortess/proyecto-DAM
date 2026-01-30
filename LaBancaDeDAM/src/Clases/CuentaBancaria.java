package Clases;

import java.util.ArrayList;
import java.util.Random;

public class CuentaBancaria {

    private int id;                // ID único de la cuenta
    private String titular;        // Nombre del usuario/titular
    private double saldo;          // Saldo actual
    private ArrayList<String> movimientos;  // Historial de operaciones

    // Constructor: solo pide el nombre del titular
    public CuentaBancaria(String titular) {
        Random random = new Random(); // [web:5]

        // id aleatorio entre 1000 y 9999
        this.id = 1000 + random.nextInt(9000); // [web:5]

        // saldo aleatorio entre 0 y 5000 €
        this.saldo = random.nextDouble() * 5000; // [web:5]

        this.titular = titular;
        this.movimientos = new ArrayList<>();
        this.movimientos.add("Cuenta creada con saldo inicial: " + saldo + "€");
    }

    // Resto de métodos igual que los tenías
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

    public boolean transferir(CuentaBancaria destino, double cantidad) {
        if (retirar(cantidad)) {
            destino.depositar(cantidad);
            movimientos.add("Transferencia enviada: -" + cantidad + "€ a cuenta " + destino.getId());
            return true;
        }
        return false;
    }

    public int getId() { return id; }
    public String getTitular() { return titular; }
    public double getSaldo() { return saldo; }
    public ArrayList<String> getMovimientos() { return movimientos; }

    @Override
    public String toString() {
        return "Cuenta [ID=" + id + ", Titular=" + titular + ", Saldo=" + saldo + "€]";
    }

    public void mostrarUltimosMovimientos() {
        System.out.println("Historial reciente:");
        int fin = Math.min(3, movimientos.size());
        for (int i = movimientos.size() - fin; i < movimientos.size(); i++) {
            System.out.println("  " + movimientos.get(i));
        }
    }
}
