package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try (FileInputStream fis = new FileInputStream(file1)) {
            while (fis.available() > 0) baos.write(fis.read());
        }
        byte[] bytes = baos.toByteArray();
        try (FileOutputStream fos = new FileOutputStream(file2)) {
            for (int i = bytes.length - 1; i >= 0; i--) {
                fos.write(bytes[i]);
            }
        }
//        byte[] encoded = Files.readAllBytes(Paths.get(file1));
//        byte[] res = new byte[encoded.length];

    }
}
