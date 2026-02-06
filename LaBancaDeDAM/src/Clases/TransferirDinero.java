//package Clases;
//
//import java.util.Random;
//
//public class TransferirDinero extends ProductoBancario {
//
//    private double cantidad;
//    private String mov1;
//
//    public TransferirDinero(Usuario titular, double cantidad, CuentaBancaria origen, CuentaBancaria destino, boolean confirmar) {
//        super(titular);
//        Random random = new Random();
//        this.id = 5000 + random.nextInt(9000);
//        this.cantidad = cantidad;
//        if (origen.getTitular() != destino.getTitular()) {
//            this.mov1 = "Error: titulares diferentes";
//        } else {
//            this.mov1 = String.format("Transfer %.2f€ %s", cantidad, confirmar ? "confirmado" : "cancelado");
//            if (confirmar) {
//                boolean ok = origen.retirar(cantidad) && destino.depositar(cantidad);
//                if (!ok) {
//                    if (origen.getSaldo() < cantidad) destino.depositar(cantidad);  // Rollback simple
//                    this.mov1 += " (fallido)";
//                }
//            }
//        }
//        System.out.println(resumen());
//    }
//
//    @Override
//    public String resumen() {
//        return String.format("TransferirDinero ID %d, %.2f€ - %s", id, cantidad, mov1);
//    }
//}
