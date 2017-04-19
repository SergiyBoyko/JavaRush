package com.javarush.task.task18.task1823;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //List<String> files = new ArrayList<String>();
        while (true) {
            String s = reader.readLine();
            if (s.equals("exit")) break;
            //files.add(s);
            ReadThread readThread = new ReadThread(s);
            readThread.start();
            readThread.join();
        }
        System.out.println(resultMap);
    }

    public static class ReadThread extends Thread {
        private String file;
        public ReadThread(String fileName) {
            file = fileName;
        }
        public void run() {
            try {
                FileInputStream fin = new FileInputStream(file);
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                while (fin.available() > 0) {
                    int bytik = fin.read();
                    if (map.containsKey(bytik)) {
                        int val = map.get(bytik) + 1;
                        map.put(bytik,val);
                    }
                    else map.put(bytik, 1);
                }
                int max = 0;
                for (Map.Entry<Integer,Integer> pair : map.entrySet()){
                    if (max<pair.getValue()) max = pair.getValue();
                }
                for (Map.Entry<Integer,Integer> pair : map.entrySet()){
                    if (max == pair.getValue()) resultMap.put(file, pair.getKey());
                }
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
