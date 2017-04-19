package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] files = new String[3];
        for (int i = 0; i < files.length; i++)
            files[i] = r.readLine();
        FileOutputStream f1 = new FileOutputStream(files[0]); //C:\z\1.txt
        FileInputStream f2 = new FileInputStream(files[1]);
        FileInputStream f3 = new FileInputStream(files[2]);
        while (f2.available() > 0) {
            byte[] buffer = new byte[f2.available()];
            int count = f2.read(buffer);
            f1.write(buffer, 0, count);
        }
        while (f3.available() > 0) {
            byte[] buffer = new byte[f3.available()];
            int count = f3.read(buffer);
            f1.write(buffer, 0, count);
        }
        f1.close();
        f2.close();
        f3.close();
    }
}
