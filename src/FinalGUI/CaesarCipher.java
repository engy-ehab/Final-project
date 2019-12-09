
package FinalGUI;

public class CaesarCipher {
 //encrypted functin in caesar cipher
    public static String encrypt_Caeser(String plain, int key) {
        char Alphabet;
        String encryptedText = "";
        for (int i = 0; i < plain.length(); i++) {
            Alphabet = plain.charAt(i);
            if (Character.isLetter(Alphabet)) {
                Alphabet = (char) (Alphabet + key);
                if (Character.isLowerCase(plain.charAt(i)) && Alphabet > 'z'
                        || Character.isUpperCase(plain.charAt(i)) && Alphabet > 'Z') {
                    Alphabet = (char) (plain.charAt(i) - (26 - key));
                }
            }
            encryptedText += Alphabet;
        }
        return encryptedText;
    }
 //decrypted functin in caesar cipher
    public static String decrypt_Caeser(String cipher, int key) {
        char Alphabet;
        String decryptedText = "";
        for (int i = 0; i < cipher.length(); i++) {
            Alphabet = cipher.charAt(i);
            if (Character.isLetter(Alphabet)) {
                Alphabet = (char) (Alphabet - key);
                if (Character.isLowerCase(cipher.charAt(i)) && Alphabet < 'a'
                        || Character.isUpperCase(cipher.charAt(i)) && Alphabet < 'A') {
                    Alphabet = (char) (cipher.charAt(i) + (26 - key));
                }
            }
            decryptedText += Alphabet;
        }
        return decryptedText;
    }

}
