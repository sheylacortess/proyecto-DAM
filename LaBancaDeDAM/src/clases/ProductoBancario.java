package clases;

import interfaces.IOperable;

import java.util.Random;

public abstract class ProductoBancario implements IOperable {
    protected int id;
    protected Usuario titular;

    public ProductoBancario(Usuario titular) {
        Random random = new Random();
        this.id = 1000 + random.nextInt(9000);
        this.titular = titular;
    }

    public Usuario getTitular() {
        return titular;
    }

    public abstract String resumen();
}
