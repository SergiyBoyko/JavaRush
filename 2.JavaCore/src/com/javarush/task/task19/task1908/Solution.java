package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String in = r.readLine();
        String out = r.readLine();
        r.close();
        //FileReader fr = new FileReader(in);
        //FileWriter fw = new FileWriter(out);
        //BufferedReader reader = new BufferedReader(fr);
        //BufferedWriter writer = new BufferedWriter(fw);
        //reader.close();
        //writer.close();
        FileReader fileReader = new FileReader(in);
        FileWriter fileWriter = new FileWriter(out);
        String x = "";
        while (fileReader.ready()) {
            int c = fileReader.read();
            if (c == 10 || c == 13) {
                if (c == 10) x += " ";
            }
            else x += (char) c;
        }
        String[] all = x.split(" ");
        for (int i = 0; i < all.length; i++) {
            char[] c = all[i].toCharArray();
            boolean access = true;
            for (int j = 0; j < c.length; j++) {
                if(!"0123456789".contains("" + c[j])) access = false;
            }
            if (access) {
                fileWriter.write(Integer.parseInt(all[i]) + " ");
            }
        }
        fileReader.close();
        fileWriter.close();
    }
}
