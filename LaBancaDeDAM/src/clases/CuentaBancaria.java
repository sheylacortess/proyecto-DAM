package clases;

import enums.TipoMovimiento;
import interfaces.IOperable;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class CuentaBancaria extends ProductoBancario implements IOperable, Serializable {

    private double saldo;
    private Historial<Movimiento> historial;

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
        this.saldo = 1000;
        this.historial = new Historial<Movimiento>();
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
            actualizarMovimiento(TipoMovimiento.DEPOSITO, cantidad, "Deposito efectuado");
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
            actualizarMovimiento(TipoMovimiento.RETIRO, cantidad, "Retiro efectuado");
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

    public void actualizarMovimiento(TipoMovimiento tipoMov, double cantidad, String descripcion) {
        Movimiento mov = new Movimiento(tipoMov, cantidad, descripcion, LocalDate.now());
        historial.agregar(mov);
    }

    public void mostrarUltimosMovimientos() {
        historial.mostrarTodos();
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
     * Genera resumen de la cuenta
     *
     * @return String con ID y saldo actual
     */
    @Override
    public String resumen() {
        return "Cuenta ID " + id + ", Saldo " + saldo + "€";
    }
}
