/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package descipher;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 *
 * @author HAGER
 */
public class DESCipher {

    public static void main(String[] args) {
        
       int [] PC1={57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36,
             63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
             
        int [] PC2={14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47,
             55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
        
        int [] LShift={1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        
        int [] key={0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0,
             0, 1, 1, 1,
             1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1,1,1,1,0,1,1,1,1};
 

        int [] Key=GenerateKey(key);
        for(int i=0;i<Key.length;i++){
            System.out.print( Key[i] + " ");
        }
         System.out.println();
// int [] plainText={0,0,0,0, 0,0,0,1, 0,0,1,0, 0,0,1,1, 0,1,0,0, 0,1,0,1, 0,1,1,0, 0,1,1,1, 1,0,0,0, 1,0,0,1, 1,0,1,0 1,0,1,1 ,1,1,0,0, 1,1,0,1, 1,1,1,0, 1,1,1,1};
int [] plainText={0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0,
             0, 1, 1, 1,
             1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 0, 1,1,1,1,0,1,1,1,1};
 int [] PlainText=InitialP(plainText);
        for(int i=0;i<PlainText.length;i++){
            System.out.print( PlainText[i] + " ");
        }
        System.out.println();
        int [] L=new int [PlainText.length/2];
        for(int i=0;i<PlainText.length/2;i++){
            L[i]=PlainText[i];
            System.out.print( L[i] + " ");
        }
        System.out.println();
        int [] R=new int [PlainText.length/2];
        for(int i=((PlainText.length)/2)+1;i<PlainText.length;i++){
            for(int j=0;j<R.length;j++){
            R[j]=PlainText[i];
//            System.out.print( R[j] + " ");
            }
        }
        for(int i=0;i<R.length;i++){
            System.out.print( R[i] + " ");
        }
    }
    int [][][] SBoxes = { // S-box 1
            {
                    {14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                    {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                    {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                    {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
            },
            // S-box 2
            {
                    {15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                    {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                    {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                    {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}
            },
            // S-box 3
            {
                    {10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                    {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                    {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                    {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
            },
            // S-box 4
            {
                    {7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                    {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                    {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                    {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}
            },
            // S-box 5
            {
                    {2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                    {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                    {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                    {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
            },
            // S-box 6
            {
                    {12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                    {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                    {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                    {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
            },
            // S-box 7
            {
                    {4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                    {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                    {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                    {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
            },
            // S-box 8
            {
                    {13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                    {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                    {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                    {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}
            }
    };
    
    public static String hexToBinary(String hex) {
        hex = hex.toUpperCase(); 

  StringBuilder binary = new StringBuilder();
  for (char c : hex.toCharArray()) {
    switch (c) {
       case '0':
         binary.append("0000");
              break;
       case '1':
          binary.append("0001");
                            break;
                        case '2':
                            binary.append("0010");
                            break;
                        case '3':
                            binary.append("0011");
                            break;
                        case '4':
                            binary.append("0100");
                            break;
                        case '5':
                            binary.append("0101");
                            break;
                        case '6':
                            binary.append("0110");
                            break;
                        case '7':
                            binary.append("0111");
                            break;
                        case '8':
                            binary.append("1000");
                            break;
                        case '9':
                            binary.append("1001");
                            break;
                        case 'a':
                        case 'A':
                            binary.append("1010");
                            break;
                        case 'b':
                        case 'B':
                            binary.append("1011");
                            break;
                        case 'c':
                        case 'C':
                            binary.append("1100");
                            break;
                        case 'd':
                        case 'D':
                            binary.append("1101");
                            break;
                        case 'e':
                        case 'E':
                            binary.append("1110");
                            break;
                        case 'f':
                        case 'F':
                            binary.append("1111");
                            break;
                        default:
                            return "Invalid";
    }
  }
  return binary.toString();
}
    public static String binaryToHexManual(String binary) {
  StringBuilder hex = new StringBuilder();
  int length = binary.length();
  int size = length % 4;
  if (size > 0) {
    for (int i = 0; i < size; i++) {
      binary = "0" + binary;
    }
  }
  for (int i = 0; i < binary.length(); i += 4) {
    String bit = binary.substring(i, i + 4);
    int decimal = Integer.parseInt(bit, 2);
    hex.append(Integer.toHexString(decimal));
  }
  return hex.toString();
}
    public static String[] divideIntoLeftRight(String str) {
  int length = str.length();
  int midPoint = length / 2;
  String firstHalf = str.substring(0, midPoint);
  String secondHalf = str.substring(midPoint);
  return new String[]{firstHalf, secondHalf};
}


    
     public static int [] GenerateKey(int [] key){
         
         int [] PC1={57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36,
             63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
         int []Key = new int [PC1.length]; 
         for(int i=0;i<Key.length;i++){
          Key[i]=key[PC1[i]-1];
         }
            
        return Key;
            
        }
     public static String leftshift(String S, int num){
         int [] LShift={1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};
        String S1 = S.substring(num);
        String S2 = S.substring(0,num);
        return S1 + S2;
        
    }
     public static int [] InitialP(int [] plainText){
         
         int [] IP={58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 48, 40,
            32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 37, 29, 21, 13, 5,
            63, 55, 47, 39, 31, 23, 15, 7};
         int []PlainText = new int [IP.length]; 
         for(int i=0;i<PlainText.length;i++){
          PlainText[i]=plainText[IP[i]-1];
         }
            
        return PlainText;
            
        }
     
      public static int [] Encryption(int [] key,int [] PlainText){
            
        return null;
            
        }
       public static int [] Decryption(int [] key,int [] CipherText){
            
        return null;
            
        }
}

