package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.FileInputStream;
import java.io.IOException;

public class Solution {

    public static void main(String[] args) throws IOException {
        String ffname = "" + args[0];
        FileInputStream file = new FileInputStream(ffname);//args[0]
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int count = 0;
        while (file.available() > 0) {
            if (alphabet.contains(("" + (char)file.read()).toLowerCase())) count++;
        }
        System.out.println(count);
        file.close();
    }
}
