package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        FileReader in = new FileReader(file);
        List<String> list = new ArrayList<String>();
        String line = "";
        while (in.ready()) {
            line += (char) in.read();
//            int c = in.read();
//            System.out.println(c+" : " + (char) c);
//            if (c != 10 && c != 13) line += (char) c;
//            if (c == 13) {
//                list.add(line);
//                line = "";
//            }
        }
        String[] w = line.split(" ");
        for (Map.Entry<Integer, String> pair : map.entrySet()) {
            for (int i = 0; i < w.length; i++) {
                if (w[i].equals(String.valueOf(pair.getKey()))) w[i] = pair.getValue();
            }
        }
        for (int i = 0; i < w.length; i++) {
            System.out.print(w[i]+" ");
        }
        in.close();
    }
}
//  C:\z\1.1.txt

