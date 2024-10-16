package me.akaishin.auth;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import me.akaishin.auth.user.FutureUsuario;

public class FutureAuth {

    private static final Path futurePath;
    private static final Path authKeyPath;
    private static final byte[] nameClave;
    private static final byte[] nameIv;
    private static final byte[] passwordClave;
    private static final byte[] passwordIv;

    static {
        // Ruta del directorio de Future en el home del usuario.
        futurePath = Paths.get(System.getProperty("user.home"), "Future");
        // Ruta del archivo de clave de autenticación.
        authKeyPath = futurePath.resolve("auth_key");

        //Clave y iv para decryptar el username
        nameClave = new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        nameIv = new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        //Clave y iv para decryptar el password
        passwordClave = new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        passwordIv = new byte[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
    }

    /**
     * Método que recupera la autenticación del usuario desde los archivos.
     * 
     * @return Objeto FutureUsuario que contiene la autenticación del usuario.
     * @throws FutureException Si ocurre un error durante el proceso de lectura.
     */
    public FutureUsuario loadAuthUser() throws Exception {
        return FutureCache.loadAuth(authKeyPath, nameClave, nameIv, passwordClave, passwordIv);
    }

    /**
     * Método que guarda la autenticación del usuario en los archivos.
     * 
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return Objeto FutureUsuario que contiene la autenticación del usuario.
     */
    public FutureUsuario saveAuthFuture(String username, String password) {
        try {
            FutureCache.saveAuth(new FutureUsuario(username, password),authKeyPath, nameClave, nameIv, passwordClave, passwordIv);
            return new FutureUsuario(username, password);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Método que verifica si existe la autenticación del usuario y la carga si es necesario.
     * 
     * @return Objeto FutureUsuario que contiene la autenticación del usuario, o null si no existe.
     */
    public FutureUsuario getAuthExists() {
        FutureUsuario auth = null;
        if (Files.exists(authKeyPath)) {
            try {
                auth = loadAuthUser();
            } catch (Exception e) {
                // ignore
            }
        }
        if (exists()) {
            try {
                if (auth == null) {
                    auth = loadAuthUser();
                    saveAuthFuture(auth.getUsername(),auth.getPassword());
                }
            } catch (Exception e) {
                // ignore
            }
        }
        return auth;
    }

    /**
     * Método que verifica si existen los archivos.
     * 
     * @return Verdadero si existen los archivos, falso en caso contrario.
     */
    public boolean exists() {
        return Files.exists(authKeyPath) && Files.exists(futurePath);
    }

    /**
     * Método que devuelve la ruta del directorio de Future.
     * 
     * @return Ruta del directorio de Future.
     */
    public static Path getAuthKeyPath() {
        return authKeyPath;
    }

    /**
     * Método que devuelve la ruta del archivo de autenticación.
     * 
     * @return Ruta del archivo de autenticación.
     */
    public static Path getFuturePath() {
        return futurePath;
    }
}
