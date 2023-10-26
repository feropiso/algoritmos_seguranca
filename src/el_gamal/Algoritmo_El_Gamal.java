package el_gamal;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Algoritmo_El_Gamal {
	
	private BigInteger p;  // Número primo grande
    private BigInteger g;  // Gerador do grupo
    private BigInteger x;  // Chave privada
    private BigInteger y;  // Chave pública
    private SecureRandom random;

    public Algoritmo_El_Gamal() {
        random = new SecureRandom();
    }

    // Gere chaves pública e privada
    public void gerarParDeChaves(int numBits) {
        p = new BigInteger(numBits, 64, random);
        g = new BigInteger("2");  // Pode escolher outro gerador
        x = new BigInteger(numBits, random);
        y = g.modPow(x, p);
    }

    // Criptografar um valor usando a chave pública
    public BigInteger criptografar(BigInteger texto, BigInteger chavePublica) {
        BigInteger k = new BigInteger(128, random);  // Número aleatório
        BigInteger c1 = g.modPow(k, p);
        BigInteger c2 = texto.multiply(chavePublica.modPow(k, p)).mod(p);
        return c1.add(c2);
    }

    // Descriptografar um valor usando a chave privada
    public BigInteger descriptografar(BigInteger[] textoCifrado) {
        BigInteger c1 = textoCifrado[0];
        BigInteger c2 = textoCifrado[1];
        BigInteger s = c1.modPow(x, p).modInverse(p);
        return c2.multiply(s).mod(p);
    }

    public static void main(String[] args) {
    	
    	Algoritmo_El_Gamal elGamal = new Algoritmo_El_Gamal();
        elGamal.gerarParDeChaves(128);

        BigInteger texto = new BigInteger("42");  // Valor a ser criptografado

        BigInteger[] textoCifrado = new BigInteger[2];
        textoCifrado[0] = elGamal.criptografar(texto, elGamal.y);
        textoCifrado[1] = elGamal.criptografar(texto, elGamal.y);

        System.out.println("Texto cifrado 1: " + textoCifrado[0]);
        System.out.println("Texto cifrado 2: " + textoCifrado[1]);

        BigInteger textoDescriptografado = elGamal.descriptografar(textoCifrado);
        System.out.println("Texto descriptografado: " + textoDescriptografado);
    }

}
