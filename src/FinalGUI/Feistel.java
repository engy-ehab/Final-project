
package FinalGUI;

/**
 *
 * @author Options
 */
public class Feistel {
    
   
    public static String FeistelCipher(String text, String key, String function) {
        int val1, val2, val3, OutFunction = 0, OutXOR;
        String temp = "", Right = "", Left = "";
        String leftText = text.substring(0, (text.length() / 2));
        String rightText = text.substring(text.length() / 2);
        String sw = function;

        for (int k = 0; k < 16; k++) {
            System.out.println("Round " + (k));
            byte[] bytes1 = rightText.getBytes();
            byte[] bytes3 = leftText.getBytes();
            byte[] bytes2 = key.getBytes();
            System.out.println(leftText + rightText);
            Left = rightText;
            Right = "";
            for (int i = 0; i < rightText.length(); i++) {
                val1 = bytes1[i];
                val2 = bytes2[i];
                val3 = bytes3[i];
                for (int j = 0; j < 1; j++) {
                    if ((val1 & 1) == 0) {
                        val1 = 0;
                    } else {
                        val1 = 1;
                    }
                    if ((val2 & 1) == 0) {
                        val2 = 0;
                    } else {
                        val2 = 1;
                    }
                    if ((val3 & 1) == 0) {
                        val3 = 0;
                    } else {
                        val3 = 1;
                    }
                    if (sw.equals("AND")) {
                        OutFunction = val1 & val2;
                    } else if (sw.equals("OR")) {

                        OutFunction = val1 | val2;
                    } else if (sw.equals("XOR")) {
                        OutFunction = val1 ^ val2;
                    }
                    OutXOR = OutFunction ^ val3;
                    Right += OutXOR;
                }
            }
            rightText = Right;
            leftText = Left;
        }
        System.out.println("Round : " + 16 + "\n" + Left + Right);
        temp = Right;
        Right = Left;
        Left = temp;
        System.out.println("The output : "+Left + Right);
        return (Left + Right);
    }
}
