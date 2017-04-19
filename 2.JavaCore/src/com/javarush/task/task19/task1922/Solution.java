package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine(); // C:\z\2.txt
        reader. close();
        BufferedReader filereader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = filereader.readLine()) != null) {
            String[] w = line.split(" ");
            int count = 0;
            for (int i = 0; i < w.length; i++) {
                if (words.contains(w[i])) count++;
            }
            if (count == 2) System.out.println(line);
        }
        filereader.close();
    }
}
