package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        FileInputStream fileInputStream = new FileInputStream(s);
        reader.close();
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        while (fileInputStream.available() > 0) {
            long l = fileInputStream.read();
            if (map.get(l) != null) {
                int n = map.get(l) + 1;
                map.put(l,n);
            }
            else map.put(l, 1);
        }
        fileInputStream.close();
        int maxq = 1;
        for (Map.Entry<Long, Integer> pair : map.entrySet()) {
            if (maxq < pair.getValue()) maxq = pair.getValue();
        }
        for (Map.Entry<Long, Integer> pair : map.entrySet()) {
            if (maxq == pair.getValue()) {
                //for (int i = 0; i < maxq; i++) {
                    System.out.print(pair.getKey() + " ");
                //}
            }
        }
    }
}
