package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Solution {
    public static void main(String[] args) throws IOException {
        String action = args[0];
        String fileName = args[1];
        String fileOutputName = args[2];
        try (FileInputStream fis = new FileInputStream(fileName)) {
            try (FileOutputStream fos = new FileOutputStream(fileOutputName)) {
                while (fis.available() > 0) {
                    if (action.equals("-e")) fos.write(fis.read() + 10);
                    else if (action.equals("-d")) fos.write(fis.read() - 10);
                }
            }
        }
    }

}
