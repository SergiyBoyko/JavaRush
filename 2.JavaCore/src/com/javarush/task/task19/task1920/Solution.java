package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        FileReader in = new FileReader(file);
        List<String> list = new ArrayList<String>();
        Map<String, Double> map = new HashMap<String, Double>();
        String line = "";
        double max = 0;
        while (in.ready()){
            int c = in.read();
            //System.out.print((char) c);
            if (c == 10 || c==13) {
                if (c == 10) {
                    //System.out.println(line);
                    String[] pair = line.split(" ");
                    if (map.containsKey(pair[0])) map.put(pair[0], map.get(pair[0]) + Double.parseDouble(pair[1]));
                    else {
                        map.put(pair[0], Double.parseDouble(pair[1]));
                        list.add(pair[0]);
                    }
                    if (max<map.get(pair[0])) max = map.get(pair[0]);
                    line = "";
                }
            }
            else line += (char) c;
        }
        String[] pair = line.split(" ");
        if (map.containsKey(pair[0])) map.put(pair[0], map.get(pair[0]) + Double.parseDouble(pair[1]));
        else {
            map.put(pair[0], Double.parseDouble(pair[1]));
            list.add(pair[0]);
        }
        in.close();
        Collections.sort(list);
        for (String s : list) {
            if (map.get(s) == max)
                System.out.println(s);
        }
    }
}
