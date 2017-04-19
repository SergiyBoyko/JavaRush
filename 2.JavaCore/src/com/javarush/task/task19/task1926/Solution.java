package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
        ArrayList<String> fileList = new ArrayList<String>();
        String input;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
        while ((input = fileReader.readLine()) != null)
            fileList.add(input);
        fileReader.close();

        ArrayList<String> resultWords = new ArrayList<String>();
        for (String current : fileList)
        {
            resultWords.add(new StringBuilder().append(current).reverse().toString());
        }

        for (String current : resultWords)
            System.out.println(current);
    }
}
