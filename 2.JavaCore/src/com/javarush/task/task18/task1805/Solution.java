package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream fin = new  FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        List<Integer> list = new ArrayList<Integer>();
        while (fin.available() > 0) {
            int n = fin.read();
            if (!list.contains(n)) list.add(n);
        }
        fin.close();
        Collections.sort(list);
        for (Integer in : list) {
            System.out.print(in + " ");
        }
    }
}
