package clases;

public class Movimiento {

    private String tipo;
    private String descripcion;
    private double importe;
    private double saldoResultante;

    public Movimiento(String tipo, String descripcion, double importe, double saldoResultante) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.importe = importe;
        this.saldoResultante = saldoResultante;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public double getSaldoResultante() {
        return saldoResultante;
    }

    @Override
    public String toString() {
        return tipo + " | " +
                descripcion + " | " +
                "Importe: " + String.format("%.2f€", importe) + " | " +
                "Saldo resultante: " + String.format("%.2f€", saldoResultante);
    }
}