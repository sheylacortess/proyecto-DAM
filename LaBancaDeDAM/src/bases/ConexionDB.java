package bases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:sqlite:mi_base_de_datos.db";

    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL)) {
            System.out.println("Conectado. bien");
        } catch (SQLException e) {
            System.out.println("Error fatal");
        }
    }
}