package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int q = 1, max = 0;
        ArrayList<Integer> integers = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            integers.add(Integer.parseInt(reader.readLine()));
        }
//        Collections.sort(integers);
        for (int i = 1; i < integers.size(); i++) {
            if (!integers.get(i - 1).equals(integers.get(i))) {
                if (max < q) max = q;
                q = 1;
            }
            else q++;
        }
        if (max < q) max = q;
        System.out.println(max);
        reader.close();
        //напишите тут ваш код

    }
}