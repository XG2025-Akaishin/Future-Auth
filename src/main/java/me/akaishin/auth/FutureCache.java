package me.akaishin.auth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import me.akaishin.auth.crypt.FutureCrypt;
import me.akaishin.auth.user.FutureUsuario;

/**
 * Clase que proporciona métodos para cargar y guardar datos de autenticación desde/hacia un archivo.
 */
public class FutureCache {
    
    /**
     * Método que carga datos de autenticación desde un archivo ubicado en path.
     * 
     * @param path Ruta del archivo que contiene los datos de autenticación.
     * @param usernameKey Arreglo de bytes utilizado para descifrar el nombre de usuario.
     * @param usernameIv Arreglo de bytes utilizado como vector de inicialización para el descifrado del nombre de usuario.
     * @param passwordKey Arreglo de bytes utilizado para descifrar la contraseña.
     * @param passwordIv Arreglo de bytes utilizado como vector de inicialización para el descifrado de la contraseña.
     * @return Objeto FutureUsuario con los datos de autenticación descifrados.
     * @throws FutureException Si ocurre un error durante la lectura o descifrado.
     */
    public static FutureUsuario loadAuth(Path path, byte[] usernameKey, byte[] usernameIv, byte[] passwordKey, byte[] passwordIv) throws Exception {
        DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(path));
        byte[] usernameBytes = readBytes(dataInputStream);
        byte[] passwordBytes = readBytes(dataInputStream);
        byte[] usernameBytes0 = FutureCrypt.decrypt(usernameBytes,usernameKey,usernameIv);
        byte[] passwordBytes0 = FutureCrypt.decrypt(passwordBytes,passwordKey,passwordIv);
        String username = new String(usernameBytes0, StandardCharsets.UTF_8);
        String password = new String(passwordBytes0, StandardCharsets.UTF_8);
        return new FutureUsuario(username, password);
    }

    /**
     * Método que lee un array de bytes desde un flujo de entrada de datos.
     * 
     * @param in Flujo de entrada de datos.
     * @return Array de bytes leído.
     * @throws IOException Si ocurre un error durante la lectura.
     */
    private static byte[] readBytes(DataInputStream in) throws IOException {
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readFully(bytes);
        return bytes;
    }

    /**
     * Método que guarda datos de autenticación en un archivo ubicado en path.
     * 
     * @param auth Objeto FutureUsuario que contiene los datos de autenticación a guardar.
     * @param path Ruta del archivo donde se guardarán los datos de autenticación.
     * @param usernameKey Arreglo de bytes utilizado para cifrar el nombre de usuario.
     * @param usernameIv Arreglo de bytes utilizado como vector de inicialización para el cifrado del nombre de usuario.
     * @param passwordKey Arreglo de bytes utilizado para cifrar la contraseña.
     * @param passwordIv Arreglo de bytes utilizado como vector de inicialización para el cifrado de la contraseña.
     * @throws FutureException Si ocurre un error durante la escritura o cifrado.
     */
    public static void saveAuth(FutureUsuario auth,Path path, byte[] usernameKey, byte[] usernameIv, byte[] passwordKey, byte[] passwordIv) throws Exception {
        byte[] usernameBytes = auth.getUsername().getBytes(StandardCharsets.UTF_8);
        byte[] passwordBytes = auth.getPassword().getBytes(StandardCharsets.UTF_8);
        byte[] encryptedUsernameBytes = FutureCrypt.encrypt(usernameBytes, usernameKey, usernameIv);
        byte[] encryptedPasswordBytes = FutureCrypt.encrypt(passwordBytes, passwordKey, passwordIv);
        DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(path));
        dataOutputStream.writeInt(encryptedUsernameBytes.length);
        dataOutputStream.write(encryptedUsernameBytes);
        dataOutputStream.writeInt(encryptedPasswordBytes.length);
        dataOutputStream.write(encryptedPasswordBytes);
        dataOutputStream.close();
    }
}
