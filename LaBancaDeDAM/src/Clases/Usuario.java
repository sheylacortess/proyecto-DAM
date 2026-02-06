package Clases;

public class Usuario {

    //atributos
    private String nombre;
    private String dni;
    private String email;

    /**
     * constructor
     * @param nombre
     * @param dni
     * @param email
     */
    public Usuario(String nombre, String dni, String email) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
    }
// getters y setters

    /**
     * @return  nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return dni del usuario
     */
    public String getDni() {
        return dni;
    }

    /**
     * @return email del usuario
     */
    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (DNI: " + dni + ", Email: " + email + ")";
    }
}
