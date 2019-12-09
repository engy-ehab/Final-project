
package FinalGUI;

import java.awt.Point;

public class PlayFairCipher {

    int length;
    String[][] table;
    public String encode_Playfair(String text, String key) {
        String keyword = editString(key);
        while (key.equals("")) {
            keyword = editString(key);
        }
        table = this.cipherTable(keyword);
        String input = editString(text);
        while (input.equals("")) {
            input = editString(text);
        }
        String output = cipher(input);
        return output;
    }

    public String decode_Playfair(String text, String key) {
        String keyword = editString(key);
        while (key.equals("")) {
            keyword = editString(key);
        }
        table = this.cipherTable(keyword);

        String input = editString(text);
        while (input.equals("")) {
            input = editString(text);
        }
        String decodedOutput = decode(input);
        return decodedOutput;
    }

    public static String editString(String text) {

        text = text.toUpperCase();        // convert text to upper case
        text = text.replaceAll("[^A-Z]", "");
        text = text.replace("J", "I");    //replace any j with i
        return text;
    }

    // creates the cipher table based on some input string (already edit)
    public String[][] cipherTable(String key) {
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        // fill string array with empty string
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playfairTable[i][j] = "";
            }
        }

        for (int k = 0; k < keyString.length(); k++) {
            boolean repeat = false;
            boolean used = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
                        repeat = true;
                    } else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }

    // cipher: takes input (all upper-case), encodes it, and returns output
    public String cipher(String text) {
        length = (int) text.length() / 2 + text.length() % 2;
        // insert x between double-letter digraphs & redefines "length"
        for (int i = 0; i < (length - 1); i++) {
            if (text.charAt(2 * i) == text.charAt(2 * i + 1)) {
                text = new StringBuffer(text).insert(2 * i + 1, 'X').toString();
                length = (int) text.length() / 2 + text.length() % 2;
            }
        }
        // adds an x to the last digraph, if necessary
        String[] digraph = new String[length];
        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && text.length() / 2 == (length - 1)) {
                text = text + "X";
            }
            digraph[j] = text.charAt(2 * j) + "" + text.charAt(2 * j + 1);
        }
        // encodes the digraphs and returns the output
        String out = "";
        String[] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for (int k = 0; k < length; k++) {
            out = out + encDigraphs[k];
        }
        return out;
    }

    // encodes the digraph input with the cipher's specifications
    public String[] encodeDigraph(String di[]) {
        String[] enc = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();

            // case 1: letters in digraph are of same row, shift columns to right
            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
                // case 2: letters in digraph are of same column, shift rows down
            } else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
                // case 3: letters in digraph form rectangle, swap first column # with second column #
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            //performs the table look-up and puts those values into the encoded array
            enc[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return enc;
    }

    // decodes the output given from the cipher and decode methods 
    public String decode(String out) {
        String decoded = "";
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();
            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded = decoded + table[r1][c1] + table[r2][c2];
        }
        return decoded;
    }
   
    // returns a point containing the row and column of the letter
    public Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c == table[i][j].charAt(0)) {
                    pt = new Point(i, j);
                }
            }
        }
        return pt;
    }

}
