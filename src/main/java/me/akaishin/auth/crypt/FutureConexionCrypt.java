package me.akaishin.auth.crypt;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase bH que maneja la cifrado y descifrado de datos utilizando el algoritmo AES.
 */
public class FutureConexionCrypt {
    private byte[] clave; // Clave de cifrado de 16 bytes.

    /**
     * Constructor que inicializa la clave de cifrado con la cadena de texto proporcionada.
     * @param clave Clave de cifrado como cadena de texto.
     */
    public FutureConexionCrypt(String clave) {
        this.clave = new byte[16];
        int indice = 0;
        while (indice < 16) {
            try {
                this.clave[indice] = clave.getBytes()[indice];
            } catch (Exception e) {
                // Se ignora la excepción si la cadena de texto es demasiado corta.
            }
            indice++;
        }
    }

    /**
     * Cifra un arreglo de bytes utilizando el algoritmo AES.
     * @param datos Arreglo de bytes a cifrar.
     * @return Arreglo de bytes cifrado.
     * @throws Exception Si ocurre un error durante el cifrado.
     */
    public byte[] cifrar(byte[] datos) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, this.obtenerClave());
        return cipher.doFinal(datos);
    }

    /**
     * Obtiene la clave de cifrado como objeto Key.
     * @return Clave de cifrado como objeto Key.
     * @throws Exception Si ocurre un error durante la creación de la clave.
     */
    private Key obtenerClave() throws Exception {
        return new SecretKeySpec(this.clave, "AES");
    }

    /**
     * Descifra un arreglo de bytes utilizando el algoritmo AES.
     * @param datos Arreglo de bytes a descifrar.
     * @return Arreglo de bytes descifrado.
     * @throws Exception Si ocurre un error durante el descifrado.
     */
    public byte[] descifrar(byte[] datos) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, this.obtenerClave());
        return cipher.doFinal(datos);
    }
}