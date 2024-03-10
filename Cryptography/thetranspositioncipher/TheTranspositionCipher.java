/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package thetranspositioncipher;

import java.security.Key;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author HAGER
 */
public class TheTranspositionCipher {

    /**
     * @param args the command line arguments
     */
    public static char [][] charMatrix;
    public static char [] cipherText;
    public static int [] kkey;
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);

         System.out.print("Enter the String of the Key: ");
        String key = scanner.nextLine();
         System.out.print("Enter the PlainText : ");
        String PlainText = scanner.nextLine();
         //int [] key={7, 4, 2 ,1 ,3 ,5, 6};
         String[] key1 = key.split(" ");
        int Key[] = new int[key1.length];
        for(int i=0;i<key1.length;i++){
            Key[i] = Integer.parseInt(key1[i]);
        }
      charMatrix =CreateMatrix(key,PlainText);
     
      
     for (int i = 0; i < charMatrix.length; i++) {
                for (int j = 0; j < charMatrix[0].length; j++) {
                    System.out.print(charMatrix[i][j] + " ");
                }
                System.out.println();
            }
//     
//     String[] numberStrings = key.split(" ");
//         int[] KeyArray = new int[numberStrings.length];
//         for (int i = 0; i < numberStrings.length; i++) {
//         KeyArray[i] = Integer.parseInt(numberStrings[i]);
//         }
//      int [] kkey=new int[key.length()];
//      for(int i=0;i<kkey.length;i++){
//          kkey[i]=key.charAt(i);
//      }
      for(int i=0;i<Key.length;i++){
      System.out.print(Key[i] + " ");
      }
      System.out.println();
      //charMatrix =CreateMatrix(key,PlainText);
       String cipherText=encrypt(charMatrix, key);
      for(int i=0;i<cipherText.length();i++){
          System.out.print(cipherText.charAt(i));
      }
      System.out.println();
      
      String plainText=decryption(charMatrix);
      for(int i=0;i<plainText.length();i++){
          System.out.print(plainText.charAt(i));
      }


    }
    
   public static char[][] CreateMatrix(String key, String PlainText) {
    PlainText = PlainText.toUpperCase().replaceAll("[^A-Z]", "");
    
     String[] key1 = key.split(" ");
        int Key[] = new int[key1.length];
        for(int i=0;i<key1.length;i++){
            Key[i] = Integer.parseInt(key1[i]);
        }

    while (PlainText.length() % Key.length != 0) {
        PlainText += "X";
    }
    charMatrix = new char[PlainText.length() / Key.length][Key.length]; 

    for (int i = 0, k = 0; i < PlainText.length(); i++) { 
        char c = PlainText.charAt(i);
        charMatrix[k / Key.length][k % Key.length] = c;
        k++;
    }

    return charMatrix;
}
   
   public static int Index(int[] arr, int element) {
       int index=0;
       
     while(index<arr.length){
            if(arr[index]==element){
                return index;
            }
            else{
                index++;
            }
        }
        return index;
    }

   public static String encrypt(char[][] charMatrix, String key) {
    String[] kkey = key.split(" ");
        int Key[] = new int[kkey.length];
        for(int i=0;i<kkey.length;i++){
            Key[i] = Integer.parseInt(kkey[i]);
        }
    int rows = charMatrix.length;
    int cols = Key.length; 
    String cipherText = "";
    for (int i = 0 ; i < cols; i++) {
        for (int j = 0; j < rows; j++) {
            int index = Index(Key, i + 1); 
                cipherText+= charMatrix[j][index];
        }
    }

    return cipherText;
}
   
   public static String decryption(char[][] charMatrix){
       String plainText="";
       int rows = charMatrix.length;
    int cols = charMatrix[0].length; 
       for(int i =0;i<rows;i++){
           for(int j=0;j<cols;j++){
               plainText+=charMatrix[i][j];
           }
       }
        return plainText;
       
   }


}

