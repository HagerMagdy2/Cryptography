
package crypto;

public class Caeser {
    static String alpha = "abcdefghijklmnopqrstuvwxyz";
     public static String Caeser_enc(String text, int key) {
        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char x = text.charAt(i);
            if (Character.isUpperCase(x)) {
                int index = alpha.indexOf(Character.toLowerCase(x));
                int newIndex = (index + key) % 26;
                char y = Character.toUpperCase(alpha.charAt(newIndex));
                cipher.append(y);
            } else if (Character.isLowerCase(x)) {
                
                int index = alpha.indexOf(x);
                int newIndex = (index + key) % 26;
                char y = alpha.charAt(newIndex);
                cipher.append(y);
            } else {
                
                cipher.append(x);
            }
        }

        return cipher.toString();
    }

    public static String Caeser_dec(String cipher, int key) {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < cipher.length(); i++) {
            char x = cipher.charAt(i);
            if (Character.isUpperCase(x)) {
                int index = alpha.indexOf(Character.toLowerCase(x));
                int newIndex = (index - key + 26) % 26;
                char y = Character.toUpperCase(alpha.charAt(newIndex));
                text.append(y);
            } else if (Character.isLowerCase(x)) {
                
                int index = alpha.indexOf(x);
                int newIndex = (index - key + 26) % 26;
                char y = alpha.charAt(newIndex);
                text.append(y);
            } else {
                
                text.append(x);
            }
        }

        return text.toString();
    }
    
    public static StringBuilder Attack(String cipher){
       StringBuilder text = new StringBuilder();
       String decrypted_string;
       int k;
        for( k=0;k<=25;k++){
           decrypted_string =Caeser_dec(cipher,k);
           text.append(decrypted_string);
        }
        return text;
    } 
}
