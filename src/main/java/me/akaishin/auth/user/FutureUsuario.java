package me.akaishin.auth.user;

/**
 * Clase de usuario contraseña y nombre
 */
public class FutureUsuario {
    private final String username;
    private final String password;

    /**
     * Constructor para crear un objeto FH con un par de valores de autenticación.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     */
    public FutureUsuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario.
     *
     * @return El nombre de usuario.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Obtiene la contraseña.
     *
     * @return La contraseña.
     */
    public String getPassword() {
        return password;
    }
}
