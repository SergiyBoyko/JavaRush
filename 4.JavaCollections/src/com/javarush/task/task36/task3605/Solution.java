package com.javarush.task.task36.task3605;

import java.io.*;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<Character> characters = new TreeSet<Character>();
        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.toLowerCase();
                for (Character ch : line.toCharArray()) {
                    if (Character.isLetter(ch) && !characters.contains(ch))
                        characters.add(ch);
                }
            }
        }
        int iterator = 5;
        for (Character ch : characters) {
            if (iterator-- == 0) break;
            System.out.print(ch);
        }
    }
}
