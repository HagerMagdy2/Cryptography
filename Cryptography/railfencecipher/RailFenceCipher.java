/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package railfencecipher;

/**
 *
 * @author HAGER
 */
public class RailFenceCipher {
    public static int [] result;
    public static  String Result;
    public static  String first;
    public static  String second;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
       String PlainText="meet me after the toga party";
       String CipherText="MEMATRHTGPRYETEFETEOAAT";
       int [] result=Encryption(PlainText);
       Result=Decryption(CipherText);
        for(int i=0;i<Result.length();i++){
           System.out.print((char) ((Result.charAt(i))));
       }
        System.out.println();
        for(int i=0;i<result.length;i++){
           System.out.print((char) ((result[i])));
       }
    
//       for(int i=0;i<result1.length;i++){
//           System.out.print((char) ((result1[i])));
//       }
//              System.out.println();

//       int [] x=combineArrays(result,result1);
//    for(int i=0;i<x.length;i++){
//           System.out.print((char) ((x[i])));
//       }
    }
    
    public static int [] Encryption(String PlainText){
        
        PlainText=PlainText.toUpperCase().replaceAll("[^A-Z]", "");
        int []result;
         if (PlainText.length() % 2 == 0) {
        int[] first = new int[PlainText.length() / 2];
        int[] second = new int[PlainText.length() / 2];
        for (int i = 0; i < PlainText.length(); i += 2) {
            first[i / 2] = PlainText.charAt(i);
        }
        for (int i = 1; i < PlainText.length(); i += 2) {
            second[i / 2] = PlainText.charAt(i);
        }
             result = new int[first.length + second.length];
         System.arraycopy(first, 0, result, 0, first.length);
         System.arraycopy(second, 0, result, first.length, second.length);
        } else {
        int[] first = new int[(PlainText.length() / 2) + 1];
        int[] second = new int[PlainText.length() / 2];
        for (int i = 0; i < PlainText.length() - 1; i += 2) {
            first[i / 2] = PlainText.charAt(i);
        }
        first[first.length - 1] = PlainText.charAt(PlainText.length() - 1); 
        for (int i = 1; i < PlainText.length(); i += 2) {
            second[i / 2] = PlainText.charAt(i);
        }
             result = new int[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        }
   
        return result;
    }
    public static String Decryption(String CipherText){
        CipherText=CipherText.toUpperCase().replaceAll("[^A-Z]", "");
       String Result="";
         if (CipherText.length() % 2 == 0) {
        String firstE = "";
        String secondE = "";
        
        for (int i = 0; i < CipherText.length()/2; i ++) {
            firstE += CipherText.charAt(i);
        }
        for (int i = CipherText.length()/2; i < CipherText.length(); i ++) {
            secondE += CipherText.charAt(i);
        }
//        for(int i=0;i<CipherText.length()/2;i++){
//            System.out.print((char)(first.charAt(i)+ second.charAt(i))%26);
//        }
        for(int i=0;i<firstE.length();i++){
             Result+=firstE.charAt(i);
             Result+=secondE.charAt(i);
            //System.out.println(Result);
        }
       
        } else {
//        String first = "";
//        String second = "";
        for (int i = 0; i < (CipherText.length()/2)+1; i ++) {
            first += CipherText.charAt(i);
        }
        for (int i = (CipherText.length()/2)+1; i < CipherText.length(); i++) {
            second += CipherText.charAt(i);
        }
//        for(int i=0;i<CipherText.length();i++){
//            System.out.print((char)(first.charAt(i)));
//            System.out.print((char)(second.charAt(i)));
//        }
        for(int i=0;i<second.length();i++){
             Result+=first.charAt(i);
             Result+=second.charAt(i);
            //System.out.println(Result);
        }
        Result+=first.charAt(first.length()-1);     
        //System.out.println(Result);
    Result=Result.replaceAll("[^A-Z]", "");
         }
        
        return Result;
    }
    
}
    
//    public static int[] combineArrays(int[] subarray1, int[] subarray2) {
//  int totalLength = subarray1.length + subarray2.length;
//  int[] combinedArray = new int[totalLength];
//
//  // Iterate through the combined array's indices
//  for (int i = 0; i < totalLength; i++) {
//    // Add element from subarray1 if its index is within bounds
//    if (i < subarray1.length) {
//      combinedArray[i] = subarray1[i];
//    } else {
//      // Add element from subarray2 (adjusted for index difference)
//      combinedArray[i] = subarray2[i - subarray1.length];
//    }
//  }
//
//  return combinedArray;
//}



