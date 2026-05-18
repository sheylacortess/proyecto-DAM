package clases;

import enums.TipoMovimiento;

import java.time.LocalDate;


public class Movimiento {

    private TipoMovimiento tipo;
    private double cantidad;
    private String descripcion;
    private LocalDate fecha;

    public Movimiento(TipoMovimiento tipo, double cantidad, String descripcion, LocalDate fecha) {
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public TipoMovimiento getTipo() {
        return tipo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String toString() {
        return "Tipo: " + tipo + " | Cantidad: " + cantidad + " | Fecha: " + fecha + " | Descripcion: " + descripcion;
    }
}
