
package FinalGUI;

import java.math.BigInteger;
import java.util.Random;

public  class RSA {
    // Bit length of each prime number.    
    int primeSize;
    // Two distinct large prime numbers p , q & Modulus N.
    BigInteger p, q, N;

    // phi = ( p – 1 ) * ( q – 1 )
    BigInteger phi;
    BigInteger E, D;
    String nt, dt, et;

    // String inputMessage, encryptedData, decryptedMessage;
    String publicKey, privateKey, randomNumber;
    // Generate two distinct large prime numbers p and q.

    public void generatePrimeNumbers() {
        p = new BigInteger(primeSize, new Random());
        while (!isprime(p)) {
            p = new BigInteger(primeSize, new Random());
        }
        do {
            q = new BigInteger(primeSize, new Random());
            while (!isprime(q)) {
                q = new BigInteger(primeSize, new Random());
            }
        } while (q.compareTo(p) == 0);
        System.out.println("q = " + q);
        System.out.println("p =" + p);
    }

    // Generate Public and Private Keys. 
    public void generatePublicPrivateKeys() {
// N = p * q
        N = p.multiply(q);

// phi = ( p – 1 ) * ( q – 1 )
        phi = p.subtract(BigInteger.valueOf(1)).multiply(q.subtract(BigInteger.valueOf(1)));

// Choose E, coprime to and less than phi
        do {
            E = new BigInteger(2 * primeSize, new Random());
        } while ((E.compareTo(phi) != -1) || (E.gcd(phi).compareTo(BigInteger.valueOf(1)) != 0));

        
// Compute D, the inverse of E mod phi
        D = E.modInverse(phi);

    }

    // Get prime number p.    
    public BigInteger getp() {
        return (p);
    }

    // Get prime number q.
    public BigInteger getq() {
        return (q);
    }

    // Get phi.
    public BigInteger getphi() {
        return (phi);
    }

    //Get modulus N. 
    public BigInteger getN() {
        return (N);
    }

    // Get Public exponent E.
    public BigInteger getE() {
        return (E);
    }
    // Get Private exponent D.

    public BigInteger getD() {
        return (D);
    }

    public String RSA(int primeSize,String text,String operation_type) {
String out = null;
        this.primeSize = primeSize;
// Generate two distinct large prime numbers p and q.
        generatePrimeNumbers();
// Generate Public and Private Keys.
        generatePublicPrivateKeys();
       if(operation_type.equalsIgnoreCase("e")){
            out =RSAencrypt(text);
       }
       else  if(operation_type.equalsIgnoreCase("d")){
            out =RSAdecrypt(text);
       }
       return  out;
    }

    public String RSAencrypt(String plaintext) {

       // E = getE();
        //N = getN();
        System.out.println("************Encryption*************");
        System.out.println("E = " + E);
        System.out.println("D = " + D);
        System.out.println("N = " + N);
        BigInteger plaintext1 = new BigInteger(plaintext);
        String recoveredciphertext = decrypt(plaintext1, E, N);

        System.out.println(recoveredciphertext);
        return recoveredciphertext;
    }

    public String encrypt(BigInteger decrypted, BigInteger E, BigInteger N) {

        BigInteger encrypted;
        encrypted = decrypted.modPow(E, N);
        return encrypted.toString();
    }

    public String RSAdecrypt(String ciphertext) {
       // D = getD();
       // N = getN();
        System.out.println("*************Decryption*************");
        System.out.println("E = " + E);
        System.out.println("D = " + D);
        System.out.println("N = " + N);
        BigInteger ciphertext1 = new BigInteger(ciphertext);
        String recoveredPlaintext = decrypt(ciphertext1, D, N);

        System.out.println(recoveredPlaintext);
        return recoveredPlaintext;

    }

    public String decrypt(BigInteger encrypted, BigInteger D, BigInteger N) {

        BigInteger decrypted;
        decrypted = encrypted.modPow(D, N);

        return decrypted.toString();
    }

    private static boolean isprime(BigInteger x) {
        BigInteger b = new BigInteger(String.valueOf(x));
        return b.isProbablePrime(1);
    }
}
    

