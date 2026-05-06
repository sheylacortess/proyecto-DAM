package clases;

public class Historial<T> {
    private final Object[] elementos;
    private int indiceActual = 0;
    private int total = 0;
    private final int capacidad;

    public Historial(int capacidad) {
        this.capacidad = capacidad;
        this.elementos = new Object[capacidad];
    }

    public void agregar(T elemento) {
        elementos[indiceActual] = elemento;
        indiceActual = (indiceActual + 1) % capacidad;
        total++;
    }

    public void mostrar() {
        int inicio = (indiceActual - 1 + capacidad) % capacidad;
        for (int i = 0; i < capacidad; i++) {
            int pos = (inicio - i + capacidad) % capacidad;
            if (elementos[pos] != null) {
                System.out.println((i + 1) + ". " + elementos[pos]);
            } else {
                break;
            }
        }
    }

    public int getTotal() {
        return total;
    }
}