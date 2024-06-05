package millerrabin;

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class MillerRabin {
    private static String PrimetryTest(BigInteger n) {
        String s1 = "May be prime";
        String s2 = "Composite";

        if (n.compareTo(BigInteger.ONE) <= 0) {
            return s2;
        }

        int k = 0;
        BigInteger q = n.subtract(BigInteger.ONE);
        while (q.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            k++;
            q = q.divide(BigInteger.TWO);
        }

        Random rand = new Random();
        for (int j = 0; j < k; j++) {
            BigInteger a = BigInteger.ONE.add(new BigInteger(n.bitLength(), rand).mod(n.subtract(BigInteger.TWO)));
            BigInteger x = a.modPow(BigInteger.TWO.pow(j).multiply(q), n);
            if (x.equals(n.subtract(BigInteger.ONE))) {
                
            }
            return s1;
        }
        return s2;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a positive integer n: ");
        BigInteger n = scanner.nextBigInteger();

        scanner.close();

        String result = PrimetryTest(n);
        System.out.println(result);
    }
}
