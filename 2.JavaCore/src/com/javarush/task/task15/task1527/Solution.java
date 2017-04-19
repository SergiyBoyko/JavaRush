package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Парсер реквестов
*/
/*
http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String s = r.readLine();
        String v="";
        List<String> list = new ArrayList<String>();
        Map<String,String> map = new HashMap<String, String>();
        int n=0;
        if (s.contains("?")) {
            n = s.indexOf('?');
            char[] c = s.toCharArray();
            s="";
            for (int i = n+1; i < c.length; i++) {
                if (c[i] == '=') {
                    list.add(s);
                    i++;
                    for (int j = i; j < c.length; j++) {
                        if (c[j] == '&' && j+1< c.length) {
                            i=j+1;
                            j = c.length;
                            map.put(s,v);
                            s="";
                            v="";
                            break;
                        }
                        v+=c[j];
                        if (j == c.length-1) {
                            i = c.length-1;
                            map.put(s,v);
                            s="";
                            v="";
                        }
                    }
                }
                if (c[i] == '&') {
                    list.add(s);
                    map.put(s,v);
                    s="";
                    v="";
                } else s+=c[i];
            }
        }
        for (String l : list) {
            System.out.print(l+" ");
        }

        Pattern doublePattern = Pattern.compile("[0-9.]");
        Pattern stringPattern = Pattern.compile("[A-Za-z]");

        System.out.println();
        for(String a: list) {

            if (a.equals("obj")) {
                Matcher matcher = stringPattern.matcher(map.get(a));
                Matcher matcher1 = doublePattern.matcher(map.get(a));
                if (matcher.find()) {
                    alert(map.get(a));
                } else if (matcher1.find()) {
                    alert(Double.parseDouble(map.get(a)));
                }
            }
        }

        /*for (String l : list) {
            if(l.equals("obj")) {
                if (map.get(l).contains(".")) alert(Double.parseDouble(map.get(l)));
                else alert(map.get(l));
            }
        }
        */
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }
    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
