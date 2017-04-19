package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream in = new FileInputStream(new File(reader.readLine()));
        FileOutputStream out = new FileOutputStream(reader.readLine());
        byte[] buffer = new byte[in.available()];

        for(int i = in.read(buffer); i > 0; i--)
            out.write(buffer[i-1]);
        in.close();
        out.close();
    }
}
