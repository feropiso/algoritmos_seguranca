package diffiehellman;

import java.math.BigInteger;

public class DiffieHellman {
    
	private static final BigInteger P = new BigInteger("23"); // Primo compartilhado
    private static final BigInteger G = new BigInteger("5");  // Gerador

    public static void main(String[] args) {
        
    	// Usuario1 e Usuario2 escolhem números secretos
        BigInteger numeroSecreto1 = new BigInteger("6");  // Número secreto de Usuario1
        BigInteger numeroSecreto2 = new BigInteger("15");   // Número secreto de Usuario2

        // Usuario1 e Usuario2 calculam suas chaves públicas
        BigInteger chave1 = calculaChave(G, numeroSecreto1, P);
        BigInteger chave2 = calculaChave(G, numeroSecreto2, P);

        // Troca das chaves públicas (normalmente, através de um canal seguro)
        // Suponhamos que Usuario1 recebe a chave pública de Usuario2 e vice-versa

        // Usuario1 calcula a chave compartilhada usando a chave pública de Usuario2
        BigInteger chaveCompartilhada1 = calculaChaveCompartilhada(chave2, numeroSecreto1, P);

        // Usuario2 calcula a chave compartilhada usando a chave pública de Usuario1
        BigInteger chaveCompartilhada2 = calculaChaveCompartilhada(chave1, numeroSecreto2, P);

        // Verifica se as chaves compartilhadas de Usuario1 e Usuario2 são iguais
        if (chaveCompartilhada1.equals(chaveCompartilhada2)) {
            System.out.println("Chave compartilhada calculada com sucesso: " + chaveCompartilhada1);
        } else {
            System.out.println("Erro na troca de chaves.");
        }
    }

    // Metodo para calcular a chave pública a partir do número secreto
    private static BigInteger calculaChave(BigInteger generator, BigInteger secret, BigInteger prime) {
        return generator.modPow(secret, prime);
    }

    // Metodo para calcular a chave compartilhada a partir da chave pública recebida
    private static BigInteger calculaChaveCompartilhada(BigInteger publicKey, BigInteger secret, BigInteger prime) {
        return publicKey.modPow(secret, prime);
    }
}

