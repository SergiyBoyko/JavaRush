package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file1 = args[0]; //"C:\\z\\1.1.txt"; //
        String file2 = args[1]; //"C:\\z\\1.txt"; //
        BufferedReader fileReader = new BufferedReader(new FileReader(file1));
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));

        List<String> list = new ArrayList<String>();
        String line;
        while ((line = fileReader.readLine()) != null) {
            String[] w = line.split(" ");
            for (int i = 0; i < w.length; i++) {
                if (w[i].length() > 6) list.add(w[i]);
            }
        }
        //System.out.println(list);
        String endl = "";
        for (int i = 0; i < list.size(); i++) {
            if (i == (list.size()-1)) endl += list.get(i);
            else endl += (list.get(i)+",");
        }
        fileWriter.write(endl);
        fileReader.close();
        fileWriter.close();
    }
}
// C:\z\1.1.txt
