package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String in = r.readLine();
        String out = r.readLine();
        r.close();
        FileReader fr = new FileReader(in);
        FileWriter fw = new FileWriter(out);
        BufferedReader reader = new BufferedReader(fr);
        BufferedWriter writer = new BufferedWriter(fw);
        reader.close();
        writer.close();
        FileReader br = new FileReader(in);
        String line = "";
        List<String> list = new ArrayList<String>();
        while (br.ready()) {
            line += (char)br.read();
        }
        br.close();
        line = line.replaceAll("\\.","!");
        FileWriter bw = new FileWriter(out);
        char[] chline = line.toCharArray();
        for (int i = 0; i < chline.length; i++) {
            bw.write((int)chline[i]);
        }
        bw.close();
    }
}
