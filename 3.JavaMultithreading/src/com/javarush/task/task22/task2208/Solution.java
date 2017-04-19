package com.javarush.task.task22.task2208;

import java.util.HashMap;
import java.util.Map;

/* 
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("name","Ivanov");
        params.put("country","Ukraine");
        params.put("city","Kiev");
        params.put("age",null);
        getQuery(params);
    }
    public static String getQuery(Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                if (stringBuilder.toString().equals(""))
                    stringBuilder.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
                else
                    stringBuilder.append(" and ").append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
            }

        }
        return stringBuilder.toString();
    }
}
