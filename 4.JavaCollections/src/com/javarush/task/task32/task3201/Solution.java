package com.javarush.task.task32.task3201;

import java.io.IOException;
import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) throws IOException {

        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
        if (raf.length() < number) {
            raf.seek(raf.length());
            raf.write(text.getBytes());
        } else {
//            String utf8 = new String(text.getBytes("ISO-8859-1"), "UTF-8");
            raf.seek(number);
            raf.write(text.getBytes());
        }
        raf.close();
    }
}
