package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String file1 = br.readLine();
        String file2 = br.readLine();
        br.close();

        List<String> lines1 = new ArrayList<String>();
        List<String> lines2 = new ArrayList<String>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file1))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines1.add(line);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file2))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines2.add(line);
            }
        }
        for (int i = 0; i < lines1.size(); i++) {
            if (lines2.size() == 0) {
                lines.add(new LineItem(Type.REMOVED, lines1.get(i)));
                break;
            }
            System.out.print("compare " + lines1.get(i) + " and " + lines2.get(0));
            if (lines1.get(i).equals(lines2.get(0))) {
                System.out.println("SAME");
                lines.add(new LineItem(Type.SAME, lines1.get(i)));
                lines2.remove(0);
            }
            else if (lines2.size() > 1 && lines1.get(i).equals(lines2.get(1))) {
                System.out.println("ADDED");
                lines.add(new LineItem(Type.ADDED, lines2.get(0)));
                lines2.remove(0);
                i--;
            } else if (lines1.size() > 1 && lines1.get(i + 1).equals(lines2.get(0))) {
                System.out.println("REMOVED");
                lines.add(new LineItem(Type.REMOVED, lines1.get(i)));
            }
        }
        if (lines2.size() != 0) {
            lines.add(new LineItem(Type.ADDED, lines2.get(0)));
        }

    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
