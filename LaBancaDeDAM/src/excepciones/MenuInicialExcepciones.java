package excepciones;

public class MenuInicialExcepciones {
    public static class ValorIntroducidoNoValido extends Exception {
        public ValorIntroducidoNoValido(String message) {
            super(message);
        }

        public static void OpcionMenuInicial(String opcion) throws ValorIntroducidoNoValido {
            if (!opcion.equals("0") && !opcion.equals("1") && !opcion.equals("2")) {
                throw new ValorIntroducidoNoValido("Introduzca una opción válida, por favor.");
            }
        }

    }
}
