package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        FileInputStream fin = new FileInputStream(file);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        while (fin.available() > 0) {
            int ch = fin.read();
            if (map.containsKey(ch)) map.put(ch, map.get(ch) + 1);
            else {
                map.put(ch, 1);
                list.add(ch);
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size() ; i++) {
            int n = list.get(i);
            char c = (char) (n);
            System.out.println(c + " " + map.get(list.get(i)));
        }
        fin.close();
    }
}
