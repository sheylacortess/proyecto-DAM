package Clases;

import java.util.Random;

public class SacarDinero extends ProductoBancario {

    private double cantidad;
    private String mov1;

    public SacarDinero(Usuario titular, double cantidad, CuentaBancaria cuenta, boolean confirmar) {
        super(titular);
        Random random = new Random();
        this.id = 4000 + random.nextInt(9000);
        this.cantidad = cantidad;
        this.mov1 = String.format("Retiro %.2f€ %s", cantidad, confirmar ? "confirmado" : "cancelado");
        if (confirmar) {
            if (!cuenta.retirar(cantidad)) {
                this.mov1 += " (fallido: saldo insuficiente)";
            }
        }
        System.out.println(resumen());
    }

    @Override
    public String resumen() {
        return String.format("SacarDinero ID %d, %.2f€ - %s", id, cantidad, mov1);
    }
}
