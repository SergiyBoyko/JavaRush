package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] files = new String[2];
        for (int i = 0; i < files.length; i++) {
            files[i] = r.readLine();
        }
        FileInputStream f1 = new FileInputStream(files[0]);
        FileInputStream f2 = new FileInputStream(files[1]);
        byte[] buf1 = null, buf2 = null;
        int c1 = 0, c2 = 0;
        while (f2.available() > 0) {
            buf1 = new byte[f2.available()];
            c1 = f2.read(buf1);
        }
        while (f1.available() > 0) {
            buf2 = new byte[f1.available()];
            c2 = f1.read(buf2);
        }
        f1.close();
        f2.close();
        FileOutputStream out = new FileOutputStream(files[0]);
        out.flush();
        out.write(buf1, 0, c1);
        out.write(buf2, 0, c2);
        out.close();
    }
}
