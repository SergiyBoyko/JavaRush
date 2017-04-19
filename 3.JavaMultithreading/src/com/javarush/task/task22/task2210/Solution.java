package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
//        String[] s = getTokens("level32.lesson13.task03", ".3");
//        for (int i = 0; i < s.length; i++) {
//            System.out.println(s[i]);
//        }
    }
    public static String [] getTokens(String query, String delimiter) {
        List<String> list = new ArrayList<String>();
        StringTokenizer tokenizer = new StringTokenizer(query, delimiter);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            list.add(token);
        }
        String[] res = new String[list.size()];
        res = list.toArray(res);
        return res;
    }
}
