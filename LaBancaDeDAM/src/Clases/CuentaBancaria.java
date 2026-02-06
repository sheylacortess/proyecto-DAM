package Clases;

import java.util.Random;

public class CuentaBancaria extends ProductoBancario {

    private double saldo;
    private String[] movimientos = new String[10];
    private int indiceActual = 0;
    private int totalMovimientos = 0;

    /**
     * Constructor
     *
     * @param titular Usuario propietario de la cuenta
     */
    public CuentaBancaria(Usuario titular) {
        super(titular);
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.saldo = 3000 + Math.round(random.nextDouble() * 7000 * 100) / 100;
        actualizarMovimiento(String.format("Cuenta creada con saldo inicial: %.2f€", saldo));
    }

    /**
     * Genera resumen de la cuenta
     *
     * @return String con ID y saldo actual
     */
    @Override
    public String resumen() {
        return "Cuenta ID " + id + ", Saldo " + saldo + "€";
    }

    /**
     * Realiza depósito
     *
     * @param cantidad cantidad a depositar
     * @return true si el depósito fue exitoso, false si la cantidad es inválida
     */
    public boolean depositar(double cantidad) {
        if (cantidad > 0) {
            double nuevoSaldo = getSaldo() + cantidad;
            setSaldo(nuevoSaldo);
            actualizarMovimiento(String.format("Depósito: +%.2f€. Saldo: %.2f€", cantidad, nuevoSaldo));
            return true;
        }
        return false;
    }

    /**
     * Realiza retiro
     *
     * @param cantidad
     * @return true o false dependiendo el resultado
     */
    public boolean retirar(double cantidad) {
        if (cantidad > 0 && cantidad <= getSaldo()) {
            double nuevoSaldo = getSaldo() - cantidad;
            setSaldo(nuevoSaldo);
            actualizarMovimiento(String.format("Retiro: -%.2f€. Saldo: %.2f€", cantidad, nuevoSaldo));
            return true;
        }
        return false;
    }

    /**
     * Transfiere dinero entre dos cuentas
     *
     * @param origen   cuenta que envía dinero
     * @param destino  cuenta que recibe dinero
     * @param cantidad
     * @return true si exitosa, false si saldo insuficiente
     */
    public static boolean transferir(CuentaBancaria origen, CuentaBancaria destino, double cantidad) {
        if (cantidad > 0 && origen.retirar(cantidad) && destino.depositar(cantidad)) {
            System.out.printf("Dinero transferido %.2f€ → %d%n", cantidad, destino.id);
            return true;
        }
        System.out.println("Transferencia fallida");
        return false;
    }

    /**
     * Invierte en cryptomonedas
     *
     * @param cuenta   cuenta que invierte
     * @param cantidad
     * @return true si exitosa, false si saldo insuficiente
     */
    public static boolean invertir(CuentaBancaria cuenta, double cantidad) {
        if (cantidad > 0 && cuenta.retirar(cantidad)) {
            double cambio = cantidad * (Math.random() * 0.7 - 0.2);  // -20% a +50%
            cuenta.setSaldo(cuenta.getSaldo() + cambio);
            System.out.printf("Crypto: %.2f€ → %.2f€%n", cantidad, cambio);
            return true;
        }
        return false;
    }


    /**
     * Registra nuevo movimiento que tiene como maximo 10
     *
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
    public double getSaldo() {
        return saldo;
    }

    /**
     * Pone nuevo saldo
     *
     * @param saldo
     */
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    /**
     * @return total de movimientos realizados
     */
    public int getTotalMovimientos() {
        return totalMovimientos;
    }

    /**
     * Muestra historial de movimientos recientes
     */
    public void mostrarUltimosMovimientos() {
        System.out.println("Ultimos movimientos:");

        int inicio = (indiceActual - 1 + 10) % 10;

        for (int i = 0; i < 10; i++) {
            int pos = (inicio - i + 10) % 10;
            if (movimientos[pos] != null) {
                System.out.println((i + 1) + ". " + movimientos[pos]);
            } else {
                break;  //por si no ha hecho ningun moviemiento
            }
        }
    }

}
