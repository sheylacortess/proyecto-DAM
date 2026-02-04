package Clases;

public class Usuario {
    private String nombre;
    private String dni;
    private String email;

    public Usuario(String nombre, String dni, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (DNI: " + dni + ", Email: " + email + ")";
    }
}
