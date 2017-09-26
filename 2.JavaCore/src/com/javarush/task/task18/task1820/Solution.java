package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String line;
        try (BufferedReader r = new BufferedReader(new FileReader(file1))) {
            line = r.readLine();
        }
        String[] strs = line.split(" ");
        List<Integer> integers = new ArrayList<Integer>();
        try (BufferedWriter w = new BufferedWriter(new FileWriter(file2))) {
            for (int i = 0; i < strs.length; i++) {
                w.write(Math.round(Float.parseFloat(strs[i])) + " ");
            }
        }

        reader.close();
    }
}
