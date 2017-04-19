package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        List<FileInputStream> list = new ArrayList<FileInputStream>();
        String s = "";
        try {
            while (true){
                s = r.readLine();
                list.add(new FileInputStream(s));
            }
        } catch (FileNotFoundException e) {
            System.out.println(s);
            for (FileInputStream f : list) {
                try {
                    f.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
