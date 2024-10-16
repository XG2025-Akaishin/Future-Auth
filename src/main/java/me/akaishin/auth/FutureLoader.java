package me.akaishin.auth;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyStore;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import me.akaishin.auth.crypt.FutureCrypt;
import me.akaishin.auth.hwid.Hwid;
import me.akaishin.auth.user.FutureUsuario;

public class FutureLoader {
    
    /**
     * Tienes muchas opciones que agregar esto solo es un ejemplo de almenos como seria
     * el auth de future o algo similar me base en la version 2.9 de futureclient
     * aunque ya me dio pereza de continuar
     * :()
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String hwidExample = Hwid.method_1935();
        FutureAuth futureAuth = new FutureAuth();
        futureAuth.saveAuthFuture("AnarchyGooD", "pepo123");
        futureAuth.getAuthExists();
        FutureUsuario futureUsuario = futureAuth.loadAuthUser();

        if (hwidExample.equals(Hwid.method_1935())) {
            if (futureUsuario.getUsername().equals("AnarchyGooD") && futureUsuario.getPassword().equals("pepo123")) {
                System.out.println("Accediste papu xde");
            } else {
                System.out.println("Error al verificar las credenciales Usuario: Nombre/Contraseña");
            }
        } else {
            System.out.println("No tiene acceso :c || tu hwid es:"+Hwid.method_1935());
        }
    }
}
