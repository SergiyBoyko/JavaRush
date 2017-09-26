package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

        String symbols = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyXz0123456789";
        boolean numAvalable;
        boolean upperAvalable;
        boolean downAvalable;
        String res;

        do {
            numAvalable = false;
            upperAvalable = false;
            downAvalable = false;
            res = "";
            for (int i = 0; i < 8; i++) {
                char c = symbols.charAt((int) ((Math.random() * symbols.length())));
                res += c;
                if (c >= 97 && c <= 122) downAvalable = true;
                else if (c >= 65 && c <= 90) upperAvalable = true;
                else if (c >= 48 && c <= 57) numAvalable = true;
            }
        } while (!(numAvalable && upperAvalable && downAvalable));

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write(res.getBytes());
        } catch (IOException e) {
            return baos;
        }

        return baos;
    }
}