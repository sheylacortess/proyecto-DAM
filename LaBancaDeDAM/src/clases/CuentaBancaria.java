package clases;

import interfaces.IMovimientos;
import interfaces.IOperable;

public class CuentaBancaria extends ProductoBancario implements IOperable, IMovimientos {

    private double saldo;
    private Historial<String> historial = new Historial<>(10);

    /**
     * Constructor por defecto
     * Crea una cuenta con un usuario genérico por defecto
     */
    public CuentaBancaria() {
        this(new Usuario("Usuario Predeterminado", "12345678A", "usuario@banca.es"));
    }

    /**
     * Constructor
     *
     * @param titular Usuario propietario de la cuenta
     */
    public CuentaBancaria(Usuario titular) {
        super(titular);
        this.saldo = 2000;
        actualizarMovimiento(String.format("Cuenta creada con saldo inicial: %.2f€", saldo));
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
     * Registra nuevo movimiento que tiene como maximo 10
     *
     * @param nuevoMov
     */
    public void actualizarMovimiento(String nuevoMov) {
        historial.agregar(nuevoMov);
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
        return historial.getTotal();
    }

    /**
     * Muestra historial de movimientos recientes
     */
    public void mostrarUltimosMovimientos() {
        System.out.println("Ultimos movimientos:");
        historial.mostrar();
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
}
