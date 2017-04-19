package com.javarush.task.task19.task1906;

/* 
Четные байты
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] files = new String[2];
        for (int i = 0; i < files.length; i++) {
            files[i] = r.readLine();
        }
        r.close();
        FileReader reader = new FileReader(files[0]);
        FileWriter writer = new FileWriter(files[1]);
        int i = 0;
        while (reader.ready()) {
            int data = reader.read();
            if (i%2 == 1) writer.write(data);
            i++;
        }
        reader.close();
        writer.close();
    }
}
