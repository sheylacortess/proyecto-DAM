package metodos;

import clases.CuentaBancaria;

import java.io.*;

public class GestorFicheros implements Serializable {

    public static void guardarCuenta(CuentaBancaria cuenta) {
        // Serialización
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            out.writeObject(cuenta);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static CuentaBancaria cargarCuenta() {
        // Deserialización
        CuentaBancaria cuenta = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.dat"))) {
            cuenta = (CuentaBancaria) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return cuenta;
    }
}
