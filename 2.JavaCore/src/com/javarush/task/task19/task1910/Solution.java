package com.javarush.task.task19.task1910;

/* 
Пунктуация
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String in = r.readLine();
        String out = r.readLine();
        r.close();
        FileReader fr = new FileReader(in);
        FileWriter fw = new FileWriter(out);
        BufferedReader reader = new BufferedReader(fr);
        BufferedWriter writer = new BufferedWriter(fw);
        reader.close();
        writer.close();
        FileReader fileReader = new FileReader(in);
        String line = "";
        while (fileReader.ready()) {
            int c = fileReader.read();
            if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
                line += (char) c;
            }
        }
        FileWriter fileWriter = new FileWriter(out);
        fileWriter.write(line);
        fileReader.close();
        fileWriter.close();
    }
}
