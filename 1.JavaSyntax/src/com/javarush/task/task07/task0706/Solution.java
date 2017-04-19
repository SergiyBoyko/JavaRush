package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.InputStreamReader;/*
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] n = new int[15];
        int p = 0;
        int sum = 0;
        for (int i = 0; i < n.length; i++) {
            n[i] = Integer.parseInt(reader.readLine());
            if (i%2==0) p+=n[i];
            sum+=n[i];
        }
        if ((sum-p)>p) System.out.println("В домах с нечетными номерами проживает больше жителей.");
        else System.out.println("В домах с четными номерами проживает больше жителей.");
    }
}
