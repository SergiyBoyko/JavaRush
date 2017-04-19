package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine(); // C:\z\1.1.txt file
        reader.close();
        reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder full = new StringBuilder("");
        while ((line = reader.readLine()) != null) {
            full.append(line + " ");
        }
        reader.close();
        full.deleteCharAt(full.length()-1);

        line = full.toString();

        String[] s = line.split(" ");
//        System.out.println(full + " " + full.length());
        List<String> corrupted = new ArrayList<String>();
        for (int i = 0; i < s.length-1; i++) {
            for (int j = i+1; j < s.length; j++) {
                if (s[i].equals(new StringBuilder(s[j]).reverse().toString()) && !corrupted.contains(s[i]) && !corrupted.contains(s[j])) {
//                    System.out.println(s[i] + " : " + s[j]);
                    corrupted.add(s[i]);
                    corrupted.add(s[j]);
//                    corrupted.add(j); && !corrupted.contains(i) && !corrupted.contains(j)
                    Pair pair = new Pair();
                    pair.first = s[i];
                    pair.second = s[j];
                    result.add(pair);
//                    System.out.println(i + " ~ " + j);
                    break;
                }
            }
        }
//        for (Pair list: result) {
//            System.out.println(list.first + " : " + list.second);
//        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                    first == null && second != null ? second :
                    second == null && first != null ? first :
                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
