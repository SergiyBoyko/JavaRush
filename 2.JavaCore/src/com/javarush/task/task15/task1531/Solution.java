package com.javarush.task.task15.task1531;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/* 
Факториал
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int input = Integer.parseInt(reader.readLine());
        reader.close();

        System.out.println(factorial(input));
    }

    public static String factorial(int n) {
        String s = "";
        if (n<0) s+=0;
        else {
            BigInteger ret = BigInteger.ONE;
            for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
            s += ret;
        }
        return s;
    }
}
