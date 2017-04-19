package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = new String[3];
        for (int i = 0; i < s.length; i++) {
            s[i] = reader.readLine();
        }
        reader.close();
        FileInputStream f1 = new FileInputStream(s[0]);
        FileOutputStream f2 = new FileOutputStream(s[1]);
        FileOutputStream f3 = new FileOutputStream(s[2]);
        while (f1.available() > 0) {
            byte[] buffer = new byte[f1.available()];
            int count = f1.read(buffer);
            if (count%2 == 1) {
                f2.write(buffer, 0, count/2+1);
                f3.write(buffer, count/2+1, count/2);
            }
            else {
                f2.write(buffer, 0, count/2);
                f3.write(buffer, count/2, count/2);
            }
        }
        f1.close();
        f2.close();
        f3.close();
    }
}
