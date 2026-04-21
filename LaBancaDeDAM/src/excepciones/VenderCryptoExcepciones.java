package excepciones;

public class VenderCryptoExcepciones {
    public static class FormatoElegidoNoValido extends Exception {
        public FormatoElegidoNoValido(String message) {
            super(message);
        }
    }

    public static class CantidadEurosNoValida extends Exception {
        public CantidadEurosNoValida(String message) {
            super(message);
        }
    }

    public static class CriptomonedaSeleccionadaException extends Exception {
        public CriptomonedaSeleccionadaException(String message) {
            super(message);
        }
    }
}
