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

    /**
     * Devuelve el menú a mostrar en el programa principal.
     *
     * @throws InterruptedException
     */
    public static void menu1() throws InterruptedException {
        System.out.println("\nHola, bienvenido a La Banca de DAM. Que quieres hacer hoy?");
        Thread.sleep(500);
        System.out.println("0 - Salir.");
        Thread.sleep(500);
        System.out.println("1 - Ver saldo de la cuenta.");
        Thread.sleep(500);
        System.out.println("2 - Transferir dinero.");
        Thread.sleep(500);
        System.out.println("3 - Retirar dinero.");
        Thread.sleep(500);
        System.out.println("4 - Invertir.");
    }
}
