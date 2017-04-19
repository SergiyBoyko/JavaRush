package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            list.add(Integer.parseInt(reader.readLine()));
        }
        int[] n = new int[10];
        for (int i = 0; i < n.length; i++) {
            n[i] = 0;
        }
        for (Integer num : list) {
            for (int i = 0; i < list.size(); i++) {
                if (num.equals(list.get(i))) n[i]++;
            }
        }
        int max= n[0];
        for (int i = 0; i < n.length; i++) {
            if (n[i] > max) max = n[i];
        }
        System.out.println(max);
    }
}