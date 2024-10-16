package me.akaishin.auth.crypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Clase que proporciona métodos para cifrar y descifrar datos utilizando el algoritmo AES en modo CBC con padding PKCS5.
 */

public class FutureCrypt {

    /**
     * Método que cifra un array de bytes utilizando una clave y un vector de inicialización.
     * 
     * @param data Array de bytes que se va a cifrar.
     * @param key  Clave secreta utilizada para cifrar los datos.
     * @param iv   Vector de inicialización utilizado para inicializar el algoritmo de cifrado.
     * @return Array de bytes cifrado.
     * @throws Exception Si ocurre un error durante el proceso de cifrado.
     */
    public static byte[] encrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    /**
     * Método que descifra un array de bytes utilizando una clave y un vector de inicialización.
     * 
     * @param data Array de bytes que se va a descifrar.
     * @param key  Clave secreta utilizada para descifrar los datos.
     * @param iv   Vector de inicialización utilizado para inicializar el algoritmo de descifrado.
     * @return Array de bytes descifrado.
     * @throws Exception Si ocurre un error durante el proceso de descifrado.
     */
    public static byte[] decrypt(byte[] data, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        return cipher.doFinal(data);
    }

    
    /**
     * Método que genera un array de bytes aleatorios de longitud especificada.
     * 
     * @param length Longitud del array de bytes que se va a generar.
     * @return Array de bytes aleatorios.
     */
    public static byte[] generateRandomBytes(int length) {
        byte[] bytes = new byte[length];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(bytes);
        return bytes;
    }
}