package com.javarush.task.task32.task3210;

/*
Используем RandomAccessFile
*/

import java.io.IOException;
import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) throws IOException {

        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        byte[] bytes = new byte[text.length()];
        raf.seek(number);
        int line = raf.read(bytes, 0, text.length());
        String s = new String(bytes);

        raf.seek(raf.length());
        if (s.equals(text)) {
            raf.write("true".getBytes());
        } else {
            raf.write("false".getBytes());
        }
        raf.close();

    }
}
