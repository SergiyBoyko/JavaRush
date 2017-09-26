package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please input your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null) return 0;
        if (s.length() == 0) return 0;

        List<Character> characters = new ArrayList<>();
        List<Integer> potentialResults = new ArrayList<>();
        char[] symbol = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (characters.contains(symbol[i])) {
                potentialResults.add(characters.size());
                characters.clear();
            }
            characters.add(symbol[i]);
        }
        potentialResults.add(characters.size());
        return Collections.max(potentialResults);
    }
}
