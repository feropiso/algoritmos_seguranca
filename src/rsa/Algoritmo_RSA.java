package rsa;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class Algoritmo_RSA {
	
	public static void main(String[] args) throws Exception {
        
		// Gere um par de chaves RSA
        KeyPairGenerator geradorParChaves = KeyPairGenerator.getInstance("RSA");
        geradorParChaves.initialize(2048); // Tamanho da chave em bits
        KeyPair parDeChaves = geradorParChaves.generateKeyPair();
        PublicKey chavePublica = parDeChaves.getPublic();
        PrivateKey chavePrivada = parDeChaves.getPrivate();

        // Texto a ser criptografado
        String texto = "Estou usando algoritmo RSA!";

        // Criptografar usando a chave pública
        byte[] criptografiaEmBytes = criptografar(texto, chavePublica);

        // Converter o texto criptografado para uma representação legível (Base64)
        String textoCriptografado = Base64.getEncoder().encodeToString(criptografiaEmBytes);
        System.out.println("Texto criptografado: " + textoCriptografado);

        // Descriptografar usando a chave privada
        String textoDecriptografado = decriptografar(criptografiaEmBytes, chavePrivada);
        System.out.println("Texto descriptografado: " + textoDecriptografado);
    }

    public static byte[] criptografar(String texto, PublicKey chavePublica) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.ENCRYPT_MODE, chavePublica);
        return c.doFinal(texto.getBytes());
    }

    public static String decriptografar(byte[] criptografiaEmBytes, PrivateKey chavePrivada) throws Exception {
        Cipher c = Cipher.getInstance("RSA");
        c.init(Cipher.DECRYPT_MODE, chavePrivada);
        byte[] decriptografiaEmBytes = c.doFinal(criptografiaEmBytes);
        return new String(decriptografiaEmBytes);
    }

}
