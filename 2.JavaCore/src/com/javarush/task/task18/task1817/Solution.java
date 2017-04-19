package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Locale;

public class Solution {
    public static void main(String[] args) throws IOException {
        String FFname = "" + args[0];
        FileInputStream fin = new FileInputStream(FFname); //C:\z\1.txt
        int space = 0;
        int ch = 0;
        while (fin.available() > 0) {
            if ((char)fin.read() == ' ') space++;
            ch++;
        }
        System.out.println(String.format(Locale.ENGLISH,"%(.2f", (double) space/ch*100));
        fin.close();
    }
}
