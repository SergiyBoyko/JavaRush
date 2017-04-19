package com.javarush.task.task19.task1919;

/* 
Считаем зарплаты
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        String file = args[0];
        FileReader in = new FileReader(file);
        List<String> list = new ArrayList<String>();
        Map<String, Double> map = new HashMap<String, Double>();
        String line = "";
        while (in.ready()){
            int c = in.read();
            //System.out.print((char) c);
            if (c == 10 || c==13) {
                if (c == 10) {
                    //System.out.println(line);
                    String[] pair = line.split(" ");
                    String data = pair[pair.length-3] + " " + pair[pair.length-2] + " " + pair[pair.length-1];
                    String name = line.replace(" "+data,"");
                    System.out.println(name + " " + data);
//                    if (map.containsKey(pair[0])) map.put(pair[0], map.get(pair[0]) + Double.parseDouble(pair[1]));
//                    else {
//                        map.put(pair[0], Double.parseDouble(pair[1]));
//                        list.add(pair[0]);
//                    }
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
            System.out.println(s + " " + map.get(s));
        }
    }
}
