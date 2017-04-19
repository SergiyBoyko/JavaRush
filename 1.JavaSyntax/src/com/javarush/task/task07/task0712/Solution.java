package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<String> list = new ArrayList<>();
        int min = 1000, max = 0;
        for (int i = 0; i < 10; i++) {
            String s = reader.readLine();
            if (s.length() > max) max = s.length();
            if (s.length() < min) min = s.length();
            list.add(s);
        }
        reader.close();
        for (String s : list) {
            if (s.length() == min || s.length() == max) {
                System.out.println(s);
                break;
            }
        }
    }
}
