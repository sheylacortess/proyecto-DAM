package Clases;

import java.util.Random;

public class CuentaBancaria extends ProductoBancario {

    private double saldo;
    private String[] movimientos = new String[10];
    private int indiceActual = 0;
    private int totalMovimientos = 0;

    /**
     * Constructor
     * @param titular Usuario propietario de la cuenta
     */
    public CuentaBancaria(Usuario titular) {
        super(titular);
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.saldo = Math.round(random.nextDouble() * 5000 * 100) / 100;
        actualizarMovimiento(String.format("Cuenta creada con saldo inicial: %.2f€", saldo));
    }

    /**
     * Genera resumen de la cuenta
     * @return String con ID y saldo actual
     */
    @Override
    public String resumen() {
        return "Cuenta ID " + id + ", Saldo " + saldo + "€";
    }

    /**
     * Realiza depósito
     * @param cantidad cantidad a depositar
     * @return true si el depósito fue exitoso, false si la cantidad es inválida
     */
    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            saldo += cantidad;
            actualizarMovimiento(String.format("Depósito: +%.2f€. Saldo: %.2f€", cantidad, saldo));
            return true;
        }
        return false;
    }
//    case 4:
//            System.out.print("Introduce la cantidad a depositar: ");
//    double cantidadDeposito = sc.nextDouble();
//    if (cuenta.depositar(cantidadDeposito)) {
//        System.out.println("Depósito realizado. Saldo actual: " + cuenta.getSaldo());
//    } else {
//        System.out.println("No se ha podido hacer el deposito, cantidad no válida.");
//    }
//    break;

    /**
     * Realiza retiro
     * @param cantidad
     * @return true o false dependiendo el resultado
     */
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= saldo) {
            saldo -= cantidad;
            actualizarMovimiento(String.format("Retiro: -%.2f€. Saldo: %.2f€", cantidad, saldo));
            return true;
        }
        return false;
    }
//    case 3:
//        System.out.print("Introduce la cantidad a retirar: ");
//    double cantidadRetiro = sc.nextDouble();
//    if (cuenta.retirar(cantidadRetiro)) {
//        System.out.println("Retiro realizado. Nuevo saldo: " + cuenta.getSaldo());
//    } else {
//        System.out.println("No tienes saldo suficiente para retirar.");
//    }
//    break;

    /**
     * Registra nuevo movimiento que tiene como maximo 10
     * @param nuevoMov
     */
    private void actualizarMovimiento(String nuevoMov) {
        movimientos[indiceActual] = nuevoMov;
        indiceActual = (indiceActual + 1) % 10;  // 10 = movimientos.length
        totalMovimientos++;
    }

    // GETTERS Y SETTERS
    /**
     * @return saldo actual de la cuenta
     */
    public double getSaldo() { return saldo; }

    /**
     * Pone nuevo saldo
     * @param saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void mostrarUltimosMovimientos() {
        System.out.println("Ultimos movimientos:");

        int inicio = (indiceActual - 1 + 10) % 10;

        for (int i = 0; i < 10; i++) {
            int pos = (inicio - i + 10) % 10;
            if (movimientos[pos] != null) {
                System.out.println((i+1) + ". " + movimientos[pos]);
            } else {
                break;  //por si no ha hecho ningun moviemiento
            }
        }
    }

}
