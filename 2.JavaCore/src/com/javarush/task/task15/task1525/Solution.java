package com.javarush.task.task15.task1525;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* 
Файл в статическом блоке
*/

public class Solution {

    public static List<String> lines = new ArrayList<String>();

    static {
        try {
            Scanner in = new Scanner(new File(Statics.FILE_NAME));
            while(in.hasNext()) {
                String s = in.nextLine();
                lines.add(s);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(lines);
    }
}
