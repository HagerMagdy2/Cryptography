/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package playfair;

import java.awt.Point;
import java.util.Scanner;

/**
 *
 * @author HAGER
 */
public class PlayFair {

    private static char[][] charTable;
    private static Point[] positions;

    public static void main(String[] args) {

    }
    public static String prepareText(String s) {
        s = s.toUpperCase().replaceAll("[^A-Z]", "");
        return s.replace("J", "I") ;
    }
     public static String encode(String s) {
        StringBuilder sb = new StringBuilder(s);
 
        for (int i = 0; i < sb.length(); i += 2) {
 
            if (i == sb.length() - 1)
                sb.append(sb.length() % 2 == 1 ? 'X' : "");
 
            else if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        }
        return encryption(sb, 1);
    }
 
    public static void createTable(String key) {
        charTable = new char[5][5];
        positions = new Point[26];
 
        String s = prepareText(key + "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
 
        for (int i = 0, k = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (positions[c - 'A'] == null) {
                charTable[k / 5][k % 5] = c;
                positions[c - 'A'] = new Point(k % 5, k / 5);
                k++;
            }
        }
    }
 
 
    public static String decryption(String s) {
        return encryption(new StringBuilder(s), 4);
    }
    public static String encryption(StringBuilder text, int direction) {
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
 
            int row1 = positions[a - 'A'].y;
            int row2 = positions[b - 'A'].y;
            int col1 = positions[a - 'A'].x;
            int col2 = positions[b - 'A'].x;
 
            if (row1 == row2) {
                col1 = (col1 + direction) % 5;
                col2 = (col2 + direction) % 5;
 
            } else if (col1 == col2) {
                row1 = (row1 + direction) % 5;
                row2 = (row2 + direction) % 5;
 
            } else {
                int tmp = col1;
                col1 = col2;
                col2 = tmp;
            }
 
            text.setCharAt(i, charTable[row1][col1]);
            text.setCharAt(i + 1, charTable[row2][col2]);
        }
        return text.toString();
    }
}

