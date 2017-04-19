package com.javarush.task.task18.task1822;

/* 
Поиск данных внутри файла
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        String insideFile;
        String sumString = "";

        BufferedReader bufferedReader2 = new BufferedReader(new FileReader(fileName));
        while  ((insideFile=bufferedReader2.readLine()) != null) {
            sumString = sumString+insideFile+" ";
        }

        String[] strArray = sumString.split(" ");
        double parsing;
        int startI = 0;
        int endI;

        for (int i = 0; i < strArray.length; i++) {
            if (strArray[i].equals(args[0])) {
                startI = i;
                break;
            }
        }

        int trueStart = startI;
        while (true) {
            try
            {
                parsing = Double.parseDouble(strArray[startI+1]);
                endI = startI+2;
                break;
            } catch (Exception e) {startI++;}
        }

        for (int i = trueStart; i<=endI; i++)
            System.out.print(strArray[i]+" ");
        bufferedReader.close();
        bufferedReader2.close();
    }
}
