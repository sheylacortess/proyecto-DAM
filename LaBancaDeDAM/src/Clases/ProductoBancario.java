package Clases;

public abstract class ProductoBancario {
    protected int id;
    protected Usuario titular;

    public ProductoBancario(Usuario titular) {
        this.titular = titular;
    }

    public Usuario getTitular() {
        return titular;
    }

    public abstract String resumen();
}
