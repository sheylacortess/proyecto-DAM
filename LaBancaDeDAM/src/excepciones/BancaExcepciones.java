package excepciones;

public class BancaExcepciones {
    public static class CantidadAIngresarNoValida extends Exception {
        public CantidadAIngresarNoValida(String message) { super(message); }
    }
    public static class CantidadARetirarNoValida extends Exception {
        public CantidadARetirarNoValida(String message) { super(message); }
    }
    public static class CompraCryptoNoValida extends Exception {
        public CompraCryptoNoValida(String message) { super(message); }
    }
    public static class EleccionCompraNoValida extends Exception {
        public EleccionCompraNoValida(String message) { super(message); }
    }
    public static class EleccionVentaNoValida extends Exception {
        public EleccionVentaNoValida(String message) { super(message); }
    }
}