package clases;

import java.util.ArrayList;

public class Historial<T> {

    private ArrayList<T> elementos;

    public Historial() {
        this.elementos = new ArrayList<>();
    }

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public void mostrarTodos() {
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }
}
