package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим все файлы
*/
public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list = new ArrayList<String>();
        File rootFile = new File(root);
        Queue<File> queue = new PriorityQueue<>();
        Collections.addAll(queue, rootFile.listFiles());
        while (queue.size() > 0) {
            File file = queue.poll();
            if (file.isDirectory()) {
                Collections.addAll(queue, file.listFiles());
            }
            else list.add(file.getAbsolutePath());
        }
        return list;

    }

    public static void main(String[] args) throws IOException {
        List<String> list = getFileTree("C:\\z");
        for (String s : list) {
            System.out.println(s);
        }
    }
}
