package Metodos;

import java.util.Scanner;

public class Herramientas {

    /**
     * Almacena el valor de la opción (tipo String) seleccionada por el usuario,
     * empleando un mensaje para pedir al usuario la introducción de datos.
     *
     * @param mensaje Mensaje que se muestra al usuario para pedir la introducción
     *                de la opción que desea ejecutar.
     * @return opción seleccionada por el usuario.
     */
    public static String opcion(String mensaje) {
        Scanner s = new Scanner(System.in);
        System.out.println();
        System.out.print(mensaje);
        return s.nextLine();
    }
}
