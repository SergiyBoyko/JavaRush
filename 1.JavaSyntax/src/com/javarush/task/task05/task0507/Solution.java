package com.javarush.task.task05.task0507;

/* 
Среднее арифметическое
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        double sum = 0;
        while (true) {
            int x = Integer.parseInt(r.readLine());
            if (x == -1) break;
            sum+=x;
            i++;
        }
        if (i != 0) sum /= i;
        System.out.println(sum);
    }
}

