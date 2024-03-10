/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package onetimepadcipher;

import java.util.Random;

/**
 *
 * @author HAGER
 */
public class OneTimePadCipher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        int[] key={9 ,0, 1, 7, 23, 15, 21, 14, 11 ,11, 2, 8, 9};
//        key = key.replaceAll("[^0-25]", "");
//        
//        System.out.print(key.length());
    
     int s = 10;
    String randomString = RandomKey(s);
    System.out.println(randomString);
    
  
}

  public static String RandomKey(int length) {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    StringBuilder result = new StringBuilder(length);
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      int index = random.nextInt(characters.length());
      result.append(characters.charAt(index));
    }
    return result.toString();
  }


 
        
        public static String Encryption(String Key,String PlainText){
             PlainText = PlainText.toUpperCase().replaceAll("[^A-Z]", "");       
            String CText = "";

        for (int i = 0; i < PlainText.length(); i++) {
            int x = (PlainText.charAt(i) + Key.charAt(i)) % 26;
            x += 'A';

            CText += (char) (x);
        }
        return CText;
        }
         public static String Decryption(String Key,String CipherText){
             CipherText = CipherText.toUpperCase().replaceAll("[^A-Z]", "");
//              int[] Key={9,0,1,7,23,15,21,14,11,11,2,8,9};
            String PText = "";

        for (int i = 0; i < CipherText.length()
                && i < Key.length(); i++) {
            int x = (CipherText.charAt(i)
                    - Key.charAt(i) + 26) % 26;

            x += 65;
            PText += (char) (x);
        }
        return PText;
        }
    
}
