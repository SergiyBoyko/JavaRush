package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file1 = args[0]; //"C:\\z\\1.txt";
        String file2 = args[1]; //"C:\\z\\1.1.txt";
        FileReader fileReader = new FileReader(file1);
        FileWriter fileWriter = new FileWriter(file2);
        List<String> list = new ArrayList<String>();
        String line = "";
        while (fileReader.ready()) {
            int c = fileReader.read();
            //System.out.println(c+" : " + (char) c);
            if (c != 10 && c != 13) line += (char) c;
            if (c == 13) line += " ";
        }
        String[] w = line.split(" ");
        for (int i = 0; i < w.length; i++) {
            if (w[i].matches(".*[0-9].*"))  list.add(w[i]);
        }
        for (String s : list) {
            fileWriter.write(s + " ");
        }
        System.out.println(line);
        System.out.println(list);
        fileReader.close();
        fileWriter.close();
    }
}
