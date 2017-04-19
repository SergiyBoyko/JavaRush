package com.javarush.task.task18.task1801;

/*
Максимальный байт
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        FileInputStream fileInputStream = new FileInputStream(reader.readLine());
        int max = 0;
        while (fileInputStream.available() > 0){
            int p = fileInputStream.read();
            if (p > max) max= p;
        }
        System.out.println(max);
        fileInputStream.close();
    }
}
