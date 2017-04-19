package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static HashMap<String, Integer> createMap() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("fst",450);
        map.put("snd",390);
        map.put("trd",400);
        map.put("fourth",660);
        map.put("fth",700);
        map.put("sixth",890);
        map.put("sth",800);
        map.put("eth",610);
        map.put("nth",600);
        map.put("tth",500);
        return map;
    }

    public static void removeItemFromMap(HashMap<String, Integer> map) {
        HashMap<String,Integer> copy = new HashMap<String, Integer>(map);
        for (Map.Entry<String, Integer> pair : copy.entrySet()) {
            if (pair.getValue() < 500) map.remove(pair.getKey());
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> hm = createMap();
        removeItemFromMap(hm);
    }
}