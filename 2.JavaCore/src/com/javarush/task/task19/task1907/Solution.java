package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        reader.close();
//        BufferedReader reader1 = new BufferedReader(new FileReader(s));
//        reader1.close();
        FileReader fr = new FileReader(s);
        String line = "";
        int count = 0;
        while (fr.ready()) {
            line += (char) fr.read();
        }
        while (true) {
            String ds = line.replaceFirst("world","");
            if (ds.equals(line)) break;
            line = ds;
            count++;
        }
        fr.close();
        System.out.println(count);
    }
}
