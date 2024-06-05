/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rsa;

import java.math.BigInteger;
import java.util.Random;


public class RSA {
 
  // Static field
   // private static BigInteger n;
  private static Random  r = new Random();
  private static BigInteger one = new BigInteger("1");
 private static  BigInteger  p = BigInteger.probablePrime(256, r);
 private static final  BigInteger q = BigInteger.probablePrime(256, r);
   
   private static final BigInteger n = p.multiply(q); // n = modulua

    // (Totient funtzioa) kalkulatu
 private static final  BigInteger phi = p.subtract(one).multiply( q.subtract(one));

    // System.out.println(phi);

  private static BigInteger e = new BigInteger(phi.bitLength(), r);

    static {
      //  e = new BigInteger(phi.bitLength(), r);
        while (e.compareTo(one) <= 0 || !phi.gcd(e).equals(one) || e.compareTo(phi) >= 0) {
            e = new BigInteger(phi.bitLength() - 1, r);
        }
      
    }
    
    

   
   private static BigInteger d = e.modInverse(phi);

     
    public static void main(String[] args) {
        // TODO code application logic here
          String PlainText = "CRYPTOGRAPHY";

    System.out.println(Encryption(PlainText));
    System.out.println(Decryption( Encryption(PlainText)) );
    
    String alphabeticResult = convertToAlphabetic(Decryption( Encryption(PlainText)));
        System.out.println("Alphabetic result: " + alphabeticResult);
        
         String alphabeticResult1 = convertToAlphabetic(Encryption(PlainText));
        System.out.println("Alphabetic result1: " + alphabeticResult1);

    }
    
     private static String convertToAlphabetic(BigInteger value) {
        String digits = value.toString();
        StringBuilder result = new StringBuilder();

        // Map each two-digit chunk to an alphabet
        for (int i = 0; i < digits.length(); i += 2) {
            String chunk = digits.substring(i, Math.min(i + 2, digits.length()));
            int intValue = Integer.parseInt(chunk);
            char alphabet = (char) ( intValue); // Assuming A=00, B=01, etc.
            result.append(alphabet);
        }

        return result.toString();
    }
    
    public static BigInteger Encryption(String PlainText) {

    // k = c(m) = m^e mod n    
    String Message = "";
    for(int i=0; i<PlainText.length(); i++){
        Message +=  ((int)PlainText.charAt(i));
    }
    BigInteger message = new BigInteger(Message);
    return message.modPow(e, n);
  }

  public static BigInteger Decryption( BigInteger Ciphertext) {
    return Ciphertext.modPow(d, n);
  }
    
}
