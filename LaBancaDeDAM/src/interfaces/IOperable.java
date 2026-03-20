package interfaces;

public interface IOperable {
    boolean depositar(double cantidad);

    boolean retirar(double cantidad);

    String resumen();
}
