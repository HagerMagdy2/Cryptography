/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package vigenerecipher;

import java.awt.RenderingHints.Key;
import java.util.*;

/**
 *
 * @author HAGER
 */
public class VigenereCipher {
public static int[] PText;
public static int[] CText;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
//        scanner.nextLine();
        
         System.out.print("Enter the String Key ");
        String key = scanner.nextLine();

        System.out.print("Enter the PlainText  ");
        String PlainText = scanner.nextLine();
        
         System.out.print("Enter the CipherText  ");
         String CipherText = scanner.nextLine();
        
        Encryption(PrepareKey(key,PlainText),PlainText);
        Decryption(PrepareKey(key,CipherText),CipherText);
        
        
         for (int i = 0; i < PlainText.length(); i++) {
          
           System.out.print((char) ((CText[i]) % 26 + 65)); 
             
       }
         
         System.out.println("//////////////////////////////////");
         
         for (int i = 0; i < CipherText.length(); i++) {
            
           System.out.print((char) ((PText[i]) % 26 + 65)); 
             
       }
    }
    
    public static String PrepareKey(String Key,String PlainText){
       Key = Key.toUpperCase().replaceAll("[^A-Z]", "");
           int x = PlainText.length();

        for (int i = 0;; i++) {
            if (x == i) {
                i = 0;
            }
            if (Key.length() == PlainText.length()) {
                break;
            }
            Key += (Key.charAt(i));
        }
        return Key;
    }
        
        public static String Encryption(String Key ,String PlainText){
             PlainText = PlainText.toUpperCase().replaceAll("[^A-Z]", "");
            String CText = "";

        for (int i = 0; i < PlainText.length(); i++) {
            int x = (PlainText.charAt(i) + Key.charAt(i)) % 26;
            x += 'A';

            CText += (char) (x);
        }
        return CText;
        }
         public static String Decryption(String Key ,String CipherText){
             CipherText = CipherText.toUpperCase().replaceAll("[^A-Z]", "");
            String PText = "";

        for (int i = 0; i < CipherText.length()
                && i < Key.length(); i++) {
            // converting in range 0-25 
            int x = (CipherText.charAt(i)
                    - Key.charAt(i) + 26) % 26;

       
            x += 'A';
            PText += (char) (x);
        }
        return PText;
        }
    }
    

