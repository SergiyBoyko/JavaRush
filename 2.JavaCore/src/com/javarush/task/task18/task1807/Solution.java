package com.javarush.task.task18.task1807;

/* 
Подсчет запятых
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        FileInputStream fin = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        int q = 0;
        while (fin.available() > 0) {
            if (fin.read() == 44) q++;
        }
        fin.close();
        System.out.println(q);
    }
}
