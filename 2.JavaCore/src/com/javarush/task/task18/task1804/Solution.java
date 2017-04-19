package com.javarush.task.task18.task1804;

import java.io.BufferedReader;
import java.io.FileInputStream;/*
Самые редкие байты
*/
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        FileInputStream in = new FileInputStream(new BufferedReader(new InputStreamReader(System.in)).readLine());
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        List<Integer> list = new ArrayList<Integer>();
        while (in.available() > 0) {
            int n = in.read();
            if (map.containsKey(n)) map.put(n, map.get(n) + 1);
            else {
                map.put(n, 1);
                list.add(n);
            }
        }
        in.close();
        boolean b = true;
        int mapMIN = 0;
        for (Map.Entry<Integer, Integer> pair : map.entrySet()) {
            if (b) {
                b = false;
                mapMIN = pair.getValue();
            }
            else if (mapMIN > pair.getValue()) mapMIN = pair.getValue();
        }
        for (Integer integer : list) {
            if (map.get(integer) == mapMIN) System.out.print(integer + " ");
        }
    }
}
