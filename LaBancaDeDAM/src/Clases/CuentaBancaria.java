package Clases;
import java.util.ArrayList;
public class CuentaBancaria {

        private int id;                // ID único de la cuenta
        private String titular;        // Nombre del usuario/titular
        private double saldo;          // Saldo actual
        private ArrayList<String> movimientos;  // Historial de operaciones

        // Constructor
        public CuentaBancaria(int id, String titular, double saldoInicial) {
            this.id = id;
            this.titular = titular;
            this.saldo = saldoInicial;
            this.movimientos = new ArrayList<>();
            this.movimientos.add("Cuenta creada con saldo inicial: " + saldoInicial + "€");
        }

        // Depositar dinero
        public boolean depositar(double cantidad) {
            if (cantidad > 0) {
                saldo += cantidad;
                movimientos.add("Depósito: +" + cantidad + "€. Saldo: " + saldo + "€");
                return true;
            }
            return false;
        }

        // Retirar dinero
        public boolean retirar(double cantidad) {
            if (cantidad > 0 && cantidad <= saldo) {
                saldo -= cantidad;
                movimientos.add("Retiro: -" + cantidad + "€. Saldo: " + saldo + "€");
                return true;
            }
            return false;
        }

        // Transferir (para usar después)
        public boolean transferir(CuentaBancaria destino, double cantidad) {
            if (retirar(cantidad)) {
                destino.depositar(cantidad);
                movimientos.add("Transferencia enviada: -" + cantidad + "€ a cuenta " + destino.getId());
                return true;
            }
            return false;
        }

        // Getters
        public int getId() { return id; }
        public String getTitular() { return titular; }
        public double getSaldo() { return saldo; }
        public ArrayList<String> getMovimientos() { return movimientos; }

        // Mostrar info
        @Override
        public String toString() {
            return "Cuenta [ID=" + id + ", Titular=" + titular + ", Saldo=" + saldo + "€]";
        }

        // Últimos 3 movimientos
        public void mostrarUltimosMovimientos() {
            System.out.println("Historial reciente:");
            int fin = Math.min(3, movimientos.size());
            for (int i = movimientos.size() - fin; i < movimientos.size(); i++) {
                System.out.println("  " + movimientos.get(i));
            }
        }
    }
