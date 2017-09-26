package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        //MMXII 2012; MCMXCVII 1997; MCDLXXIV 1474;
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        List<Integer> parts = new ArrayList<>();
        for (Character c : s.toCharArray()) {
            switch (c) {
                case 'I':
                    parts.add(1);
                    break;
                case 'V':
                    parts.add(5);
                    break;
                case 'X':
                    parts.add(10);
                    break;
                case 'L':
                    parts.add(50);
                    break;
                case 'C':
                    parts.add(100);
                    break;
                case 'D':
                    parts.add(500);
                    break;
                case 'M':
                    parts.add(1000);
                    break;
                default:
                    return 0;
            }
        }
        int result = 0;
        for (int i = 0; i < parts.size(); i++) {
            if (i != parts.size() - 1) {
                if (parts.get(i) >= parts.get(i + 1)) {
                    result += parts.get(i);
                } else result -= parts.get(i);
            } else result += parts.get(i);
        }
        return result;
    }
}
