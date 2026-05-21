package clases;

import java.util.ArrayList;
import java.util.List;

public class GestorMovimientos {

    private static List<Movimiento> movimientos = new ArrayList<>();

    public static void registrarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
    }

    public static List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public static void mostrarMovimientos() {
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados.");
            return;
        }

        for (int i = 0; i < movimientos.size(); i++) {
            System.out.println((i + 1) + ". " + movimientos.get(i));
        }
    }

    public static void generarPdfDeTodosLosMovimientos() {
        PdfTicketService.generarPdfMovimientos(movimientos);
    }
}