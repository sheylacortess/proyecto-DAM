package clases;

import interfaces.IOperable;

public abstract class ProductoBancario implements IOperable {
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
