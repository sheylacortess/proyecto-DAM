package bases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Data Access Only
public class UsuarioDAO {

    public static void crearTabla() {
        String sql = "CREATE TABLE IF NOT EXISTS usuarios (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "dni TEXT UNIQUE NOT NULL," +
                "email TEXT NOT NULL," +
                "password TEXT NOT NULL" +
                ")";
        try (Connection conn = ConexionDB.getConexion();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creando tabla: " + e.getMessage());
        }
    }

    public static boolean registrar(String nombre, String dni, String email, String password) {
        String sql = "INSERT INTO usuarios (nombre, dni, email, password) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, dni);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al registrar: " + e.getMessage());
            return false;
        }
    }

    public static boolean login(String dni, String password) {
        String sql = "SELECT * FROM usuarios WHERE dni = ? AND password = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dni);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // true si encuentra un usuario
        } catch (SQLException e) {
            System.out.println("Error en login: " + e.getMessage());
            return false;
        }
    }
}