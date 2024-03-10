/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hillcipher;
import java.util.*;

/**
 *
 * @author HAGER
 */
public class HillCipher {
    public static int[][] Ekey;
    public static int[][] PText;
    public static int[] result;
    public static int[][] inverseKey;
    public static int[][] newKey;
    
   
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
         System.out.print("Enter the dimension of the square matrix: ");
        int dim = scanner.nextInt();
        scanner.nextLine();
         System.out.print("Enter the String of the square matrix: ");
        String key = scanner.nextLine();
         System.out.print("Enter the PlainText of the square matrix: ");
        String PlainText = scanner.nextLine();
      Ekey =fillMatrix(dim,key);
      PText=MatrixPlainText(dim, PlainText);
      
     for(int row=0;row<PlainText.length()/dim;row++) {
     Encryption(row,PText,Ekey);
     }
     String cipherText="RRLMWBKAS";
//      Ekey=new int[dim][dim];
      for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(Ekey[i][j] + " ");
            }
            System.out.println();
        }
      
      
      System.out.println("/////////////");
      for (int i = 0; i < PlainText.length()/dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(PText[i][j] + " ");
            }
            System.out.println();
        }
      System.out.println("/////////////");
      for(int row=0;row<PlainText.length()/dim;row++) {
     Encryption(row,PText,Ekey);
     
       for (int i = 0; i < dim; i++) {
           System.out.print((result[i])%26 + " ");
            }
            System.out.println();
      }
      System.out.println("/////////////");
      for(int row=0;row<PlainText.length()/dim;row++) {
     Encryption(row,PText,Ekey);
     
       for (int i = 0; i < dim; i++) {
           System.out.print((char) ((result[i]) % 26 + 65)); 
      
       }  
      }
       System.out.println("");
       System.out.println(Determent(Ekey));
      
      KeyInverse(Determent(Ekey), Ekey);
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print(inverseKey[i][j] + " ");
            }
            System.out.println();
        }
      
       newKey= Attack (KeyInverse(Determent(MatrixPlainText(dim, PlainText)), MatrixPlainText(dim, PlainText)),MatrixPlainText(dim, cipherText));
         for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                System.out.print((newKey[i][j])%26 + " ");
            }
            System.out.println();
        }
         
         
      
      
    }
       public static int [][]  fillMatrix(int Dim,String Key) {
           Key = Key.toUpperCase().replaceAll("[^A-Z]", "");
        Ekey=new int[Dim][Dim];
        String s = Key ;
        for (int i = 0, k = 0; i < s.length(); i++) {
            char c = s.charAt(i);
                Ekey[k / Dim][k % Dim] = (c-'A')%26;
               
                k++;
            }
        return Ekey;
       }
       public static int [][]  MatrixPlainText(int Dim,String PlainText) {
           PlainText = PlainText.toUpperCase().replaceAll("[^A-Z]", "");
           while (PlainText.length() % Dim != 0){
            PlainText += "X";
        }
        PText=new int[PlainText.length()/Dim][Dim];
        String s = PlainText ;
        for (int i = 0, k = 0; i < s.length(); i++) {
            char c = s.charAt(i);
                PText[k / Dim][k % Dim] = (c-65)%26;
               
                k++;
            }
        return PText;
       }
       
       public static int[] Encryption (int row,int PlainText[][],int KeyMatrix[][]){
            result = new int[KeyMatrix.length];
          
            for (int i = 0; i < KeyMatrix.length; i++) {
            for (int k = 0; k < KeyMatrix.length; k++) {
            result[i] += PlainText[row][k] * KeyMatrix[k][i];
        }
           }  
          
        
            return result;
       }
       
        public static int[][] Attack(int[][] PlainText, int[][] KeyMatrix) {
           newKey = new int[PlainText.length][KeyMatrix[0].length];
          for (int i = 0; i < PlainText.length; i++) {
           for (int j = 0; j < KeyMatrix[0].length; j++) {
            for (int k = 0; k < PlainText[0].length; k++) {
                newKey[i][j] += PlainText[i][k] * KeyMatrix[k][j];
            }
        }
    }
    return newKey;
}
       
       
       public static int Determent(int KeyMatrix[][]){
           
          int Det=0;
           
        
               Det =( KeyMatrix[0][0] * ((KeyMatrix[1][1] * KeyMatrix[2][2]) - (KeyMatrix[1][2] * KeyMatrix[2][1]))
                    - KeyMatrix[0][1] * ((KeyMatrix[1][0] * KeyMatrix[2][2]) - (KeyMatrix[1][2] * KeyMatrix[2][0]))
                    + KeyMatrix[0][2] * ((KeyMatrix[1][0] * KeyMatrix[2][1]) - (KeyMatrix[1][1] * KeyMatrix[2][0])))%26;

           if(Det<0){
            Det+=26;
        }
        int newDet=0;
        for(int i=0;i<26;i++){
            if((  (Det)  *i  )%26==1){
                newDet =i;
            }
        }

        return newDet;
           
       }
       
       public static int [][] KeyInverse(int Det,int KeyMatrix[][]){
            inverseKey=new int [3][3];
        inverseKey[0][0] = (KeyMatrix[1][1] * KeyMatrix[2][2] - KeyMatrix[1][2] * KeyMatrix[2][1])  * Det%26;
        inverseKey[0][1] = (KeyMatrix[0][2] * KeyMatrix[2][1] - KeyMatrix[0][1] * KeyMatrix[2][2])  * Det%26;
        inverseKey[0][2] = (KeyMatrix[0][1] * KeyMatrix[1][2] - KeyMatrix[0][2] * KeyMatrix[1][1])  * Det%26;
        inverseKey[1][0] = (KeyMatrix[1][2] * KeyMatrix[2][0] - KeyMatrix[1][0] * KeyMatrix[2][2])  * Det%26;
        inverseKey[1][1] = (KeyMatrix[0][0] * KeyMatrix[2][2] - KeyMatrix[0][2] * KeyMatrix[2][0])  * Det%26;
        inverseKey[1][2] = (KeyMatrix[0][2] * KeyMatrix[1][0] - KeyMatrix[0][0] * KeyMatrix[1][2])  * Det%26;
        inverseKey[2][0] = (KeyMatrix[1][0] * KeyMatrix[2][1] - KeyMatrix[1][1] * KeyMatrix[2][0])  * Det%26;
        inverseKey[2][1] = (KeyMatrix[0][1] * KeyMatrix[2][0] - KeyMatrix[0][0] * KeyMatrix[2][1])  * Det%26;
        inverseKey[2][2] = (KeyMatrix[0][0] * KeyMatrix[1][1] - KeyMatrix[0][1] * KeyMatrix[1][0])  * Det%26;
        
        
          for(int i=0;i<3;i++){
           for(int j=0;j<3;j++){
        if (inverseKey[i][j]<0){
                    inverseKey[i][j]+=26;
               }
            }
        }

           return inverseKey;
       }
       
//       public static int [][] preparePlainText(int Dim,String PlainText){
//           int colume=PlainTent.Length()/Dim;
//           PText= new int [Dim][(PlainText.length()/Dim)];
//            String s = PlainText ;
//            for (int i = 0; i < PlainText.length(); i += Dim) {
//            char a = PlainText.charAt(i);
//            char b = PlainText.charAt(i + 1);
//            }
//            for (int i = 0, k = 0; i < s.length(); i++) {
//            char c = s.charAt(i);
//                PText[k / Dim] = (c-97)%26;
//               
//                k++;
//            
//       }
//       
//        return PText;
//    }
}
    
    

