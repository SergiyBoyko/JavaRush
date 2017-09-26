package com.javarush.task.task30.task3009;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

/* 
Палиндром?
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(getRadix("112"));        //expected output: [3, 27, 13, 15]
        System.out.println(getRadix("123"));        //expected output: [6]
        System.out.println(getRadix("5321"));       //expected output: []
        System.out.println(getRadix("1A"));         //expected output: []
    }

    private static Set<Integer> getRadix(String number) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 2; i <= 36; i++) {
            String original, reverse = "";
            BigInteger b;
            try {
                b = new BigInteger(number, 10);

            } catch (NumberFormatException e) {
                continue;
            }
            original = b.toString(i);
            int length = original.length();

            for (int j = length - 1; j >= 0; j--)
                reverse = reverse + original.charAt(j);

            if (original.equals(reverse)) {
                set.add(i);
            }
        }

        return set;
    }
}